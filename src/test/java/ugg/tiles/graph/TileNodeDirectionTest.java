package ugg.tiles.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
    public void test_fromOrdinal_invalidOrdinal() {
        boolean threwError = false;

        try {
            TileNodeDirection.fromOrdinal(-1);
        }
        catch (IndexOutOfBoundsException ordinalWasOutOfRangeException) {
            threwError = true;
        }
        finally {
            Assertions.assertTrue(threwError);
        }
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
        TileNodeDirection actualDirection = TileNodeDirection.NORTHWEST.clockwise();

        Assertions.assertEquals(expectedDirection, actualDirection);
    }

    @Test
    public void test_clockwise_fullCircle() {
        TileNodeDirection expectedDirection = TileNodeDirection.NORTH;
        TileNodeDirection actualDirection = TileNodeDirection.NORTH
                .clockwise()
                .clockwise()
                .clockwise()
                .clockwise()
                .clockwise()
                .clockwise()
                .clockwise()
                .clockwise();

        Assertions.assertEquals(expectedDirection, actualDirection);
    }

    @Test
    public void test_clockwise_allRotations() {
        for (int i = 0; i < TileNodeDirection.values().length; i++) {
            TileNodeDirection expectedDirection = TileNodeDirection.fromOrdinal((i + 1) % 8);

            assert TileNodeDirection.fromOrdinal(i) != null;
            TileNodeDirection actualDirection = TileNodeDirection.fromOrdinal(i).clockwise();

            Assertions.assertEquals(expectedDirection, actualDirection);
        }
    }


    @Test
    public void test_counterclockwise() {
        TileNodeDirection expectedDirection = TileNodeDirection.NORTH;
        TileNodeDirection actualDirection = TileNodeDirection.NORTHEAST.counterclockwise();

        Assertions.assertEquals(expectedDirection, actualDirection);
    }

    @Test
    public void test_counterclockwise_fullCircle() {
        TileNodeDirection expectedDirection = TileNodeDirection.NORTH;
        TileNodeDirection actualDirection = TileNodeDirection.NORTH
                .counterclockwise()
                .counterclockwise()
                .counterclockwise()
                .counterclockwise()
                .counterclockwise()
                .counterclockwise()
                .counterclockwise()
                .counterclockwise();

        Assertions.assertEquals(expectedDirection, actualDirection);
    }

    @Test
    public void test_counterclockwise_allRotations() {
        for (int i = 0; i < TileNodeDirection.values().length; i++) {
            TileNodeDirection expectedDirection = TileNodeDirection.fromOrdinal((i + 7) % 8);

            assert TileNodeDirection.fromOrdinal(i) != null;
            TileNodeDirection actualDirection = TileNodeDirection.fromOrdinal(i).counterclockwise();

            Assertions.assertEquals(expectedDirection, actualDirection);
        }
    }
}
