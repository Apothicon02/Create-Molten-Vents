package com.Apothic0n.MoltenVents.core.objects;

import com.Apothic0n.MoltenVents.MoltenVents;
import com.Apothic0n.MoltenVents.MoltenVentsJsonReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class MoltenBlocks {
    private MoltenBlocks() {}

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MoltenVents.MODID);

    public static final List<Map<RegistryObject<Block>, RegistryObject<Block>>> moltenBlocks = new ArrayList<>(List.of());

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

    public static Map<RegistryObject<Block>, RegistryObject<Block>> createMoltenBlocks(String blockName) {
        return Map.of(
            BLOCKS.register("dormant_molten_" + blockName, () ->
                    new DormantMoltenBlock(BlockBehaviour.Properties.copy(Blocks.TUFF).sound(SoundType.TUFF))),

            BLOCKS.register("active_molten_" + blockName, () ->
                    new ActiveMoltenBlock(BlockBehaviour.Properties.copy(Blocks.TUFF).explosionResistance(1200).sound(SoundType.TUFF).lightLevel((brightness) -> {return 15;})))
        );
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}