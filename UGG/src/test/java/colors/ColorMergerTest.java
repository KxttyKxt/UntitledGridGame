package colors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ColorMergerTest {
    @Test
    void test_merge_singleColorSoSameExactObject() {
        Color expectedColor = ColorMaker.make(SimpleColor.GREEN);
        Color[] arrayOfOneColor = new Color[]{expectedColor};
        Color actualColor = ColorMerger.merge(arrayOfOneColor);

        Assertions.assertSame(expectedColor, actualColor);
    }


    @Test
    void test_mergeColors_multiple() {
        Color fgColor = ColorMaker.make(SimpleColor.MAGENTA);
        Color bgColor = ColorMaker.make(SimpleColor.BG_BLUE);

        Color[] colorsArray = new Color[]{fgColor, bgColor};
        Color mergedColor = ColorMerger.merge(colorsArray);

        String expectedAnsiCode = "\u001B[35m\u001B[44m";
        String actualAnsiCode = mergedColor.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
    }


    @Test
    void test_deduplicateColors_onePairWithNonDuplicate() {
        Color[] colors = {
                ColorMaker.make(SimpleColor.RED),
                ColorMaker.make(SimpleColor.RED),
                ColorMaker.make(SimpleColor.GREEN)
        };
        Color mergedColor = ColorMerger.merge(colors);

        String expectedAnsiCode = "\u001B[31m\u001B[32m";
        String actualAnsiCode = mergedColor.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
    }

    @Test
    void test_deduplicateColors_onlyOneDuplicatePair() {
        Color[] colors = {
                ColorMaker.make(SimpleColor.RED),
                ColorMaker.make(SimpleColor.RED)
        };
        Color mergedColor = ColorMerger.merge(colors);

        String expectedAnsiCode = "\u001B[31m";
        String actualAnsiCode = mergedColor.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
    }

    @Test
    void test_deduplicateColors_twoDuplicatePairs() {
        Color[] colors = {
                ColorMaker.make(SimpleColor.RED),
                ColorMaker.make(SimpleColor.RED),
                ColorMaker.make(SimpleColor.GREEN),
                ColorMaker.make(SimpleColor.GREEN),
                ColorMaker.make(SimpleColor.BLUE)
        };
        Color mergedColor = ColorMerger.merge(colors);

        String expectedAnsiCode = "\u001B[31m\u001B[32m\u001B[34m";
        String actualAnsiCode = mergedColor.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
    }

    @Test
    void test_deduplicateColors_noPairButHasNull() {
        Color[] colors = {
                ColorMaker.make(SimpleColor.RED),
                null
        };
        Color mergedColor = ColorMerger.merge(colors);

        String expectedAnsiCode = "\u001B[31m";
        String actualAnsiCode = mergedColor.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
    }

    @Test
    void test_deduplicateColors_onePairButHasNull() {
        Color[] colors = {
                ColorMaker.make(SimpleColor.RED),
                ColorMaker.make(SimpleColor.RED),
                null
        };
        Color mergedColor = ColorMerger.merge(colors);

        String expectedAnsiCode = "\u001B[31m";
        String actualAnsiCode = mergedColor.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
    }
}
