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
        System.out.println(actualColor.colorize("sample"));
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
        System.out.println(mergedColor.colorize("sample"));
    }


    @Test
    public void test_mergeColors_error() {
        boolean errorThrown = false;

        try {
            Color mergedColor = ColorMerger.mergeColors(new Color[]{
                    Colorizer.getColor(SimpleColor.RED), null
            });

            System.out.printf("%s%n", mergedColor);
        }
        catch (IllegalArgumentException nullColorWasPassedInArray) {
            errorThrown = true;
        }
        finally {
            Assertions.assertTrue(errorThrown);
        }
    }
}
