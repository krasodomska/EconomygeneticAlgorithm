package coreElements;

import carthography.Biome;
import carthography.BiomeMixer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StaticData {
    private static List<Biome> startBiomes;
    private static List<BiomeMixer> mixedBiomes;

    StaticData() {
        loadData();
    }

    public static List<Biome> getStartBiomes() {
        if (startBiomes == null) return new StaticData().getStartBiomes();
        return startBiomes;
    }

    public static List<BiomeMixer> getMixedBiomes() {
        if (startBiomes == null) return new StaticData().getMixedBiomes();
        return mixedBiomes;
    }

    public static List<Biome> getAllBiomes() {
        List<Biome> biomes = new ArrayList<>(getStartBiomes());
        biomes.addAll(getMixedBiomes().stream().map(biomeMixer -> biomeMixer.getFinalBiome()).collect(Collectors.toList()));
        return biomes;
    }

    public static void saveData(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("target/biomes_start.json"), getStartBiomes());
            objectMapper.writeValue(new File("target/biomes_mixed.json"), getMixedBiomes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void loadData(){
        String startBiomes = StaticData.class.getClassLoader().getResource("biomes_start.json").getFile();
        String mixBiomes = StaticData.class.getClassLoader().getResource("biomes_mixed.json").getFile();
        try {
            StaticData.startBiomes = new ObjectMapper().readValue(new File(startBiomes), new TypeReference<List<Biome>>(){});
            StaticData.mixedBiomes = new ObjectMapper().readValue(new File(mixBiomes), new TypeReference<List<BiomeMixer>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
