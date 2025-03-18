package core.json;

import core.Chunk;
import core.ChunkGrid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

class JsonGridBuilderTest {
    JsonChunkBuilder chunkBuilder = JsonChunkBuilderTest.chunkBuilder;
    JsonGridBuilder gridBuilder = JsonGridBuilder.withChunkBuilder(chunkBuilder);

    @Test
    void test_constructGridFromJson() throws IOException {
        URL actualChunkGridURL = getClass().getResource("grid/test-grid.json");

        ChunkGrid expectedChunkGrid = gridFromChunks();
        ChunkGrid actualChunkGrid = gridBuilder.constructGridFromJson(actualChunkGridURL);

        Assertions.assertEquals(expectedChunkGrid, actualChunkGrid);
    }
    ChunkGrid gridFromChunks() throws IOException {
        URL chunk1URL = getClass().getResource("chunk/test-chunk-1.json");
        Chunk chunk1 = chunkBuilder.constructChunkFromJson(chunk1URL);

        URL chunk2URL = getClass().getResource("chunk/test-chunk-2.json");
        Chunk chunk2 = chunkBuilder.constructChunkFromJson(chunk2URL);

        return ChunkGrid.newGrid(new Chunk[][]{{chunk1, null, chunk2}});
    }
}