package com.Apothic0n.MoltenVents.core;

import com.Apothic0n.MoltenVents.config.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.injection.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.minecraft.world.level.block.Block.UPDATE_ALL;

public class OrestoneBlockEntity extends BlockEntity
{
    public static int spreadDistance = 5; //Max 5
    private static final RandomSource random = RandomSource.create();

    private static final Map<BlockPos, Long> lastTick = new HashMap<>();

    public OrestoneBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MoltenVentsRegistry.ORESTONE_BLOCK_ENTITY.get(), blockPos, blockState);

    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t) {
        if (level.isClientSide || level.getServer().getLevel(level.dimension()) == null)
            return;

        if (isDormant(blockState))
            dormantTick(level, blockPos, blockState);
        else
            activeTick(level, blockPos, blockState);
    }

    private static boolean isDormant(BlockState blockState)
    {
        var key = Registry.BLOCK.getKey(blockState.getBlock());
        var path = key.getPath();
        return path.startsWith("dormant");
    }

    private static Block GetBaseBlock(BlockState blockState)
    {
        return MoltenVentsRegistry.OreStone.FromBlock(blockState.getBlock()).GetCreateBlock();
    }

    public static <T extends BlockEntity> void dormantTick(Level level, BlockPos blockPos, BlockState blockState) {
        if (!level.getBlockState(blockPos.above()).is(Blocks.TNT))
            return;

        level.setBlock(blockPos.above(), Blocks.AIR.defaultBlockState(), UPDATE_ALL);

        PrimedTnt primedtnt = new PrimedTnt(level, (double) blockPos.above().getX() + 0.5D, (double) blockPos.above().getY(), (double) blockPos.above().getZ() + 0.5D, null);
        level.addFreshEntity(primedtnt);
        level.playSound(null, primedtnt.getX(), primedtnt.getY(), primedtnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
        level.setBlock(blockPos, MoltenVentsRegistry.getActiveOrestoneFor(blockState.getBlock()).defaultBlockState(), UPDATE_ALL);
    }

    public static <T extends BlockEntity> void activeTick(Level level, BlockPos blockPos, BlockState blockState)
    {
        if (level.isClientSide)
            return;

        if (!CommonConfig.useSource.get())
        {
            var last = lastTick.getOrDefault(blockPos, 0L);

            if (level.getGameTime() - last < CommonConfig.flowingConversionSpeed.get().intValue())
                return;

            lastTick.put(blockPos, level.getGameTime());
        }


        for (int y = -1; y <= 1; y++)
        for (int z = -1; z <= 1; z++)
        for (int x = -1; x <= 1; x++)
        {
            if (z == 0 && y == 0 && x == 0)
                continue;

            replaceLava(level, blockPos.relative(Direction.Axis.Y, y).relative(Direction.Axis.X, x).relative(Direction.Axis.Z, z), blockState);
        }
    }

    private static void replaceLava(Level level, BlockPos relative, BlockState baseBlockState)
    {
        var blockState = level.getBlockState(relative);
        if (!blockState.is(MoltenVentsRegistry.RequiredLiquid.get()))
            return;

        if (CommonConfig.useSource.get() && !blockState.getFluidState().isSource())
            return;

        var baseBlock = GetBaseBlock(baseBlockState);
        level.setBlock(relative, baseBlock.defaultBlockState(), UPDATE_ALL);
        level.playSound(null, relative, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 1.0f, 1.0f);
        level.getServer().getLevel(level.dimension()).sendParticles(ParticleTypes.LARGE_SMOKE, (double)relative.getX() + 0.5D, (double) relative.getY() + 0.25D, (double) relative.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D);
    }
}