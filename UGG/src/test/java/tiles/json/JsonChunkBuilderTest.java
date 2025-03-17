package tiles.json;

import display.SimpleDisplay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tiles.Chunk;
import tiles.Tile;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

@SuppressWarnings("CanBeFinal")
class JsonChunkBuilderTest {

    static JsonChunkBuilder gridGenerator = new JsonChunkBuilder() {
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

        Chunk expectedChunk = new Chunk(new Tile[][]{
                { Tile.withOnlyText("A"), Tile.withOnlyText("A"), Tile.withOnlyText("A") },
                { Tile.withOnlyText("B"), null,                   Tile.withOnlyText("B") },
                { Tile.withOnlyText("A"), Tile.withOnlyText("A"), Tile.withOnlyText("A") }
        });
        Chunk actualChunk = gridGenerator.constructGridFromJson(testJsonURL);

        Assertions.assertEquals(expectedChunk, actualChunk);
    }
}