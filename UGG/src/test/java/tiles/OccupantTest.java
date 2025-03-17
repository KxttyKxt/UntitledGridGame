package tiles;

import display.SimpleDisplay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OccupantTest {

    @Test
    void display() {
        String expectedDisplay = "O";
        String actualDisplay = Occupant.newOccupant().display();

        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }


    @Test
    void test_equals_true_same() {
        Occupant occupant = Occupant.withOnlyText("occupant");
        Assertions.assertEquals(occupant, occupant);
    }

    @Test
    void test_equals_true() {
        Occupant occupant1 = Occupant.newOccupant();
        Occupant occupant2 = Occupant.newOccupant();

        Assertions.assertEquals(occupant1, occupant2);
    }


    @Test
    void test_notEquals_differentClass() {
        // assertEquals is necessary to fulfill the usage of Tile#equals()
        //noinspection AssertBetweenInconvertibleTypes
        Assertions.assertNotEquals(Occupant.newOccupant(), "literally a string");
    }

    @Test
    void test_notEquals_null() {
        Assertions.assertNotEquals(Occupant.newOccupant(), null);
    }

    @Test
    void test_notEquals_differentOccupant() {
        Occupant occupant1 = Occupant.newOccupant();
        Occupant occupant2 = Occupant.newOccupant(SimpleDisplay.withOnlyText("Occupants Too"));

        Assertions.assertNotEquals(occupant1, occupant2);
    }
}