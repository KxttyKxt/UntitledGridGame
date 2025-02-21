package ugg.tiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ugg.colors.ColorMaker;
import ugg.colors.SimpleColor;

import static ugg.tiles.PresetTestingTiles.*;
import static ugg.tiles.TileMatrix.cellForEmptyTileDisplay;

public class TileMatrixTest {
    private static TileMatrix tileMatrix;
    private static final boolean SHOULD_PRINT_MATRICES = false;

    private void tileMatrixSizeOne() {
        initializeTileMatrix(1, 1);
    }
    private void tileMatrixSizeOne(Tile tileToSet) {
        tileMatrixSizeOne();
        tileMatrix.setTile(tileToSet, 0, 0);
    }

    private void tileMatrixSizeTwoAcross() {
        initializeTileMatrix(1, 2);
    }
    private void tileMatrixSizeTwoAcross(Tile firstTile, Tile secondTile) {
        tileMatrixSizeTwoAcross();
        tileMatrix.setTile(firstTile, 0, 0);
        tileMatrix.setTile(secondTile, 0, 1);
    }

    private void initializeTileMatrix(int rows, int columns) {
        tileMatrix = new TileMatrix(rows, columns);
    }
    private void initializeTileMatrix(Tile[][] tilesForMatrix) {
        tileMatrix = new TileMatrix(tilesForMatrix);
    }


    private void assertEqualAndPrintMatrixToString(String title, String expectedToString) {
        Assertions.assertEquals(expectedToString, tileMatrix.toString());
        printMatrixToStringComparison(title, expectedToString);
    }
    private void printMatrixToStringComparison(String title, String expectedToString) {
        if (!SHOULD_PRINT_MATRICES)
            return;

        String header = String.format("===== %s =====", title);
        String footer = "=".repeat(header.length());

        System.out.printf("%n%s%n", header);
        System.out.printf("Expected:%n%s%n", expectedToString);
        System.out.printf("Actual:%n%s%n", tileMatrix.toString());
        System.out.printf("%s%n", footer);
    }


    @Test
    public void test_constructor_nonPositiveValuesException_rows() {
        boolean errorWasThrown = false;

        try { tileMatrix = new TileMatrix(-1, 2); }
        catch (IllegalArgumentException arraySizesCannotBeNonpositiveException) {
            errorWasThrown = true;
        }

        Assertions.assertTrue(errorWasThrown);
    }

    @Test
    public void test_constructor_nonPositiveValuesException_columns() {
        boolean errorWasThrown = false;

        try { tileMatrix = new TileMatrix(2, 0); }
        catch (IllegalArgumentException arraySizesCannotBeNonpositiveException) {
            errorWasThrown = true;
        }

        Assertions.assertTrue(errorWasThrown);
    }

    @Test
    public void test_constructor_nonPositiveValuesException_both() {
        boolean errorWasThrown = false;

        try { tileMatrix = new TileMatrix(0, -1); }
        catch (IllegalArgumentException arraySizesCannotBeNonpositiveException) {
            errorWasThrown = true;
        }

        Assertions.assertTrue(errorWasThrown);
    }

    @Test
    public void test_constructor_nonPositiveValuesException_noException() {
        boolean errorWasThrown = false;

        try { tileMatrixSizeOne(); }
        catch (IllegalArgumentException arraySizesCannotBeNonpositiveException) {
            errorWasThrown = true;
        }

        Assertions.assertFalse(errorWasThrown);
    }


    @Test
    public void test_setAndGetTile_oneTile() {
        tileMatrixSizeOne(tileA);
        Assertions.assertEquals(tileA, tileMatrix.getTile(0, 0));
    }

    @Test
    public void test_setAndGetTile_twoTiles() {
        tileMatrixSizeTwoAcross(tileA, tileB);

        Assertions.assertEquals(tileA, tileMatrix.getTile(0, 0));
        Assertions.assertEquals(tileB, tileMatrix.getTile(0, 1));
    }

    @Test
    public void test_getTile_empty() {
        tileMatrixSizeOne();
        Assertions.assertTrue(tileMatrix.getTile(0, 0).isEmpty());
    }


    @Test
    public void test_addTile_true() {
        tileMatrixSizeOne();
        Assertions.assertTrue(tileMatrix.addTile(tileA, 0, 0));
    }

    @Test
    public void test_addTile_false() {
        tileMatrixSizeOne();

        tileMatrix.setTile(tileA, 0, 0);
        Assertions.assertFalse(tileMatrix.addTile(tileB, 0, 0));
    }


    @Test
    public void test_removeTile_returnNull() {
        tileMatrixSizeOne();
        Assertions.assertNull(tileMatrix.removeTile(0, 0));
    }

    @Test
    public void test_removeTile_returnTileA() {
        tileMatrixSizeOne(tileA);

        Tile actualTileRemoved = tileMatrix.removeTile(0, 0);
        Assertions.assertEquals(tileA, actualTileRemoved);
    }

    @Test
    public void test_removeTile_ensureIndexIsNowEmptyTile() {
        tileMatrixSizeOne(tileA);
        Assertions.assertEquals(tileA, tileMatrix.getTile(0, 0));

        tileMatrix.removeTile(0, 0);
        Assertions.assertTrue(tileMatrix.getTile(0, 0).isIdenticalTo(emptyTile));
    }


    @Test
    public void test_toString_sizeOneNull() {
        tileMatrixSizeOne();
        assertEqualAndPrintMatrixToString("Size-One Null", cellForEmptyTileDisplay);
    }

    @Test
    public void test_toString_sizeOneTile() {
        tileMatrixSizeOne(tileA);
        assertEqualAndPrintMatrixToString("Size-One Tile", "[ A ]");
    }

    @Test
    public void test_toString_sizeOneColored() {
        tileMatrixSizeOne(redTile);

        String expectedToString = String.format(
                "[ %s ]", ColorMaker.make(SimpleColor.RED).colorize("#")
        );
        assertEqualAndPrintMatrixToString("Size-One Colored", expectedToString);
    }


    @Test
    public void test_toString_sizeTwoNull() {
        tileMatrixSizeTwoAcross();
        assertEqualAndPrintMatrixToString("Size-Two Null", cellForEmptyTileDisplay.repeat(2));
    }

    @Test
    public void test_toString_sizeTwoTile() {
        tileMatrixSizeTwoAcross(tileA, tileB);
        assertEqualAndPrintMatrixToString("Size-Two Tiles", "[ A ][ B ]");
    }

    @Test
    public void test_toString_sizeTwoColored() {
        tileMatrixSizeTwoAcross(redTile, greenTile);

        String expectedToString = String.format(
                "[ %s ][ %s ]",
                RED.colorize(COLORED_CONTENTS),
                GREEN.colorize(COLORED_CONTENTS)
        );
        assertEqualAndPrintMatrixToString("Size-Two Colored", expectedToString);
    }


    @Test
    public void test_toString_sizeTwoByTwoNull() {
        initializeTileMatrix(2, 2);
        assertEqualAndPrintMatrixToString(
                "Size Two-By-Two Null",
                String.format(
                        "%s%n%s",
                        cellForEmptyTileDisplay.repeat(2),
                        cellForEmptyTileDisplay.repeat(2)
                )
        );
    }

    @Test
    public void test_toString_sizeTwoByTwoTile() {
        Tile[][] tilesForMatrix = {
                {tileA, tileB},
                {tileC, tileD}
        };
        initializeTileMatrix(tilesForMatrix);

        assertEqualAndPrintMatrixToString(
                "Size Two-By-Two Tiles",
                String.format("[ A ][ B ]%n[ C ][ D ]")
        );
    }

    @Test
    public void test_toString_sizeTwoByTwoColored() {
        Tile[][] tilesForMatrix = {
                {redTile, greenTile},
                {yellowTile, magentaTile}
        };
        initializeTileMatrix(tilesForMatrix);

        assertEqualAndPrintMatrixToString(
                "Size Two-By-Two Colored",
                String.format(
                        "[ %s ][ %s ]%n[ %s ][ %s ]",
                        RED.colorize(COLORED_CONTENTS),
                        GREEN.colorize(COLORED_CONTENTS),
                        YELLOW.colorize(COLORED_CONTENTS),
                        MAGENTA.colorize(COLORED_CONTENTS)
                )
        );
    }

    @Test
    public void test_toString_sizeTwoByTwoFreeStyle() {

        Tile[][] tilesForMatrix = {
                {mergedColorTile, tileC},
                {null, yellowTile}
        };
        initializeTileMatrix(tilesForMatrix);

        assertEqualAndPrintMatrixToString(
                "Size Two-By-Two Colored",
                String.format(
                        "[ %s ][ C ]%n[ . ][ %s ]",
                        MERGED_COLOR.colorize(COLORED_CONTENTS),
                        YELLOW.colorize(COLORED_CONTENTS)
                )
        );
    }



}
