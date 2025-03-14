package tiles.json;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tiles.Tile;
import tiles.TileGrid;

import java.io.IOException;
import java.net.URL;

@SuppressWarnings("CanBeFinal")
class JsonGridBuilderTest {

    static JsonGridBuilder gridGenerator = new JsonGridBuilder(JsonTileRegistryTest.tileRegistry) {
        @Override
        protected TileGrid constructGridFromJson(URL jsonFileURL) throws IOException {
            return super.constructGridFromJson(jsonFileURL);
        }
    };

    @Test
    void test_constructGridFromJson() throws IOException {
        URL testJsonURL = this.getClass().getResource("test-grid.json");

        TileGrid expectedTileGrid = new TileGrid(new Tile[][]{
                { Tile.withOnlyText("A"), Tile.withOnlyText("A"), Tile.withOnlyText("A") },
                { Tile.withOnlyText("B"), null,                   Tile.withOnlyText("B") },
                { Tile.withOnlyText("A"), Tile.withOnlyText("A"), Tile.withOnlyText("A") }
        });
        TileGrid actualTileGrid = gridGenerator.constructGridFromJson(testJsonURL);

        Assertions.assertEquals(expectedTileGrid, actualTileGrid);
    }
}