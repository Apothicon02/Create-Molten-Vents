package com.Apothic0n.MoltenVents.api.biome.features;

import com.Apothic0n.MoltenVents.config.CommonConfig;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

import static com.Apothic0n.MoltenVents.api.biome.features.MoltenVentsConfiguredFeatures.*;
import static net.minecraft.data.worldgen.placement.PlacementUtils.createKey;

public class MoltenVentsPlacedFeatures {
    public static final ResourceKey<PlacedFeature> ASURINE_VENT_CHECKED = createKey("asurine_vent_checked");
    public static final ResourceKey<PlacedFeature> VERIDIUM_VENT_CHECKED = createKey("veridium_vent_checked");
    public static final ResourceKey<PlacedFeature> CRIMSITE_VENT_CHECKED = createKey("crimsite_vent_checked");
    public static final ResourceKey<PlacedFeature> OCHRUM_VENT_CHECKED = createKey("ochrum_vent_checked");

    public static final ResourceKey<PlacedFeature> AQUATIC_ASURINE_VENT_CHECKED = createKey("aquatic_asurine_vent_checked");
    public static final ResourceKey<PlacedFeature> AQUATIC_VERIDIUM_VENT_CHECKED = createKey("aquatic_veridium_vent_checked");
    public static final ResourceKey<PlacedFeature> AQUATIC_CRIMSITE_VENT_CHECKED = createKey("aquatic_crimsite_vent_checked");
    public static final ResourceKey<PlacedFeature> AQUATIC_OCHRUM_VENT_CHECKED = createKey("aquatic_ochrum_vent_checked");
    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holderGetter = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(ASURINE_VENT_CHECKED, new PlacedFeature(holderGetter.getOrThrow(ASURINE_VENT), List.of(RarityFilter.onAverageOnceEvery(690), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
        context.register(VERIDIUM_VENT_CHECKED, new PlacedFeature(holderGetter.getOrThrow(VERIDIUM_VENT), List.of(RarityFilter.onAverageOnceEvery(690), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
        context.register(CRIMSITE_VENT_CHECKED, new PlacedFeature(holderGetter.getOrThrow(CRIMSITE_VENT), List.of(RarityFilter.onAverageOnceEvery(690), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
        context.register(OCHRUM_VENT_CHECKED, new PlacedFeature(holderGetter.getOrThrow(OCHRUM_VENT), List.of(RarityFilter.onAverageOnceEvery(690), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));

        context.register(AQUATIC_ASURINE_VENT_CHECKED, new PlacedFeature(holderGetter.getOrThrow(AQUATIC_ASURINE_VENT), List.of(RarityFilter.onAverageOnceEvery(690), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
        context.register(AQUATIC_VERIDIUM_VENT_CHECKED, new PlacedFeature(holderGetter.getOrThrow(AQUATIC_VERIDIUM_VENT), List.of(RarityFilter.onAverageOnceEvery(690), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
        context.register(AQUATIC_CRIMSITE_VENT_CHECKED, new PlacedFeature(holderGetter.getOrThrow(AQUATIC_CRIMSITE_VENT), List.of(RarityFilter.onAverageOnceEvery(690), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
        context.register(AQUATIC_OCHRUM_VENT_CHECKED, new PlacedFeature(holderGetter.getOrThrow(AQUATIC_OCHRUM_VENT), List.of(RarityFilter.onAverageOnceEvery(690), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
    }
}
