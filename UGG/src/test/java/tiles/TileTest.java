package tiles;

import colors.Color;
import colors.ColorMaker;
import colors.SimpleColor;
import display.Displayable;
import display.SimpleDisplay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileTest {
    private Tile defaultTile() {
        return new Tile(Tile.defaultTileDisplay);
    }
    private Tile quickTextTile(String text) {
        return new Tile(text, null);
    }

    @Test
    void test_addContents_true() {
        Displayable contentsToAdd = new SimpleDisplay("contents", null);

        Tile tile = quickTextTile("Tile");
        Assertions.assertEquals("Tile", tile.toString());

        tile.addContents(contentsToAdd);
        Assertions.assertEquals("contents", tile.toString());
    }

    @Test
    void test_addContents_false() {
        Displayable contentsToAdd = new SimpleDisplay("New", null);

        Tile tile = new Tile(Tile.defaultTileDisplay);
        tile.addContents(new SimpleDisplay("Old", null));
        Assertions.assertEquals("Old", tile.toString());

        tile.addContents(contentsToAdd);
        Assertions.assertEquals("Old", tile.toString());
    }


    @Test
    void test_swapContentsWith_firstHasContents() {
        Tile tileA = quickTextTile("A");
        Tile tileB = quickTextTile("B");

        tileA.addContents(new SimpleDisplay("Junk", ColorMaker.make(SimpleColor.BLUE)));

        Assertions.assertEquals("Junk", tileA.toString());
        Assertions.assertEquals("B", tileB.toString());

        tileA.swapContentsWith(tileB);

        Assertions.assertEquals("A", tileA.toString());
        Assertions.assertEquals("Junk", tileB.toString());
    }

    @Test
    void test_swapContentsWith_secondHasContents() {
        Tile tileA = quickTextTile("A");
        Tile tileB = quickTextTile("B");

        tileB.addContents(new SimpleDisplay("Junk", ColorMaker.make(SimpleColor.BLUE)));

        Assertions.assertEquals("A", tileA.toString());
        Assertions.assertEquals("Junk", tileB.toString());

        tileA.swapContentsWith(tileB);

        Assertions.assertEquals("Junk", tileA.toString());
        Assertions.assertEquals("B", tileB.toString());
    }

    @Test
    void test_swapContentsWith_bothHaveContents() {
        Tile tileA = quickTextTile("A");
        Tile tileB = quickTextTile("B");

        tileA.addContents(new SimpleDisplay("Junk", ColorMaker.make(SimpleColor.BLUE)));
        tileB.addContents(new SimpleDisplay("Trash", ColorMaker.make(SimpleColor.CYAN)));

        Assertions.assertEquals("Junk", tileA.toString());
        Assertions.assertEquals("Trash", tileB.toString());

        tileA.swapContentsWith(tileB);

        Assertions.assertEquals("Trash", tileA.toString());
        Assertions.assertEquals("Junk", tileB.toString());
    }

    @Test
    void test_swapContentsWith_neitherHaveContents() {
        Tile tileA = quickTextTile("A");
        Tile tileB = quickTextTile("B");

        Assertions.assertEquals("A", tileA.toString());
        Assertions.assertEquals("B", tileB.toString());

        tileA.swapContentsWith(tileB);

        Assertions.assertEquals("A", tileA.toString());
        Assertions.assertEquals("B", tileB.toString());
    }


    @Test
    void test_transferContentsTo_true_emptyContents() {
        Tile tileA = quickTextTile("A");
        Tile tileB = quickTextTile("B");

        tileA.addContents(new SimpleDisplay("Junk", ColorMaker.make(SimpleColor.BLUE)));

        Assertions.assertTrue(tileA.transferContentsTo(tileB));
    }

    @Test
    void test_transferContentsTo_true_traversableFirstNoSecondYes() {
        Tile tileA = new Tile(Tile.defaultTileDisplay, false);
        Tile tileB = quickTextTile("B");

        tileA.addContents(new SimpleDisplay("Junk", ColorMaker.make(SimpleColor.BLUE)));

        Assertions.assertTrue(tileA.transferContentsTo(tileB));
    }

    @Test
    void test_transferContentsTo_false_bothHaveContents() {
        Tile tileA = quickTextTile("A");
        Tile tileB = quickTextTile("B");

        tileA.addContents(new SimpleDisplay("Junk", ColorMaker.make(SimpleColor.BLUE)));
        tileB.addContents(new SimpleDisplay("Trash", ColorMaker.make(SimpleColor.CYAN)));

        Assertions.assertFalse(tileA.transferContentsTo(tileB));
    }

    @Test
    void test_transferContentsTo_false_neitherHaveContents() {
        Tile tileA = quickTextTile("A");
        Tile tileB = quickTextTile("B");

        Assertions.assertFalse(tileA.transferContentsTo(tileB));
    }

    @Test
    void test_transferContentsTo_false_firstHasNoContents() {
        Tile tileA = quickTextTile("A");
        Tile tileB = quickTextTile("B");

        tileB.addContents(new SimpleDisplay("Junk", ColorMaker.make(SimpleColor.BLUE)));

        Assertions.assertFalse(tileA.transferContentsTo(tileB));
    }

    @Test
    void test_transferContentsTo_false_secondNotTraversable() {
        Tile tileA = quickTextTile("A");
        Tile tileB = new Tile(Tile.defaultTileDisplay, false);

        tileA.addContents(new SimpleDisplay("Junk", ColorMaker.make(SimpleColor.BLUE)));

        Assertions.assertFalse(tileA.transferContentsTo(tileB));
    }


    @Test
    void test_display_color() {
        Color red = ColorMaker.make(SimpleColor.RED);

        String expectedDisplay = red.colorize(Tile.defaultTileDisplayText);
        String actualDisplay = new Tile(new SimpleDisplay(Tile.defaultTileDisplayText, red)).display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }

    @Test
    void test_display_text() {
        String expectedDisplay = "s";
        String actualDisplay = quickTextTile("string").display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }

    @Test
    void test_display_both() {
        String tileBaseString = "bwa!";
        Color tileBaseColor = ColorMaker.make(SimpleColor.YELLOW);

        String expectedDisplay = tileBaseColor.colorize(tileBaseString.substring(0, 1));
        String actualDisplay = new Tile(tileBaseString, tileBaseColor).display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }

    @Test
    void test_display_contents() {
        Tile tile = defaultTile();
        String contentsString = "Hiya!";
        Color contentsColor = ColorMaker.make(SimpleColor.GREEN);
        tile.addContents(new SimpleDisplay(contentsString, ColorMaker.make(SimpleColor.GREEN)));

        String expectedDisplay = contentsColor.colorize(contentsString.substring(0, 1));
        String actualDisplay = tile.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }


    @Test
    void test_equals_true_same() {
        Tile tile = defaultTile();
        Assertions.assertEquals(tile, tile);
    }

    @Test
    void test_notEquals_null() {
        Tile tile = defaultTile();
        Assertions.assertNotEquals(tile, null);
    }

    @Test
    void test_notEquals_differentClass() {
        // For test coverage
        Tile tile = defaultTile();
        //noinspection AssertBetweenInconvertibleTypes
        Assertions.assertNotEquals(tile, "String!");
    }

    @Test
    void test_equals_default() {
        Tile tile1 = defaultTile();
        Tile tile2 = defaultTile();
        Assertions.assertEquals(tile1, tile2);
    }

    @Test
    void test_equals_onlyText() {
        Tile tile1 = quickTextTile("text");
        Tile tile2 = quickTextTile("text");
        Assertions.assertEquals(tile1, tile2);
    }

    @Test
    void test_equals_onlyColors() {
        Color red = ColorMaker.make(SimpleColor.RED);
        Tile tile1 = new Tile(new SimpleDisplay(null, red));
        Tile tile2 = new Tile(new SimpleDisplay(null, red));
        Assertions.assertEquals(tile1, tile2);
    }

    @Test
    void test_equals_both() {
        Color red = ColorMaker.make(SimpleColor.RED);
        Tile tile1 = new Tile("text", red);
        Tile tile2 = new Tile("text", red);
        Assertions.assertEquals(tile1, tile2);
    }

    @Test
    void test_equals_nullText() {
        Color red = ColorMaker.make(SimpleColor.RED);
        Tile tile1 = new Tile(null, red);
        Tile tile2 = new Tile(null, red);
        Assertions.assertEquals(tile1, tile2);
    }

    @Test
    void test_equals_nullColor() {
        Tile tile1 = new Tile("text", null);
        Tile tile2 = new Tile("text", null);
        Assertions.assertEquals(tile1, tile2);
    }

    @Test
    void test_equals_bothNull() {
        Tile tile1 = new Tile(null, null);
        Tile tile2 = new Tile(null, null);
        Assertions.assertEquals(tile1, tile2);
    }



    @Test
    void test_notEquals_onlyText() {
        Tile tile1 = quickTextTile("this");
        Tile tile2 = quickTextTile("that");
        Assertions.assertNotEquals(tile1, tile2);
    }

    @Test
    void test_notEquals_onlyColors() {
        Tile tile1 = new Tile(Tile.defaultTileDisplayText, ColorMaker.make(SimpleColor.RED));
        Tile tile2 = new Tile(Tile.defaultTileDisplayText, ColorMaker.make(SimpleColor.YELLOW));
        Assertions.assertNotEquals(tile1, tile2);
    }

    @Test
    void test_notEquals_both() {
        Tile tile1 = new Tile("this", ColorMaker.make(SimpleColor.RED));
        Tile tile2 = new Tile("that", ColorMaker.make(SimpleColor.YELLOW));
        Assertions.assertNotEquals(tile1, tile2);
    }

    @Test
    void test_notEquals_nullText() {
        Color red = ColorMaker.make(SimpleColor.RED);
        Tile tile1 = new Tile(null, red);
        Tile tile2 = new Tile("not null!", red);
        Assertions.assertNotEquals(tile1, tile2);
    }

    @Test
    void test_notEquals_nullColor() {
        Tile tile1 = new Tile("text", null);
        Tile tile2 = new Tile("text", ColorMaker.make(SimpleColor.YELLOW));
        Assertions.assertNotEquals(tile1, tile2);
    }

    @Test
    void test_notEquals_bothNull() {
        Tile tile1 = new Tile(null, null);
        Tile tile2 = new Tile("not null!", ColorMaker.make(SimpleColor.YELLOW));
        Assertions.assertNotEquals(tile1, tile2);
    }


    @Test
    void test_equals_contents_both() {
        Tile tile1 = defaultTile();
        Tile tile2 = defaultTile();

        tile1.addContents(new SimpleDisplay("contents", ColorMaker.make(SimpleColor.RED)));
        tile2.addContents(new SimpleDisplay("contents", ColorMaker.make(SimpleColor.RED)));

        Assertions.assertEquals(tile1, tile2);
    }

    @Test
    void test_equals_contents_null() {
        Tile tile1 = defaultTile();
        Tile tile2 = defaultTile();

        Assertions.assertEquals(tile1, tile2);
    }

    @Test
    void test_notEquals_contents_bothButDiff() {
        Tile tile1 = defaultTile();
        Tile tile2 = defaultTile();

        tile1.addContents(new SimpleDisplay("contents", ColorMaker.make(SimpleColor.RED)));
        tile2.addContents(new SimpleDisplay("uuh....contents..?", ColorMaker.make(SimpleColor.RED)));

        Assertions.assertNotEquals(tile1, tile2);
    }

    @Test
    void test_notEquals_contents_firstNull() {
        Tile tile1 = defaultTile();
        Tile tile2 = defaultTile();

        tile2.addContents(new SimpleDisplay("contents", ColorMaker.make(SimpleColor.RED)));

        Assertions.assertNotEquals(tile1, tile2);
    }

    @Test
    void test_notEquals_contents_secondNull() {
        Tile tile1 = defaultTile();
        Tile tile2 = defaultTile();

        tile1.addContents(new SimpleDisplay("contents", ColorMaker.make(SimpleColor.RED)));

        Assertions.assertNotEquals(tile1, tile2);
    }
}