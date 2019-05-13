package carthography;

public class BiomeMixer {
    private final String firstBiome;
    private final String secondBiome;
    private final Biome finalBiome;

    public BiomeMixer(String firstBiome, String secondBiome, Biome finalBiome) {
        this.firstBiome = firstBiome;
        this.secondBiome = secondBiome;
        this.finalBiome = finalBiome;
    }

    public String getFirstBiome() {
        return firstBiome;
    }

    public String getSecondBiome() {
        return secondBiome;
    }

    public Biome getFinalBiome() {
        return finalBiome;
    }

    public Biome mixBiomes(String biome1, String biome2){
        if (firstBiome.equals(biome1) && secondBiome.equals(biome2))
            return finalBiome;
        if (secondBiome.equals(biome1) && firstBiome.equals(biome2))
            return finalBiome;
        return null;
    }
}
