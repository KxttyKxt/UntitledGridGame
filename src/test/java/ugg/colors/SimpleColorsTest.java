package ugg.colors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleColorsTest {
    protected void handleColorizedStrings(String expectedColorizedString, String actualColorizedString) {
        Assertions.assertEquals(expectedColorizedString, actualColorizedString);

        System.out.printf("Expected: %-40s | Actual: %s%n",
                expectedColorizedString, actualColorizedString
        );
    }


    @Test
    public void test_colorize_red() {
        String stringToColorize = "Look, I'm red!";

        String expectedColorizedString = String.format("%s%s%s",
                "\u001B[31m", stringToColorize, "\u001B[0m"
        );
        String actualColorizedString = SimpleColors.colorize(stringToColorize, SimpleColors.RED);

        handleColorizedStrings(expectedColorizedString, actualColorizedString);
    }

    @Test
    public void test_colorize_brightGreen() {
        String stringToColorize = "Look, I'm bright green!";

        String expectedColorizedString = String.format("%s%s%s",
                "\u001B[92m", stringToColorize, "\u001B[0m"
        );
        String actualColorizedString = SimpleColors.colorize(stringToColorize, SimpleColors.BRIGHT_GREEN);

        handleColorizedStrings(expectedColorizedString, actualColorizedString);
    }


    @Test
    public void test_colorize_redBG() {
        String stringToColorize = "Look, it's red!";

        String expectedColorizedString = String.format("%s%s%s",
                "\u001B[41m", stringToColorize, "\u001B[0m"
        );
        String actualColorizedString = SimpleColors.colorize(stringToColorize, SimpleColors.BG_RED);

        handleColorizedStrings(expectedColorizedString, actualColorizedString);
    }

    @Test
    public void test_colorize_brightGreenBG() {
        String stringToColorize = "Look, it's bright green!";

        String expectedColorizedString = String.format("%s%s%s",
                "\u001B[102m", stringToColorize, "\u001B[0m"
        );
        String actualColorizedString = SimpleColors.colorize(stringToColorize, SimpleColors.BG_BRIGHT_GREEN);

        handleColorizedStrings(expectedColorizedString, actualColorizedString);
    }


    @Test
    public void test_colorize_invalidColor() {
        int invalidColorCode = -1;
        boolean errorThrownForInvalidColor = false;

        try {
            SimpleColors.colorize("test", invalidColorCode);
        }
        catch (IllegalArgumentException colorCodeWasNotValidException) {
            errorThrownForInvalidColor = true;
        }
        finally {
            Assertions.assertTrue(errorThrownForInvalidColor);
        }
    }
}
