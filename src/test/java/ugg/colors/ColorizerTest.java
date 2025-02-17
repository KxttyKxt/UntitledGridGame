package ugg.colors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ColorizerTest {
    @Test
    public void test_getColor_simpleColor() {
        Color color = Colorizer.getColor(SimpleColor.RED);

        String expectedAnsiCode = "\u001B[31m";
        String actualAnsiCode = color.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
        System.out.println(color.colorize("sample"));
    }


    @Test
    public void test_getColor_colorID_noBG() {
        Color color = Colorizer.getColor(65, false);

        String expectedAnsiCode = "\u001B[38;5;65m";
        String actualAnsiCode = color.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
        System.out.println(color.colorize("sample"));
    }

    @Test
    public void test_getColor_colorID_yesBG() {
        Color color = Colorizer.getColor(65, true);

        String expectedAnsiCode = "\u001B[48;5;65m";
        String actualAnsiCode = color.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
        System.out.println(color.colorize("sample"));
    }

    @Test
    public void test_getColor_colorID_invalid_tooHigh() {
        boolean errorThrown = false;

        try {
            Colorizer.getColor(300, true);
        }
        catch (IllegalArgumentException colorIDWasOutOfRangeException) {
            errorThrown = true;
        }
        finally {
            Assertions.assertTrue(errorThrown);
        }
    }

    @Test
    public void test_getColor_colorID_invalid_tooLow() {
        boolean errorThrown = false;

        try {
            Colorizer.getColor(-3, true);
        }
        catch (IllegalArgumentException colorIDWasOutOfRangeException) {
            errorThrown = true;
        }
        finally {
            Assertions.assertTrue(errorThrown);
        }
    }


    @Test
    public void test_getColor_rgb_noBG() {
        int[] rgb = {200, 100, 50};
        Color color = Colorizer.getColor(rgb, false);

        String expectedAnsiCode = "\u001B[38;2;200;100;50m";
        String actualAnsiCode = color.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
        System.out.println(color.colorize("sample"));
    }

    @Test
    public void test_getColor_rgb_yesBG() {
        int[] rgb = {200, 100, 50};
        Color color = Colorizer.getColor(rgb, true);

        String expectedAnsiCode = "\u001B[48;2;200;100;50m";
        String actualAnsiCode = color.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
        System.out.println(color.colorize("sample"));
    }

    @Test
    public void test_getColor_rgb_invalid_range() {
        int[] rgb = {300, -3, 50};
        boolean errorThrown = false;

        try {
            Colorizer.getColor(rgb, true);
        }
        catch (IllegalArgumentException colorIDWasOutOfRangeException) {
            errorThrown = true;
        }
        finally {
            Assertions.assertTrue(errorThrown);
        }
    }

    @Test
    public void test_getColor_rgb_invalid_tooLong() {
        int[] rgb = {200, 100, 50, 2};
        boolean errorThrown = false;

        try {
            Colorizer.getColor(rgb, true);
        }
        catch (IllegalArgumentException colorIDWasOutOfRangeException) {
            errorThrown = true;
        }
        finally {
            Assertions.assertTrue(errorThrown);
        }
    }

    @Test
    public void test_getColor_rgb_invalid_tooShort() {
        int[] rgb = {200, 100};
        boolean errorThrown = false;

        try {
            Colorizer.getColor(rgb, true);
        }
        catch (IllegalArgumentException colorIDWasOutOfRangeException) {
            errorThrown = true;
        }
        finally {
            Assertions.assertTrue(errorThrown);
        }
    }

}
