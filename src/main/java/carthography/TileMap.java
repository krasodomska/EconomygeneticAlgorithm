package carthography;

public class TileMap {
    Tile[][] tiles;

    @Override
    public String toString() {
        for (Tile[] line : tiles){
            for (Tile tile : line){
                System.out.print(tile.biome.charAt(0) + ",");
            }
            System.out.println();
        }
        return super.toString();
    }
}
