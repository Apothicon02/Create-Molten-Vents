package com.Apothic0n.MoltenVents.core;

import com.Apothic0n.MoltenVents.config.CommonConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

public class AquaticMoltenVentFeature extends MoltenVentFeature
{
    public AquaticMoltenVentFeature(Codec<SimpleBlockConfiguration> pContext) {
        super(pContext);
    }

    public boolean shouldGenerate(FeaturePlaceContext<SimpleBlockConfiguration> pContext) {
        return CommonConfig.generateUnderwater.get() && pContext.level().getBlockState(pContext.origin().above(2)).is(Blocks.WATER);
    }

    public int getVentDepth() {
        Integer ventDepth = CommonConfig.ventDepth.get();
        if (ventDepth > 80)
            ventDepth = 80;
        return ventDepth;
    }
}
