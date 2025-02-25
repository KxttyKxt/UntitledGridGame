package tiles;

import colors.Color;
import colors.ColorMaker;
import colors.ColorMerger;
import colors.SimpleColor;
import display.SimpleDisplay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TileGridTest {
    @BeforeEach
    void resetTestingFields() {
        tileGrid = null;
        exceptionWasThrown = false;
    }

    private static TileGrid tileGrid;
    private boolean exceptionWasThrown;

    private static final int[] originPos = {0, 0};
    private static final int[] acrossPos = {0, 1};

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
        tileGrid = new TileGrid(rows, columns);
    }
    private void initializeTileMatrix(Tile[][] tilesForMatrix) {
        tileGrid = new TileGrid(tilesForMatrix);
    }


    @Test
    void test_constructor_nonPositiveValuesException_rows() {
        try { tileGrid = new TileGrid(-1, 2); }
        catch (IllegalArgumentException arraySizesCannotBeNonpositiveException) {
            exceptionWasThrown = true;
        }

        Assertions.assertTrue(exceptionWasThrown);
    }

    @Test
    void test_constructor_nonPositiveValuesException_columns() {
        try { tileGrid = new TileGrid(2, 0); }
        catch (IllegalArgumentException arraySizesCannotBeNonpositiveException) {
            exceptionWasThrown = true;
        }

        Assertions.assertTrue(exceptionWasThrown);
    }

    @Test
    void test_constructor_nonPositiveValuesException_both() {
        try { tileGrid = new TileGrid(0, -1); }
        catch (IllegalArgumentException arraySizesCannotBeNonpositiveException) {
            exceptionWasThrown = true;
        }

        Assertions.assertTrue(exceptionWasThrown);
    }

    @Test
    void test_constructor_nonPositiveValuesException_noException() {
        try { tileMatrixSizeOne(); }
        catch (IllegalArgumentException arraySizesCannotBeNonpositiveException) {
            exceptionWasThrown = true;
        }

        Assertions.assertFalse(exceptionWasThrown);
    }


    @Test
    void test_swapContents() {
        Tile tileA =
                new Tile(Tile.defaultBaseDisplay, new SimpleDisplay("contents", null));
        Tile tileB =
                new Tile(Tile.defaultBaseDisplay, new SimpleDisplay("contents too", null));

        tileMatrixSizeTwoAcross(tileA, tileB);

        Assertions.assertEquals(tileA.toString(), "contents");
        Assertions.assertEquals(tileB.toString(), "contents too");

        tileGrid.swapContents(originPos, acrossPos);

        Assertions.assertEquals(tileA.toString(), "contents too");
        Assertions.assertEquals(tileB.toString(), "contents");
    }

    @Test
    void test_swapContents_coordsOutOfBoundsException() {
        try {
            tileMatrixSizeTwoAcross();
            tileGrid.swapContents(new int[]{0, 0}, new int[]{0, 2});
        }
        catch (IndexOutOfBoundsException coordsOutOfBoundsException) {
            exceptionWasThrown = true;
        }

        Assertions.assertTrue(exceptionWasThrown);
    }


    @Test
    void test_transferContents_true() {
        Tile contentsTile =
                new Tile(Tile.defaultBaseDisplay, new SimpleDisplay("contents", null));
        Tile emptyTile =
                new Tile();

        tileMatrixSizeTwoAcross(contentsTile, emptyTile);
        Assertions.assertTrue(tileGrid.transferContents(originPos, acrossPos));
    }

    @Test
    void test_transferContents_false() {
        Tile contentsTile =
                new Tile(Tile.defaultBaseDisplay, new SimpleDisplay("contents", null));
        Tile alsoContentsTile =
                new Tile(Tile.defaultBaseDisplay, new SimpleDisplay("contents too", null));

        tileMatrixSizeTwoAcross(contentsTile, alsoContentsTile);
        Assertions.assertFalse(tileGrid.transferContents(originPos, acrossPos));
    }

    @Test
    void test_transferContents_falseWithEmptyOrigin() {
        Tile emptyTile =
                new Tile();
        Tile contentsTile =
                new Tile(Tile.defaultBaseDisplay, new SimpleDisplay("contents", null));

        tileMatrixSizeTwoAcross(emptyTile, contentsTile);
        Assertions.assertFalse(tileGrid.transferContents(originPos, acrossPos));
    }

    @Test
    void test_transferContents_coordsOutOfBoundsException() {
        try {
            tileMatrixSizeTwoAcross();
            tileGrid.transferContents(new int[]{0, 0}, new int[]{0, 2});
        }
        catch (IndexOutOfBoundsException coordsOutOfBoundsException) {
            exceptionWasThrown = true;
        }

        Assertions.assertTrue(exceptionWasThrown);
    }


    @Test
    void test_toString_sizeOne_tile() {
        tileMatrixSizeOne(new Tile("A", null));

        Assertions.assertEquals(tileGrid.toString(),
                String.format(TileGrid.FORMAT_FOR_CELL, "A"));
    }

    @Test
    void test_toString_sizeOne_colored() {
        Tile redTile = new Tile("A", ColorMaker.make(SimpleColor.RED));
        tileMatrixSizeOne(redTile);

        Assertions.assertEquals(tileGrid.toString(),
                String.format(TileGrid.FORMAT_FOR_CELL, redTile.display()));
    }

    @Test
    void test_toString_sizeOne_empty() {
        tileMatrixSizeOne();
        Assertions.assertEquals(tileGrid.toString(), TileGrid.EMPTY_CELL);
    }

    @Test
    void test_toString_sizeOne_null() {
        initializeTileMatrix(new Tile[][]{{null}});
        Assertions.assertEquals(tileGrid.toString(), TileGrid.NULL_CELL);
    }


    @Test
    void test_toString_sizeTwo_tile() {
        tileMatrixSizeTwoAcross(new Tile("A", null), new Tile("B", null));

        Assertions.assertEquals(tileGrid.toString(),
                String.format(TileGrid.FORMAT_FOR_CELL.repeat(2), "A", "B"));
    }

    @Test
    void test_toString_sizeTwo_colored() {
        Tile redTile = new Tile(null, ColorMaker.make(SimpleColor.RED));
        Tile greenTile = new Tile(null, ColorMaker.make(SimpleColor.GREEN));
        tileMatrixSizeTwoAcross(redTile, greenTile);

        Assertions.assertEquals(tileGrid.toString(), String.format(
                        TileGrid.FORMAT_FOR_CELL.repeat(2),
                        redTile.display(), greenTile.display()
        ));
    }

    @Test
    void test_toString_sizeTwo_empty() {
        tileMatrixSizeTwoAcross();
        Assertions.assertEquals(TileGrid.EMPTY_CELL.repeat(2), tileGrid.toString());
    }

    @Test
    void test_toString_sizeTwo_null() {
        initializeTileMatrix(new Tile[][]{{null, null}});
        Assertions.assertEquals(TileGrid.NULL_CELL.repeat(2), tileGrid.toString());
    }


    @Test
    void test_toString_sizeTwoByTwo_tile() {
        Tile[][] tilesForMatrix = {
                {new Tile("A", null), new Tile("B", null)},
                {new Tile("C", null), new Tile("D", null)}
        };
        initializeTileMatrix(tilesForMatrix);

        Assertions.assertEquals(String.format("[ A ][ B ]%n[ C ][ D ]"), tileGrid.toString());
    }

    @Test
    void test_toString_sizeTwoByTwo_colored() {
        Tile redTile = new Tile("Red", ColorMaker.make(SimpleColor.RED));
        Tile greenTile = new Tile("Green", ColorMaker.make(SimpleColor.GREEN));
        Tile yellowTile = new Tile("Yellow", ColorMaker.make(SimpleColor.YELLOW));
        Tile magentaTile = new Tile("Magenta", ColorMaker.make(SimpleColor.MAGENTA));

        Tile[][] tilesForMatrix = {
                {redTile, greenTile},
                {yellowTile, magentaTile}
        };
        initializeTileMatrix(tilesForMatrix);

        String expectedToString = String.format(
                        "[ %s ][ %s ]%n[ %s ][ %s ]",
                        redTile.display(),
                        greenTile.display(),
                        yellowTile.display(),
                        magentaTile.display()
        );

        Assertions.assertEquals(expectedToString, tileGrid.toString());
    }

    @Test
    void test_toString_sizeTwoByTwo_empty() {
        initializeTileMatrix(2, 2);

        String expectedToString = String.format(
                "%s%n%s",
                TileGrid.EMPTY_CELL.repeat(2),
                TileGrid.EMPTY_CELL.repeat(2)
        );

        Assertions.assertEquals(expectedToString, tileGrid.toString());
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
    void test_toString_sizeThreeByFour_freestyle() {
        Tile tileA = new Tile("A", null);
        Tile tileB = new Tile("B", null);
        Tile tileC = new Tile("C", null);
        Tile tileD = new Tile("D", null);
        Tile redTile = new Tile("Red", ColorMaker.make(SimpleColor.RED));
        Tile greenTile = new Tile("Green", ColorMaker.make(SimpleColor.GREEN));
        Tile yellowTile = new Tile("Yellow", ColorMaker.make(SimpleColor.YELLOW));
        Tile magentaTile = new Tile("Green", ColorMaker.make(SimpleColor.MAGENTA));
        Tile mergedColorTile = new Tile("Merged", ColorMerger.merge( new Color[]{
                ColorMaker.make(SimpleColor.CYAN),
                ColorMaker.make(SimpleColor.BG_RED)
        }));

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
                        redTile.display(),
                        greenTile.display(),
                        yellowTile.display(),
                        magentaTile.display()
                ),
                String.format(
                        ROW_TEMPLATE,
                        Tile.defaultBaseDisplay.display(),
                        Tile.defaultBaseColor.colorize("-"),
                        Tile.defaultBaseText.charAt(0),
                        mergedColorTile.display()
                )
        );
        Assertions.assertEquals(expectedToString, tileGrid.toString());
    }

}
