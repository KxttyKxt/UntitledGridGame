package tiles;

import colors.ColorMaker;
import colors.SimpleColor;
import display.Displayable;
import display.SimpleDisplay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileTest {

    private Tile tileA() {
        return Tile.withOnlyText("A");
    }
    private Tile tileB() {
        return Tile.withOnlyText("B");
    }

    private Tile blueJunkTileWithContents() {
        return Tile.withTileDisplay(Tile.defaultTileDisplay)
                .andContentsDisplay(blueJunkDisplay())
                .build();
    }
    private SimpleDisplay blueJunkDisplay() {
        return SimpleDisplay.withText("Junk").andColor(ColorMaker.make(SimpleColor.BLUE));
    }

    @Test
    void test_addContents_true() {
        Tile tile = Tile.defaultTile();
        Displayable contents = SimpleDisplay.withOnlyText("contents");

        Assertions.assertTrue(tile.addContents(contents));
    }

    @Test
    void test_addContents_false() {
        Displayable preExistingContents = SimpleDisplay.withOnlyText("Old");
        Displayable contentsToAdd = SimpleDisplay.withOnlyText("New");

        Tile tile = Tile.defaultTile();
        Assertions.assertTrue(tile.addContents(preExistingContents));
        Assertions.assertFalse(tile.addContents(contentsToAdd));
    }


    @Test
    void test_transferContentsTo_true_emptyContents() {
        Tile tileA = tileA();
        Tile tileB = tileB();

        tileA.addContents(blueJunkDisplay());

        Assertions.assertTrue(tileA.transferContentsTo(tileB));
    }

    @Test
    void test_transferContentsTo_true_traversableFirstNoSecondYes() {

        Tile tileA = Tile.withTileDisplay(Tile.defaultTileDisplay).andTraversable(false).build();
        Tile tileB = tileB();

        tileA.addContents(blueJunkDisplay());

        Assertions.assertTrue(tileA.transferContentsTo(tileB));
    }

    @Test
    void test_transferContentsTo_false_bothHaveContents() {
        Displayable contentsA = SimpleDisplay.withText("Junk").andColor(ColorMaker.make(SimpleColor.BLUE));
        Displayable contentsB = SimpleDisplay.withText("Trash").andColor(ColorMaker.make(SimpleColor.CYAN));

        Tile tileA = tileA();
        tileA.addContents(contentsA);
        Tile tileB = tileB();
        tileB.addContents(contentsB);

        Assertions.assertFalse(tileA.transferContentsTo(tileB));
    }

    @Test
    void test_transferContentsTo_false_neitherHaveContents() {
        Tile tileA = tileA();
        Tile tileB = tileB();

        Assertions.assertFalse(tileA.transferContentsTo(tileB));
    }

    @Test
    void test_transferContentsTo_false_firstHasNoContents() {
        Tile tileA = tileA();
        Tile tileB = tileB();

        tileB.addContents(blueJunkDisplay());

        Assertions.assertFalse(tileA.transferContentsTo(tileB));
    }

    @Test
    void test_transferContentsTo_false_secondNotTraversable() {
        Tile tileA = blueJunkTileWithContents();
        Tile tileB = Tile.withTileDisplay(Tile.defaultTileDisplay).andTraversable(false).build();

        Assertions.assertFalse(tileA.transferContentsTo(tileB));
    }


    @Test
    void test_display_tile() {
        Tile tileToDisplay = Tile.defaultTile();

        String expectedDisplay = Tile.defaultTileDisplay.display();
        String actualDisplay = tileToDisplay.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }

    @Test
    void test_display_contents() {
        Tile tileToDisplay = blueJunkTileWithContents();

        String expectedDisplay = blueJunkDisplay().display();
        String actualDisplay = tileToDisplay.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }
}