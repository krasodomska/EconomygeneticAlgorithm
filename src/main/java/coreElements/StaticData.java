package coreElements;

import carthography.Biome;
import carthography.BiomeMixer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaticData {
    private static Map<String, Biome> startBiomes;
    private static Map<String, BiomeMixer> mixedBiomes;

    StaticData() {
        loadData();
    }

    public static Map<String, Biome> getStartBiomes() {
        if (startBiomes == null) return new StaticData().getStartBiomes();
        return startBiomes;
    }

    public static Map<String, BiomeMixer> getMixedBiomes() {
        if (startBiomes == null) return new StaticData().getMixedBiomes();
        return mixedBiomes;
    }

    public static Map<String, Biome> getAllBiomes() {
        Map<String, Biome> biomes = new HashMap<>(getStartBiomes());
        getMixedBiomes().values().forEach(biomeMixer -> biomes.put(biomeMixer.getFinalBiome().getBiomeName(), biomeMixer.getFinalBiome()));
        return biomes;
    }

    public static void saveData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("target/biomes_start.json"), getStartBiomes());
            objectMapper.writeValue(new File("target/biomes_mixed.json"), getMixedBiomes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadData() {
        List<Biome> startBiomesList = Collections.emptyList();
        List<BiomeMixer> mixedBiomesList = Collections.emptyList();
        try {
            startBiomesList = new ObjectMapper()
                    .readValue(
                            new File(
                                    StaticData.class.
                                            getClassLoader().
                                            getResource("biomes_start.json")
                                            .getFile()
                            ),
                            new TypeReference<List<Biome>>() {
                            });

            mixedBiomesList = new ObjectMapper()
                    .readValue(
                            new File(
                                    StaticData.class.
                                            getClassLoader().
                                            getResource("biomes_mixed.json")
                                            .getFile()
                            ),
                            new TypeReference<List<BiomeMixer>>() {
                            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        startBiomes = new HashMap<>();
        mixedBiomes = new HashMap<>();
        startBiomesList.forEach(biome -> startBiomes.put(biome.getBiomeName(), biome));
        mixedBiomesList.forEach(biome -> mixedBiomes.put(biome.getFinalBiome().getBiomeName(), biome));
    }
}
