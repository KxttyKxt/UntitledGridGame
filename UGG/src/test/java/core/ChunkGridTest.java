package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ChunkGridTest {
    private ChunkGrid grid() {
        return ChunkGrid.newGrid(new Chunk[][]{{chunk1(), chunk2()}});
    }
    private Chunk chunk1() {
        return new Chunk(new Tile[][]{{Tile.withOnlyText("1")}});
    }
    private Chunk chunk2() {
        return new Chunk(new Tile[][]{{Tile.withOnlyText("2")}});
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