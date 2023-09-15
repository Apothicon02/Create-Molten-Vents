package com.Apothic0n.MoltenVents.core.objects;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

import static com.Apothic0n.MoltenVents.core.objects.MoltenBlockEntities.getMoltenBlockEntities;

public class ActiveMoltenBlock extends Block implements EntityBlock {
    public ActiveMoltenBlock(Properties properties) {
        super(properties);
    }


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        List<Map<Block, BlockEntityType<?>>> moltenBlockEntities = getMoltenBlockEntities();
        BlockEntityType<?> activeMoltenBlockEntity = null;
        for (int i = 0; i < moltenBlockEntities.size(); i++) {
            BlockEntityType<?> activeBlockEntity = moltenBlockEntities.get(i).get(this.asBlock());
            if (activeBlockEntity != null) {
                activeMoltenBlockEntity = activeBlockEntity;
            }
        }
        return blockEntityType == activeMoltenBlockEntity ? ActiveMoltenBlockEntity::tick : null;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        List<Map<Block, BlockEntityType<?>>> moltenBlockEntities = getMoltenBlockEntities();
        BlockEntityType<?> activeMoltenBlockEntity = null;
        for (int i = 0; i < moltenBlockEntities.size(); i++) {
            BlockEntityType<?> activeBlockEntity = moltenBlockEntities.get(i).get(this.asBlock());
            if (activeBlockEntity != null) {
                activeMoltenBlockEntity = activeBlockEntity;
            }
        }
        return activeMoltenBlockEntity.create(blockPos, blockState);
    }
}