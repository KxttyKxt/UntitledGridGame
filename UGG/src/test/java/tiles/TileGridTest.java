package tiles;

import colors.ColorMaker;
import colors.SimpleColor;
import display.SimpleDisplay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TileGridTest {
    @BeforeEach
    void resetTestingFields() {
        tileGrid = null;
    }

    private Tile defaultTile() {
        return new Tile(Tile.defaultTileDisplay);
    }

    private static TileGrid tileGrid;

    private static final int[] originPos = {0, 0};
    private static final int[] acrossPos = {0, 1};

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
    void test_transferContents_true() {
        Tile contentsTile = defaultTile();
        contentsTile.addContents(new SimpleDisplay("contents", null));
        Tile emptyTile = defaultTile();

        tileMatrixSizeTwoAcross(contentsTile, emptyTile);
        Assertions.assertTrue(tileGrid.transferContents(originPos, acrossPos));
    }

    @Test
    void test_transferContents_false() {
        Tile contentsTile = defaultTile();
        contentsTile.addContents(new SimpleDisplay("contents", null));
        Tile alsoContentsTile = defaultTile();
        alsoContentsTile.addContents(new SimpleDisplay("contents too", null));

        tileMatrixSizeTwoAcross(contentsTile, alsoContentsTile);
        Assertions.assertFalse(tileGrid.transferContents(originPos, acrossPos));
    }

    @Test
    void test_transferContents_falseWithEmptyOrigin() {
        Tile emptyTile = defaultTile();
        Tile contentsTile = defaultTile();
        contentsTile.addContents(new SimpleDisplay("contents", null));

        tileMatrixSizeTwoAcross(emptyTile, contentsTile);
        Assertions.assertFalse(tileGrid.transferContents(originPos, acrossPos));
    }

    @Test
    void test_transferContents_falseWithCoordsOutOfBounds() {
        tileMatrixSizeTwoAcross(defaultTile(), defaultTile());
        Assertions.assertFalse(tileGrid.transferContents(new int[]{0, 0}, new int[]{0, 2}));
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

        Assertions.assertEquals(String.format(
                "%s%n%s",
                String.format(TileGrid.FORMAT_FOR_CELL.repeat(2), "A", "B"),
                String.format(TileGrid.FORMAT_FOR_CELL.repeat(2), "C", "D")
        ), tileGrid.toString());
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

        Assertions.assertEquals(String.format(
                "%s%n%s",
                String.format(TileGrid.FORMAT_FOR_CELL.repeat(2), redTile.display(), greenTile.display()),
                String.format(TileGrid.FORMAT_FOR_CELL.repeat(2), yellowTile.display(), magentaTile.display())
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

}
