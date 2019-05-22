package carthography;

public class BiomeMixer {
    private String firstBiome;
    private String secondBiome;
    private Biome finalBiome;

    public BiomeMixer() {
    }

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

    public void setFirstBiome(String firstBiome) {
        this.firstBiome = firstBiome;
    }

    public void setSecondBiome(String secondBiome) {
        this.secondBiome = secondBiome;
    }

    public void setFinalBiome(Biome finalBiome) {
        this.finalBiome = finalBiome;
    }
}
