package com.Apothic0n.MoltenVents;

import com.Apothic0n.MoltenVents.api.biome.features.MoltenVentsFeatures;
import com.Apothic0n.MoltenVents.config.Configs;
import com.Apothic0n.MoltenVents.core.objects.MoltenBlockEntities;
import com.Apothic0n.MoltenVents.core.objects.MoltenBlocks;
import com.Apothic0n.MoltenVents.core.objects.MoltenItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file.
@Mod("molten_vents")
public class MoltenVents
{
    public static final String MODID = "molten_vents";

    public MoltenVents(IEventBus eventBus, ModContainer container) throws Exception {
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
}
