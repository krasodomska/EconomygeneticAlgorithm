package carthography;


import coreElements.StaticData;

class MapGeneratorTest {
    public static void main(String[] args) {

        StaticData.getAllBiomes()
                .forEach(biome -> System.out.println(biome.getBiomeName()));
    }
}