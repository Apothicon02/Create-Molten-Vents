package com.Apothic0n.MoltenVents.core.objects;

import com.Apothic0n.MoltenVents.MoltenVents;
import com.Apothic0n.MoltenVents.MoltenVentsJsonReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MoltenBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MoltenVents.MODID);

    public static final List<Map<RegistryObject<Block>, RegistryObject<BlockEntityType<?>>>> moltenBlockEntities =  new ArrayList<>(List.of());

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

    public static Map<RegistryObject<Block>, RegistryObject<BlockEntityType<?>>> createMoltenBlockEntities(RegistryObject block) {
        RegistryObject activeMoltenBlock = null;
        for (int i = 0; i < MoltenBlocks.moltenBlocks.size(); i++) {
            RegistryObject activeBlock = MoltenBlocks.moltenBlocks.get(i).get(block);
            if (activeBlock != null) {
                activeMoltenBlock = activeBlock;
            }
        }
        RegistryObject<BlockEntityType<?>> blockEntityType = null;
        RegistryObject finalActiveMoltenBlock = activeMoltenBlock;
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
