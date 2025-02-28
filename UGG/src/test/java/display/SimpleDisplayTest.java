package display;

import colors.Color;
import colors.ColorMaker;
import colors.SimpleColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleDisplayTest {
    @Test
    void test_constructor_nullText() {
        SimpleDisplay nullTextDisplay = new SimpleDisplay(null, null);
        Assertions.assertEquals(SimpleDisplay.emptyTextFormat, nullTextDisplay.toString());
    }

    @Test
    void test_constructor_emptyText() {
        SimpleDisplay nullTextDisplay = new SimpleDisplay("", null);
        Assertions.assertEquals(SimpleDisplay.emptyTextFormat, nullTextDisplay.toString());
    }

    @Test
    void test_constructor_text() {
        SimpleDisplay nullTextDisplay = new SimpleDisplay("text!", null);
        Assertions.assertEquals("text!", nullTextDisplay.toString());
    }


    @Test
    void test_display_color() {
        Color red = ColorMaker.make(SimpleColor.RED);
        SimpleDisplay redDisplay = new SimpleDisplay("Super Duper", red);

        String expectedDisplay = red.colorize("S");
        String actualDisplay = redDisplay.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }

    @Test
    void test_display_noColor() {
        SimpleDisplay noColorDisplay = new SimpleDisplay("Super Duper", null);
        Assertions.assertEquals("S", noColorDisplay.display());
    }


    @Test
    void test_equals_same() {
        SimpleDisplay simpleDisplay = new SimpleDisplay("", null);
        Assertions.assertEquals(simpleDisplay, simpleDisplay);
    }

    @Test
    void test_notEquals_null() {
        SimpleDisplay display = new SimpleDisplay("", null);
        Assertions.assertNotEquals(display, null);
    }

    @Test
    void test_NotEquals_differentClass() {
        // For test coverage
        SimpleDisplay display = new SimpleDisplay("", null);
        //noinspection AssertBetweenInconvertibleTypes
        Assertions.assertNotEquals(display, "literally a string");
    }

    @Test
    void test_NotEquals_text() {
        SimpleDisplay display = new SimpleDisplay("text", null);
        SimpleDisplay otherDisplay = new SimpleDisplay("text!", null);

        Assertions.assertNotEquals(display, otherDisplay);
    }

    @Test
    void test_NotEquals_firstColorNull() {
        SimpleDisplay display = new SimpleDisplay("text", null);
        SimpleDisplay otherDisplay = new SimpleDisplay("text", ColorMaker.make(SimpleColor.RED));

        Assertions.assertNotEquals(display, otherDisplay);
    }

    @Test
    void test_NotEquals_secondColorNull() {
        SimpleDisplay display = new SimpleDisplay("text", ColorMaker.make(SimpleColor.RED));
        SimpleDisplay otherDisplay = new SimpleDisplay("text", null);

        Assertions.assertNotEquals(display, otherDisplay);
    }

    @Test
    void test_equals_color() {
        SimpleDisplay display = new SimpleDisplay("text", ColorMaker.make(SimpleColor.RED));
        SimpleDisplay otherDisplay = new SimpleDisplay("text", ColorMaker.make(SimpleColor.RED));

        Assertions.assertEquals(display, otherDisplay);
    }
}
