package display;

import colors.Color;
import colors.ColorMaker;
import colors.SimpleColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SimpleDisplayTest {
    @Test
    void test_builder_bothNull() {
        SimpleDisplay nullTextDisplay = SimpleDisplay.withText(null).andColor(null);
        Assertions.assertEquals(SimpleDisplay.emptyTextFormat, nullTextDisplay.toString());
    }

    @Test
    void test_builder_emptyText() {
        SimpleDisplay nullTextDisplay = SimpleDisplay.withText("").andColor(null);
        Assertions.assertEquals(SimpleDisplay.emptyTextFormat, nullTextDisplay.toString());
    }

    @Test
    void test_builder_onlyText() {
        SimpleDisplay onlyTextDisplay = SimpleDisplay.withText("text!").andColor(null);
        Assertions.assertEquals("text!", onlyTextDisplay.toString());
    }


    @Test
    void test_display_color() {
        Color red = ColorMaker.make(SimpleColor.RED);
        SimpleDisplay redDisplay = SimpleDisplay.withText("Super Duper").andColor(red);

        String expectedDisplay = red.colorize("S");
        String actualDisplay = redDisplay.display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }

    @Test
    void test_display_noColor() {
        SimpleDisplay noColorDisplay = SimpleDisplay.withText("Super Duper").andColor(null);
        Assertions.assertEquals("S", noColorDisplay.display());
    }


    @Test
    void test_equals_same() {
        SimpleDisplay simpleDisplay = SimpleDisplay.emptyDisplay();
        Assertions.assertEquals(simpleDisplay, simpleDisplay);
    }

    @Test
    void test_notEquals_null() {
        SimpleDisplay display = SimpleDisplay.emptyDisplay();
        Assertions.assertNotEquals(display, null);
    }

    @Test
    void test_NotEquals_differentClass() {
        // For test coverage
        SimpleDisplay display = SimpleDisplay.emptyDisplay();
        //noinspection AssertBetweenInconvertibleTypes
        Assertions.assertNotEquals(display, "literally a string");
    }

    @Test
    void test_NotEquals_text() {
        SimpleDisplay display = SimpleDisplay.withText("text").andColor(null);
        SimpleDisplay otherDisplay = SimpleDisplay.withText("text!").andColor(null);

        Assertions.assertNotEquals(display, otherDisplay);
    }

    @Test
    void test_NotEquals_firstColorNull() {
        SimpleDisplay display = SimpleDisplay.withText("text").andColor(null);
        SimpleDisplay otherDisplay = SimpleDisplay.withText("text").andColor(ColorMaker.make(SimpleColor.RED));

        Assertions.assertNotEquals(display, otherDisplay);
    }

    @Test
    void test_NotEquals_secondColorNull() {
        SimpleDisplay display = SimpleDisplay.withText("text").andColor(ColorMaker.make(SimpleColor.RED));
        SimpleDisplay otherDisplay = SimpleDisplay.withText("text").andColor(null);

        Assertions.assertNotEquals(display, otherDisplay);
    }

    @Test
    void test_equals_color() {
        SimpleDisplay display = SimpleDisplay.withText("text").andColor(ColorMaker.make(SimpleColor.RED));
        SimpleDisplay otherDisplay = SimpleDisplay.withText("text").andColor(ColorMaker.make(SimpleColor.RED));

        Assertions.assertEquals(display, otherDisplay);
    }
}
