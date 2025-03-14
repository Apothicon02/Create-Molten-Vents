package com.Apothic0n.MoltenVents.api.biome.features;

import com.Apothic0n.MoltenVents.MoltenVents;
import com.Apothic0n.MoltenVents.api.biome.features.configurations.MoltenVentConfiguration;
import com.Apothic0n.MoltenVents.api.biome.features.types.MoltenVentFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MoltenVentsFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, MoltenVents.MODID);

    public static final DeferredHolder<Feature<?>, Feature<?>> MOLTEN_VENT = FEATURES.register("molten_vent", () ->
            new MoltenVentFeature(MoltenVentConfiguration.CODEC));

    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
