package ugg.colors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ColorMakerTest {
    @BeforeEach
    public void resetExceptionThrownStatus() {
        exceptionWasThrown = false;
    }
    private boolean exceptionWasThrown;

    @Test
    public void test_make_simple() {
        Color color = ColorMaker.make(SimpleColor.RED);

        String expectedAnsiCode = "\u001B[31m";
        String actualAnsiCode = color.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
    }


    @Test
    public void test_make_id_noBG() {
        Color color = ColorMaker.make(65, false);

        String expectedAnsiCode = "\u001B[38;5;65m";
        String actualAnsiCode = color.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
    }

    @Test
    public void test_make_id_yesBG() {
        Color color = ColorMaker.make(65, true);

        String expectedAnsiCode = "\u001B[48;5;65m";
        String actualAnsiCode = color.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
    }


    @Test
    public void test_make_rgb_noBG() {
        int[] rgb = {200, 100, 50};
        Color color = ColorMaker.make(rgb, false);

        String expectedAnsiCode = "\u001B[38;2;200;100;50m";
        String actualAnsiCode = color.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
    }

    @Test
    public void test_make_rgb_yesBG() {
        int[] rgb = {200, 100, 50};
        Color color = ColorMaker.make(rgb, true);

        String expectedAnsiCode = "\u001B[48;2;200;100;50m";
        String actualAnsiCode = color.ansiCode;

        Assertions.assertEquals(expectedAnsiCode, actualAnsiCode);
    }


    @Test
    public void test_make_id_colorIDWasOutOfRangeException_above() {
        try {
            ColorMaker.make(300, true);
        }
        catch (IllegalArgumentException colorIDWasOutOfRangeException) {
            exceptionWasThrown = true;
        }

        Assertions.assertTrue(exceptionWasThrown);
    }

    @Test
    public void test_make_id_colorIDWasOutOfRangeException_below() {
        try {
            ColorMaker.make(-3, true);
        }
        catch (IllegalArgumentException colorIDWasOutOfRangeException) {
            exceptionWasThrown = true;
        }

        Assertions.assertTrue(exceptionWasThrown);
    }

    @Test
    public void test_make_rgb_rgbValueOutOfRangeException() {
        int[] rgb = {300, -3, 50};

        try {
            ColorMaker.make(rgb, true);
        }
        catch (IllegalArgumentException rgbValueOutOfRangeException) {
            exceptionWasThrown = true;
        }

        Assertions.assertTrue(exceptionWasThrown);
    }

    @Test
    public void test_make_rgb_arrayTooLongException() {
        int[] rgb = {200, 100, 50, 2};

        try {
            ColorMaker.make(rgb, true);
        }
        catch (IllegalArgumentException arrayTooLongException) {
            exceptionWasThrown = true;
        }

        Assertions.assertTrue(exceptionWasThrown);
    }

    @Test
    public void test_make_rgb_arrayTooShortException() {
        int[] rgb = {200, 100};

        try {
            ColorMaker.make(rgb, true);
        }
        catch (IllegalArgumentException arrayTooShortException) {
            exceptionWasThrown = true;
        }

        Assertions.assertTrue(exceptionWasThrown);
    }

}
