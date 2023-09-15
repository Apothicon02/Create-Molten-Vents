package com.Apothic0n.MoltenVents.api.biome.features.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class MoltenVentConfiguration implements FeatureConfiguration {
    public static final Codec<MoltenVentConfiguration> CODEC = RecordCodecBuilder.create((fields) -> {
        return fields.group(BlockStateProvider.CODEC.fieldOf("decorativeBlock").forGetter((v) -> {
            return v.decorativeBlock;
        }), BlockStateProvider.CODEC.fieldOf("outerBlock").forGetter((v) -> {
            return v.outerBlock;
        }), BlockStateProvider.CODEC.fieldOf("innerBlock").forGetter((v) -> {
            return v.innerBlock;
        }), BlockStateProvider.CODEC.fieldOf("liquidBlock").forGetter((v) -> {
            return v.liquidBlock;
        }), IntProvider.codec(10, 255).fieldOf("depth").forGetter((v) -> {
            return v.depth;
        }), Codec.BOOL.fieldOf("underwater").orElse(false).forGetter((v) -> {
            return v.underwater;
        })).apply(fields, MoltenVentConfiguration::new);
    });

    private final BlockStateProvider decorativeBlock;
    private final BlockStateProvider outerBlock;
    private final BlockStateProvider innerBlock;
    private final BlockStateProvider liquidBlock;
    private final IntProvider depth;
    public final Boolean underwater;

    public MoltenVentConfiguration(BlockStateProvider decorativeBlock, BlockStateProvider outerBlock, BlockStateProvider innerBlock, BlockStateProvider liquidBlock, IntProvider depth, Boolean underwater) {
        this.decorativeBlock = decorativeBlock;
        this.outerBlock = outerBlock;
        this.innerBlock = innerBlock;
        this.liquidBlock = liquidBlock;
        this.depth = depth;
        this.underwater = underwater;
    }

    public BlockStateProvider getDecorativeBlock() {
        return this.decorativeBlock;
    }
    public BlockStateProvider getOuterBlock() {
        return this.outerBlock;
    }
    public BlockStateProvider getInnerBlock() {
        return this.innerBlock;
    }
    public BlockStateProvider getLiquidBlock() {
        return this.liquidBlock;
    }
    public IntProvider getDepth() {return this.depth;}
}