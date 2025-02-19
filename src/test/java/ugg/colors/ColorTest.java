package ugg.colors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ColorTest {
    @Test
    public void test_colorize_simpleColor() {
        Color color = Colorizer.getColor(SimpleColor.GREEN);
        String input = "I am green!";

        String expectedOutput = String.format("\u001B[32m%s%s", input, Colorizer.ansiReset);
        String actualOutput = color.colorize(input);

        Assertions.assertEquals(expectedOutput, actualOutput);
        System.out.println(color.colorize("sample"));
    }

    @Test
    public void test_colorize_colorID() {
        Color color = Colorizer.getColor(17, false);
        String input = "I am 8-bit!";

        String expectedOutput = String.format("\u001B[38;5;17m%s%s", input, Colorizer.ansiReset);
        String actualOutput = color.colorize(input);

        Assertions.assertEquals(expectedOutput, actualOutput);
        System.out.println(color.colorize("sample"));
    }

    @Test
    public void test_colorize_rgb() {
        int[] rgb = {200, 100, 50};
        Color color = Colorizer.getColor(rgb, false);
        String input = "I am 8-bit!";

        String expectedOutput = String.format("\u001B[38;2;200;100;50m%s%s", input, Colorizer.ansiReset);
        String actualOutput = color.colorize(input);

        Assertions.assertEquals(expectedOutput, actualOutput);
        System.out.println(color.colorize("sample"));
    }


    @Test
    public void test_equals_sameExactObject() {
        Color color = Colorizer.getColor(SimpleColor.RED);
        Assertions.assertEquals(color, color);
    }

    @Test
    public void test_equals_nullComparison() {
        Color color = Colorizer.getColor(SimpleColor.RED);
        Assertions.assertNotEquals(color, null);
    }

    @Test
    public void test_equals_otherClass() {
        // Intentional redundant call for complete coverage
        Color color = Colorizer.getColor(SimpleColor.RED);
        //noinspection AssertBetweenInconvertibleTypes
        Assertions.assertNotEquals(color, "this is a string");
    }
}
