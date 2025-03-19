package core.json;

import core.Chunk;
import core.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

@SuppressWarnings("CanBeFinal")
class JsonChunkBuilderTest {

    static JsonChunkBuilder chunkBuilder =
            JsonChunkBuilder.usingRegistry(ChunkBuilderTileRegistryTest.tileRegistry);

    @Test
    void test_constructChunkFromJson() throws IOException {
        URL testJsonURL = this.getClass().getResource("chunk/test-chunk-1.json");

        Chunk expectedChunk = Chunk.newChunk(new Tile[][]{
                { Tile.withOnlyText("A"), Tile.withOnlyText("A"), Tile.withOnlyText("A") },
                { Tile.withOnlyText("B"), null,                   Tile.withOnlyText("B") },
                { Tile.withOnlyText("A"), Tile.withOnlyText("A"), Tile.withOnlyText("A") }
        });
        Chunk actualChunk = chunkBuilder.constructChunkFromJson(testJsonURL);

        Assertions.assertEquals(expectedChunk, actualChunk);
    }
}