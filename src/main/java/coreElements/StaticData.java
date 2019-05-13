package coreElements;

import carthography.Biome;
import carthography.BiomeMixer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StaticData {
    private static List<Biome> startBiomes;
    private static List<BiomeMixer> mixedBiomes;

    StaticData() {
        startBiomes = Arrays.asList(
                new Biome(
                        "FARMLANDS",
                        Arrays.asList(ItemName.FOOD, ItemName.CLOTHES),
                        Arrays.asList(ItemName.GOLD),
                        Arrays.asList(ItemName.LUMBER)
                ),
                new Biome(
                        "MOUNTAINS",
                        Arrays.asList(ItemName.LUMBER, ItemName.GOLD),
                        Arrays.asList(ItemName.CLOTHES),
                        Arrays.asList(ItemName.FOOD)
                ),
                new Biome(
                        "OCEAN",
                        Arrays.asList(),
                        Arrays.asList(ItemName.FOOD, ItemName.GOLD),
                        Arrays.asList(ItemName.LUMBER, ItemName.CLOTHES)
                ),
                new Biome(
                        "DESERT",
                        Arrays.asList(ItemName.GOLD),
                        Arrays.asList(),
                        Arrays.asList(ItemName.LUMBER, ItemName.CLOTHES, ItemName.FOOD)
                )
        );

        mixedBiomes = Arrays.asList(
                new BiomeMixer(
                        "FARMLANDS",
                        "MOUNTAINS",
                        new Biome(
                                "HIGHLANDS",
                                Arrays.asList(ItemName.CLOTHES),
                                Arrays.asList(ItemName.GOLD, ItemName.FOOD, ItemName.LUMBER),
                                Arrays.asList()
                        )
                ),
                new BiomeMixer(
                        "FARMLANDS",
                        "OCEAN",
                        new Biome(
                                "SEASHORE",
                                Arrays.asList(),
                                Arrays.asList(),
                                Arrays.asList(ItemName.LUMBER, ItemName.CLOTHES)
                        )
                ),
                new BiomeMixer(
                        "FARMLANDS",
                        "DESERT",
                        new Biome(
                                "BADLANDS",
                                Arrays.asList(ItemName.CLOTHES),
                                Arrays.asList(ItemName.FOOD),
                                Arrays.asList(ItemName.LUMBER)
                        )
                )
        );
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
}
