package com.Apothic0n.MoltenVents.core;

import com.Apothic0n.MoltenVents.MoltenVents;
import com.google.gson.Gson;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MoltenVents.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {

    @SubscribeEvent
    public static void addReloadListener(AddReloadListenerEvent event) {
        event.addListener(new MoltenVentsConductiveData(new Gson()));
        event.addListener(new MoltenVentsConvertibleData(new Gson()));
    }
}
