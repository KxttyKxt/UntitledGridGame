package ugg.tiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ugg.colors.Color;
import ugg.colors.ColorMaker;
import ugg.colors.ColorMerger;
import ugg.colors.SimpleColor;

import static ugg.tiles.Tile.COLORED_CONTENTS;
import static ugg.tiles.TileMatrix.EMPTY_CELL;
import static ugg.tiles.TileMatrix.NULL_CELL;

public class TileMatrixTest {
    @BeforeAll
    public static void initializeTilesAndColors() {
        tileA = new Tile("A");
        tileB = new Tile("B");
        tileC = new Tile("C");
        tileD = new Tile("D");

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
    }

    @BeforeEach
    public void resetExceptionThrownStatus() {
        exceptionWasThrown = false;
    }

    // Initialize as true for matrices to display in the console.
    private final boolean SHOULD_PRINT_MATRICES = true;

    private static TileMatrix tileMatrix;
    private boolean exceptionWasThrown;

    private static Tile tileA, tileB, tileC, tileD;
    private static Tile redTile, greenTile, yellowTile, magentaTile, mergedColorTile;

    private static Color red, green, yellow, magenta, mergedColor;


    private void tileMatrixSizeOne() {
        initializeTileMatrix(1, 1);
    }
    private void tileMatrixSizeOne(Tile tileToSet) {
        initializeTileMatrix(new Tile[][]{{tileToSet}});
    }

    private void tileMatrixSizeTwoAcross() {
        initializeTileMatrix(1, 2);
    }
    private void tileMatrixSizeTwoAcross(Tile firstTile, Tile secondTile) {
        initializeTileMatrix(new Tile[][]{{firstTile, secondTile}});
    }

    private void initializeTileMatrix(int rows, int columns) {
        tileMatrix = new TileMatrix(rows, columns);
    }
    private void initializeTileMatrix(Tile[][] tilesForMatrix) {
        tileMatrix = new TileMatrix(tilesForMatrix);
    }


    @Test
    public void test_constructor_nonPositiveValuesException_rows() {
        try { tileMatrix = new TileMatrix(-1, 2); }
        catch (IllegalArgumentException arraySizesCannotBeNonpositiveException) {
            exceptionWasThrown = true;
        }

        Assertions.assertTrue(exceptionWasThrown);
    }

    @Test
    public void test_constructor_nonPositiveValuesException_columns() {
        try { tileMatrix = new TileMatrix(2, 0); }
        catch (IllegalArgumentException arraySizesCannotBeNonpositiveException) {
            exceptionWasThrown = true;
        }

        Assertions.assertTrue(exceptionWasThrown);
    }

    @Test
    public void test_constructor_nonPositiveValuesException_both() {
        try { tileMatrix = new TileMatrix(0, -1); }
        catch (IllegalArgumentException arraySizesCannotBeNonpositiveException) {
            exceptionWasThrown = true;
        }

        Assertions.assertTrue(exceptionWasThrown);
    }

    @Test
    public void test_constructor_nonPositiveValuesException_noException() {
        try { tileMatrixSizeOne(); }
        catch (IllegalArgumentException arraySizesCannotBeNonpositiveException) {
            exceptionWasThrown = true;
        }

        Assertions.assertFalse(exceptionWasThrown);
    }


    @Test
    public void test_swapContents() {
        tileMatrixSizeTwoAcross(tileA, tileB);
        swapAndAssertAndReset(tileA, tileB);
    }

    @Test
    public void test_swapContents_tileNotInMatrixException() {
        try {
            tileMatrixSizeTwoAcross();
            swapAndAssertAndReset(tileA, tileB);

            if (SHOULD_PRINT_MATRICES)
                System.out.printf("Back to Normal:%n%s%n", tileMatrix);
        }
        catch (IllegalArgumentException tileNotInMatrixException) {
            exceptionWasThrown = true;
        }

        Assertions.assertTrue(exceptionWasThrown);
    }

    private void swapAndAssertAndReset(Tile swapTile1, Tile swapTile2) {
        if (SHOULD_PRINT_MATRICES)
            System.out.printf("Before Swap:%n%s%n", tileMatrix);

        swapAndAssert(swapTile1, swapTile2);

        if (SHOULD_PRINT_MATRICES)
            System.out.printf("After Swap:%n%s%n", tileMatrix);

        swapAndAssert(swapTile1, swapTile2);

        if (SHOULD_PRINT_MATRICES)
            System.out.printf("After Reset:%n%s%n", tileMatrix);
    }
    private void swapAndAssert(Tile swapTile1, Tile swapTile2) {
        String swapTile1Contents = swapTile1.toString();
        String swapTile2Contents = swapTile2.toString();

        tileMatrix.swapContents(swapTile1, swapTile2);

        Assertions.assertEquals(swapTile1Contents, swapTile2.toString());
        Assertions.assertEquals(swapTile2Contents, swapTile1.toString());
    }


    @Test
    public void test_transferContents_true() {
        Tile origin = new Tile("@", ColorMaker.make(SimpleColor.CYAN));
        Tile destination = new Tile();

        tileMatrixSizeTwoAcross(origin, destination);
        transferAndAssert(origin, destination, true);
    }

    @Test
    public void test_transferContents_false() {
        tileMatrixSizeTwoAcross(tileA, tileB);
        transferAndAssert(tileA, tileB, false);
    }

    @Test
    public void test_transferContents_falseWithEmptyOrigin() {
        Tile emptyTile = new Tile();

        tileMatrixSizeTwoAcross(emptyTile, tileB);
        transferAndAssert(emptyTile, tileB, false);
    }

    @Test
    public void test_transferContents_tileNotInMatrixException() {
        try {
            tileMatrixSizeTwoAcross();
            transferAndAssert(tileA, tileB, false);
        }
        catch (IllegalArgumentException tileNotInMatrixException) {
            exceptionWasThrown = true;
        }

        Assertions.assertTrue(exceptionWasThrown);
    }

    private void transferAndAssert(Tile origin, Tile destination, boolean expectedResult) {
        if (SHOULD_PRINT_MATRICES)
            System.out.printf("Before Transfer:%n%s%n", tileMatrix);

        boolean transferIsSuccessful;
        transferIsSuccessful = tileMatrix.transferContents(origin, destination);

        Assertions.assertEquals(expectedResult, transferIsSuccessful);

        if (transferIsSuccessful) {
            if (SHOULD_PRINT_MATRICES)
                System.out.printf("After Transfer:%n%s%n", tileMatrix);

            tileMatrix.transferContents(destination, origin);

            if (SHOULD_PRINT_MATRICES)
                System.out.printf("After Reset:%n%s%n", tileMatrix);
        }
        else
            if (SHOULD_PRINT_MATRICES)
                System.out.printf("Transfer Failed!%n%s%n", tileMatrix);
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
    public void test_toString_sizeOne_empty() {
        tileMatrixSizeOne();
        assertEqualAndPrintMatrixToString("Size-One Empty", EMPTY_CELL);
    }

    @Test
    public void test_toString_sizeOne_null() {
        initializeTileMatrix(new Tile[][]{{null}});
        assertEqualAndPrintMatrixToString("Size-One null", NULL_CELL);
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
    public void test_toString_sizeTwo_empty() {
        tileMatrixSizeTwoAcross();
        assertEqualAndPrintMatrixToString("Size-Two Empty", EMPTY_CELL.repeat(2));
    }

    @Test
    public void test_toString_sizeTwo_null() {
        initializeTileMatrix(new Tile[][]{{null, null}});
        assertEqualAndPrintMatrixToString("Size-Two Null", NULL_CELL.repeat(2));
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
    public void test_toString_sizeTwoByTwo_empty() {
        initializeTileMatrix(2, 2);
        assertEqualAndPrintMatrixToString(
                "Size Two-By-Two Empty",
                String.format(
                        "%s%n%s",
                        EMPTY_CELL.repeat(2),
                        EMPTY_CELL.repeat(2)
                )
        );
    }

    @Test
    public void test_toString_sizeTwoByTwo_null() {
        Tile[][] nullTileMatrix = {
                {null, null},
                {null, null}
        };
        initializeTileMatrix(nullTileMatrix);

        assertEqualAndPrintMatrixToString(
                "Size Two-By-Two Null",
                String.format(
                        "%s%n%s",
                        NULL_CELL.repeat(2),
                        NULL_CELL.repeat(2)
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
                        "[ %s ][ C ]%n     [ %s ]",
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
                {new Tile(), new Tile((String) null), new Tile((Color) null), mergedColorTile}
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

}
