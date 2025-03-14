package com.Apothic0n.MoltenVents.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class CommonConfig {
    public static ModConfigSpec.BooleanValue useSource;
    public static ModConfigSpec.ConfigValue<? extends Integer> ventDepth;
    public static ModConfigSpec.BooleanValue generateUnderwater;

    public static void registerCommonConfig(ModConfigSpec.Builder COMMON_BUILDER) {
        COMMON_BUILDER.comment("General settings for Molten Vents").push("common");

        useSource = COMMON_BUILDER
                .comment("When true, orestone blocks will only replace liquid source blocks. This means that you will require a constant supply of liquid to generate orestones. Default: true")
                .define("useLiquid", true);

        COMMON_BUILDER.pop();
    }
}