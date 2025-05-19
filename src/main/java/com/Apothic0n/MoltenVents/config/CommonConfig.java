package com.Apothic0n.MoltenVents.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class CommonConfig {
    public static ModConfigSpec.BooleanValue useSource;
    public static ModConfigSpec.BooleanValue generateLandVents;
    public static ModConfigSpec.BooleanValue generateUnderwaterVents;

    public static void registerCommonConfig(ModConfigSpec.Builder COMMON_BUILDER) {
        COMMON_BUILDER.comment("General settings for Molten Vents").push("common");

        useSource = COMMON_BUILDER
                .comment("When true, orestone blocks will only replace liquid source blocks. This means that you will require a constant supply of liquid to generate orestones. Default: true")
                .define("useLiquid", true);
        generateLandVents = COMMON_BUILDER
                .comment("Whether or not to generate molten vents on land. Default: true")
                .define("generateLandVents", true);
        generateUnderwaterVents = COMMON_BUILDER
                .comment("Whether or not to generate molten vents underwater. Default: true")
                .define("generateUnderwaterVents", true);

        COMMON_BUILDER.pop();
    }
}