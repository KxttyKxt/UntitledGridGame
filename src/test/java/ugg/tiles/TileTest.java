package ugg.tiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ugg.colors.Color;
import ugg.colors.ColorMaker;
import ugg.colors.SimpleColor;

import static ugg.tiles.PresetTestingTiles.*;

public class TileTest {
    @Test
    public void test_swapContents() {
        swapWithTileAAndTest(tileB);
    }

    @Test
    public void test_swapContents_withEmpty() {
        swapWithTileAAndTest(emptyTile);
    }

    @Test
    public void test_swapContents_withNullWhichMeansEmpty() {
        swapWithTileAAndTest(nullTile);
    }

    private void swapWithTileAAndTest(Tile tile2) {
        String tile1Contents = tileA.toString();
        String tile2Contents = tile2.toString();

        tileA.swapContentsWith(tile2);

        Assertions.assertEquals(tile1Contents, tile2.toString());
        Assertions.assertEquals(tile2Contents, PresetTestingTiles.tileA.toString());

        tileA.swapContentsWith(tile2);
    }


    @Test
    public void test_transferContents_to_true() {
        resetTileTransfers();

        Assertions.assertTrue(transferTileA.transferContentsTo(emptyTransferTile));

        Assertions.assertEquals("", transferTileA.toString());
        Assertions.assertEquals("A", emptyTransferTile.toString());
    }

    @Test
    public void test_transferContents_to_false() {
        resetTileTransfers();

        Assertions.assertFalse(transferTileA.transferContentsTo(transferTileB));

        Assertions.assertEquals("A", transferTileA.toString());
        Assertions.assertEquals("B", transferTileB.toString());
    }

    private void resetTileTransfers() {
        transferTileA = new Tile("A");
        transferTileB = new Tile("B");
        emptyTransferTile = new Tile("");
    }
    private Tile transferTileA, transferTileB, emptyTransferTile;

    @Test
    public void test_displayContents_singleColorFG() {
        Color tileColor = ColorMaker.make(SimpleColor.RED);
        Tile coloredTile = new Tile("#", tileColor);

        String expectedDisplay = String.format(tileColor.colorize("#"));
        String actualDisplay = coloredTile.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }

    @Test
    public void test_displayContents_singleColorBG() {
        String expectedDisplay = String.format(BG_RED.colorize("#"));
        String actualDisplay = bgRedTile.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }

    @Test
    public void test_displayContents_mergedColor() {
        String expectedDisplay = String.format(MERGED_COLOR.colorize(COLORED_CONTENTS));
        String actualDisplay = mergedColorTile.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }

    @Test
    public void test_displayContents_emptyContents() {
        String expectedDisplay = Tile.EMPTY_CONTENTS_DISPLAY;
        String actualDisplay = emptyTile.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }

    @Test
    public void test_displayContents_nullWhichMeansEmptyContents() {
        String expectedDisplay = Tile.EMPTY_CONTENTS_DISPLAY;
        String actualDisplay = nullTile.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }

    @Test
    public void test_displayContents_nullColor() {
        String expectedDisplay = "#";
        String actualDisplay = uncoloredTile.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }


    @Test
    public void test_toString_contents() {
        Tile notNullTile = new Tile("test");
        Assertions.assertEquals("test", notNullTile.toString());
    }

    @Test
    public void test_toString_empty() {
        Tile emptyTile = new Tile("");
        Assertions.assertEquals("", emptyTile.toString());
    }

    @Test
    public void test_toString_nullWhichMeansEmpty() {
        Tile nullTile = new Tile(null);
        Assertions.assertEquals("", nullTile.toString());
    }


    @Test
    public void test_isIdenticalTo_true_noColor() {
        Tile newTileA = new Tile("A");
        Assertions.assertTrue(tileA.isIdenticalTo(newTileA));
        Assertions.assertTrue(newTileA.isIdenticalTo(tileA));
    }

    @Test
    public void test_isIdenticalTo_true_yesColor() {
        Tile newRedTile = new Tile(COLORED_CONTENTS, RED);
        Assertions.assertTrue(redTile.isIdenticalTo(newRedTile));
        Assertions.assertTrue(newRedTile.isIdenticalTo(redTile));
    }

    @Test
    public void test_isIdenticalTo_false_oneColorIsNull() {
        Assertions.assertFalse(tileA.isIdenticalTo(redTile));
        Assertions.assertFalse(redTile.isIdenticalTo(tileA));
    }

    @Test
    public void test_isIdenticalTo_false_contentsDoNotMatch() {
        Assertions.assertFalse(tileA.isIdenticalTo(tileB));
        Assertions.assertFalse(tileB.isIdenticalTo(tileA));
    }

    @Test
    public void test_isIdenticalTo_false_colorsDoNotMatch() {
        Assertions.assertFalse(redTile.isIdenticalTo(greenTile));
        Assertions.assertFalse(greenTile.isIdenticalTo(redTile));
    }

    @Test
    public void test_isIdenticalTo_false_allDoNotMatch() {
        Assertions.assertFalse(tileA.isIdenticalTo(redTile));
        Assertions.assertFalse(redTile.isIdenticalTo(tileA));
    }
}
