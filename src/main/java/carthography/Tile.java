package carthography;

import main.Agent;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    Integer size;
    String biome;
    List<Agent> residents;

    public Tile(Integer size, Biome biome, List<Agent> residents) {
        this.size = size;
        this.biome = biome.getBiomeName();
        this.residents = residents;
    }

    public Tile(Integer size, String biome) {
        this.size = size;
        this.biome = biome;
        this.residents = new ArrayList<>();
    }
}
