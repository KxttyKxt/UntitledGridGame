package ugg.tiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ugg.colors.Color;
import ugg.colors.ColorMaker;
import ugg.colors.ColorMerger;
import ugg.colors.SimpleColor;

import static ugg.tiles.Tile.COLORED_CONTENTS;

public class TileTest {
    @BeforeAll
    public static void initializeTestingObjects() {
        tileA = new Tile("A");
        tileB = new Tile("B");
        emptyContentsTile = new Tile();
        nullContentsTile = new Tile((String) null);

        transferTileA = new Tile("A");
        transferTileB = new Tile("B");
        emptyTransferTile = new Tile();

        red = ColorMaker.make(SimpleColor.RED);
        bgRed = ColorMaker.make(SimpleColor.BG_RED);
        mergedColor = ColorMerger.merge(new Color[]{
                red, ColorMaker.make(SimpleColor.BG_GREEN)
        });

        redTile = new Tile(red);
        bgRedTile = new Tile(bgRed);
        mergedColorTile = new Tile(mergedColor);
        uncoloredTile = new Tile((Color) null);
    }

    private static Tile tileA, tileB, emptyContentsTile, nullContentsTile;
    private static Tile transferTileA, transferTileB, emptyTransferTile;

    private static Color red, bgRed, mergedColor;
    private static Tile redTile, bgRedTile, mergedColorTile, uncoloredTile;


    @Test
    public void test_swapContents() {
        swapWithTileAAndTest(tileB);
    }

    @Test
    public void test_swapContents_withEmpty() {
        swapWithTileAAndTest(emptyContentsTile);
    }

    @Test
    public void test_swapContents_withNullWhichMeansEmpty() {
        swapWithTileAAndTest(nullContentsTile);
    }

    private void swapWithTileAAndTest(Tile toSwapWith) {
        String tileAContents = tileA.toString();
        String tileToSwapWithContents = toSwapWith.toString();

        tileA.swapContentsWith(toSwapWith);

        Assertions.assertEquals(tileAContents, toSwapWith.toString());
        Assertions.assertEquals(tileToSwapWithContents, tileA.toString());

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
        resetTransferTiles();
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
    private void resetTransferTiles() {
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


    @Test
    public void test_displayContents_emptyContents() {
        String expectedDisplay = Tile.EMPTY_CONTENTS_DISPLAY;
        String actualDisplay = emptyContentsTile.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }

    @Test
    public void test_displayContents_nullWhichMeansEmptyContents() {
        String expectedDisplay = Tile.EMPTY_CONTENTS_DISPLAY;
        String actualDisplay = nullContentsTile.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }

    @Test
    public void test_displayContents_singleColorFG() {
        testColoredSymbolEqualsColoredTileDisplay(red, redTile);
    }

    @Test
    public void test_displayContents_singleColorBG() {
        testColoredSymbolEqualsColoredTileDisplay(bgRed, bgRedTile);
    }

    @Test
    public void test_displayContents_mergedColor() {
        testColoredSymbolEqualsColoredTileDisplay(mergedColor, mergedColorTile);
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
        Assertions.assertEquals("", emptyContentsTile.toString());
    }

    @Test
    public void test_toString_nullWhichMeansEmpty() {
        Assertions.assertEquals("", nullContentsTile.toString());
    }


    @Test
    public void test_equals_same() {
        // Applies coverage to Tile#equals()
        //noinspection EqualsWithItself
        Assertions.assertSame(tileA, tileA);
    }

    @Test
    public void test_equals_true_noColor() {
        Tile newTileA = new Tile("A");

        Assertions.assertNotSame(tileA, newTileA);
        Assertions.assertEquals(tileA, newTileA);
    }

    @Test
    public void test_equals_true_yesColor() {
        Tile newRedTile = new Tile(red);
        Assertions.assertEquals(redTile, newRedTile);
    }

    @Test
    public void test_equals_false_oneColorIsNull() {
        Tile tileARed = new Tile("A", red);
        Assertions.assertNotEquals(tileA, tileARed);
    }

    @Test
    public void test_equals_false_contentsDoNotMatch() {
        Assertions.assertNotEquals(tileA, tileB);
    }

    @Test
    public void test_equals_false_colorsDoNotMatch() {
        Assertions.assertNotEquals(redTile, bgRedTile);
    }

    @Test
    public void test_equals_false_allDoNotMatch() {
        Assertions.assertNotEquals(tileA, redTile);
    }

    @Test
    public void test_equals_false_nullComparison() {
        Assertions.assertNotEquals(tileA, null);
    }

    @Test
    public void test_equals_false_notATileObject() {
        // Applies coverage to Tile#equals()
        //noinspection AssertBetweenInconvertibleTypes
        Assertions.assertNotEquals(redTile, red);
    }
}
