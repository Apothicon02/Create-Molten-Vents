package com.Apothic0n.MoltenVents.core;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class OrestoneBlock extends Block implements EntityBlock
{
    public OrestoneBlock(Properties properties) {
        super(properties);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return blockEntityType == MoltenVentsRegistry.ORESTONE_BLOCK_ENTITY.get() ? OrestoneBlockEntity::tick : null;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return MoltenVentsRegistry.ORESTONE_BLOCK_ENTITY.get().create(blockPos, blockState);
    }
}