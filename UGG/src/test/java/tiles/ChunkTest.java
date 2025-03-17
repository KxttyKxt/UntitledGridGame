package tiles;

import display.SimpleDisplay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ChunkTest {
    private static Chunk chunk;

    private static final Point2D originPoint = Point2D.of(0, 0);
    private static final Point2D acrossPoint = Point2D.of(1, 0);

    private Tile tileWithContents() {
        Tile toReturn = Tile.defaultTile();
        toReturn.addContents(defaultOccupant());

        return toReturn;
    }
    private Tile textTile(String text) {
        return Tile.withOnlyText(text);
    }
    private Occupant defaultOccupant() {
        return Occupant.newOccupant(SimpleDisplay.withOnlyText("occupant"));
    }

    private void tileMatrixSizeOne(Tile tileToSet) {
        initializeTileMatrix(new Tile[][]{{tileToSet}});
    }

    private void tileMatrixSizeTwoAcross(Tile firstTile, Tile secondTile) {
        initializeTileMatrix(new Tile[][]{{firstTile, secondTile}});
    }

    private void initializeTileMatrix(Tile[][] tilesForMatrix) {
        chunk = new Chunk(tilesForMatrix);
    }


    @Test
    void test_addOccupant_true() {
        tileMatrixSizeOne(Tile.defaultTile());

        Occupant occupant = defaultOccupant();
        Assertions.assertTrue(chunk.addOccupant(occupant, originPoint));
    }

    @Test
    void test_addOccupant_false() {
        Tile preExistingTile = Tile.withTileDisplay(Tile.defaultTileDisplay)
                        .andOccupant(defaultOccupant())
                        .build();

        tileMatrixSizeOne(preExistingTile);

        Occupant occupant = defaultOccupant();
        Assertions.assertFalse(chunk.addOccupant(occupant, originPoint));
    }


    @Test
    void test_transferOccupant_true() {
        Tile contentsTile = tileWithContents();
        Tile emptyTile = Tile.defaultTile();

        tileMatrixSizeTwoAcross(contentsTile, emptyTile);
        Assertions.assertTrue(chunk.transferOccupant(originPoint, acrossPoint));
    }

    @Test
    void test_transferOccupant_false() {
        Tile contentsTile = tileWithContents();

        Tile alsoContentsTile = Tile.defaultTile();
        alsoContentsTile.addContents(Occupant.newOccupant(SimpleDisplay.withOnlyText("occupant too")));

        tileMatrixSizeTwoAcross(contentsTile, alsoContentsTile);
        Assertions.assertFalse(chunk.transferOccupant(originPoint, acrossPoint));
    }

    @Test
    void test_transferOccupant_falseWithEmptyOrigin() {
        Tile emptyTile = Tile.defaultTile();
        Tile contentsTile = tileWithContents();

        tileMatrixSizeTwoAcross(emptyTile, contentsTile);
        Assertions.assertFalse(chunk.transferOccupant(originPoint, acrossPoint));
    }


    @Test
    void test_toString_sizeOne() {
        tileMatrixSizeOne(textTile("A"));

        Assertions.assertEquals(chunk.toString(),
                String.format(Chunk.FORMAT_FOR_CELL, "A"));
    }
    @Test
    void test_toString_sizeOne_null() {
        initializeTileMatrix(new Tile[][]{{null}});
        Assertions.assertEquals(chunk.toString(), Chunk.NULL_CELL);
    }


    @Test
    void test_toString_sizeTwo() {
        tileMatrixSizeTwoAcross(textTile("A"), textTile("B"));

        Assertions.assertEquals(chunk.toString(),
                String.format(Chunk.FORMAT_FOR_CELL.repeat(2), "A", "B"));
    }
    @Test
    void test_toString_sizeTwo_null() {
        initializeTileMatrix(new Tile[][]{{null, null}});
        Assertions.assertEquals(Chunk.NULL_CELL.repeat(2), chunk.toString());
    }


    @Test
    void test_toString_sizeTwoByTwo_tile() {
        Tile[][] tilesForMatrix = {
                {textTile("A"), textTile("B")},
                {textTile("C"), textTile("D")}
        };
        initializeTileMatrix(tilesForMatrix);

        Assertions.assertEquals(String.format(
                "%s%n%s",
                String.format(Chunk.FORMAT_FOR_CELL.repeat(2), "A", "B"),
                String.format(Chunk.FORMAT_FOR_CELL.repeat(2), "C", "D")
        ), chunk.toString());
    }
    @Test
    void test_toString_sizeTwoByTwo_null() {
        Tile[][] nullTileMatrix = {
                {null, null},
                {null, null}
        };
        initializeTileMatrix(nullTileMatrix);

        String expectedToString = String.format(
                "%s%n%s",
                Chunk.NULL_CELL.repeat(2),
                Chunk.NULL_CELL.repeat(2)
        );

        Assertions.assertEquals(expectedToString, chunk.toString());
    }


    @Test
    void test_equals_same() {
        Assertions.assertEquals(chunk, chunk);
    }

    @Test
    void test_equals_matrices() {
        tileMatrixSizeOne(Tile.defaultTile());
        Chunk newChunk = new Chunk(new Tile[][]{{Tile.defaultTile()}});

        Assertions.assertEquals(chunk, newChunk);
    }

    @Test
    void test_notEquals_null() {
        tileMatrixSizeOne(Tile.defaultTile());
        Assertions.assertNotEquals(chunk, null);
    }

    @Test
    void test_notEquals_differentClass() {
        // For test coverage
        tileMatrixSizeOne(Tile.defaultTile());
        //noinspection AssertBetweenInconvertibleTypes
        Assertions.assertNotEquals(chunk, "literally a string");
    }

    @Test
    void test_notEquals_differentMatrices() {
        tileMatrixSizeOne(Tile.defaultTile());
        Chunk newChunk = new Chunk(new Tile[][]{{Tile.withOnlyText("different")}});

        Assertions.assertNotEquals(chunk, newChunk);
    }

    @Test
    void test_notEquals_differentMatrixSizes() {
        tileMatrixSizeOne(Tile.defaultTile());
        Chunk newChunk = new Chunk(new Tile[][]{{Tile.defaultTile(), Tile.defaultTile()}});

        Assertions.assertNotEquals(chunk, newChunk);
    }
}
