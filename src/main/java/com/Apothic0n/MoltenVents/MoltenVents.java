package com.Apothic0n.MoltenVents;

import com.Apothic0n.MoltenVents.api.biome.features.MoltenVentsFeatures;
import com.Apothic0n.MoltenVents.config.Configs;
import com.Apothic0n.MoltenVents.core.objects.MoltenBlockEntities;
import com.Apothic0n.MoltenVents.core.objects.MoltenBlocks;
import com.Apothic0n.MoltenVents.core.objects.MoltenItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file.
@Mod("molten_vents")
public class MoltenVents
{
    public static final String MODID = "molten_vents";

    public MoltenVents() throws Exception {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);

        MoltenVentsJsonReader.main();
        MoltenBlocks.createCustomMoltenBlocks();
        MoltenBlocks.register(eventBus);
        MoltenBlockEntities.createCustomMoltenBlockEntitiess();
        MoltenBlockEntities.register(eventBus);
        MoltenItems.createCustomMoltenItems();
        MoltenItems.register(eventBus);
        Configs.register();
        MoltenVentsFeatures.register(eventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

        });
    }
}
