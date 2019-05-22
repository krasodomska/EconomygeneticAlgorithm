package carthography;

import coreElements.StaticData;

import java.util.Objects;

public class MapGenerator {
    String[][] biomeMap;
    String[][] tmp;
    int width;
    int height;

    public TileMap generate(int width, int height, int minTileSize, int maxTileSize) {
        this.width = width;
        this.height = height;
        biomeMap = new String[width][height];

        for (Biome biome : StaticData.getStartBiomes().values()) {
            int xCor = getIntBt(1, width - 2);
            int yCor = getIntBt(1, height - 2);
            while (biomeMap[xCor][yCor] != null) {
                xCor = getIntBt(1, width - 2);
                yCor = getIntBt(1, height - 2);
            }

            biomeMap[xCor][yCor] = biome.getBiomeName();
        }

        while (!checkIfAllNonNull())
            growBiomes();

        reworkMixes();

        return convertToMap();
    }

    void reworkMixes() {
        tmp = new String[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height - 1; y++) {
                if (biomeMap[x][y].equals(biomeMap[x][y + 1])) tmp[x][y] = biomeMap[x][y];
                else {
                    String biome1 = biomeMap[x][y];
                    String biome2 = biomeMap[x][y + 1];
                    tmp[x][y] = StaticData.getMixedBiomes().values().stream()
                            .map(biomeMixer -> biomeMixer.mixBiomes(biome1, biome2))
                            .filter(Objects::nonNull)
                            .map(Biome::getBiomeName)
                            .findFirst()
                            .orElse(biomeMap[x][y]);
                    tmp[x][y + 1] = StaticData.getMixedBiomes().values().stream()
                            .map(biomeMixer -> biomeMixer.mixBiomes(biome1, biome2))
                            .filter(Objects::nonNull)
                            .map(Biome::getBiomeName)
                            .findFirst()
                            .orElse(biomeMap[x][y + 1]);
                }
            }
        }

        cloneTMPtoBM();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width - 1; x++) {
                if (biomeMap[x][y].equals(biomeMap[x + 1][y])) tmp[x][y] = biomeMap[x][y];
                else {
                    String biome1 = biomeMap[x][y];
                    String biome2 = biomeMap[x + 1][y];
                    tmp[x][y] = StaticData.getMixedBiomes().values().stream()
                            .map(biomeMixer -> biomeMixer.mixBiomes(biome1, biome2))
                            .filter(Objects::nonNull)
                            .map(Biome::getBiomeName)
                            .findFirst()
                            .orElse(biomeMap[x][y]);
                    tmp[x + 1][y] = StaticData.getMixedBiomes().values().stream()
                            .map(biomeMixer -> biomeMixer.mixBiomes(biome1, biome2))
                            .filter(Objects::nonNull)
                            .map(Biome::getBiomeName)
                            .findFirst()
                            .orElse(biomeMap[x + 1][y]);
                }

            }
        }
    }

    void cloneTMPtoBM() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++)
                if (tmp[x][y] != null) biomeMap[x][y] = tmp[x][y];
        }
    }

    TileMap convertToMap() {
        TileMap ready = new TileMap();
        ready.tiles = new Tile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                ready.tiles[x][y] = new Tile(getIntBt(1, 7), biomeMap[x][y], x, y);
            }
        }
        return ready;
    }

    void growBiomes() {
        tmp = new String[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (biomeMap[x][y] != null) {
                    tmp[x][y] = biomeMap[x][y];
                    growOutIfPossible(x, y, biomeMap[x][y]);
                }
            }
        }

        cloneTMPtoBM();
    }

    private void growOutIfPossible(int x, int y, String biome) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (checkIfAvailable(x + i, y + j))
                    changeValueIfPossible(x + i, y + j, biome);
            }
        }
    }

    private boolean checkIfAvailable(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height)
            return biomeMap[x][y] == null;
        return false;
    }

    private void changeValueIfPossible(int x, int y, String biome) {
        if (x >= 0 && x < width && y >= 0 && y < height)
            tmp[x][y] = biome;
    }

    boolean checkIfAllNonNull() {
        for (String[] tab : biomeMap) {
            for (String val : tab) {
                if (val == null) return false;
            }
        }
        return true;
    }

    int getIntBt(int min, int max) {
        return (int) (min + (Math.random() * (max - min)));
    }
}
