package com.Apothic0n.MoltenVents.api.biome.features.types;

import com.Apothic0n.MoltenVents.api.biome.features.MoltenVentsConfiguredFeatures;
import com.Apothic0n.MoltenVents.config.CommonConfig;
import com.Apothic0n.MoltenVents.core.objects.MoltenBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

public class MoltenVentFeature extends Feature<SimpleBlockConfiguration> {
    public MoltenVentFeature(Codec<SimpleBlockConfiguration> pContext) {
        super(pContext);
    }

    public boolean place(FeaturePlaceContext<SimpleBlockConfiguration> pContext) {
        SimpleBlockConfiguration ventConfiguration = pContext.config();
        RandomSource random = pContext.random();
        BlockPos origin = pContext.origin();
        WorldGenLevel worldgenlevel = pContext.level();
        BlockState outerBlock = ventConfiguration.toPlace().getState(random, origin);
        BlockState innerblock = Blocks.COAL_BLOCK.defaultBlockState();
        BlockState liquidblock = Blocks.LAVA.defaultBlockState();
        Integer ventDepth = CommonConfig.ventDepth.get();
        Boolean disabled = CommonConfig.generateUnderwater.get();

        if (!worldgenlevel.getBlockState(origin.below()).is(Blocks.WATER) && !disabled) {
            if (outerBlock.is(MoltenBlocks.Asurine)) {
                innerblock = MoltenBlocks.DORMANT_ASURINE.get().defaultBlockState();
            } else if (outerBlock.is(MoltenBlocks.Veridium)) {
                innerblock = MoltenBlocks.DORMANT_VERIDIUM.get().defaultBlockState();
            } else if (outerBlock.is(MoltenBlocks.Crimsite)) {
                innerblock = MoltenBlocks.DORMANT_CRIMSITE.get().defaultBlockState();
            } else if (outerBlock.is(MoltenBlocks.Ochrum)) {
                innerblock = MoltenBlocks.DORMANT_OCHRUM.get().defaultBlockState();
            }

            worldgenlevel.setBlock(origin.above().north(3).east(), outerBlock, 3);
            worldgenlevel.setBlock(origin.above().north(3).west(), outerBlock, 3);
            worldgenlevel.setBlock(origin.above().south(3).east(), outerBlock, 3);
            worldgenlevel.setBlock(origin.above().north().east(3), outerBlock, 3);
            worldgenlevel.setBlock(origin.above().south().east(3), outerBlock, 3);
            worldgenlevel.setBlock(origin.above().south().west(3), outerBlock, 3);



            worldgenlevel.setBlock(origin.north(3), outerBlock, 3);
            worldgenlevel.setBlock(origin.east(3), outerBlock, 3);
            worldgenlevel.setBlock(origin.south(3), outerBlock, 3);
            worldgenlevel.setBlock(origin.west(3), outerBlock, 3);
            worldgenlevel.setBlock(origin.north(3).east(), outerBlock, 3);
            worldgenlevel.setBlock(origin.north(3).west(), outerBlock, 3);
            worldgenlevel.setBlock(origin.south(3).east(), outerBlock, 3);
            worldgenlevel.setBlock(origin.south(3).west(), outerBlock, 3);
            worldgenlevel.setBlock(origin.north().east(3), outerBlock, 3);
            worldgenlevel.setBlock(origin.north().west(3), outerBlock, 3);
            worldgenlevel.setBlock(origin.south().east(3), outerBlock, 3);
            worldgenlevel.setBlock(origin.south().west(3), outerBlock, 3);
            worldgenlevel.setBlock(origin.north(2).east(2), outerBlock, 3);
            worldgenlevel.setBlock(origin.north(2).west(2), outerBlock, 3);
            worldgenlevel.setBlock(origin.south(2).east(2), outerBlock, 3);
            worldgenlevel.setBlock(origin.south(2).west(2), outerBlock, 3);
            worldgenlevel.setBlock(origin.north(3).east(2), outerBlock, 3);
            worldgenlevel.setBlock(origin.north(3).west(2), outerBlock, 3);
            worldgenlevel.setBlock(origin.south(3).east(2), outerBlock, 3);
            worldgenlevel.setBlock(origin.south(3).west(2), outerBlock, 3);
            worldgenlevel.setBlock(origin.north(2).east(3), outerBlock, 3);
            worldgenlevel.setBlock(origin.north(2).west(3), outerBlock, 3);
            worldgenlevel.setBlock(origin.south(2).east(3), outerBlock, 3);
            worldgenlevel.setBlock(origin.south(2).west(3), outerBlock, 3);


            worldgenlevel.setBlock(origin.below().north(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below().east(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below().south(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below().west(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below().north(2).east(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below().north(2).west(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below().south(2).east(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below().south(2).west(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below().north().east(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below().north().west(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below().south().east(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below().south().west(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below().north(2).east(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below().north(2).west(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below().south(2).east(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below().south(2).west(2), outerBlock, 2);

            worldgenlevel.setBlock(origin.below().north(3), outerBlock, 3);
            worldgenlevel.setBlock(origin.below().east(3), outerBlock, 3);
            worldgenlevel.setBlock(origin.below().south(3), outerBlock, 3);
            worldgenlevel.setBlock(origin.below().west(3), outerBlock, 3);
            worldgenlevel.setBlock(origin.below().north(3).east(), outerBlock, 3);
            worldgenlevel.setBlock(origin.below().north(3).west(), outerBlock, 3);
            worldgenlevel.setBlock(origin.below().south(3).east(), outerBlock, 3);
            worldgenlevel.setBlock(origin.below().south(3).west(), outerBlock, 3);
            worldgenlevel.setBlock(origin.below().north().east(3), outerBlock, 3);
            worldgenlevel.setBlock(origin.below().north().west(3), outerBlock, 3);
            worldgenlevel.setBlock(origin.below().south().east(3), outerBlock, 3);
            worldgenlevel.setBlock(origin.below().south().west(3), outerBlock, 3);
            worldgenlevel.setBlock(origin.below().north(2).east(2), outerBlock, 3);
            worldgenlevel.setBlock(origin.below().north(2).west(2), outerBlock, 3);
            worldgenlevel.setBlock(origin.below().south(2).east(2), outerBlock, 3);
            worldgenlevel.setBlock(origin.below().south(2).west(2), outerBlock, 3);
            worldgenlevel.setBlock(origin.below().north(3).east(2), outerBlock, 3);
            worldgenlevel.setBlock(origin.below().north(3).west(2), outerBlock, 3);
            worldgenlevel.setBlock(origin.below().south(3).east(2), outerBlock, 3);
            worldgenlevel.setBlock(origin.below().south(3).west(2), outerBlock, 3);
            worldgenlevel.setBlock(origin.below().north(2).east(3), outerBlock, 3);
            worldgenlevel.setBlock(origin.below().north(2).west(3), outerBlock, 3);
            worldgenlevel.setBlock(origin.below().south(2).east(3), outerBlock, 3);
            worldgenlevel.setBlock(origin.below().south(2).west(3), outerBlock, 3);


            worldgenlevel.setBlock(origin.below(2).north(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(2).east(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(2).south(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(2).west(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(2).north().east(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(2).north().west(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(2).south().east(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(2).south().west(), outerBlock, 2);

            worldgenlevel.setBlock(origin.below(2).north(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(2).east(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(2).south(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(2).west(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(2).north(2).east(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(2).north(2).west(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(2).south(2).east(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(2).south(2).west(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(2).north().east(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(2).north().west(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(2).south().east(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(2).south().west(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(2).north(2).east(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(2).north(2).west(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(2).south(2).east(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(2).south(2).west(2), outerBlock, 2);


            worldgenlevel.setBlock(origin.below(3), innerblock, 2);

            worldgenlevel.setBlock(origin.below(3).north(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(3).east(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(3).south(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(3).west(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(3).north().east(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(3).north().west(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(3).south().east(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(3).south().west(), outerBlock, 2);

            worldgenlevel.setBlock(origin.below(3).north(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(3).east(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(3).south(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(3).west(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(3).north(2).east(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(3).north(2).west(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(3).south(2).east(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(3).south(2).west(), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(3).north().east(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(3).north().west(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(3).south().east(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(3).south().west(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(3).north(2).east(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(3).north(2).west(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(3).south(2).east(2), outerBlock, 2);
            worldgenlevel.setBlock(origin.below(3).south(2).west(2), outerBlock, 2);

            if (ventDepth > 10) {
                for (int y = 4; y <= (ventDepth / 2) + 4; y++) {
                    worldgenlevel.setBlock(origin.below(y), liquidblock, 2);

                    worldgenlevel.setBlock(origin.below(y).north(), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).east(), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).south(), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).west(), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).north().east(), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).north().west(), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).south().east(), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).south().west(), outerBlock, 2);

                    worldgenlevel.setBlock(origin.below(y).north(2), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).east(2), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).south(2), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).west(2), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).north(2).east(), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).north(2).west(), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).south(2).east(), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).south(2).west(), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).north().east(2), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).north().west(2), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).south().east(2), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).south().west(2), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).north(2).east(2), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).north(2).west(2), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).south(2).east(2), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).south(2).west(2), outerBlock, 2);
                }

                for (int y = (ventDepth / 2) + 5; y <= ventDepth + 4; y++) {
                    worldgenlevel.setBlock(origin.below(y), liquidblock, 2);

                    worldgenlevel.setBlock(origin.below(y).north(), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).east(), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).south(), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).west(), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).north().east(), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).north().west(), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).south().east(), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).south().west(), outerBlock, 2);

                    worldgenlevel.setBlock(origin.below(y).north(2), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).east(2), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).south(2), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).west(2), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).north(2).east(), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).north(2).west(), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).south(2).east(), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).south(2).west(), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).north().east(2), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).north().west(2), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).south().east(2), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).south().west(2), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).north(2).east(2), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).north(2).west(2), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).south(2).east(2), outerBlock, 2);
                    worldgenlevel.setBlock(origin.below(y).south(2).west(2), outerBlock, 2);
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
