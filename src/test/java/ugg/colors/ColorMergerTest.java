package ugg.colors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ColorMergerTest {
    @Test
    public void test_mergeColors_singleColorSoSameExactObject() {
        Color expectedColor = Colorizer.getColor(SimpleColor.GREEN);
        Color[] arrayOfOneColor = new Color[]{expectedColor};
        Color actualColor = ColorMerger.mergeColors(arrayOfOneColor);

        Assertions.assertSame(expectedColor, actualColor);
    }

    @Test
    public void test_mergeColors_multipleColors() {
        Color fgColor = Colorizer.getColor(SimpleColor.MAGENTA);
        Color bgColor = Colorizer.getColor(SimpleColor.BG_BLUE);

        Color[] colorsArray = new Color[]{fgColor, bgColor};
        Color mergedColor = ColorMerger.mergeColors(colorsArray);

        String expectedAnsiCode = "\u001B[35m\u001B[44m";
        String actualAnsiCode = mergedColor.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
    }


    @Test
    public void test_deduplicateColors_onePairWithNonDuplicate() {
        Color[] colors = {
                Colorizer.getColor(SimpleColor.RED),
                Colorizer.getColor(SimpleColor.RED),
                Colorizer.getColor(SimpleColor.GREEN)
        };
        Color mergedColor = ColorMerger.mergeColors(colors);

        String expectedAnsiCode = "\u001B[31m\u001B[32m";
        String actualAnsiCode = mergedColor.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
    }

    @Test
    public void test_deduplicateColors_onlyOneDuplicatePair() {
        Color[] colors = {
                Colorizer.getColor(SimpleColor.RED),
                Colorizer.getColor(SimpleColor.RED)
        };
        Color mergedColor = ColorMerger.mergeColors(colors);

        String expectedAnsiCode = "\u001B[31m";
        String actualAnsiCode = mergedColor.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
    }

    @Test
    public void test_deduplicateColors_twoDuplicatePairs() {
        Color[] colors = {
                Colorizer.getColor(SimpleColor.RED),
                Colorizer.getColor(SimpleColor.RED),
                Colorizer.getColor(SimpleColor.GREEN),
                Colorizer.getColor(SimpleColor.GREEN),
                Colorizer.getColor(SimpleColor.BLUE)
        };
        Color mergedColor = ColorMerger.mergeColors(colors);

        String expectedAnsiCode = "\u001B[31m\u001B[32m\u001B[34m";
        String actualAnsiCode = mergedColor.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
    }

    @Test
    public void test_deduplicateColors_noPairButHasNull() {
        Color[] colors = {
                Colorizer.getColor(SimpleColor.RED),
                null
        };
        Color mergedColor = ColorMerger.mergeColors(colors);

        String expectedAnsiCode = "\u001B[31m";
        String actualAnsiCode = mergedColor.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
    }

    @Test
    public void test_deduplicateColors_onePairButHasNull() {
        Color[] colors = {
                Colorizer.getColor(SimpleColor.RED),
                Colorizer.getColor(SimpleColor.RED),
                null
        };
        Color mergedColor = ColorMerger.mergeColors(colors);

        String expectedAnsiCode = "\u001B[31m";
        String actualAnsiCode = mergedColor.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
    }
}
