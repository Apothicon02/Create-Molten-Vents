package com.Apothic0n.MoltenVents.config;


import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Configs {

    public static void register() {
        registerCommonConfigs();
    }

    private static void registerCommonConfigs() {
        ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();
        CommonConfig.registerCommonConfig(COMMON_BUILDER);
        ModLoadingContext.get().getActiveContainer().registerConfig(ModConfig.Type.COMMON, COMMON_BUILDER.build());
    }
}