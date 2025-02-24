package ugg.colors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ColorTest {
    @Test
    void test_colorize_simpleColor() {
        Color color = ColorMaker.make(SimpleColor.GREEN);
        String input = "I am green!";

        String expectedOutput = String.format("\u001B[32m%s%s", input, ColorMaker.ansiReset);
        String actualOutput = color.colorize(input);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void test_colorize_colorID() {
        Color color = ColorMaker.make(17, false);
        String input = "I am 8-bit!";

        String expectedOutput = String.format("\u001B[38;5;17m%s%s", input, ColorMaker.ansiReset);
        String actualOutput = color.colorize(input);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void test_colorize_rgb() {
        int[] rgb = {200, 100, 50};
        Color color = ColorMaker.make(rgb, false);
        String input = "I am 8-bit!";

        String expectedOutput = String.format("\u001B[38;2;200;100;50m%s%s", input, ColorMaker.ansiReset);
        String actualOutput = color.colorize(input);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }


    @Test
    void test_equals_sameExactObject() {
        Color color = ColorMaker.make(SimpleColor.RED);
        Assertions.assertEquals(color, color);
    }

    @Test
    void test_equals_nullComparison() {
        Color color = ColorMaker.make(SimpleColor.RED);
        Assertions.assertNotEquals(color, null);
    }

    @Test
    void test_equals_otherClass() {
        // Applies coverage to Color#equals()
        Color color = ColorMaker.make(SimpleColor.RED);
        //noinspection AssertBetweenInconvertibleTypes
        Assertions.assertNotEquals(color, "this is a string");
    }
}
