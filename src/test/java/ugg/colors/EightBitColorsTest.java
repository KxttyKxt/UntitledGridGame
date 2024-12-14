package ugg.colors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EightBitColorsTest extends SimpleColorsTest {
    @Test
    public void test_colorize_invalidColor() {
        boolean exceptionForInvalidColorWasThrown = false;

        try {
            EightBitColors.colorize("test", -1, false);
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
        int colorID = 18;
        String stringToColorize = "Look!";

        String expectedColorizedString = String.format("%s%s%s",
                "\u001B[38;5;18m", stringToColorize, "\u001B[0m"
        );
        String actualColorizedString = EightBitColors.colorize(stringToColorize, colorID, false);

        handleColorizedStrings(expectedColorizedString, actualColorizedString);
    }

    @Test
    public void test_colorize_yesBG() {
        int colorID = 18;
        String stringToColorize = "Look!";

        String expectedColorizedString = String.format("%s%s%s",
                "\u001B[48;5;18m", stringToColorize, "\u001B[0m"
        );
        String actualColorizedString = EightBitColors.colorize(stringToColorize, colorID, true);

        handleColorizedStrings(expectedColorizedString, actualColorizedString);
    }
}
