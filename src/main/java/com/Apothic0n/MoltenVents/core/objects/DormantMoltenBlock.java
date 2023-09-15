package com.Apothic0n.MoltenVents.core.objects;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DormantMoltenBlock extends Block {
    public DormantMoltenBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public void wasExploded(Level level, BlockPos blockPos, Explosion explosion) {
        List<Map<Block, Block>> moltenBlocks = MoltenBlocks.getMoltenBlocks();
        if (!level.isClientSide) {
            for (int i = 0; i < MoltenBlocks.moltenBlocks.size(); i++) {
                Block activeBlock = moltenBlocks.get(i).get(this.asBlock());
                if (activeBlock != null) {
                    level.setBlock(blockPos, activeBlock.defaultBlockState(), UPDATE_ALL);
                }
            }
        }
    }
}