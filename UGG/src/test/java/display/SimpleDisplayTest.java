package display;

import colors.ColorMaker;
import colors.SimpleColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleDisplayTest {
    @Test
    void test_constructor_emptyText() {
        Assertions.assertEquals("-", new SimpleDisplay("", null).toString());
    }

    @Test
    void test_notEquals_nullComparison() {
        Assertions.assertNotEquals(new SimpleDisplay("Text", ColorMaker.make(SimpleColor.RED)), null);
    }

    @Test
    void test_notEquals_differentClass() {
        // For test coverage
        //noinspection AssertBetweenInconvertibleTypes
        Assertions.assertNotEquals(new SimpleDisplay("Text", ColorMaker.make(SimpleColor.RED)), "String");
    }

    @Test
    void test_notEquals_secondHasNullColor() {
        SimpleDisplay display1 = new SimpleDisplay("Text", ColorMaker.make(SimpleColor.RED));
        SimpleDisplay display2 = new SimpleDisplay("Text", null);

        Assertions.assertNotEquals(display1, display2);
    }
}
