package ugg.tiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ugg.colors.Color;

import static ugg.tiles.PresetTestingResources.*;

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

    private void swapWithTileAAndTest(Tile toSwapWith) {
        String tileAContents = tileA.toString();
        String tileToSwapWithContents = toSwapWith.toString();

        tileA.swapContentsWith(toSwapWith);

        Assertions.assertEquals(tileAContents, toSwapWith.toString());
        Assertions.assertEquals(tileToSwapWithContents, PresetTestingResources.tileA.toString());

        tileA.swapContentsWith(toSwapWith);
    }


    @Test
    public void test_transferContents_to_true() {
        resetTransferTilesAndRunTransferTest(emptyTransferTile, true);
    }

    @Test
    public void test_transferContents_to_false() {
        resetTransferTilesAndRunTransferTest(transferTileB, false);
    }

    private void resetTransferTilesAndRunTransferTest(Tile toTransferTo, boolean expectedResult) {
        resetTileTransfers();
        transferWithTransferTileAAndTest(toTransferTo, expectedResult);
    }
    private void transferWithTransferTileAAndTest(Tile targetTile, boolean expectedResult) {
        String transferTileAContentsBefore = transferTileA.toString();
        String targetTileContentsBefore = targetTile.toString();

        boolean transferredSuccessfully = transferTileA.transferContentsTo(targetTile);
        Assertions.assertEquals(expectedResult, transferredSuccessfully);

        String transferTileAContentsAfter = transferTileA.toString();
        String targetTileContentsAfter = targetTile.toString();

        if (transferredSuccessfully) {
            Assertions.assertEquals(transferTileAContentsBefore, targetTileContentsAfter);
            Assertions.assertEquals(targetTileContentsBefore, transferTileAContentsAfter);
        }
        else {
            Assertions.assertEquals(transferTileAContentsBefore, transferTileAContentsAfter);
            Assertions.assertEquals(targetTileContentsBefore, targetTileContentsAfter);
        }
    }
    private void resetTileTransfers() {
        boolean transferTileAIsCorrect;
        boolean transferTileBIsCorrect;
        boolean emptyTransferTileIsCorrect;
        boolean resetComplete;

        while (true) {
            transferTileAIsCorrect = transferTileA.toString().equals("A");
            transferTileBIsCorrect = transferTileB.toString().equals("B");
            emptyTransferTileIsCorrect = emptyTransferTile.toString().isEmpty();

            resetComplete = transferTileAIsCorrect
                            && transferTileBIsCorrect
                            && emptyTransferTileIsCorrect;

            if (resetComplete) {
                break;
            }


            if (!transferTileAIsCorrect) {
                transferTileA.swapContentsWith(
                        (transferTileB.toString().equals("A"))
                        ? transferTileB
                        : emptyTransferTile
                );
            }

            if (!transferTileBIsCorrect) {
                transferTileB.swapContentsWith(
                        (transferTileA.toString().equals("B"))
                        ? transferTileA
                        : emptyTransferTile
                );
            }
        }
    }

    private final Tile transferTileA = new Tile("A");
    private final Tile transferTileB = new Tile("B");
    private final Tile emptyTransferTile = new Tile();


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
    public void test_displayContents_singleColorFG() {
        testColoredSymbolEqualsColoredTileDisplay(RED, redTile);
    }

    @Test
    public void test_displayContents_singleColorBG() {
        testColoredSymbolEqualsColoredTileDisplay(BG_RED, bgRedTile);
    }

    @Test
    public void test_displayContents_mergedColor() {
        testColoredSymbolEqualsColoredTileDisplay(MERGED_COLOR, mergedColorTile);
    }

    @Test
    public void test_displayContents_nullColor() {
        testColoredSymbolEqualsColoredTileDisplay(null, uncoloredTile);
    }

    private void testColoredSymbolEqualsColoredTileDisplay(Color color, Tile tile) {
        String expectedDisplay = (color != null)
                ? color.colorize(COLORED_CONTENTS)
                : COLORED_CONTENTS;
        String actualDisplay = tile.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }


    @Test
    public void test_toString_contents() {
        Assertions.assertEquals("A", tileA.toString());
    }

    @Test
    public void test_toString_empty() {
        Assertions.assertEquals("", emptyTile.toString());
    }

    @Test
    public void test_toString_nullWhichMeansEmpty() {
        Assertions.assertEquals("", nullTile.toString());
    }


    @Test
    public void test_isIdenticalTo_true_noColor() {
        Tile newTileA = new Tile("A");
        testIsIdenticalToBiconditionally(tileA, newTileA, true);
    }

    @Test
    public void test_isIdenticalTo_true_yesColor() {
        Tile newRedTile = new Tile(COLORED_CONTENTS, RED);
        testIsIdenticalToBiconditionally(redTile, newRedTile, true);
    }

    @Test
    public void test_isIdenticalTo_false_oneColorIsNull() {
        testIsIdenticalToBiconditionally(redTile, tileA, false);
    }

    @Test
    public void test_isIdenticalTo_false_contentsDoNotMatch() {
        testIsIdenticalToBiconditionally(tileA, tileB, false);
    }

    @Test
    public void test_isIdenticalTo_false_colorsDoNotMatch() {
        testIsIdenticalToBiconditionally(redTile, greenTile, false);
    }

    @Test
    public void test_isIdenticalTo_false_allDoNotMatch() {
        testIsIdenticalToBiconditionally(tileA, redTile, false);
    }

    private void testIsIdenticalToBiconditionally(Tile tile1, Tile tile2, boolean expectedResult) {
        Assertions.assertEquals(expectedResult, tile1.isIdenticalTo(tile2));
        Assertions.assertEquals(expectedResult, tile2.isIdenticalTo(tile1));
    }
}
