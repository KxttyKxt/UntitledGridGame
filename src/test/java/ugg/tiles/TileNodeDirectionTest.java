package ugg.tiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ugg.tiles.graph.TileNodeDirection;

import java.util.Arrays;

public class TileNodeDirectionTest {

    @Test
    public void test_fromOrdinal() {
        TileNodeDirection[] expectedDirections =
                TileNodeDirection.values();

        TileNodeDirection[] actualDirections =
                getActualDirectionsFromOrdinals();

        Assertions.assertArrayEquals(expectedDirections, actualDirections);
    }
    private TileNodeDirection[] getActualDirectionsFromOrdinals() {
        int length = TileNodeDirection.values().length;
        TileNodeDirection[] toReturn = new TileNodeDirection[length];

        for (int i = 0; i < length; i++) {
            toReturn[i] = TileNodeDirection.fromOrdinal(i);
        }

        return toReturn;
    }

    @Test
    public void test_fromOrdinal_null() {
        TileNodeDirection direction = TileNodeDirection.fromOrdinal(4);
        Assertions.assertNull(direction);
    }


    @Test
    public void test_opposite() {
        TileNodeDirection expectedDirection = TileNodeDirection.SOUTH;
        TileNodeDirection actualDirection = TileNodeDirection.NORTH.opposite();

        Assertions.assertEquals(expectedDirection, actualDirection);
    }

    @Test
    public void test_opposite_opposite() {
        TileNodeDirection expectedDirection = TileNodeDirection.NORTH;
        TileNodeDirection actualDirection = TileNodeDirection.NORTH.opposite().opposite();

        Assertions.assertEquals(expectedDirection, actualDirection);
    }

    @Test
    public void test_allOpposites() {
        Assertions.assertEquals(TileNodeDirection.NORTH, TileNodeDirection.SOUTH.opposite());
        Assertions.assertEquals(TileNodeDirection.EAST, TileNodeDirection.WEST.opposite());
        Assertions.assertEquals(TileNodeDirection.SOUTH, TileNodeDirection.NORTH.opposite());
        Assertions.assertEquals(TileNodeDirection.WEST, TileNodeDirection.EAST.opposite());
    }


    @Test
    public void test_clockwise() {
        TileNodeDirection expectedDirection = TileNodeDirection.NORTH;
        TileNodeDirection actualDirection = TileNodeDirection.WEST.clockwise();

        Assertions.assertEquals(expectedDirection, actualDirection);
    }

    @Test
    public void test_clockwise_fullCircle() {
        TileNodeDirection expectedDirection = TileNodeDirection.NORTH;
        TileNodeDirection actualDirection = TileNodeDirection.NORTH
                .clockwise()
                .clockwise()
                .clockwise()
                .clockwise();

        Assertions.assertEquals(expectedDirection, actualDirection);
    }

    @Test
    public void test_clockwise_allRotations() {
        Assertions.assertEquals(TileNodeDirection.NORTH, TileNodeDirection.WEST.clockwise());
        Assertions.assertEquals(TileNodeDirection.EAST, TileNodeDirection.NORTH.clockwise());
        Assertions.assertEquals(TileNodeDirection.SOUTH, TileNodeDirection.EAST.clockwise());
        Assertions.assertEquals(TileNodeDirection.WEST, TileNodeDirection.SOUTH.clockwise());
    }


    @Test
    public void test_counterclockwise() {
        TileNodeDirection expectedDirection = TileNodeDirection.NORTH;
        TileNodeDirection actualDirection = TileNodeDirection.EAST.counterclockwise();

        Assertions.assertEquals(expectedDirection, actualDirection);
    }

    @Test
    public void test_counterclockwise_fullCircle() {
        TileNodeDirection expectedDirection = TileNodeDirection.NORTH;
        TileNodeDirection actualDirection = TileNodeDirection.NORTH
                .counterclockwise()
                .counterclockwise()
                .counterclockwise()
                .counterclockwise();

        Assertions.assertEquals(expectedDirection, actualDirection);
    }

    @Test
    public void test_counterclockwise_allRotations() {
        Assertions.assertEquals(TileNodeDirection.NORTH, TileNodeDirection.EAST.counterclockwise());
        Assertions.assertEquals(TileNodeDirection.WEST, TileNodeDirection.NORTH.counterclockwise());
        Assertions.assertEquals(TileNodeDirection.SOUTH, TileNodeDirection.WEST.counterclockwise());
        Assertions.assertEquals(TileNodeDirection.EAST, TileNodeDirection.SOUTH.counterclockwise());
    }
}
