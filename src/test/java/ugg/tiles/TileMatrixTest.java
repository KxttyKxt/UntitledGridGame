package ugg.tiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ugg.colors.Color;
import ugg.colors.ColorMaker;
import ugg.colors.ColorMerger;
import ugg.colors.SimpleColor;

import static ugg.tiles.Tile.COLORED_CONTENTS;
import static ugg.tiles.TileMatrix.cellForEmptyTileDisplay;

public class TileMatrixTest {
    private static TileMatrix tileMatrix;
    private static final boolean SHOULD_PRINT_MATRICES = false;

    @BeforeAll
    public static void initializeTilesAndColors() {
        tileA = new Tile("A");
        tileB = new Tile("B");
        tileC = new Tile("C");
        tileD = new Tile("D");
        emptyTile = new Tile();
        nullContentsTile = new Tile((String) null);

        red = ColorMaker.make(SimpleColor.RED);
        green = ColorMaker.make(SimpleColor.GREEN);
        yellow = ColorMaker.make(SimpleColor.YELLOW);
        magenta = ColorMaker.make(SimpleColor.MAGENTA);
        mergedColor = ColorMerger.merge(new Color[]{
                red, ColorMaker.make(SimpleColor.BG_GREEN)
        });

        redTile = new Tile(red);
        greenTile = new Tile(green);
        yellowTile = new Tile(yellow);
        magentaTile = new Tile(magenta);
        mergedColorTile = new Tile(mergedColor);
        uncoloredTile = new Tile((Color) null);
    }

    private static Tile tileA, tileB, tileC, tileD, emptyTile, nullContentsTile;

    private static Color red, green, yellow, magenta, mergedColor;
    private static Tile redTile, greenTile, yellowTile, magentaTile, mergedColorTile, uncoloredTile;

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
    public void test_toString_sizeOne_null() {
        tileMatrixSizeOne();
        assertEqualAndPrintMatrixToString("Size-One Null", cellForEmptyTileDisplay);
    }

    @Test
    public void test_toString_sizeOne_tile() {
        tileMatrixSizeOne(tileA);
        assertEqualAndPrintMatrixToString("Size-One Tile", "[ A ]");
    }

    @Test
    public void test_toString_sizeOne_colored() {
        tileMatrixSizeOne(redTile);

        String expectedToString = String.format(
                "[ %s ]", ColorMaker.make(SimpleColor.RED).colorize("#")
        );
        assertEqualAndPrintMatrixToString("Size-One Colored", expectedToString);
    }


    @Test
    public void test_toString_sizeTwo_null() {
        tileMatrixSizeTwoAcross();
        assertEqualAndPrintMatrixToString("Size-Two Null", cellForEmptyTileDisplay.repeat(2));
    }

    @Test
    public void test_toString_sizeTwo_tile() {
        tileMatrixSizeTwoAcross(tileA, tileB);
        assertEqualAndPrintMatrixToString("Size-Two Tiles", "[ A ][ B ]");
    }

    @Test
    public void test_toString_sizeTwo_colored() {
        tileMatrixSizeTwoAcross(redTile, greenTile);

        String expectedToString = String.format(
                "[ %s ][ %s ]",
                red.colorize(COLORED_CONTENTS),
                green.colorize(COLORED_CONTENTS)
        );
        assertEqualAndPrintMatrixToString("Size-Two Colored", expectedToString);
    }


    @Test
    public void test_toString_sizeTwoByTwo_null() {
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
    public void test_toString_sizeTwoByTwo_tile() {
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
    public void test_toString_sizeTwoByTwo_colored() {
        Tile[][] tilesForMatrix = {
                {redTile, greenTile},
                {yellowTile, magentaTile}
        };
        initializeTileMatrix(tilesForMatrix);

        assertEqualAndPrintMatrixToString(
                "Size Two-By-Two Colored",
                String.format(
                        "[ %s ][ %s ]%n[ %s ][ %s ]",
                        red.colorize(COLORED_CONTENTS),
                        green.colorize(COLORED_CONTENTS),
                        yellow.colorize(COLORED_CONTENTS),
                        magenta.colorize(COLORED_CONTENTS)
                )
        );
    }

    @Test
    public void test_toString_sizeTwoByTwo_freestyle() {

        Tile[][] tilesForMatrix = {
                {mergedColorTile, tileC},
                {null, yellowTile}
        };
        initializeTileMatrix(tilesForMatrix);

        assertEqualAndPrintMatrixToString(
                "Size Two-By-Two Freestyle",
                String.format(
                        "[ %s ][ C ]%n[ . ][ %s ]",
                        mergedColor.colorize(COLORED_CONTENTS),
                        yellow.colorize(COLORED_CONTENTS)
                )
        );
    }


    @Test
    public void test_toString_sizeThreeByFour_freestyle() {
        Tile[][] tilesForMatrix = {
                {tileA, tileB, tileC, tileD},
                {redTile, greenTile, yellowTile, magentaTile},
                {emptyTile, nullContentsTile, uncoloredTile, mergedColorTile}
        };
        initializeTileMatrix(tilesForMatrix);

        // It's a lot, but at least it's comprehensive.
        final String ROW_TEMPLATE = "[ %s ][ %s ][ %s ][ %s ]";
        String expectedToString = String.format("%s%n%s%n%s",
                String.format(
                        ROW_TEMPLATE,
                        "A", "B", "C", "D"
                ),
                String.format(
                        ROW_TEMPLATE,
                        red.colorize(COLORED_CONTENTS),
                        green.colorize(COLORED_CONTENTS),
                        yellow.colorize(COLORED_CONTENTS),
                        magenta.colorize(COLORED_CONTENTS)
                ),
                String.format(
                        ROW_TEMPLATE,
                        Tile.EMPTY_CONTENTS_DISPLAY,
                        Tile.EMPTY_CONTENTS_DISPLAY,
                        COLORED_CONTENTS,
                        mergedColor.colorize(COLORED_CONTENTS)
                )
        );
        assertEqualAndPrintMatrixToString("Ultimate Freestyle", expectedToString);
    }

}
