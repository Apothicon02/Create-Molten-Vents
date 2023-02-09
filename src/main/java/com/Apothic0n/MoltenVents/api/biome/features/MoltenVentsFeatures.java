package com.Apothic0n.MoltenVents.api.biome.features;

import com.Apothic0n.MoltenVents.config.CommonConfig;
import com.Apothic0n.MoltenVents.core.objects.MoltenBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class MoltenVentsFeatures {
    //Blocks
    public static final Block Asurine = Registry.BLOCK.get(new ResourceLocation("create", "asurine"));
    public static final Block Veridium = Registry.BLOCK.get(new ResourceLocation("create", "veridium"));
    public static final Block Crimsite = Registry.BLOCK.get(new ResourceLocation("create", "crimsite"));
    public static final Block Ochrum = Registry.BLOCK.get(new ResourceLocation("create", "ochrum"));
    public static final Block RequiredLiquid = Registry.BLOCK.get(new ResourceLocation(CommonConfig.requiredLiquid.get()));

    //ConfiguredFeatures
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> ASURINE_VENT_CONFIGURED = FeatureUtils.register("molten_vents:asurine_vent", MoltenVentsFeatureRegistry.MOLTEN_VENT.get(),
            new SimpleBlockConfiguration(BlockStateProvider.simple(Asurine)));

    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> VERIDIUM_VENT_CONFIGURED = FeatureUtils.register("molten_vents:veridium_vent", MoltenVentsFeatureRegistry.MOLTEN_VENT.get(),
            new SimpleBlockConfiguration(BlockStateProvider.simple(Veridium)));

    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> CRIMSITE_VENT_CONFIGURED = FeatureUtils.register("molten_vents:crimsite_vent", MoltenVentsFeatureRegistry.MOLTEN_VENT.get(),
            new SimpleBlockConfiguration(BlockStateProvider.simple(Crimsite)));

    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> OCHRUM_VENT_CONFIGURED = FeatureUtils.register("molten_vents:ochrum_vent", MoltenVentsFeatureRegistry.MOLTEN_VENT.get(),
            new SimpleBlockConfiguration(BlockStateProvider.simple(Ochrum)));

    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> AQUATIC_ASURINE_VENT_CONFIGURED = FeatureUtils.register("molten_vents:aquatic_asurine_vent", MoltenVentsFeatureRegistry.AQUATIC_MOLTEN_VENT.get(),
            new SimpleBlockConfiguration(BlockStateProvider.simple(Asurine)));

    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> AQUATIC_VERIDIUM_VENT_CONFIGURED = FeatureUtils.register("molten_vents:aquatic_veridium_vent", MoltenVentsFeatureRegistry.AQUATIC_MOLTEN_VENT.get(),
            new SimpleBlockConfiguration(BlockStateProvider.simple(Veridium)));

    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> AQUATIC_CRIMSITE_VENT_CONFIGURED = FeatureUtils.register("molten_vents:aquatic_crimsite_vent", MoltenVentsFeatureRegistry.AQUATIC_MOLTEN_VENT.get(),
            new SimpleBlockConfiguration(BlockStateProvider.simple(Crimsite)));

    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> AQUATIC_OCHRUM_VENT_CONFIGURED = FeatureUtils.register("molten_vents:aquatic_ochrum_vent", MoltenVentsFeatureRegistry.AQUATIC_MOLTEN_VENT.get(),
            new SimpleBlockConfiguration(BlockStateProvider.simple(Ochrum)));

    //PlacedFeatures
    public static final Holder<PlacedFeature> ASURINE_VENT_PLACED = PlacementUtils.register("molten_vents:asurine_vent", ASURINE_VENT_CONFIGURED, RarityFilter.onAverageOnceEvery(CommonConfig.ventRarity.get()), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    public static final Holder<PlacedFeature> VERIDIUM_VENT_PLACED = PlacementUtils.register("molten_vents:veridium_vent", VERIDIUM_VENT_CONFIGURED, RarityFilter.onAverageOnceEvery(CommonConfig.ventRarity.get()), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    public static final Holder<PlacedFeature> CRIMSITE_VENT_PLACED = PlacementUtils.register("molten_vents:crimsite_vent", CRIMSITE_VENT_CONFIGURED, RarityFilter.onAverageOnceEvery(CommonConfig.ventRarity.get()), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    public static final Holder<PlacedFeature> OCHRUM_VENT_PLACED = PlacementUtils.register("molten_vents:ochrum_vent", OCHRUM_VENT_CONFIGURED, RarityFilter.onAverageOnceEvery(CommonConfig.ventRarity.get()), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

    public static final Holder<PlacedFeature> AQUATIC_ASURINE_VENT_PLACED = PlacementUtils.register("molten_vents:aquatic_asurine_vent", AQUATIC_ASURINE_VENT_CONFIGURED, RarityFilter.onAverageOnceEvery(CommonConfig.ventRarity.get()), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());
    public static final Holder<PlacedFeature> AQUATIC_VERIDIUM_VENT_PLACED = PlacementUtils.register("molten_vents:aquatic_veridium_vent", AQUATIC_VERIDIUM_VENT_CONFIGURED, RarityFilter.onAverageOnceEvery(CommonConfig.ventRarity.get()), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());
    public static final Holder<PlacedFeature> AQUATIC_CRIMSITE_VENT_PLACED = PlacementUtils.register("molten_vents:aquatic_crimsite_vent", AQUATIC_CRIMSITE_VENT_CONFIGURED, RarityFilter.onAverageOnceEvery(CommonConfig.ventRarity.get()), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());
    public static final Holder<PlacedFeature> AQUATIC_OCHRUM_VENT_PLACED = PlacementUtils.register("molten_vents:aquatic_ochrum_vent", AQUATIC_OCHRUM_VENT_CONFIGURED, RarityFilter.onAverageOnceEvery(CommonConfig.ventRarity.get()), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());
}
