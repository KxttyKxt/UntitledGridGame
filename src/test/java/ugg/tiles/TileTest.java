package ugg.tiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ugg.colors.Color;
import ugg.colors.ColorMaker;
import ugg.colors.SimpleColor;
import ugg.interfaces.Displayable;
import ugg.interfaces.SimpleDisplay;

public class TileTest {
    private static final String baseSymbol = Tile.defaultBaseText.substring(0, 1);

    @Test
    public void test_addContents_true() {
        Displayable contentsToAdd = new SimpleDisplay("contents", Tile.defaultBaseColor);

        Tile tile = new Tile("Tile");
        Assertions.assertEquals("Tile", tile.toString());

        Assertions.assertTrue(tile.addContents(contentsToAdd));
        Assertions.assertEquals("contents", tile.toString());
    }

    @Test
    public void test_addContents_false() {
        Displayable tileBase = Tile.defaultBaseDisplay;
        Displayable tileContents = new SimpleDisplay("Old", Tile.defaultBaseColor);

        Displayable contentsToAdd = new SimpleDisplay("New", Tile.defaultBaseColor);

        Tile tile = new Tile(tileBase, tileContents);
        Assertions.assertEquals("Old", tile.toString());

        Assertions.assertFalse(tile.addContents(contentsToAdd));
        Assertions.assertEquals("Old", tile.toString());
    }


    @Test
    public void test_swapContentsWith_firstHasContents() {
        Tile tileA = new Tile("A");
        Tile tileB = new Tile("B");

        tileA.addContents(new SimpleDisplay("Junk", ColorMaker.make(SimpleColor.BLUE)));

        Assertions.assertEquals("Junk", tileA.toString());
        Assertions.assertEquals("B", tileB.toString());

        tileA.swapContentsWith(tileB);

        Assertions.assertEquals("A", tileA.toString());
        Assertions.assertEquals("Junk", tileB.toString());
    }

    @Test
    public void test_swapContentsWith_secondHasContents() {
        Tile tileA = new Tile("A");
        Tile tileB = new Tile("B");

        tileB.addContents(new SimpleDisplay("Junk", ColorMaker.make(SimpleColor.BLUE)));

        Assertions.assertEquals("A", tileA.toString());
        Assertions.assertEquals("Junk", tileB.toString());

        tileA.swapContentsWith(tileB);

        Assertions.assertEquals("Junk", tileA.toString());
        Assertions.assertEquals("B", tileB.toString());
    }

    @Test
    public void test_swapContentsWith_bothHaveContents() {
        Tile tileA = new Tile("A");
        Tile tileB = new Tile("B");

        tileA.addContents(new SimpleDisplay("Junk", ColorMaker.make(SimpleColor.BLUE)));
        tileB.addContents(new SimpleDisplay("Trash", ColorMaker.make(SimpleColor.CYAN)));

        Assertions.assertEquals("Junk", tileA.toString());
        Assertions.assertEquals("Trash", tileB.toString());

        tileA.swapContentsWith(tileB);

        Assertions.assertEquals("Trash", tileA.toString());
        Assertions.assertEquals("Junk", tileB.toString());
    }

    @Test
    public void test_swapContentsWith_neitherHaveContents() {
        Tile tileA = new Tile("A");
        Tile tileB = new Tile("B");

        Assertions.assertEquals("A", tileA.toString());
        Assertions.assertEquals("B", tileB.toString());

        tileA.swapContentsWith(tileB);

        Assertions.assertEquals("A", tileA.toString());
        Assertions.assertEquals("B", tileB.toString());
    }


    @Test
    public void test_transferContentsTo_true() {
        Tile tileA = new Tile("A");
        Tile tileB = new Tile("B");

        tileA.addContents(new SimpleDisplay("Junk", ColorMaker.make(SimpleColor.BLUE)));

        Assertions.assertTrue(tileA.transferContentsTo(tileB));
    }

    @Test
    public void test_transferContentsTo_false_bothHaveContents() {
        Tile tileA = new Tile("A");
        Tile tileB = new Tile("B");

        tileA.addContents(new SimpleDisplay("Junk", ColorMaker.make(SimpleColor.BLUE)));
        tileB.addContents(new SimpleDisplay("Trash", ColorMaker.make(SimpleColor.CYAN)));

        Assertions.assertFalse(tileA.transferContentsTo(tileB));
    }

    @Test
    public void test_transferContentsTo_false_neitherHaveContents() {
        Tile tileA = new Tile("A");
        Tile tileB = new Tile("B");

        Assertions.assertFalse(tileA.transferContentsTo(tileB));
    }

    @Test
    public void test_transferContentsTo_false_firstHasNoContents() {
        Tile tileA = new Tile("A");
        Tile tileB = new Tile("B");

        tileB.addContents(new SimpleDisplay("Junk", ColorMaker.make(SimpleColor.BLUE)));

        Assertions.assertFalse(tileA.transferContentsTo(tileB));
    }


    @Test
    public void test_display_default() {
        String expectedDisplay = Tile.defaultBaseDisplay.display();
        String actualDisplay = new Tile().display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }

    @Test
    public void test_display_color() {
        Color red = ColorMaker.make(SimpleColor.RED);

        String expectedDisplay = red.colorize(baseSymbol);
        String actualDisplay = new Tile(red).display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }

    @Test
    public void test_display_text() {
        String expectedDisplay = Tile.defaultBaseColor.colorize("s");
        String actualDisplay = new Tile("string").display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }

    @Test
    public void test_display_both() {
        String tileBaseString = "bwa!";
        Color tileBaseColor = ColorMaker.make(SimpleColor.YELLOW);

        String expectedDisplay = tileBaseColor.colorize(tileBaseString.substring(0, 1));
        String actualDisplay = new Tile(tileBaseString, tileBaseColor).display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }

    @Test
    public void test_display_contents() {
        Tile tile = new Tile();
        String contentsString = "Hiya!";
        Color contentsColor = ColorMaker.make(SimpleColor.GREEN);
        tile.addContents(new SimpleDisplay(contentsString, ColorMaker.make(SimpleColor.GREEN)));

        String expectedDisplay = contentsColor.colorize(contentsString.substring(0, 1));
        String actualDisplay = tile.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }


    @Test
    public void test_equals_true_same() {
        Tile tile = new Tile();
        Assertions.assertEquals(tile, tile);
    }

    @Test
    public void test_notEquals_null() {
        Tile tile = new Tile();
        Assertions.assertNotEquals(tile, null);
    }

    @Test
    public void test_notEquals_differentClass() {
        // For test coverage
        Tile tile = new Tile();
        //noinspection AssertBetweenInconvertibleTypes
        Assertions.assertNotEquals(tile, "String!");
    }

    @Test
    public void test_equals_default() {
        Tile tile1 = new Tile();
        Tile tile2 = new Tile();
        Assertions.assertEquals(tile1, tile2);
    }

    @Test
    public void test_equals_onlyText() {
        Tile tile1 = new Tile("text");
        Tile tile2 = new Tile("text");
        Assertions.assertEquals(tile1, tile2);
    }

    @Test
    public void test_equals_onlyColors() {
        Color red = ColorMaker.make(SimpleColor.RED);
        Tile tile1 = new Tile(red);
        Tile tile2 = new Tile(red);
        Assertions.assertEquals(tile1, tile2);
    }

    @Test
    public void test_equals_both() {
        Color red = ColorMaker.make(SimpleColor.RED);
        Tile tile1 = new Tile("text", red);
        Tile tile2 = new Tile("text", red);
        Assertions.assertEquals(tile1, tile2);
    }

    @Test
    public void test_equals_nullText() {
        Color red = ColorMaker.make(SimpleColor.RED);
        Tile tile1 = new Tile(null, red);
        Tile tile2 = new Tile(null, red);
        Assertions.assertEquals(tile1, tile2);
    }

    @Test
    public void test_equals_nullColor() {
        Tile tile1 = new Tile("text", null);
        Tile tile2 = new Tile("text", null);
        Assertions.assertEquals(tile1, tile2);
    }

    @Test
    public void test_equals_bothNull() {
        Tile tile1 = new Tile((String) null, null);
        Tile tile2 = new Tile((String) null, null);
        Assertions.assertEquals(tile1, tile2);
    }



    @Test
    public void test_notEquals_onlyText() {
        Tile tile1 = new Tile("this");
        Tile tile2 = new Tile("that");
        Assertions.assertNotEquals(tile1, tile2);
    }

    @Test
    public void test_notEquals_onlyColors() {
        Tile tile1 = new Tile(ColorMaker.make(SimpleColor.RED));
        Tile tile2 = new Tile(ColorMaker.make(SimpleColor.YELLOW));
        Assertions.assertNotEquals(tile1, tile2);
    }

    @Test
    public void test_notEquals_both() {
        Tile tile1 = new Tile("this", ColorMaker.make(SimpleColor.RED));
        Tile tile2 = new Tile("that", ColorMaker.make(SimpleColor.YELLOW));
        Assertions.assertNotEquals(tile1, tile2);
    }

    @Test
    public void test_notEquals_nullText() {
        Color red = ColorMaker.make(SimpleColor.RED);
        Tile tile1 = new Tile(null, red);
        Tile tile2 = new Tile("not null!", red);
        Assertions.assertNotEquals(tile1, tile2);
    }

    @Test
    public void test_notEquals_nullColor() {
        Tile tile1 = new Tile("text", null);
        Tile tile2 = new Tile("text", ColorMaker.make(SimpleColor.YELLOW));
        Assertions.assertNotEquals(tile1, tile2);
    }

    @Test
    public void test_notEquals_bothNull() {
        Tile tile1 = new Tile((String) null, null);
        Tile tile2 = new Tile("not null!", ColorMaker.make(SimpleColor.YELLOW));
        Assertions.assertNotEquals(tile1, tile2);
    }


    @Test
    public void test_equals_contents_both() {
        Tile tile1 = new Tile();
        Tile tile2 = new Tile();

        tile1.addContents(new SimpleDisplay("contents", ColorMaker.make(SimpleColor.RED)));
        tile2.addContents(new SimpleDisplay("contents", ColorMaker.make(SimpleColor.RED)));

        Assertions.assertEquals(tile1, tile2);
    }

    @Test
    public void test_equals_contents_null() {
        Tile tile1 = new Tile();
        Tile tile2 = new Tile();

        Assertions.assertEquals(tile1, tile2);
    }

    @Test
    public void test_notEquals_contents_bothButDiff() {
        Tile tile1 = new Tile();
        Tile tile2 = new Tile();

        tile1.addContents(new SimpleDisplay("contents", ColorMaker.make(SimpleColor.RED)));
        tile2.addContents(new SimpleDisplay("uuh....contents..?", ColorMaker.make(SimpleColor.RED)));

        Assertions.assertNotEquals(tile1, tile2);
    }

    @Test
    public void test_notEquals_contents_firstNull() {
        Tile tile1 = new Tile();
        Tile tile2 = new Tile();

        tile2.addContents(new SimpleDisplay("contents", ColorMaker.make(SimpleColor.RED)));

        Assertions.assertNotEquals(tile1, tile2);
    }

    @Test
    public void test_notEquals_contents_secondNull() {
        Tile tile1 = new Tile();
        Tile tile2 = new Tile();

        tile1.addContents(new SimpleDisplay("contents", ColorMaker.make(SimpleColor.RED)));

        Assertions.assertNotEquals(tile1, tile2);
    }
}