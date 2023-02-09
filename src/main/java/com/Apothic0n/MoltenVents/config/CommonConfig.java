package com.Apothic0n.MoltenVents.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
    public static ForgeConfigSpec.BooleanValue useLava;
    public static ForgeConfigSpec.ConfigValue<? extends Integer> ventRarity;
    public static ForgeConfigSpec.ConfigValue<? extends Integer> ventDepth;


    public static void registerCommonConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
        COMMON_BUILDER.comment("General settings for Molten Vents").push("common");

        useLava = COMMON_BUILDER
                .comment("When true, orestone blocks will only replace lava source blocks. This means that you will require a constant supply of lava to generate orestones. Default: true")
                .define("useLava", true);

        ventRarity = COMMON_BUILDER
                .comment("The rarity of vents. The higher the number the rarer the vents. Default: 690")
                .define("ventRarity", 690);

        ventDepth = COMMON_BUILDER
                .comment("The depth of vents. When set below 10 the vents will stop generating immediately below the molten ore block, which is useful for specific cases such as when using the Stratospheric Expansion mod to prevent the vents from sticking through the bottom of the islands. Default: 100")
                .define("ventDepth", 100);

        COMMON_BUILDER.pop();
    }
}