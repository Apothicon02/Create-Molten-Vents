package com.Apothic0n.MoltenVents.core;

import com.Apothic0n.MoltenVents.MoltenVents;
import com.Apothic0n.MoltenVents.core.objects.MoltenItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber(modid = MoltenVents.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEvents {
    @SubscribeEvent
    public static void addItemsToTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.OP_BLOCKS)) {
            for (int i = 0; i < MoltenItems.moltenItems.size(); i++) {
                event.accept(MoltenItems.moltenItems.get(i).get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            }
        }
    }
}
