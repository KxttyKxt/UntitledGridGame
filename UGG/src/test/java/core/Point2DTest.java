package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Point2DTest {
    private final Point2D upperBounds = Point2D.of(5, 5);


    @Test
    void test_delta() {
        Point2D base = Point2D.of(5, 3);
        Point2D delta = new Point2D(1, 2);

        Point2D expectedPoint2D = Point2D.of(6, 5);
        Point2D actualPoint2D = base.delta(delta);

        Assertions.assertEquals(expectedPoint2D, actualPoint2D);
    }


    @Test
    void test_withinRange_true() {
        Point2D point = Point2D.of(3, 1);
        Assertions.assertTrue(point.withinRange(upperBounds));
    }

    @Test
    void test_withinRange_false_xBelowZero() {
        Point2D point = Point2D.of(-3, 1);
        Assertions.assertFalse(point.withinRange(upperBounds));
    }

    @Test
    void test_withinRange_false_yBelowZero() {
        Point2D point = Point2D.of(3, -1);
        Assertions.assertFalse(point.withinRange(upperBounds));
    }

    @Test
    void test_withinRange_false_xAboveBound() {
        Point2D point = Point2D.of(300, 1);
        Assertions.assertFalse(point.withinRange(upperBounds));
    }

    @Test
    void test_withinRange_false_yAboveBound() {
        Point2D point = Point2D.of(3, 100);
        Assertions.assertFalse(point.withinRange(upperBounds));
    }


    @Test
    void test_outOfRangeDelta_inRange() {
        Point2D point = Point2D.of(3, 3);

        Point2D expectedDelta = Point2D.of(0, 0);
        Point2D actualDelta = point.outOfRangeDelta(upperBounds);

        Assertions.assertEquals(expectedDelta, actualDelta);
    }

    @Test
    void test_outOfRangeDelta_aboveRange() {
        Point2D point = Point2D.of(300, 300);

        Point2D expectedDelta = Point2D.of(1, 1);
        Point2D actualDelta = point.outOfRangeDelta(upperBounds);

        Assertions.assertEquals(expectedDelta, actualDelta);
    }

    @Test
    void test_outOfRangeDelta_belowRange() {
        Point2D point = Point2D.of(-3, -3);

        Point2D expectedDelta = Point2D.of(-1, -1);
        Point2D actualDelta = point.outOfRangeDelta(upperBounds);

        Assertions.assertEquals(expectedDelta, actualDelta);
    }


    @Test
    void test_toString() {
        Point2D point = Point2D.of(3, 4);

        String expectedToString = "x:3, y:4";
        String actualToString = point.toString();

        Assertions.assertEquals(expectedToString, actualToString);
    }
}