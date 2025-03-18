package core.json;

import core.Chunk;
import core.Tile;
import display.SimpleDisplay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

@SuppressWarnings("CanBeFinal")
class JsonChunkBuilderTest {

    static JsonChunkBuilder chunkBuilder = new JsonChunkBuilder() {
        @Override
        protected Map<String, Tile.Builder> createMapForRegistry() {
            return Map.of(
                    "A", Tile.withTileDisplay(SimpleDisplay.withOnlyText("A")),
                    "B", Tile.withTileDisplay(SimpleDisplay.withOnlyText("B"))
            );
        }
    };

    @Test
    void test_constructChunkFromJson() throws IOException {
        URL testJsonURL = this.getClass().getResource("chunk/test-chunk.json");

        Chunk expectedChunk = new Chunk(new Tile[][]{
                { Tile.withOnlyText("A"), Tile.withOnlyText("A"), Tile.withOnlyText("A") },
                { Tile.withOnlyText("B"), null,                   Tile.withOnlyText("B") },
                { Tile.withOnlyText("A"), Tile.withOnlyText("A"), Tile.withOnlyText("A") }
        });
        Chunk actualChunk = chunkBuilder.constructChunkFromJson(testJsonURL);

        Assertions.assertEquals(expectedChunk, actualChunk);
    }
}