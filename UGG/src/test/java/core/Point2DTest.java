package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Point2DTest {
    @Test
    void test_delta() {
        Point2D base = Point2D.of(5, 3);
        Point2D delta = new Point2D(1, 2);

        Point2D expectedPoint2D = Point2D.of(6, 5);
        Point2D actualPoint2D = base.delta(delta);

        Assertions.assertEquals(expectedPoint2D, actualPoint2D);
    }

    @Test
    void test_toString() {
        Point2D point = Point2D.of(3, 4);

        String expectedToString = "x:3, y:4";
        String actualToString = point.toString();

        Assertions.assertEquals(expectedToString, actualToString);
    }
}