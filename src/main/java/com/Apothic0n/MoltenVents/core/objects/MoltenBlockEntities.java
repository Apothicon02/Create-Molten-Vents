package com.Apothic0n.MoltenVents.core.objects;

import com.Apothic0n.MoltenVents.MoltenVents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MoltenBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MoltenVents.MODID);

    public static final List<Map<DeferredHolder<Block, Block>, DeferredHolder<BlockEntityType<?>, BlockEntityType<?>>>> moltenBlockEntities =  new ArrayList<>(List.of());

    public static List<Map<Block, BlockEntityType<?>>> getMoltenBlockEntities() {
        List<Map<Block, BlockEntityType<?>>> moltenBlocksBlockEntities = new ArrayList<>(List.of());
        for (int i = 0; i < moltenBlockEntities.size(); i++) {
            moltenBlocksBlockEntities.add(Map.of(moltenBlockEntities.get(i).keySet().iterator().next().get(), moltenBlockEntities.get(i).values().iterator().next().get()));
        }
        return moltenBlocksBlockEntities;
    }

    public static void createCustomMoltenBlockEntitiess() {
        for (int i = 0; i < MoltenBlocks.moltenBlocks.size(); i++) {
            moltenBlockEntities.add(createMoltenBlockEntities(MoltenBlocks.moltenBlocks.get(i).entrySet().iterator().next().getKey()));
        }
    }

    public static Map<DeferredHolder<Block, Block>, DeferredHolder<BlockEntityType<?>, BlockEntityType<?>>> createMoltenBlockEntities(DeferredHolder block) {
        DeferredHolder activeMoltenBlock = null;
        for (int i = 0; i < MoltenBlocks.moltenBlocks.size(); i++) {
            DeferredHolder activeBlock = MoltenBlocks.moltenBlocks.get(i).get(block);
            if (activeBlock != null) {
                activeMoltenBlock = activeBlock;
            }
        }
        DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> blockEntityType = null;
        DeferredHolder finalActiveMoltenBlock = activeMoltenBlock;
        if (finalActiveMoltenBlock != null) {
            blockEntityType = BLOCK_ENTITIES.register(activeMoltenBlock.getKey().location().getPath() + "_block_entity", () ->
                    BlockEntityType.Builder.of(ActiveMoltenBlockEntity::new, (Block) finalActiveMoltenBlock.get()).build(null));
        }
        return Map.of(activeMoltenBlock, blockEntityType);
    }
    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
