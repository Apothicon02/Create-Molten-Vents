package com.Apothic0n.MoltenVents;

import com.Apothic0n.MoltenVents.config.Configs;
import com.Apothic0n.MoltenVents.core.MoltenVentsRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file.
@Mod("molten_vents")
public class MoltenVents
{
    public static final String MODID = "molten_vents";

    public MoltenVents()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);

        Configs.register();
        MoltenVentsRegistry.register(eventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            for (MoltenVentsRegistry.OreStone value : MoltenVentsRegistry.OreStone.values())
                value.registerFeatures();
        });
    }
}
