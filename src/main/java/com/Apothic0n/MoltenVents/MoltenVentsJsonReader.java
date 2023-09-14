package com.Apothic0n.MoltenVents;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MoltenVentsJsonReader {
    public static List<String> customBlocks;
    public static void main() throws Exception {
        final Path moltenVentsCustomBlocks = Path.of(FMLPaths.MODSDIR.get().toString() + "/molten_vents_custom_blocks.json");
        Gson gson = new Gson();
        if (!Files.exists(moltenVentsCustomBlocks)) {
            JsonWriter writer = new JsonWriter(new FileWriter(moltenVentsCustomBlocks.toString()));
            JsonObject defaultData = gson.fromJson("{\"values\":[\"asurine\",\"veridium\",\"crimsite\",\"ochrum\",\"scorchia\",\"scoria\"]}", JsonObject.class);
            gson.toJson(defaultData, writer);
            writer.close();
        }
        JsonReader reader = new JsonReader(new FileReader(moltenVentsCustomBlocks.toString()));
        JsonObject data = gson.fromJson(reader, JsonObject.class);
        JsonArray customBlockNames = data.get("values").getAsJsonArray();
        List<String> tempCustomBlocks = new ArrayList<>(List.of());
        for (int i = 0; i < customBlockNames.size(); i++) {
            tempCustomBlocks.add(customBlockNames.get(i).getAsString());
        }
        customBlocks = tempCustomBlocks;
    }
}
