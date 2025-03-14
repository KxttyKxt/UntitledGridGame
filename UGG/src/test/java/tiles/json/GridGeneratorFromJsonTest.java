package tiles.json;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tiles.Tile;
import tiles.TileGrid;

import java.io.IOException;
import java.net.URL;

@SuppressWarnings("CanBeFinal")
class GridGeneratorFromJsonTest {

    static GridGeneratorFromJson gridGenerator = new GridGeneratorFromJson(TileGeneratorFromJsonTest.tileGenerator) {
        @Override
        protected TileGrid constructGridFromJson(URL jsonFileURL) throws IOException {
            return super.constructGridFromJson(jsonFileURL);
        }
    };

    @Test
    void test_constructGridFromJson() throws IOException {
        URL testJsonURL = this.getClass().getResource("test-grid.json");

        TileGrid expectedTileGrid = new TileGrid(new Tile[][]{
                { Tile.defaultTile(),     Tile.defaultTile(), Tile.defaultTile() },
                { Tile.withOnlyText("A"), null,               Tile.withOnlyText("A") },
                { Tile.defaultTile(),     Tile.defaultTile(), Tile.defaultTile() }
        });
        TileGrid actualTileGrid = gridGenerator.constructGridFromJson(testJsonURL);

        Assertions.assertEquals(expectedTileGrid, actualTileGrid);
    }
}