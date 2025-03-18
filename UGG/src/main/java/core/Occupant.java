package core;

import display.Displayable;
import display.SimpleDisplay;

public class Occupant implements Displayable {
    private final Displayable display;

    public static Occupant newOccupant(Displayable display) {
        return new Occupant(display);
    }

    public static Occupant newOccupant() {
        return newOccupant(SimpleDisplay.withOnlyText("Occupant"));
    }

    public static Occupant withOnlyText(String text) {
        return new Occupant(SimpleDisplay.withOnlyText(text));
    }

    private Occupant(Displayable display) {
        this.display = display;
    }

    @Override
    public String display() {
        return display.display();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Occupant that = (Occupant) o;
        return this.display.equals(that.display);
    }
}
