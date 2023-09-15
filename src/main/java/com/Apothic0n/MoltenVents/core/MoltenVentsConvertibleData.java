package com.Apothic0n.MoltenVents.core;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class MoltenVentsConvertibleData extends SimpleJsonResourceReloadListener {

    public MoltenVentsConvertibleData(Gson gson) {
        super(gson, "molten_vents/blocks/convertible");
    }

    public static Map<ResourceLocation, JsonElement> convertibleBlocksMap;
    @Override
    protected void apply(Map<ResourceLocation, JsonElement> map, @NotNull ResourceManager resourceManager, @NotNull ProfilerFiller profilerFiller) {
        convertibleBlocksMap = map;
    }
}
