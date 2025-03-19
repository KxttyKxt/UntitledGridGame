package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ChunkGridTest {
    private ChunkGrid grid() {
        return ChunkGrid.newGrid(new Chunk[][]{{chunk1(), chunk2()}});
    }
    private Chunk chunk1() {
        return Chunk.newChunk(new Tile[][]{{Tile.withOnlyText("1")}});
    }
    private Chunk chunk2() {
        return Chunk.newChunk(new Tile[][]{{Tile.withOnlyText("2")}});
    }


    @Test
    void test_getChunk_00() {
        Chunk chunk1 = chunk1();
        Chunk chunk2 = chunk2();

        ChunkGrid grid = ChunkGrid.newGrid(new Chunk[][]{{chunk1, chunk2}});

        Assertions.assertSame(chunk1, grid.getChunk(Point2D.of(0, 0)));
    }

    @Test
    void test_getChunk_01() {
        Chunk chunk1 = chunk1();
        Chunk chunk2 = chunk2();

        ChunkGrid grid = ChunkGrid.newGrid(new Chunk[][]{{chunk1, chunk2}});

        Assertions.assertSame(chunk2, grid.getChunk(Point2D.of(1, 0)));
    }

    @Test
    void test_getChunk_Null() {
        Chunk chunk1 = chunk1();
        Chunk chunk2 = chunk2();

        ChunkGrid grid = ChunkGrid.newGrid(new Chunk[][]{{chunk1, chunk2}});

        Assertions.assertNull(grid.getChunk(Point2D.of(1000, 1000)));
    }


    @Test
    void test_equals_same() {
        ChunkGrid grid = grid();
        Assertions.assertEquals(grid, grid);
    }

    @Test
    void test_equals_grids() {
        ChunkGrid newGrid = ChunkGrid.newGrid(new Chunk[][]{{chunk1(), chunk2()}});
        Assertions.assertEquals(grid(), newGrid);
    }

    @Test
    void test_notEquals_null() {
        Assertions.assertNotEquals(grid(), null);
    }

    @Test
    void test_notEquals_differentClass() {
        // For test coverage
        //noinspection AssertBetweenInconvertibleTypes
        Assertions.assertNotEquals(grid(), "literally a string");
    }

    @Test
    void test_notEquals_differentMatrices() {
        ChunkGrid newGrid = ChunkGrid.newGrid(new Chunk[][]{{chunk1()}});

        Assertions.assertNotEquals(grid(), newGrid);
    }
}