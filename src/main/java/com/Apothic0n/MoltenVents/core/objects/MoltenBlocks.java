package com.Apothic0n.MoltenVents.core.objects;

import com.Apothic0n.MoltenVents.MoltenVents;
import com.Apothic0n.MoltenVents.MoltenVentsJsonReader;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public final class MoltenBlocks {
    private MoltenBlocks() {}

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, MoltenVents.MODID);

    public static final Supplier<Block> Asurine = () -> BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("create", "asurine"));
    public static final Supplier<Block> Veridium = () -> BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("create", "veridium"));
    public static final Supplier<Block> Crimsite = () -> BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("create", "crimsite"));
    public static final Supplier<Block> Ochrum = () -> BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("create", "ochrum"));
    public static final Supplier<Block> Scorchia = () -> BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("create", "scorchia"));
    public static final Supplier<Block> Scoria = () -> BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("create", "scoria"));

    public static final List<Map<DeferredHolder<Block, Block>, DeferredHolder<Block, Block>>> moltenBlocks = new ArrayList<>(List.of());

    public static List<Map<Block, Block>> getMoltenBlocks() {
        List<Map<Block, Block>> moltenBlocksBlocks = new ArrayList<>(List.of());
        for (int i = 0; i < moltenBlocks.size(); i++) {
            moltenBlocksBlocks.add(Map.of(moltenBlocks.get(i).keySet().iterator().next().get(), moltenBlocks.get(i).values().iterator().next().get()));
        }
        return moltenBlocksBlocks;
    }

    public static void createCustomMoltenBlocks() {
        for (int i = 0; i < MoltenVentsJsonReader.customBlocks.size(); i++) {
            moltenBlocks.add(createMoltenBlocks(MoltenVentsJsonReader.customBlocks.get(i)));
        }
    }

    public static Map<DeferredHolder<Block, Block>, DeferredHolder<Block, Block>> createMoltenBlocks(String blockName) {
        return Map.of(
            BLOCKS.register("dormant_molten_" + blockName, () ->
                    new DormantMoltenBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF).sound(SoundType.TUFF).pushReaction(PushReaction.BLOCK))),

            BLOCKS.register("active_molten_" + blockName, () ->
                    new ActiveMoltenBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF).explosionResistance(1200).sound(SoundType.TUFF).lightLevel((brightness) -> {return 15;})))
        );
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}