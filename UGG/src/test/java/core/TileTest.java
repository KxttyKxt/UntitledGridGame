package core;

import colors.ColorMaker;
import colors.SimpleColor;
import display.Displayable;
import display.SimpleDisplay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TileTest {

    private Tile tileA() {
        return Tile.withOnlyText("A");
    }
    private Tile tileB() {
        return Tile.withOnlyText("B");
    }

    private Tile tileWithBlueJunkContents() {
        return Tile.withTileDisplay(Tile.defaultTileDisplay)
                .andOccupant(blueJunkOccupant())
                .build();
    }
    private Occupant blueJunkOccupant() {
        return Occupant.newOccupant(blueJunkDisplay());
    }
    private Displayable blueJunkDisplay() {
        return SimpleDisplay.withText("Junk").andColor(ColorMaker.make(SimpleColor.BLUE));
    }

    private Occupant defaultOccupant() {
        return Occupant.newOccupant();
    }


    @Test
    void test_addContents_true() {
        Tile tile = Tile.defaultTile();
        Occupant occupant = defaultOccupant();

        Assertions.assertTrue(tile.addContents(occupant));
    }

    @Test
    void test_addContents_false() {
        Occupant preExistingOccupant = Occupant.withOnlyText("Old");
        Occupant occupantToAdd = Occupant.withOnlyText("New");

        Tile tile = Tile.defaultTile();
        Assertions.assertTrue(tile.addContents(preExistingOccupant));
        Assertions.assertFalse(tile.addContents(occupantToAdd));
    }


    @Test
    void test_transferContentsTo_true_emptyOccupant() {
        Tile tileA = tileA();
        Tile tileB = tileB();

        tileA.addContents(blueJunkOccupant());

        Assertions.assertTrue(tileA.transferOccupantTo(tileB));
    }

    @Test
    void test_transferOccupantTo_true_traversableFirstNoSecondYes() {

        Tile tileA = Tile.withTileDisplay(Tile.defaultTileDisplay).andTraversable(false).build();
        Tile tileB = tileB();

        tileA.addContents(blueJunkOccupant());

        Assertions.assertTrue(tileA.transferOccupantTo(tileB));
    }

    @Test
    void test_transferContentsTo_false_bothHaveOccupant() {
        Occupant occupantA = blueJunkOccupant();
        Occupant occupantB = Occupant.newOccupant(
                SimpleDisplay.withText("Trash")
                        .andColor(ColorMaker.make(SimpleColor.CYAN))
        );

        Tile tileA = tileA();
        tileA.addContents(occupantA);
        Tile tileB = tileB();
        tileB.addContents(occupantB);

        Assertions.assertFalse(tileA.transferOccupantTo(tileB));
    }

    @Test
    void test_transferContentsTo_false_neitherHaveOccupant() {
        Tile tileA = tileA();
        Tile tileB = tileB();

        Assertions.assertFalse(tileA.transferOccupantTo(tileB));
    }

    @Test
    void test_transferContentsTo_false_firstHasNoOccupant() {
        Tile tileA = tileA();
        Tile tileB = tileB();

        tileB.addContents(blueJunkOccupant());

        Assertions.assertFalse(tileA.transferOccupantTo(tileB));
    }

    @Test
    void test_transferOccupantTo_false_secondNotTraversable() {
        Tile tileA = tileWithBlueJunkContents();
        Tile tileB = Tile.withTileDisplay(Tile.defaultTileDisplay).andTraversable(false).build();

        Assertions.assertFalse(tileA.transferOccupantTo(tileB));
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
        Tile tileToDisplay = tileWithBlueJunkContents();

        String expectedDisplay = blueJunkOccupant().display();
        String actualDisplay = tileToDisplay.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }


    @Test
    void test_equals_true_same() {
        Tile tile = Tile.defaultTile();
        Assertions.assertEquals(tile, tile);
    }

    @Test
    void test_equals_true_nullContents() {
        Tile tile1 = Tile.defaultTile();
        Tile tile2 = Tile.defaultTile();

        Assertions.assertEquals(tile1, tile2);
    }

    @Test
    void test_equals_true_equalContents() {
        Tile tile1 = tileWithBlueJunkContents();
        Tile tile2 = tileWithBlueJunkContents();

        Assertions.assertEquals(tile1, tile2);
    }


    @Test
    void test_notEquals_differentClass() {
        // assertEquals is necessary to fulfill the usage of Tile#equals()
        //noinspection AssertBetweenInconvertibleTypes
        Assertions.assertNotEquals(Tile.defaultTile(), "literally a string");
    }

    @Test
    void test_notEquals_null() {
        Assertions.assertNotEquals(Tile.defaultTile(), null);
    }

    @Test
    void test_notEquals_differentTiles() {
        Tile tile1 = Tile.defaultTile();
        Tile tile2 = Tile.withOnlyText("A");

        Assertions.assertNotEquals(tile1, tile2);
    }

    @Test
    void test_notEquals_oneHasNullContents() {
        Tile noContents = Tile.defaultTile();
        Tile yesContents = tileWithBlueJunkContents();

        Assertions.assertNotEquals(noContents, yesContents);
        Assertions.assertNotEquals(yesContents, noContents);
    }

    @Test
    void test_notEquals_differentContents() {
        Tile blueJunkTile = tileWithBlueJunkContents();

        Tile diffTile = Tile.withTileDisplay(Tile.defaultTileDisplay)
                .andOccupant(Occupant.withOnlyText("bwa")).build();

        Assertions.assertNotEquals(blueJunkTile, diffTile);
    }

    @Test
    void test_notEquals_differentTraversability() {
        Tile traversableTile = Tile.defaultTile();
        Tile notTraversableTile = Tile.withTileDisplay(Tile.defaultTileDisplay)
                .andTraversable(false)
                .build();

        Assertions.assertNotEquals(traversableTile, notTraversableTile);
    }

}