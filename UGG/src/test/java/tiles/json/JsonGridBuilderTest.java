package tiles.json;

import display.SimpleDisplay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tiles.Tile;
import tiles.TileGrid;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

@SuppressWarnings("CanBeFinal")
class JsonGridBuilderTest {

    static JsonGridBuilder gridGenerator = new JsonGridBuilder() {
        @Override
        protected Map<String, Tile.Builder> createMapForRegistry() {
            return Map.of(
                    "A", Tile.withTileDisplay(SimpleDisplay.withOnlyText("A")),
                    "B", Tile.withTileDisplay(SimpleDisplay.withOnlyText("B"))
            );
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