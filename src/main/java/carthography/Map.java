package carthography;

public class Map {
    Tile[][] tiles;

    @Override
    public String toString() {
        for (Tile[] line : tiles){
            for (Tile tile : line){
                System.out.print("" +tile.biome.charAt(0) + tile.size + ",");
            }
            System.out.println();
        }
        return super.toString();
    }
}
