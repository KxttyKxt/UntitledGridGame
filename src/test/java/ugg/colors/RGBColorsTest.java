package ugg.colors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RGBColorsTest extends SimpleColorsTest {
    @Test
    public void test_colorize_invalidColor() {
        boolean exceptionForInvalidColorWasThrown = false;

        try {
            RGBColors.colorize("test", new int[]{0, 0, -1}, false);
        }
        catch (IllegalArgumentException colorIdWasInvalidException) {
            exceptionForInvalidColorWasThrown = true;
        }
        finally {
            Assertions.assertTrue(exceptionForInvalidColorWasThrown);
        }
    }

    @Test
    public void test_colorize_noBG() {
        int[] colorIDs = {200, 200, 200};
        String stringToColorize = "Look!";

        String expectedColorizedString = String.format("%s%s%s",
                "\u001B[38;2;200;200;200m", stringToColorize, "\u001B[0m"
        );
        String actualColorizedString = RGBColors.colorize(stringToColorize, colorIDs, false);

        handleColorizedStrings(expectedColorizedString, actualColorizedString);
    }

    @Test
    public void test_colorize_yesBG() {
        int[] colorIDs = {200, 200, 200};
        String stringToColorize = "Look!";

        String expectedColorizedString = String.format("%s%s%s",
                "\u001B[48;2;200;200;200m", stringToColorize, "\u001B[0m"
        );
        String actualColorizedString = RGBColors.colorize(stringToColorize, colorIDs, true);

        handleColorizedStrings(expectedColorizedString, actualColorizedString);
    }
}
