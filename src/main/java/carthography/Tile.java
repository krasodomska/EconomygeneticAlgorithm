package carthography;

public class Tile {
    String biome;
    private int x;
    private int y;
    private Integer size;

    public Tile(Integer size, String biome, int x, int y) {
        this.size = size;
        this.biome = biome;
        this.x = x;
        this.y = y;
    }

    public Tile(Integer size, String biome) {
        this.size = size;
        this.biome = biome;
    }

    public boolean inRange(Tile another, Integer range){
        return Math.abs(another.x - this.x) + Math.abs(another.y - this.y) <= range;
    }
}
