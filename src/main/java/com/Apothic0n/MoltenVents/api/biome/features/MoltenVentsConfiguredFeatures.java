package com.Apothic0n.MoltenVents.api.biome.features;

import com.Apothic0n.MoltenVents.MoltenVents;
import com.Apothic0n.MoltenVents.config.CommonConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import static com.Apothic0n.MoltenVents.api.biome.features.MoltenVentsFeatures.AQUATIC_MOLTEN_VENT;
import static com.Apothic0n.MoltenVents.api.biome.features.MoltenVentsFeatures.MOLTEN_VENT;
import static com.Apothic0n.MoltenVents.core.objects.MoltenBlocks.*;

public class MoltenVentsConfiguredFeatures {
    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(MoltenVents.MODID, name));
    }

    public static final ResourceKey<ConfiguredFeature<?, ?>> ASURINE_VENT = createKey("asurine_vent");
    public static final ResourceKey<ConfiguredFeature<?, ?>> VERIDIUM_VENT = createKey("veridium_vent");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CRIMSITE_VENT = createKey("crimsite_vent");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OCHRUM_VENT = createKey("ochrum_vent");

    public static final ResourceKey<ConfiguredFeature<?, ?>> AQUATIC_ASURINE_VENT = createKey("aquatic_asurine_vent");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AQUATIC_VERIDIUM_VENT = createKey("aquatic_veridium_vent");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AQUATIC_CRIMSITE_VENT = createKey("aquatic_crimsite_vent");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AQUATIC_OCHRUM_VENT = createKey("aquatic_ochrum_vent");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        context.register(ASURINE_VENT, new ConfiguredFeature<>(MOLTEN_VENT.get(), new SimpleBlockConfiguration(BlockStateProvider.simple(Asurine.get()))));
        context.register(VERIDIUM_VENT, new ConfiguredFeature<>(MOLTEN_VENT.get(), new SimpleBlockConfiguration(BlockStateProvider.simple(Veridium.get()))));
        context.register(CRIMSITE_VENT, new ConfiguredFeature<>(MOLTEN_VENT.get(), new SimpleBlockConfiguration(BlockStateProvider.simple(Crimsite.get()))));
        context.register(OCHRUM_VENT, new ConfiguredFeature<>(MOLTEN_VENT.get(), new SimpleBlockConfiguration(BlockStateProvider.simple(Ochrum.get()))));

        context.register(AQUATIC_ASURINE_VENT, new ConfiguredFeature<>(AQUATIC_MOLTEN_VENT.get(), new SimpleBlockConfiguration(BlockStateProvider.simple(Asurine.get()))));
        context.register(AQUATIC_VERIDIUM_VENT, new ConfiguredFeature<>(AQUATIC_MOLTEN_VENT.get(), new SimpleBlockConfiguration(BlockStateProvider.simple(Veridium.get()))));
        context.register(AQUATIC_CRIMSITE_VENT, new ConfiguredFeature<>(AQUATIC_MOLTEN_VENT.get(), new SimpleBlockConfiguration(BlockStateProvider.simple(Crimsite.get()))));
        context.register(AQUATIC_OCHRUM_VENT, new ConfiguredFeature<>(AQUATIC_MOLTEN_VENT.get(), new SimpleBlockConfiguration(BlockStateProvider.simple(Ochrum.get()))));
    }
}
