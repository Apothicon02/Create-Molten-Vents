package com.Apothic0n.MoltenVents.api.biome.features;

import com.Apothic0n.MoltenVents.MoltenVents;
import com.Apothic0n.MoltenVents.api.biome.features.types.MoltenVentFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MoltenVentsFeatureRegistry {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, MoltenVents.MODID);

    public static final RegistryObject<Feature<SimpleBlockConfiguration>> MOLTEN_VENT = FEATURES.register("molten_vent", () ->
            new MoltenVentFeature(SimpleBlockConfiguration.CODEC));

    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
