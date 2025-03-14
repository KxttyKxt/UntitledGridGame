package tiles;

import display.Displayable;
import display.SimpleDisplay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TileGridTest {
    private static TileGrid tileGrid;

    private static final int[] originPos = {0, 0};
    private static final int[] acrossPos = {0, 1};

    private Tile tileWithContents() {
        Tile toReturn = Tile.defaultTile();
        toReturn.addContents(SimpleDisplay.withOnlyText("contents"));

        return toReturn;
    }
    private Tile textTile(String text) {
        return Tile.withOnlyText(text);
    }

    private void tileMatrixSizeOne(Tile tileToSet) {
        initializeTileMatrix(new Tile[][]{{tileToSet}});
    }

    private void tileMatrixSizeTwoAcross(Tile firstTile, Tile secondTile) {
        initializeTileMatrix(new Tile[][]{{firstTile, secondTile}});
    }

    private void initializeTileMatrix(Tile[][] tilesForMatrix) {
        tileGrid = new TileGrid(tilesForMatrix);
    }


    @Test
    void test_addContents_true() {
        tileMatrixSizeOne(Tile.defaultTile());

        Displayable contentsDisplay = SimpleDisplay.withOnlyText("contents");
        Assertions.assertTrue(tileGrid.addContents(contentsDisplay, 0, 0));
    }

    @Test
    void test_addContents_false() {
        Tile preExistingTile = Tile.withTileDisplay(Tile.defaultTileDisplay)
                        .andContentsDisplay(SimpleDisplay.withOnlyText("pre-contents"))
                        .build();

        tileMatrixSizeOne(preExistingTile);

        Displayable contentsDisplay = SimpleDisplay.withOnlyText("contents");
        Assertions.assertFalse(tileGrid.addContents(contentsDisplay, 0, 0));
    }


    @Test
    void test_transferContents_true() {
        Tile contentsTile = tileWithContents();
        Tile emptyTile = Tile.defaultTile();

        tileMatrixSizeTwoAcross(contentsTile, emptyTile);
        Assertions.assertTrue(tileGrid.transferContents(originPos, acrossPos));
    }

    @Test
    void test_transferContents_false() {
        Tile contentsTile = tileWithContents();

        Tile alsoContentsTile = Tile.defaultTile();
        alsoContentsTile.addContents(SimpleDisplay.withOnlyText("contents too"));

        tileMatrixSizeTwoAcross(contentsTile, alsoContentsTile);
        Assertions.assertFalse(tileGrid.transferContents(originPos, acrossPos));
    }

    @Test
    void test_transferContents_falseWithEmptyOrigin() {
        Tile emptyTile = Tile.defaultTile();
        Tile contentsTile = tileWithContents();

        tileMatrixSizeTwoAcross(emptyTile, contentsTile);
        Assertions.assertFalse(tileGrid.transferContents(originPos, acrossPos));
    }


    @Test
    void test_toString_sizeOne() {
        tileMatrixSizeOne(textTile("A"));

        Assertions.assertEquals(tileGrid.toString(),
                String.format(TileGrid.FORMAT_FOR_CELL, "A"));
    }
    @Test
    void test_toString_sizeOne_null() {
        initializeTileMatrix(new Tile[][]{{null}});
        Assertions.assertEquals(tileGrid.toString(), TileGrid.NULL_CELL);
    }


    @Test
    void test_toString_sizeTwo() {
        tileMatrixSizeTwoAcross(textTile("A"), textTile("B"));

        Assertions.assertEquals(tileGrid.toString(),
                String.format(TileGrid.FORMAT_FOR_CELL.repeat(2), "A", "B"));
    }
    @Test
    void test_toString_sizeTwo_null() {
        initializeTileMatrix(new Tile[][]{{null, null}});
        Assertions.assertEquals(TileGrid.NULL_CELL.repeat(2), tileGrid.toString());
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
                String.format(TileGrid.FORMAT_FOR_CELL.repeat(2), "A", "B"),
                String.format(TileGrid.FORMAT_FOR_CELL.repeat(2), "C", "D")
        ), tileGrid.toString());
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
                TileGrid.NULL_CELL.repeat(2),
                TileGrid.NULL_CELL.repeat(2)
        );

        Assertions.assertEquals(expectedToString, tileGrid.toString());
    }


    @Test
    void test_equals_same() {
        Assertions.assertEquals(tileGrid, tileGrid);
    }

    @Test
    void test_equals_matrices() {
        tileMatrixSizeOne(Tile.defaultTile());
        TileGrid newTileGrid = new TileGrid(new Tile[][]{{Tile.defaultTile()}});

        Assertions.assertEquals(tileGrid, newTileGrid);
    }

    @Test
    void test_notEquals_null() {
        tileMatrixSizeOne(Tile.defaultTile());
        Assertions.assertNotEquals(tileGrid, null);
    }

    @Test
    void test_notEquals_differentClass() {
        // For test coverage
        tileMatrixSizeOne(Tile.defaultTile());
        //noinspection AssertBetweenInconvertibleTypes
        Assertions.assertNotEquals(tileGrid, "literally a string");
    }

    @Test
    void test_notEquals_differentMatrices() {
        tileMatrixSizeOne(Tile.defaultTile());
        TileGrid newTileGrid = new TileGrid(new Tile[][]{{Tile.withOnlyText("different")}});

        Assertions.assertNotEquals(tileGrid, newTileGrid);
    }

    @Test
    void test_notEquals_differentMatrixSizes() {
        tileMatrixSizeOne(Tile.defaultTile());
        TileGrid newTileGrid = new TileGrid(new Tile[][]{{Tile.defaultTile(), Tile.defaultTile()}});

        Assertions.assertNotEquals(tileGrid, newTileGrid);
    }
}
