package ugg.tiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ugg.colors.Color;
import ugg.colors.ColorMaker;
import ugg.colors.ColorMerger;
import ugg.colors.SimpleColor;

public class TileTest {
    @Test
    public void test_swapContents() {
        Tile tile1 = new Tile("A");
        Tile tile2 = new Tile("B");

        swapAndTest(tile1, tile2);
    }

    @Test
    public void test_swapContents_withEmpty() {
        Tile tile1 = new Tile("A");
        Tile tile2 = new Tile("");

        swapAndTest(tile1, tile2);
    }

    @Test
    public void test_swapContents_withNull() {
        Tile tile1 = new Tile("A");
        Tile tile2 = new Tile(null);

        swapAndTest(tile1, tile2);
    }

    private void swapAndTest(Tile tile1, Tile tile2) {
        String tile1Contents = tile1.toString();
        String tile2Contents = tile2.toString();

        tile1.swapContentsWith(tile2);

        Assertions.assertEquals(tile1Contents, tile2.toString());
        Assertions.assertEquals(tile2Contents, tile1.toString());
    }


    @Test
    public void test_transferContents_to_true() {
        Tile tile1 = new Tile("Contents");
        Tile tile2 = new Tile("");

        Assertions.assertTrue(tile1.transferContentsTo(tile2));

        Assertions.assertEquals("", tile1.toString());
        Assertions.assertEquals("Contents", tile2.toString());
    }

    @Test
    public void test_transferContents_to_false() {
        Tile tile1 = new Tile("Contents");
        Tile tile2 = new Tile("Also Contents");

        Assertions.assertFalse(tile1.transferContentsTo(tile2));

        Assertions.assertEquals("Contents", tile1.toString());
        Assertions.assertEquals("Also Contents", tile2.toString());
    }


    @Test
    public void test_displayContents_nullContents() {
        Tile nullTile = new Tile(null);

        String expectedDisplay = String.format(ColorMaker.make(SimpleColor.BLACK).colorize("-"));
        String actualDisplay = nullTile.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }

    @Test
    public void test_displayContents_emptyContents() {
        Tile emptyTile = new Tile("");

        String expectedDisplay = String.format(ColorMaker.make(SimpleColor.BRIGHT_BLACK).colorize("."));
        String actualDisplay = emptyTile.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }

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
        Color tileColor = ColorMaker.make(SimpleColor.BG_RED);
        Tile coloredTile = new Tile("#", tileColor);

        String expectedDisplay = String.format(tileColor.colorize("#"));
        String actualDisplay = coloredTile.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }

    @Test
    public void test_displayContents_mergedColor() {
        Color tileColorFG = ColorMaker.make(SimpleColor.RED);
        Color tileColorBG = ColorMaker.make(SimpleColor.BG_BLUE);

        Color mergedTileColor = ColorMerger.mergeColors(new Color[]{tileColorFG, tileColorBG});
        Tile coloredTile = new Tile("#", mergedTileColor);

        String expectedDisplay = String.format(mergedTileColor.colorize("#"));
        String actualDisplay = coloredTile.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }


}
