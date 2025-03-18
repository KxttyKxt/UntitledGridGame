package tiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ChunkGridTest {
    @Test
    void test_setActive() {
        Chunk chunk1 = new Chunk(new Tile[][]{{Tile.withOnlyText("1")}});
        Chunk chunk2 = new Chunk(new Tile[][]{{Tile.withOnlyText("2")}});

        ChunkGrid grid = ChunkGrid.newGrid(new Chunk[][]{{chunk1, chunk2}});
        Assertions.assertEquals(chunk1, grid.getActiveChunk());

        grid.setActiveChunk(Point2D.of(1,0));
        Assertions.assertEquals(chunk2, grid.getActiveChunk());
    }

    @Test
    void test_toString() {
        Chunk chunk1 = new Chunk(new Tile[][]{{Tile.withOnlyText("1")}});
        Chunk chunk2 = new Chunk(new Tile[][]{{Tile.withOnlyText("2")}});

        ChunkGrid grid = ChunkGrid.newGrid(new Chunk[][]{{chunk1, chunk2}});
        Assertions.assertEquals(chunk1.toString(), grid.toString());

        grid.setActiveChunk(Point2D.of(1,0));
        Assertions.assertEquals(chunk2.toString(), grid.toString());
    }
}