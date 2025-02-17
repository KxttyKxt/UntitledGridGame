package ugg.colors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CompoundColorizerTest {
    @Test
    public void test_getCompoundColor_singleColorSoReturnExactSameObject() {
        Color expectedColor = Colorizer.getColor(SimpleColor.GREEN);
        Color[] arrayOfOneColor = new Color[]{expectedColor};
        Color actualColor = CompoundColorizer.getCompoundColor(arrayOfOneColor);

        Assertions.assertSame(expectedColor, actualColor);
        System.out.println(actualColor.colorize("sample"));
    }

    @Test
    public void test_getCompoundColors_multipleColors() {
        Color fgColor = Colorizer.getColor(SimpleColor.MAGENTA);
        Color bgColor = Colorizer.getColor(SimpleColor.BG_BLUE);
        Color[] colorsArray = new Color[]{fgColor, bgColor};
        Color compoundColor = CompoundColorizer.getCompoundColor(colorsArray);

        String expectedAnsiCode = "\u001B[35m\u001B[44m";
        String actualAnsiCode = compoundColor.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
        System.out.println(compoundColor.colorize("sample"));
    }


    @Test
    public void test_getCompoundColors_nullColorError() {
        boolean errorThrown = false;

        try {
            Color compoundColor = CompoundColorizer.getCompoundColor(new Color[]{
                    Colorizer.getColor(SimpleColor.RED), null
            });
            System.out.printf("%s%n", compoundColor);
        }
        catch (IllegalArgumentException nullColorWasPassedInArray) {
            errorThrown = true;
        }
        finally {
            Assertions.assertTrue(errorThrown);
        }
    }
}
