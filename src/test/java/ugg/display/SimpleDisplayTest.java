package ugg.display;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ugg.colors.ColorMaker;
import ugg.colors.SimpleColor;

public class SimpleDisplayTest {
    @Test
    public void test_constructor_emptyText() {
        Assertions.assertEquals("-", new SimpleDisplay("", null).toString());
    }

    @Test
    public void test_notEquals_nullComparison() {
        Assertions.assertNotEquals(new SimpleDisplay("Text", ColorMaker.make(SimpleColor.RED)), null);
    }

    @Test
    public void test_notEquals_differentClass() {
        // For test coverage
        //noinspection AssertBetweenInconvertibleTypes
        Assertions.assertNotEquals(new SimpleDisplay("Text", ColorMaker.make(SimpleColor.RED)), "String");
    }

    @Test
    public void test_notEquals_secondHasNullColor() {
        SimpleDisplay display1 = new SimpleDisplay("Text", ColorMaker.make(SimpleColor.RED));
        SimpleDisplay display2 = new SimpleDisplay("Text", null);

        Assertions.assertNotEquals(display1, display2);
    }
}
