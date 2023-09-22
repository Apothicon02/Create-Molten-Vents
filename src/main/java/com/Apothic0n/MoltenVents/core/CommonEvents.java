package com.Apothic0n.MoltenVents.core;

import com.Apothic0n.MoltenVents.MoltenVents;
import com.Apothic0n.MoltenVents.api.biome.features.MoltenVentsFeatures;
import com.Apothic0n.MoltenVents.api.biome.features.configurations.MoltenVentConfiguration;
import com.google.gson.Gson;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = MoltenVents.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {

    @SubscribeEvent
    public static void addReloadListener(AddReloadListenerEvent event) {
        event.addListener(new MoltenVentsConductiveData(new Gson()));
        event.addListener(new MoltenVentsConvertibleData(new Gson()));
    }

    static Holder<PlacedFeature> asurineVent;
    static Holder<PlacedFeature> crimsiteVent;
    static Holder<PlacedFeature> veridiumVent;
    static Holder<PlacedFeature> ochrumVent;
    static Holder<PlacedFeature> scorchiaVent;
    static Holder<PlacedFeature> scoriaVent;
    static Holder<PlacedFeature> aquaticAsurineVent;
    static Holder<PlacedFeature> aquaticCrimsiteVent;
    static Holder<PlacedFeature> aquaticVeridiumVent;
    static Holder<PlacedFeature> aquaticOchrumvent;
    static Holder<PlacedFeature> aquaticScorchiaVent;
    static Holder<PlacedFeature> aquaticScoriaVent;
    static boolean hasCreatedFeatures = false;
    @SubscribeEvent
    public static void biomeLoading(@Nonnull BiomeLoadingEvent event) {
        if (!hasCreatedFeatures) {
            asurineVent = PlacementUtils.register("molten_vents:asurine_vent", FeatureUtils.register("molten_vents:asurine_vent", MoltenVentsFeatures.MOLTEN_VENT.get(), new MoltenVentConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Registry.BLOCK.get(new ResourceLocation("create", "cut_asurine_wall")).defaultBlockState(), 3).add(Registry.BLOCK.get(new ResourceLocation("create", "cut_asurine_slab")).defaultBlockState(), 7)),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Registry.BLOCK.get(new ResourceLocation("create", "asurine")).defaultBlockState(), 8).add(Registry.BLOCK.get(new ResourceLocation("create", "cut_asurine")).defaultBlockState(), 2)),
                    WeightedStateProvider.simple(Registry.BLOCK.get(new ResourceLocation("molten_vents", "dormant_molten_asurine"))),
                    BlockStateProvider.simple(Registry.BLOCK.get(new ResourceLocation("minecraft", "lava"))),
                    UniformInt.of(80, 100), Boolean.FALSE
            )), RarityFilter.onAverageOnceEvery(1000), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

            crimsiteVent = PlacementUtils.register("molten_vents:crimsite_vent", FeatureUtils.register("molten_vents:crimsite_vent", MoltenVentsFeatures.MOLTEN_VENT.get(), new MoltenVentConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Registry.BLOCK.get(new ResourceLocation("create", "cut_crimsite_wall")).defaultBlockState(), 3).add(Registry.BLOCK.get(new ResourceLocation("create", "cut_crimsite_slab")).defaultBlockState(), 7)),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Registry.BLOCK.get(new ResourceLocation("create", "crimsite")).defaultBlockState(), 8).add(Registry.BLOCK.get(new ResourceLocation("create", "cut_crimsite")).defaultBlockState(), 2)),
                    WeightedStateProvider.simple(Registry.BLOCK.get(new ResourceLocation("molten_vents", "dormant_molten_crimsite"))),
                    BlockStateProvider.simple(Registry.BLOCK.get(new ResourceLocation("minecraft", "lava"))),
                    UniformInt.of(80, 100), Boolean.FALSE
            )), RarityFilter.onAverageOnceEvery(1000), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

            veridiumVent = PlacementUtils.register("molten_vents:veridium_vent", FeatureUtils.register("molten_vents:veridium_vent", MoltenVentsFeatures.MOLTEN_VENT.get(), new MoltenVentConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Registry.BLOCK.get(new ResourceLocation("create", "cut_veridium_wall")).defaultBlockState(), 3).add(Registry.BLOCK.get(new ResourceLocation("create", "cut_veridium_slab")).defaultBlockState(), 7)),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Registry.BLOCK.get(new ResourceLocation("create", "veridium")).defaultBlockState(), 8).add(Registry.BLOCK.get(new ResourceLocation("create", "cut_veridium")).defaultBlockState(), 2)),
                    WeightedStateProvider.simple(Registry.BLOCK.get(new ResourceLocation("molten_vents", "dormant_molten_veridium"))),
                    BlockStateProvider.simple(Registry.BLOCK.get(new ResourceLocation("minecraft", "lava"))),
                    UniformInt.of(80, 100), Boolean.FALSE
            )), RarityFilter.onAverageOnceEvery(1000), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

            ochrumVent = PlacementUtils.register("molten_vents:ochrum_vent", FeatureUtils.register("molten_vents:ochrum_vent", MoltenVentsFeatures.MOLTEN_VENT.get(), new MoltenVentConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Registry.BLOCK.get(new ResourceLocation("create", "cut_ochrum_wall")).defaultBlockState(), 3).add(Registry.BLOCK.get(new ResourceLocation("create", "cut_ochrum_slab")).defaultBlockState(), 7)),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Registry.BLOCK.get(new ResourceLocation("create", "ochrum")).defaultBlockState(), 8).add(Registry.BLOCK.get(new ResourceLocation("create", "cut_ochrum")).defaultBlockState(), 2)),
                    WeightedStateProvider.simple(Registry.BLOCK.get(new ResourceLocation("molten_vents", "dormant_molten_ochrum"))),
                    BlockStateProvider.simple(Registry.BLOCK.get(new ResourceLocation("minecraft", "lava"))),
                    UniformInt.of(80, 100), Boolean.FALSE
            )), RarityFilter.onAverageOnceEvery(1000), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

            scorchiaVent = PlacementUtils.register("molten_vents:scorchia_vent", FeatureUtils.register("molten_vents:scorchia_vent", MoltenVentsFeatures.MOLTEN_VENT.get(), new MoltenVentConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Registry.BLOCK.get(new ResourceLocation("create", "cut_scorchia_wall")).defaultBlockState(), 3).add(Registry.BLOCK.get(new ResourceLocation("create", "cut_scorchia_slab")).defaultBlockState(), 7)),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Registry.BLOCK.get(new ResourceLocation("create", "scorchia")).defaultBlockState(), 8).add(Registry.BLOCK.get(new ResourceLocation("create", "cut_scorchia")).defaultBlockState(), 2)),
                    WeightedStateProvider.simple(Registry.BLOCK.get(new ResourceLocation("molten_vents", "dormant_molten_scorchia"))),
                    BlockStateProvider.simple(Registry.BLOCK.get(new ResourceLocation("minecraft", "lava"))),
                    UniformInt.of(80, 100), Boolean.FALSE
            )), RarityFilter.onAverageOnceEvery(1000), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

            scoriaVent = PlacementUtils.register("molten_vents:scoria_vent", FeatureUtils.register("molten_vents:scoria_vent", MoltenVentsFeatures.MOLTEN_VENT.get(), new MoltenVentConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Registry.BLOCK.get(new ResourceLocation("create", "cut_scoria_wall")).defaultBlockState(), 3).add(Registry.BLOCK.get(new ResourceLocation("create", "cut_scoria_slab")).defaultBlockState(), 7)),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Registry.BLOCK.get(new ResourceLocation("create", "scoria")).defaultBlockState(), 8).add(Registry.BLOCK.get(new ResourceLocation("create", "cut_scoria")).defaultBlockState(), 2)),
                    WeightedStateProvider.simple(Registry.BLOCK.get(new ResourceLocation("molten_vents", "dormant_molten_scoria"))),
                    BlockStateProvider.simple(Registry.BLOCK.get(new ResourceLocation("minecraft", "lava"))),
                    UniformInt.of(80, 100), Boolean.FALSE
            )), RarityFilter.onAverageOnceEvery(1000), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());


            aquaticAsurineVent = PlacementUtils.register("molten_vents:aquatic_asurine_vent", FeatureUtils.register("molten_vents:aquatic_asurine_vent", MoltenVentsFeatures.MOLTEN_VENT.get(), new MoltenVentConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Registry.BLOCK.get(new ResourceLocation("create", "cut_asurine_wall")).defaultBlockState(), 3).add(Registry.BLOCK.get(new ResourceLocation("create", "cut_asurine_slab")).defaultBlockState(), 7)),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Registry.BLOCK.get(new ResourceLocation("create", "asurine")).defaultBlockState(), 8).add(Registry.BLOCK.get(new ResourceLocation("create", "cut_asurine")).defaultBlockState(), 2)),
                    WeightedStateProvider.simple(Registry.BLOCK.get(new ResourceLocation("molten_vents", "dormant_molten_asurine"))),
                    BlockStateProvider.simple(Registry.BLOCK.get(new ResourceLocation("minecraft", "lava"))),
                    UniformInt.of(20, 35), Boolean.FALSE
            )), RarityFilter.onAverageOnceEvery(690), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());

            aquaticCrimsiteVent = PlacementUtils.register("molten_vents:aquatic_crimsite_vent", FeatureUtils.register("molten_vents:aquatic_crimsite_vent", MoltenVentsFeatures.MOLTEN_VENT.get(), new MoltenVentConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Registry.BLOCK.get(new ResourceLocation("create", "cut_crimsite_wall")).defaultBlockState(), 3).add(Registry.BLOCK.get(new ResourceLocation("create", "cut_crimsite_slab")).defaultBlockState(), 7)),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Registry.BLOCK.get(new ResourceLocation("create", "crimsite")).defaultBlockState(), 8).add(Registry.BLOCK.get(new ResourceLocation("create", "cut_crimsite")).defaultBlockState(), 2)),
                    WeightedStateProvider.simple(Registry.BLOCK.get(new ResourceLocation("molten_vents", "dormant_molten_crimsite"))),
                    BlockStateProvider.simple(Registry.BLOCK.get(new ResourceLocation("minecraft", "lava"))),
                    UniformInt.of(20, 35), Boolean.FALSE
            )), RarityFilter.onAverageOnceEvery(690), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());

            aquaticVeridiumVent = PlacementUtils.register("molten_vents:aquatic_veridium_vent", FeatureUtils.register("molten_vents:aquatic_veridium_vent", MoltenVentsFeatures.MOLTEN_VENT.get(), new MoltenVentConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Registry.BLOCK.get(new ResourceLocation("create", "cut_veridium_wall")).defaultBlockState(), 3).add(Registry.BLOCK.get(new ResourceLocation("create", "cut_veridium_slab")).defaultBlockState(), 7)),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Registry.BLOCK.get(new ResourceLocation("create", "veridium")).defaultBlockState(), 8).add(Registry.BLOCK.get(new ResourceLocation("create", "cut_veridium")).defaultBlockState(), 2)),
                    WeightedStateProvider.simple(Registry.BLOCK.get(new ResourceLocation("molten_vents", "dormant_molten_veridium"))),
                    BlockStateProvider.simple(Registry.BLOCK.get(new ResourceLocation("minecraft", "lava"))),
                    UniformInt.of(20, 35), Boolean.FALSE
            )), RarityFilter.onAverageOnceEvery(690), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());

            aquaticOchrumvent = PlacementUtils.register("molten_vents:aquatic_ochrum_vent", FeatureUtils.register("molten_vents:aquatic_ochrum_vent", MoltenVentsFeatures.MOLTEN_VENT.get(), new MoltenVentConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Registry.BLOCK.get(new ResourceLocation("create", "cut_ochrum_wall")).defaultBlockState(), 3).add(Registry.BLOCK.get(new ResourceLocation("create", "cut_ochrum_slab")).defaultBlockState(), 7)),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Registry.BLOCK.get(new ResourceLocation("create", "ochrum")).defaultBlockState(), 8).add(Registry.BLOCK.get(new ResourceLocation("create", "cut_ochrum")).defaultBlockState(), 2)),
                    WeightedStateProvider.simple(Registry.BLOCK.get(new ResourceLocation("molten_vents", "dormant_molten_ochrum"))),
                    BlockStateProvider.simple(Registry.BLOCK.get(new ResourceLocation("minecraft", "lava"))),
                    UniformInt.of(20, 35), Boolean.FALSE
            )), RarityFilter.onAverageOnceEvery(690), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());

            aquaticScorchiaVent = PlacementUtils.register("molten_vents:aquatic_scorchia_vent", FeatureUtils.register("molten_vents:aquatic_scorchia_vent", MoltenVentsFeatures.MOLTEN_VENT.get(), new MoltenVentConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Registry.BLOCK.get(new ResourceLocation("create", "cut_scorchia_wall")).defaultBlockState(), 3).add(Registry.BLOCK.get(new ResourceLocation("create", "cut_scorchia_slab")).defaultBlockState(), 7)),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Registry.BLOCK.get(new ResourceLocation("create", "scorchia")).defaultBlockState(), 8).add(Registry.BLOCK.get(new ResourceLocation("create", "cut_scorchia")).defaultBlockState(), 2)),
                    WeightedStateProvider.simple(Registry.BLOCK.get(new ResourceLocation("molten_vents", "dormant_molten_scorchia"))),
                    BlockStateProvider.simple(Registry.BLOCK.get(new ResourceLocation("minecraft", "lava"))),
                    UniformInt.of(20, 35), Boolean.FALSE
            )), RarityFilter.onAverageOnceEvery(690), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());

            aquaticScoriaVent = PlacementUtils.register("molten_vents:aquatic_scoria_vent", FeatureUtils.register("molten_vents:aquatic_scoria_vent", MoltenVentsFeatures.MOLTEN_VENT.get(), new MoltenVentConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Registry.BLOCK.get(new ResourceLocation("create", "cut_scoria_wall")).defaultBlockState(), 3).add(Registry.BLOCK.get(new ResourceLocation("create", "cut_scoria_slab")).defaultBlockState(), 7)),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Registry.BLOCK.get(new ResourceLocation("create", "scoria")).defaultBlockState(), 8).add(Registry.BLOCK.get(new ResourceLocation("create", "cut_scoria")).defaultBlockState(), 2)),
                    WeightedStateProvider.simple(Registry.BLOCK.get(new ResourceLocation("molten_vents", "dormant_molten_scoria"))),
                    BlockStateProvider.simple(Registry.BLOCK.get(new ResourceLocation("minecraft", "lava"))),
                    UniformInt.of(20, 35), Boolean.FALSE
            )), RarityFilter.onAverageOnceEvery(690), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());
            hasCreatedFeatures = true;
        }
        if(!event.getCategory().equals(Biome.BiomeCategory.NETHER) && !event.getCategory().equals(Biome.BiomeCategory.THEEND)) {
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, asurineVent);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, crimsiteVent);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, veridiumVent);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ochrumVent);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, scorchiaVent);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, scoriaVent);

            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, aquaticAsurineVent);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, aquaticCrimsiteVent);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, aquaticVeridiumVent);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, aquaticOchrumvent);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, aquaticScorchiaVent);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, aquaticScoriaVent);
        }
    }
}
