package com.Apothic0n.MoltenVents.api.biome.features;

import com.Apothic0n.MoltenVents.MoltenVents;
import com.Apothic0n.MoltenVents.api.biome.features.configurations.MoltenVentConfiguration;
import com.Apothic0n.MoltenVents.api.biome.features.types.MoltenVentFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MoltenVentsFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, MoltenVents.MODID);

    public static final RegistryObject<Feature<MoltenVentConfiguration>> MOLTEN_VENT = FEATURES.register("molten_vent", () ->
            new MoltenVentFeature(MoltenVentConfiguration.CODEC));

    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
