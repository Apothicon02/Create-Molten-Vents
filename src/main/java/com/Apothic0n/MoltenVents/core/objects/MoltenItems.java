package com.Apothic0n.MoltenVents.core.objects;

import com.Apothic0n.MoltenVents.MoltenVents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class MoltenItems extends Items {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MoltenVents.MODID);

    public static final List<RegistryObject<Item>> moltenItems = new ArrayList<>(List.of());
    public static void createCustomMoltenItems() {
        for (int i = 0; i < MoltenBlocks.moltenBlocks.size(); i++) {
            int finalI = i;
            RegistryObject<Block> dormantBlock = MoltenBlocks.moltenBlocks.get(finalI).keySet().iterator().next();
            RegistryObject<Block> activeBlock = MoltenBlocks.moltenBlocks.get(finalI).values().iterator().next();
            moltenItems.add(ITEMS.register(dormantBlock.getKey().location().getPath(), () ->
                    new BlockItem(dormantBlock.get(), new Item.Properties())));
            moltenItems.add(ITEMS.register(activeBlock.getKey().location().getPath(), () ->
                    new BlockItem(activeBlock.get(), new Item.Properties())));
        }
    }

    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
}
