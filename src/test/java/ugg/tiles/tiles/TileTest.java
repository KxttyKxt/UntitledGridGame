package ugg.tiles.tiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ugg.colors.Color;
import ugg.colors.Colorizer;
import ugg.colors.CompoundColorizer;
import ugg.colors.SimpleColor;

public class TileTest {
    @Test
    public void test_swapContentsWith() {
        Tile tile1 = new Tile("A");
        Tile tile2 = new Tile("B");

        swapAndTest(tile1, tile2);
    }

    @Test
    public void test_swapContents_With_oneEmpty() {
        Tile tile1 = new Tile("A");
        Tile tile2 = new Tile("");

        swapAndTest(tile1, tile2);
    }

    @Test
    public void test_swapContents_With_oneNull() {
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
    public void test_transferContents_To_true() {
        Tile tile1 = new Tile("Contents");
        Tile tile2 = new Tile("");

        Assertions.assertTrue(tile1.transferContentsTo(tile2));

        Assertions.assertEquals("", tile1.toString());
        Assertions.assertEquals("Contents", tile2.toString());
    }

    @Test
    public void test_transferContents_To_false() {
        Tile tile1 = new Tile("Contents");
        Tile tile2 = new Tile("Also Contents");

        Assertions.assertFalse(tile1.transferContentsTo(tile2));

        Assertions.assertEquals("Contents", tile1.toString());
        Assertions.assertEquals("Also Contents", tile2.toString());
    }


    @Test
    public void test_displayContents_nullContent() {
        Tile nullTile = new Tile(null);

        String expectedDisplay = String.format(Colorizer.getColor(SimpleColor.BLACK).colorize("-"));
        String actualDisplay = nullTile.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
        System.out.printf("%s%n", actualDisplay);
    }

    @Test
    public void test_displayContents_emptyContent() {
        Tile emptyTile = new Tile("");

        String expectedDisplay = String.format(Colorizer.getColor(SimpleColor.BRIGHT_BLACK).colorize("."));
        String actualDisplay = emptyTile.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
        System.out.printf("%s%n", actualDisplay);
    }

    @Test
    public void test_displayContents_colored_singleColorFG() {
        Color tileColor = Colorizer.getColor(SimpleColor.RED);
        Tile coloredTile = new Tile("#", tileColor);

        String expectedDisplay = String.format(tileColor.colorize("#"));
        String actualDisplay = coloredTile.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
        System.out.printf("%s%n", actualDisplay);
    }

    @Test
    public void test_displayContents_colored_singleColorBG() {
        Color tileColor = Colorizer.getColor(SimpleColor.BG_RED);
        Tile coloredTile = new Tile("#", tileColor);

        String expectedDisplay = String.format(tileColor.colorize("#"));
        String actualDisplay = coloredTile.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
        System.out.printf("%s%n", actualDisplay);
    }

    @Test
    public void test_displayContents_colored_compoundColor() {
        Color tileColorFG = Colorizer.getColor(SimpleColor.RED);
        Color tileColorBG = Colorizer.getColor(SimpleColor.BG_BLUE);
        Color compoundTileColor = CompoundColorizer.getCompoundColor(new Color[]{tileColorFG, tileColorBG});
        Tile coloredTile = new Tile("#", compoundTileColor);

        String expectedDisplay = String.format(compoundTileColor.colorize("#"));
        String actualDisplay = coloredTile.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
        System.out.printf("%s%n", actualDisplay);
    }


}
