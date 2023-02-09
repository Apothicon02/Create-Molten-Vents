package com.Apothic0n.MoltenVents.core.events;

import com.Apothic0n.MoltenVents.MoltenVents;
import com.Apothic0n.MoltenVents.api.biome.features.MoltenVentsFeatures;
import com.Apothic0n.MoltenVents.config.CommonConfig;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;


@Mod.EventBusSubscriber(modid = MoltenVents.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonModEvents {

    @SubscribeEvent
    public static void biomeLoading(@Nonnull BiomeLoadingEvent event) {
        Boolean addedFeature = false;
        if(!event.getCategory().equals(Biome.BiomeCategory.NETHER) && !event.getCategory().equals(Biome.BiomeCategory.THEEND) && event.getName().getNamespace().equals("minecraft")) {
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MoltenVentsFeatures.ASURINE_VENT_PLACED);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MoltenVentsFeatures.VERIDIUM_VENT_PLACED);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MoltenVentsFeatures.CRIMSITE_VENT_PLACED);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MoltenVentsFeatures.OCHRUM_VENT_PLACED);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MoltenVentsFeatures.AQUATIC_ASURINE_VENT_PLACED);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MoltenVentsFeatures.AQUATIC_VERIDIUM_VENT_PLACED);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MoltenVentsFeatures.AQUATIC_CRIMSITE_VENT_PLACED);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MoltenVentsFeatures.AQUATIC_OCHRUM_VENT_PLACED);
            addedFeature = true;
        }
        if (!addedFeature && event.getName().getNamespace().equals(CommonConfig.modName.get())) {
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MoltenVentsFeatures.ASURINE_VENT_PLACED);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MoltenVentsFeatures.VERIDIUM_VENT_PLACED);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MoltenVentsFeatures.CRIMSITE_VENT_PLACED);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MoltenVentsFeatures.OCHRUM_VENT_PLACED);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MoltenVentsFeatures.AQUATIC_ASURINE_VENT_PLACED);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MoltenVentsFeatures.AQUATIC_VERIDIUM_VENT_PLACED);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MoltenVentsFeatures.AQUATIC_CRIMSITE_VENT_PLACED);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MoltenVentsFeatures.AQUATIC_OCHRUM_VENT_PLACED);
        }
    }
}
