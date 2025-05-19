package com.Apothic0n.MoltenVents;

import com.Apothic0n.MoltenVents.core.objects.ActiveMoltenBlockEntity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.neoforged.fml.loading.FMLPaths;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MoltenVentsJsonReader {
    public static List<String> customBlocks;
    public static void main() throws Exception {
        final Path moltenVentsCustomBlocks = Path.of(FMLPaths.CONFIGDIR.get() + "/molten_vents/custom_blocks.json");
        Gson gson = new Gson();
        if (!Files.exists(moltenVentsCustomBlocks)) {
            Files.createDirectories(Path.of(FMLPaths.CONFIGDIR.get() + "/molten_vents"));
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

        for (String name : customBlocks) {
            createConductive(gson, Path.of(ActiveMoltenBlockEntity.configDir + "/conductive/" + name + ".json"), name);
            createConvertible(gson, Path.of(ActiveMoltenBlockEntity.configDir + "/convertible/" + name + ".json"));
        }
    }

    public static void createConductive(Gson gson, Path dir, String name) throws IOException {
        if (!Files.exists(dir)) {
            Files.createDirectories(Path.of(ActiveMoltenBlockEntity.configDir + "/conductive"));
            JsonWriter writer = new JsonWriter(new FileWriter(dir.toString()));
            JsonObject defaultData = gson.fromJson(
                    "{\n" +
                            "  \"values\": [\n" +
                            "    \"create:" + name + "\",\n" +
                            "    \"molten_vents:dormant_molten_" + name + "\",\n" +
                            "    \"molten_vents:active_molten_" + name + "\"\n" +
                            "  ]\n" +
                            "}",
                    JsonObject.class);
            gson.toJson(defaultData, writer);
            writer.close();
        }
    }

    public static void createConvertible(Gson gson, Path dir) throws IOException {
        if (!Files.exists(dir)) {
            Files.createDirectories(Path.of(ActiveMoltenBlockEntity.configDir + "/convertible"));
            JsonWriter writer = new JsonWriter(new FileWriter(dir.toString()));
            JsonObject defaultData = gson.fromJson(
                    "{\n" +
                            "  \"values\": [\n" +
                            "    \"minecraft:lava\"\n" +
                            "  ]\n" +
                            "}",
                    JsonObject.class);
            gson.toJson(defaultData, writer);
            writer.close();
        }
    }
}