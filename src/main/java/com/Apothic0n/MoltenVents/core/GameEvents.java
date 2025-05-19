package com.Apothic0n.MoltenVents.core;

import com.Apothic0n.MoltenVents.MoltenVents;
import com.Apothic0n.MoltenVents.core.objects.ActiveMoltenBlockEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

@EventBusSubscriber(modid = MoltenVents.MODID, bus = EventBusSubscriber.Bus.GAME)
public class GameEvents {
    public static int ticks = 0;

    @SubscribeEvent
    public static void serverTick(ServerTickEvent.Post event) {
        ticks++;
        if (ticks > 200) { //every 10 seconds
            ActiveMoltenBlockEntity.reload();
            ticks = 0;
        }
    }
}