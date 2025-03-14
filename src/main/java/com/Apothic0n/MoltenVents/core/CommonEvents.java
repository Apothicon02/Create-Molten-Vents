package com.Apothic0n.MoltenVents.core;

import com.Apothic0n.MoltenVents.MoltenVents;
import com.google.gson.Gson;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;

@EventBusSubscriber(modid = MoltenVents.MODID, bus = EventBusSubscriber.Bus.GAME)
public class CommonEvents {

    @SubscribeEvent
    public static void addReloadListener(AddReloadListenerEvent event) {
        event.addListener(new MoltenVentsConductiveData(new Gson()));
        event.addListener(new MoltenVentsConvertibleData(new Gson()));
    }
}
