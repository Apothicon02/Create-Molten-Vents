package com.Apothic0n.MoltenVents.core;

import com.Apothic0n.MoltenVents.MoltenVents;
import com.Apothic0n.MoltenVents.config.CommonConfig;
import com.simibubi.create.Create;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Supplier;

public class MoltenVentsRegistry
{
    public static final Supplier<Block> RequiredLiquid = () -> Registry.BLOCK.get(new ResourceLocation(CommonConfig.requiredLiquid.get()));

    public static final List<RegistryObject<OrestoneBlock>> AllOreStoneVariants = new ArrayList<>();

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MoltenVents.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MoltenVents.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MoltenVents.MODID);
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, MoltenVents.MODID);

    public static final RegistryObject<Feature<SimpleBlockConfiguration>> MOLTEN_VENT = FEATURES.register("molten_vent", () -> new MoltenVentFeature(SimpleBlockConfiguration.CODEC));
    public static final RegistryObject<Feature<SimpleBlockConfiguration>> AQUATIC_MOLTEN_VENT = FEATURES.register("aquatic_molten_vent", () -> new AquaticMoltenVentFeature(SimpleBlockConfiguration.CODEC));

    public static final RegistryObject<BlockEntityType<?>> ORESTONE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("orestone_block_entity", () -> BlockEntityType.Builder.of(OrestoneBlockEntity::new, getAllOreStones()).build(null));


    public static void register(IEventBus eventBus) {
        for (OreStone value : OreStone.values())
            value.registerOreStoneVariant();

        BLOCK_ENTITIES.register(eventBus);
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        FEATURES.register(eventBus);
    }


    private static Block[] getAllOreStones() { return AllOreStoneVariants.stream().map(RegistryObject::get).toArray(Block[]::new); }

    public static OrestoneBlock getDormantOrestoneFor(Block block) { return Objects.requireNonNull(OreStone.FromBlock(block)).dormant.get(); }

    public static OrestoneBlock getActiveOrestoneFor(Block block) { return Objects.requireNonNull(OreStone.FromBlock(block)).molten.get(); }


    public enum OreStone
    {
        Asurine("asurine"),
        Crimsite("crimsite"),
        Ochrum("ochrum"),
        Veridium("veridium");

        public final String name;

        public RegistryObject<OrestoneBlock> dormant;
        public RegistryObject<OrestoneBlock> molten;
        public RegistryObject<Item> dormantItem;
        public RegistryObject<Item> moltenItem;

        private Holder<ConfiguredFeature<SimpleBlockConfiguration,?>> ventConfigured;
        private Holder<ConfiguredFeature<SimpleBlockConfiguration,?>> aquaticVentConfigured;
        private Holder<PlacedFeature> ventPlaced;
        private Holder<PlacedFeature> aquaticVentPlaced;

        OreStone(String name) { this.name = name; }

        public void registerOreStoneVariant() {
            molten = BLOCKS.register("molten_" + name, () -> new OrestoneBlock(BlockBehaviour.Properties.copy(Blocks.TUFF).explosionResistance(1200).sound(SoundType.TUFF).lightLevel((brightness) -> 15)));
            dormant = BLOCKS.register("dormant_" + name, () -> new OrestoneBlock(BlockBehaviour.Properties.copy(Blocks.TUFF).explosionResistance(1200).sound(SoundType.TUFF).lightLevel((brightness) -> 5)));

            moltenItem = ITEMS.register("molten_" + name, () -> new BlockItem(molten.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
            dormantItem = ITEMS.register("dormant_" + name, () -> new BlockItem(dormant.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

            AllOreStoneVariants.add(molten);
            AllOreStoneVariants.add(dormant);
        }

        public void registerFeatures() {
            var blockConfig = new SimpleBlockConfiguration(BlockStateProvider.simple(GetCreateBlock()));
            ventConfigured = FeatureUtils.register("molten_vents:" + name + "_vent", MOLTEN_VENT.get(), blockConfig);
            aquaticVentConfigured = FeatureUtils.register("molten_vents:aquatic_" + name + "_vent", MOLTEN_VENT.get(), blockConfig);

            ventPlaced = PlacementUtils.register("molten_vents:" + name + "_vent", ventConfigured, RarityFilter.onAverageOnceEvery(CommonConfig.ventRarity.get()), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
            aquaticVentPlaced = PlacementUtils.register("molten_vents:aquatic_" + name + "_vent", aquaticVentConfigured, RarityFilter.onAverageOnceEvery(CommonConfig.ventRarity.get()), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());
        }

        public @NotNull Block GetCreateBlock() {
            return Registry.BLOCK.get(new ResourceLocation("create", name));
        }

        public static @NotNull OreStone FromBlock(Block block) {
            var path = Registry.BLOCK.getKey(block).getPath();
            for (OreStone value : OreStone.values())
            {
                if (value.dormant.getId().getPath().equals(path)) return value;
                if (value.molten.getId().getPath().equals(path)) return value;
                if (path.contains(value.name)) return value;
            }
            return null;
        }
    }
}
