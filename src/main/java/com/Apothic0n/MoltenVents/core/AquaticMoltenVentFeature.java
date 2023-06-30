package com.Apothic0n.MoltenVents.core;

import com.Apothic0n.MoltenVents.config.CommonConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

import java.util.List;

import static java.util.Arrays.asList;

public class AquaticMoltenVentFeature extends Feature<SimpleBlockConfiguration> {
    public AquaticMoltenVentFeature(Codec<SimpleBlockConfiguration> pContext) {
        super(pContext);
    }

    public boolean place(FeaturePlaceContext<SimpleBlockConfiguration> pContext) {
        BlockState outerBlock = pContext.config().toPlace().getState(pContext.random(), pContext.origin());
        BlockState innerblock = MoltenVentsRegistry.OreStone.FromBlock(outerBlock.getBlock()).dormant.get().defaultBlockState();
        BlockState liquidblock = Blocks.WATER.defaultBlockState();

        if (!shouldGenerate(pContext))
            return false;

        var origin = pContext.origin();

        generateVent(origin.above(), pContext.level(), outerBlock, innerblock, liquidblock, getVentDepth(), pContext.random());
        return true;
    }

    public boolean shouldGenerate(FeaturePlaceContext<SimpleBlockConfiguration> pContext) {
        boolean valueToReturn = false;
        if (CommonConfig.generateUnderwater.get()) {
            BlockPos origin = pContext.origin();
            if (pContext.level().getBlockState(origin.above(4).north(5)).is(Blocks.WATER) && //Ensure it generates underwater
                    pContext.level().getBlockState(origin.above(4).east(5)).is(Blocks.WATER) &&
                    pContext.level().getBlockState(origin.above(4).south(5)).is(Blocks.WATER) &&
                    pContext.level().getBlockState(origin.above(4).west(5)).is(Blocks.WATER)) {
                valueToReturn = true;
            }
        }
        return valueToReturn;
    }

    public int getVentDepth() {
        return CommonConfig.ventDepth.get();
    }

    public static List<List<Integer>> ventRingUpperFormat = asList(
            asList(0, 1, 1, 1, 1, 1, 0),
            asList(1, 1, 0, 0, 0, 1, 1),
            asList(1, 0, 0, 0, 0, 0, 1),
            asList(1, 0, 0, 0, 0, 0, 1),
            asList(1, 0, 0, 0, 0, 0, 1),
            asList(1, 1, 0, 0, 0, 1, 1),
            asList(0, 1, 1, 1, 1, 1, 0)
    );

    public static List<List<Integer>> ventRingLowerFormat = asList(
            asList(0, 0, 1, 0, 1, 0, 1, 0, 0),
            asList(1, 0, 1, 1, 1, 0, 1, 0, 1),
            asList(0, 1, 1, 2, 2, 2, 1, 1, 1),
            asList(0, 0, 2, 0, 0, 0, 2, 0, 0),
            asList(1, 1, 2, 0, 0, 0, 2, 1, 0),
            asList(0, 1, 2, 0, 0, 0, 2, 0, 0),
            asList(1, 1, 1, 2, 2, 2, 1, 1, 1),
            asList(1, 0, 1, 1, 0, 0, 1, 1, 0),
            asList(0, 0, 1, 1, 0, 0, 1, 0, 0)
    );

    public static List<List<Integer>> pillarFormat = asList(
            asList(0, 1, 1, 1, 0),
            asList(1, 1, 1, 1, 1),
            asList(1, 1, 0, 1, 1),
            asList(1, 1, 1, 1, 1),
            asList(0, 1, 1, 1, 0)
    );

    public static void generateVent(BlockPos origin, WorldGenLevel worldgenlevel, BlockState outerBlock, BlockState innerblock, BlockState liquidblock, Integer ventDepth, RandomSource random)
    {
        var wallName = new ResourceLocation("create", "cut_" + MoltenVentsRegistry.OreStone.FromBlock(outerBlock.getBlock()).name + "_wall");
        var wall = Registry.BLOCK.get(wallName).defaultBlockState();

        pasteFormat(ventRingUpperFormat, origin.above(), worldgenlevel, CommonConfig.generateUnderwater.get() ? outerBlock : wall, random, 20);
        pasteFormat(ventRingUpperFormat, origin, worldgenlevel, outerBlock, random, 100);
        pasteFormat(ventRingLowerFormat, origin.below(1), worldgenlevel, CommonConfig.fillWithLava.get() ? Blocks.BLACKSTONE.defaultBlockState() : outerBlock, random, 80);
        pasteFormat(ventRingLowerFormat, origin.below(2), worldgenlevel, CommonConfig.fillWithLava.get() ? Blocks.BLACKSTONE.defaultBlockState() : outerBlock, random, 60);

        pasteFormat(pillarFormat, origin.below(3), worldgenlevel, outerBlock, random, 80);

        if (!CommonConfig.generateUnderwater.get() && CommonConfig.fillWithLava.get())
        {
            for (int z = -1; z <= 1; z++)
                for (int x = -1; x <= 1; x++)
                {
                    worldgenlevel.setBlock(origin.below(2).relative(Direction.Axis.X, x).relative(Direction.Axis.Z, z), Blocks.BLACKSTONE.defaultBlockState(), 2);
                    worldgenlevel.setBlock(origin.below(1).relative(Direction.Axis.X, x).relative(Direction.Axis.Z, z), Blocks.LAVA.defaultBlockState(), 2);
                }

            // place air over lava
            pasteFormat(pillarFormat, origin, worldgenlevel, Blocks.AIR.defaultBlockState(), random, 80);
            pasteFormat(pillarFormat, origin.above(), worldgenlevel, Blocks.AIR.defaultBlockState(), random, 80);
        }

        // try to spread random orestones and other decorations in the area
        for (int y = -2; y <= 1; y++)
            for (int z = -6; z <= 6; z++)
                for (int x = -6; x <= 6; x++)
                {
                    if (CommonConfig.generateUnderwater.get())
                        continue;

                    if (Mth.abs(z) < 2 && Mth.abs(x) < 2)
                        continue;

                    var offset = origin.relative(Direction.Axis.Y, y).relative(Direction.Axis.X, x).relative(Direction.Axis.Z, z);
                    var blockState = worldgenlevel.getBlockState(offset);
                    if (blockState.is(Blocks.AIR) || blockState.is(BlockTags.LEAVES) || blockState.is(BlockTags.REPLACEABLE_PLANTS) || blockState.is(BlockTags.LOGS) || blockState.is(BlockTags.FEATURES_CANNOT_REPLACE))
                        continue;

                    if (random.nextInt(0, 100) > 20)
                    {
                        if (random.nextInt(0, 100) > 70)
                            worldgenlevel.setBlock(offset, CommonConfig.fillWithLava.get() ? Blocks.BLACKSTONE.defaultBlockState() : Blocks.ROOTED_DIRT.defaultBlockState(), 2);

                        continue;
                    }

                    worldgenlevel.setBlock(offset, outerBlock, 2);

                    // try to place dripstone or a wall on top
                    if (!worldgenlevel.getBlockState(offset.above()).is(Blocks.AIR))
                        continue;

                    if (random.nextInt(0, 100) > 50) {
                        if (random.nextInt(0, 100) > 50) {
                            worldgenlevel.setBlock(offset.above(), Blocks.POINTED_DRIPSTONE.defaultBlockState().setValue(PointedDripstoneBlock.THICKNESS, DripstoneThickness.FRUSTUM), 2);
                            worldgenlevel.setBlock(offset.above(2), Blocks.POINTED_DRIPSTONE.defaultBlockState(), 2);
                        }
                        else {
                            worldgenlevel.setBlock(offset.above(), Blocks.POINTED_DRIPSTONE.defaultBlockState(), 2);
                        }
                        continue;
                    }

                    if (random.nextInt(0, 100) > 25) {
                        // check to make sure two walls never generate next to each other
                        if (!worldgenlevel.getBlockState(offset.above().east()).is(wall.getBlock()) && !worldgenlevel.getBlockState(offset.above().west()).is(wall.getBlock()) && !worldgenlevel.getBlockState(offset.above().north()).is(wall.getBlock()) && !worldgenlevel.getBlockState(offset.above().south()).is(wall.getBlock()))
                        {
                            worldgenlevel.setBlock(offset.above(), wall, 2);
                        }
                        continue;
                    }
                }

        // set inner orestone block entity
        worldgenlevel.setBlock(origin.below(2), innerblock, 2);

        if (ventDepth < 10)
            return;

        // generate pillars
        for (int y = 4; y <= (ventDepth / 2) + 4; y++)
        {
            worldgenlevel.setBlock(origin.below(y), liquidblock, 2);
            pasteFormat(pillarFormat, origin.below(y), worldgenlevel, outerBlock, random, 100);
        }

        for (int y = (ventDepth / 2) + 5; y <= ventDepth + 4; y++)
        {
            worldgenlevel.setBlock(origin.below(y), liquidblock, 2);
            pasteFormat(pillarFormat, origin.below(y), worldgenlevel, outerBlock, random, 100);
        }
    }


    private static void pasteFormat(List<List<Integer>> format, BlockPos origin, WorldGenLevel worldgenlevel, BlockState outerBlock, RandomSource random, int chance)
    {
        var offset = (format.size() - 1) / 2;
        var formatStart = origin.north(offset).west(offset);

        for (int i = 0; i < format.size(); i++)
        {
            List<Integer> list = format.get(i);
            for (int j = 0; j < list.size(); j++)
            {
                Integer item = list.get(j);
                if (item == 0)
                    continue;

                if (chance != 100 && item == 1 && random.nextInt(0, 100) > chance)
                    continue;

                worldgenlevel.setBlock(formatStart.south(i).east(j), outerBlock, 3);
            }
        }
    }
}
