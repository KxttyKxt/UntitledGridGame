package ugg.colors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ColorMakerTest {
    @Test
    public void test_make() {
        Color color = ColorMaker.make(SimpleColor.RED);

        String expectedAnsiCode = "\u001B[31m";
        String actualAnsiCode = color.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
    }


    @Test
    public void test_make_idNoBG() {
        Color color = ColorMaker.make(65, false);

        String expectedAnsiCode = "\u001B[38;5;65m";
        String actualAnsiCode = color.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
    }

    @Test
    public void test_make_idYesBG() {
        Color color = ColorMaker.make(65, true);

        String expectedAnsiCode = "\u001B[48;5;65m";
        String actualAnsiCode = color.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
    }

    @Test
    public void test_make_idInvalid_tooHigh() {
        boolean errorThrown = false;

        try {
            ColorMaker.make(300, true);
        }
        catch (IllegalArgumentException colorIDWasOutOfRangeException) {
            errorThrown = true;
        }
        finally {
            Assertions.assertTrue(errorThrown);
        }
    }

    @Test
    public void test_make_idInvalid_tooLow() {
        boolean errorThrown = false;

        try {
            ColorMaker.make(-3, true);
        }
        catch (IllegalArgumentException colorIDWasOutOfRangeException) {
            errorThrown = true;
        }
        finally {
            Assertions.assertTrue(errorThrown);
        }
    }


    @Test
    public void test_make_rgbNoBG() {
        int[] rgb = {200, 100, 50};
        Color color = ColorMaker.make(rgb, false);

        String expectedAnsiCode = "\u001B[38;2;200;100;50m";
        String actualAnsiCode = color.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
    }

    @Test
    public void test_make_rgbYesBG() {
        int[] rgb = {200, 100, 50};
        Color color = ColorMaker.make(rgb, true);

        String expectedAnsiCode = "\u001B[48;2;200;100;50m";
        String actualAnsiCode = color.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
    }

    @Test
    public void test_make_rgbInvalid_outOfRange() {
        int[] rgb = {300, -3, 50};
        boolean errorThrown = false;

        try {
            ColorMaker.make(rgb, true);
        }
        catch (IllegalArgumentException rgbValueOutOfRangeException) {
            errorThrown = true;
        }
        finally {
            Assertions.assertTrue(errorThrown);
        }
    }

    @Test
    public void test_make_rgbInvalid_arrayTooLong() {
        int[] rgb = {200, 100, 50, 2};
        boolean errorThrown = false;

        try {
            ColorMaker.make(rgb, true);
        }
        catch (IllegalArgumentException arrayTooLongException) {
            errorThrown = true;
        }
        finally {
            Assertions.assertTrue(errorThrown);
        }
    }

    @Test
    public void test_make_rgbInvalid_arrayTooShort() {
        int[] rgb = {200, 100};
        boolean errorThrown = false;

        try {
            ColorMaker.make(rgb, true);
        }
        catch (IllegalArgumentException arrayTooShortException) {
            errorThrown = true;
        }
        finally {
            Assertions.assertTrue(errorThrown);
        }
    }

}
