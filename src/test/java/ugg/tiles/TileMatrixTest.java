package ugg.tiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ugg.colors.Color;
import ugg.colors.ColorMaker;
import ugg.colors.ColorMerger;
import ugg.colors.SimpleColor;

public class TileMatrixTest {
    @BeforeAll
    public static void initializeTiles() {
        tileA = new Tile("A");
        tileB = new Tile("B");
        tileC = new Tile("C");
        tileD = new Tile("D");

        redTile = new Tile("#", ColorMaker.make(SimpleColor.RED));
        greenTile = new Tile("#", ColorMaker.make(SimpleColor.GREEN));
        yellowTile = new Tile("#", ColorMaker.make(SimpleColor.YELLOW));
        magentaTile = new Tile("#", ColorMaker.make(SimpleColor.MAGENTA));

        wildTileColor = ColorMerger.mergeColors(new Color[]{
                ColorMaker.make(SimpleColor.BLUE),
                ColorMaker.make(SimpleColor.BG_BRIGHT_BLACK)
        });
        wildTile = new Tile("&", wildTileColor);
    }
    private static TileMatrix tileMatrix;
    private static Tile tileA, tileB, tileC, tileD;
    private static Tile redTile, greenTile, yellowTile, magentaTile;

    private static Color wildTileColor;
    private static Tile wildTile;

    private static final String EMPTY_TILE_DISPLAY =
            String.format("[ %s ]", Tile.EMPTY_CONTENTS_DISPLAY);

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

        try {
            tileMatrix = new TileMatrix(-1, 2);
        }
        catch (IllegalArgumentException arraySizesCannotBeNegativeException) {
            errorWasThrown = true;
        }

        Assertions.assertTrue(errorWasThrown);
    }

    @Test
    public void test_constructor_nonPositiveValuesException_columns() {
        boolean errorWasThrown = false;

        try {
            tileMatrix = new TileMatrix(2, 0);
        }
        catch (IllegalArgumentException arraySizesCannotBeNegativeException) {
            errorWasThrown = true;
        }

        Assertions.assertTrue(errorWasThrown);
    }

    @Test
    public void test_constructor_nonPositiveValuesException_both() {
        boolean errorWasThrown = false;

        try {
            tileMatrix = new TileMatrix(0, -1);
        }
        catch (IllegalArgumentException arraySizesCannotBeNegativeException) {
            errorWasThrown = true;
        }

        Assertions.assertTrue(errorWasThrown);
    }

    @Test
    public void test_constructor_nonPositiveValuesException_noException() {
        boolean errorWasThrown = false;

        try {
            tileMatrixSizeOne();
        }
        catch (IllegalArgumentException arraySizesCannotBeNegativeException) {
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
        tileMatrix = new TileMatrix(1, 2);

        tileMatrix.setTile(tileA, 0, 0);
        tileMatrix.setTile(tileB, 0, 1);

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

        Tile expectedTileRemoved = tileA;
        Tile actualTileRemoved = tileMatrix.removeTile(0, 0);

        Assertions.assertEquals(expectedTileRemoved, actualTileRemoved);
    }

    @Test
    public void test_removeTile_ensureIndexIsNowEmptyTile() {
        tileMatrixSizeOne(tileA);
        Assertions.assertEquals(tileA, tileMatrix.getTile(0, 0));

        tileMatrix.removeTile(0, 0);
        Tile expectedExample = new Tile();

        Assertions.assertTrue(expectedExample.isIdenticalTo(tileMatrix.getTile(0, 0)));
    }


    @Test
    public void test_toString_sizeOneNull() {
        tileMatrixSizeOne();
        assertEqualAndPrintMatrixToString("Size-One Null", EMPTY_TILE_DISPLAY);
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
                "[ %s ]",
                ColorMaker.make(SimpleColor.RED).colorize("#")
        );
        assertEqualAndPrintMatrixToString("Size-One Colored", expectedToString);
    }


    @Test
    public void test_toString_sizeTwoNull() {
        tileMatrixSizeTwoAcross();
        assertEqualAndPrintMatrixToString("Size-Two Null", EMPTY_TILE_DISPLAY.repeat(2));
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
                ColorMaker.make(SimpleColor.RED).colorize("#"),
                ColorMaker.make(SimpleColor.GREEN).colorize("#")
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
                        EMPTY_TILE_DISPLAY.repeat(2),
                        EMPTY_TILE_DISPLAY.repeat(2)
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
                        ColorMaker.make(SimpleColor.RED).colorize("#"),
                        ColorMaker.make(SimpleColor.GREEN).colorize("#"),
                        ColorMaker.make(SimpleColor.YELLOW).colorize("#"),
                        ColorMaker.make(SimpleColor.MAGENTA).colorize("#")
                )
        );
    }

    @Test
    public void test_toString_sizeTwoByTwoFreeStyle() {

        Tile[][] tilesForMatrix = {
                {wildTile, tileC},
                {null, yellowTile}
        };
        initializeTileMatrix(tilesForMatrix);

        assertEqualAndPrintMatrixToString(
                "Size Two-By-Two Colored",
                String.format(
                        "[ %s ][ C ]%n[ . ][ %s ]",
                        wildTileColor.colorize("&"),
                        ColorMaker.make(SimpleColor.YELLOW).colorize("#")
                )
        );
    }



}
