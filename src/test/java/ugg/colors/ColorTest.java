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
}
