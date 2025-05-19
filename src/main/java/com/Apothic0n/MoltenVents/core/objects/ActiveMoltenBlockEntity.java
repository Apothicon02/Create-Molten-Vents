package com.Apothic0n.MoltenVents.core.objects;

import com.Apothic0n.MoltenVents.MoltenVentsJsonReader;
import com.Apothic0n.MoltenVents.config.CommonConfig;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.fml.loading.FMLPaths;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static com.Apothic0n.MoltenVents.core.objects.MoltenBlockEntities.getMoltenBlockEntities;
import static net.minecraft.world.level.block.Block.UPDATE_ALL;

public class ActiveMoltenBlockEntity extends BlockEntity {
    public ActiveMoltenBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(activeMoltenBlockEntity(blockState), blockPos, blockState);
    }

    public static String configDir = FMLPaths.CONFIGDIR.get() + "/molten_vents";
    private static List<String> readBlocks = new ArrayList<>(List.of());
    private static Map<String, List<String>> conductiveMap = new HashMap<>(Map.of());
    private static Map<String, List<String>> convertibleMap = new HashMap<>(Map.of());
    private static BlockEntityType<?> activeMoltenBlockEntity(BlockState blockState) {
        List<Map<Block, BlockEntityType<?>>> moltenBlockEntities = getMoltenBlockEntities();
        BlockEntityType<?> activeMoltenBlockEntity = null;
        for (int i = 0; i < moltenBlockEntities.size(); i++) {
            BlockEntityType<?> activeBlockEntity = moltenBlockEntities.get(i).get(blockState.getBlock());
            if (activeBlockEntity != null) {
                activeMoltenBlockEntity = activeBlockEntity;
            }
        }
        return(activeMoltenBlockEntity);
    }

    public static void reload() {
        readBlocks = new ArrayList<>(List.of());
        conductiveMap = new HashMap<>(Map.of());
        convertibleMap = new HashMap<>(Map.of());
    }

    private static Map<Integer, List<Block>> getLists(BlockState blockState) throws IOException {
        String name = blockState.getBlock().builtInRegistryHolder().key().location().getPath().substring(14);
        if (!readBlocks.contains(name)) {
            readBlocks.add(name);
            Gson gson = new Gson();
            Path conductive = Path.of(configDir + "/conductive/" + name + ".json");
            Path convertible = Path.of(configDir + "/convertible/" + name + ".json");
            MoltenVentsJsonReader.createConductive(gson, conductive, name);

            JsonReader reader = new JsonReader(new FileReader(conductive.toString()));
            JsonObject data = gson.fromJson(reader, JsonObject.class);
            JsonArray conductiveBlockNames = data.get("values").getAsJsonArray();
            List<String> tempConductiveBlocks = new ArrayList<>(List.of());
            for (int i = 0; i < conductiveBlockNames.size(); i++) {
                tempConductiveBlocks.add(conductiveBlockNames.get(i).getAsString());
            }
            conductiveMap.put(name, tempConductiveBlocks);

            MoltenVentsJsonReader.createConvertible(gson, convertible);

            reader = new JsonReader(new FileReader(convertible.toString()));
            data = gson.fromJson(reader, JsonObject.class);
            JsonArray convertibleBlockNames = data.get("values").getAsJsonArray();
            List<String> tempConvertibleBlocks = new ArrayList<>(List.of());
            for (int i = 0; i < convertibleBlockNames.size(); i++) {
                tempConvertibleBlocks.add(convertibleBlockNames.get(i).getAsString());
            }
            convertibleMap.put(name, tempConvertibleBlocks);
        }
        List<Block> conductiveBlocks = new ArrayList<>(List.of());
        List<Block> convertibleBlocks = new ArrayList<>(List.of());
        List<String> conductiveList = conductiveMap.get(name);
        for (String blockName : conductiveList) {
            conductiveBlocks.add(BuiltInRegistries.BLOCK.get(ResourceLocation.parse(blockName)));
        }
        List<String> convertibleList = convertibleMap.get(name);
        for (String blockName : convertibleList) {
            convertibleBlocks.add(BuiltInRegistries.BLOCK.get(ResourceLocation.parse(blockName)));
        }
        return (Map.of(1, conductiveBlocks, 2, convertibleBlocks));
    }

    public static int spreadDistance = 5; //Max 5

    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t) {
        if (!level.isClientSide) {
            ServerLevel serverLevel = Objects.requireNonNull(level.getServer()).getLevel(level.dimension());
            Map<Integer, List<Block>> lists = null;
            try {
                lists = getLists(blockState);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            List<Block> conductiveBlocks = lists.get(1);
            List<Block> convertibleBlocks = lists.get(2);
            spreadBlock(conductiveBlocks, convertibleBlocks, blockPos.above(), serverLevel);
            spreadBlock(conductiveBlocks, convertibleBlocks, blockPos.below(), serverLevel);
            spreadBlock(conductiveBlocks, convertibleBlocks, blockPos.north(), serverLevel);
            spreadBlock(conductiveBlocks, convertibleBlocks, blockPos.east(), serverLevel);
            spreadBlock(conductiveBlocks, convertibleBlocks, blockPos.south(), serverLevel);
            spreadBlock(conductiveBlocks, convertibleBlocks, blockPos.west(), serverLevel);
        }
    }

    private static void spreadBlock(List<Block> conductiveBlocks, List<Block> convertibleBlocks, BlockPos pos, WorldGenLevel level) {
        BlockState[] contacts = {level.getBlockState(pos.above()), level.getBlockState(pos.below()), level.getBlockState(pos.north()), level.getBlockState(pos.east()), level.getBlockState(pos.south()), level.getBlockState(pos.west())};
        Boolean isTouchingOrestone = false;
        for (int i = 0; i < contacts.length && !isTouchingOrestone; ++i) { //Check each non-diagonal neighbor to see if it is the same orestone
            if (conductiveBlocks.contains(contacts[i].getBlock())) {
                isTouchingOrestone = true;
            }
        }
        if (isTouchingOrestone && !level.getBlockState(pos).liquid() && convertibleBlocks.contains(level.getBlockState(pos).getBlock()) || isTouchingOrestone && !CommonConfig.useSource.get() && convertibleBlocks.contains(level.getBlockState(pos).getBlock()) || isTouchingOrestone && CommonConfig.useSource.get() && convertibleBlocks.contains(level.getBlockState(pos).getBlock()) && level.getBlockState(pos).getFluidState().isSource() || isTouchingOrestone && conductiveBlocks.contains(level.getBlockState(pos).getBlock())) {
            if (!level.getBlockState(pos).liquid() && convertibleBlocks.contains(level.getBlockState(pos).getBlock()) || !CommonConfig.useSource.get() && convertibleBlocks.contains(level.getBlockState(pos).getBlock()) || CommonConfig.useSource.get() && convertibleBlocks.contains(level.getBlockState(pos).getBlock()) && level.getBlockState(pos).getFluidState().isSource()) {
                level.setBlock(pos, conductiveBlocks.get(0).defaultBlockState(), UPDATE_ALL);
                level.playSound(null, pos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 1.0f, 1.0f);
                level.getServer().getLevel(level.getLevel().dimension()).sendParticles(ParticleTypes.LARGE_SMOKE, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.25D, (double)pos.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D);
            }
            List<BlockPos> convertedBlocks = convertTouching(convertibleBlocks, conductiveBlocks, pos, level);
            if (convertedBlocks != null && !convertedBlocks.isEmpty() && spreadDistance >= 1) {
                List<BlockPos> secondaryConvertedBlocks = new ArrayList<>(List.of());
                for (int i = 0; i < convertedBlocks.size(); i++) {
                    List<BlockPos> minorConvertedBlocks = convertTouching(convertibleBlocks, conductiveBlocks, convertedBlocks.get(i), level);
                    if (minorConvertedBlocks != null && minorConvertedBlocks.isEmpty()) {
                        secondaryConvertedBlocks.addAll(minorConvertedBlocks);
                    }
                }
                if (secondaryConvertedBlocks != null && !secondaryConvertedBlocks.isEmpty() && spreadDistance >= 2) {
                    List<BlockPos> teritaryConvertedBlocks = new ArrayList<>(List.of());
                    for (int i = 0; i < secondaryConvertedBlocks.size(); i++) {
                        List<BlockPos> minorConvertedBlocks = convertTouching(convertibleBlocks, conductiveBlocks, secondaryConvertedBlocks.get(i), level);
                        if (minorConvertedBlocks != null && minorConvertedBlocks.isEmpty()) {
                            teritaryConvertedBlocks.addAll(minorConvertedBlocks);
                        }
                    }
                    if (teritaryConvertedBlocks != null && !teritaryConvertedBlocks.isEmpty() && spreadDistance >= 3) {
                        List<BlockPos> quaternaryConvertedBlocks = new ArrayList<>(List.of());
                        for (int i = 0; i < teritaryConvertedBlocks.size(); i++) {
                            List<BlockPos> minorConvertedBlocks = convertTouching(convertibleBlocks, conductiveBlocks, teritaryConvertedBlocks.get(i), level);
                            if (minorConvertedBlocks != null && minorConvertedBlocks.isEmpty()) {
                                quaternaryConvertedBlocks.addAll(minorConvertedBlocks);
                            }
                        }
                        if (quaternaryConvertedBlocks != null && !quaternaryConvertedBlocks.isEmpty() && spreadDistance >= 4) {
                            List<BlockPos> quinaryConvertedBlocks = new ArrayList<>(List.of());
                            for (int i = 0; i < quaternaryConvertedBlocks.size(); i++) {
                                List<BlockPos> minorConvertedBlocks = convertTouching(convertibleBlocks, conductiveBlocks, quaternaryConvertedBlocks.get(i), level);
                                if (minorConvertedBlocks != null && minorConvertedBlocks.isEmpty()) {
                                    quinaryConvertedBlocks.addAll(minorConvertedBlocks);
                                }
                            }
                            if (quinaryConvertedBlocks != null && !quinaryConvertedBlocks.isEmpty() && spreadDistance >= 5) {
                                List<BlockPos> senaryConvertedBlocks = new ArrayList<>(List.of());
                                for (int i = 0; i < quinaryConvertedBlocks.size(); i++) {
                                    List<BlockPos> minorConvertedBlocks = convertTouching(convertibleBlocks, conductiveBlocks, quinaryConvertedBlocks.get(i), level);
                                    if (minorConvertedBlocks != null && minorConvertedBlocks.isEmpty()) {
                                        senaryConvertedBlocks.addAll(minorConvertedBlocks);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static List<BlockPos> convertTouching(List<Block> convertibleBlocks, List<Block> conductiveBlocks, BlockPos pos, WorldGenLevel level) {
        List<BlockPos> lavaBlocks = getLavaTouching(convertibleBlocks, pos, level);
        List<BlockPos> touchingBlocks = getLavaTouching(conductiveBlocks, pos, level);
        if (lavaBlocks != null && !lavaBlocks.isEmpty()) {
            for (int i = 0; i < lavaBlocks.size(); ++i) {
                BlockPos lavaPos = lavaBlocks.get(i);
                if (!level.getBlockState(lavaPos).liquid() && convertibleBlocks.contains(level.getBlockState(lavaPos).getBlock()) || !CommonConfig.useSource.get() && convertibleBlocks.contains(level.getBlockState(lavaPos).getBlock()) || CommonConfig.useSource.get() && convertibleBlocks.contains(level.getBlockState(lavaPos).getBlock()) && level.getBlockState(lavaPos).getFluidState().isSource()) {
                    level.setBlock(lavaPos, conductiveBlocks.get(0).defaultBlockState(), UPDATE_ALL);
                    level.playSound(null, lavaPos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 1.0f, 1.0f);
                    level.getServer().getLevel(level.getLevel().dimension()).sendParticles(ParticleTypes.LARGE_SMOKE, (double)lavaPos.getX() + 0.5D, (double)lavaPos.getY() + 0.25D, (double)lavaPos.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D);
                }
            }
        }
        if (touchingBlocks.size() > 0) {
            return touchingBlocks;
        }
        return null;
    }

    private static List<BlockPos> getLavaTouching(List<Block> convertibleBlocks, BlockPos pos, WorldGenLevel level) {
        BlockPos[] contacts = {pos.above(), pos.below(), pos.north(), pos.east(), pos.south(), pos.west()};
        List<BlockPos> lavaBlocks = new ArrayList<>(List.of());
        for (int i = 0; i < contacts.length; ++i) { //Check each non-diagonal neighbor to see if it is a corresponding orestone
            if (!level.getBlockState(contacts[i]).liquid() && convertibleBlocks.contains(level.getBlockState(contacts[i]).getBlock()) || !CommonConfig.useSource.get() && convertibleBlocks.contains(level.getBlockState(contacts[i]).getBlock()) || CommonConfig.useSource.get() && convertibleBlocks.contains(level.getBlockState(contacts[i]).getBlock()) && level.getBlockState(contacts[i]).getFluidState().isSource() || convertibleBlocks.contains(level.getBlockState(contacts[i]).getBlock())) {
                lavaBlocks.add(contacts[i]);
            }
        }
        if (!lavaBlocks.isEmpty()) {
            return lavaBlocks;
        }
        return null;
    }
}