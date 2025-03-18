package core;

import colors.Color;
import colors.ColorMaker;
import colors.SimpleColor;
import display.Displayable;
import display.SimpleDisplay;


public class Tile implements Displayable {

    public static Builder withTileDisplay(Displayable tileDisplay) {
        return new Builder(tileDisplay);
    }

    public static final class Builder {
        private final Displayable tileDisplay;
        private Occupant occupant;
        private boolean traversable = true;

        public Builder(Displayable tileDisplay) {
            this.tileDisplay = tileDisplay;
        }

        public Builder andOccupant(Occupant occupant) {
            this.occupant = occupant;
            return this;
        }

        public Builder andTraversable(boolean traversable) {
            this.traversable = traversable;
            return this;
        }

        public Tile build() {
            return new Tile(tileDisplay, occupant, traversable);
        }
    }


    static final String defaultTileDisplayText = ".";
    static final Color defaultTileDisplayColor = ColorMaker.make(SimpleColor.BRIGHT_BLACK);

    static final Displayable defaultTileDisplay = SimpleDisplay
            .withText(defaultTileDisplayText)
            .andColor(defaultTileDisplayColor);

    public static Tile defaultTile() {
        return new Tile(defaultTileDisplay, null, true);
    }

    public static Tile withOnlyText(String text) {
        return Tile.withTileDisplay(SimpleDisplay.withOnlyText(text)).build();
    }

    private final Displayable display;
    private Occupant occupant;
    private final boolean traversable;

    private Tile(Displayable display, Occupant occupant, boolean traversable) {
        this.display = display;
        this.occupant = occupant;
        this.traversable = traversable;
    }


    boolean addContents(Occupant occupant) {
        if (this.contentsAreEmpty()) {
            this.setOccupant(occupant);
            return true;
        }

        return false;
    }

    private void setOccupant(Occupant occupant) {
        this.occupant = occupant;
    }

    boolean transferOccupantTo(Tile that) {
        if (!this.contentsAreEmpty() && that.contentsAreEmpty() && that.traversable) {
            this.swapContentsWith(that);
            return true;
        }
        else return false;
    }

    private void swapContentsWith(Tile that) {
        Occupant temp = this.occupant;
        this.occupant = that.occupant;
        that.occupant = temp;
    }

    private boolean contentsAreEmpty() {
        return occupant == null;
    }


    @Override
    public String display() {
        if (contentsAreEmpty())
            return display.display();
        else
            return occupant.display();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile that = (Tile) o;
        boolean tileDisplaysMatch = this.display.equals(that.display);
        boolean occupantsMatch;
        boolean traversableMatch = this.traversable == that.traversable;

        if (this.occupant == null && that.occupant == null)
            occupantsMatch = true;
        else if (this.occupant == null)
            occupantsMatch = false;
        else if (that.occupant == null)
            occupantsMatch = false;
        else
            occupantsMatch = this.occupant.equals(that.occupant);


        return tileDisplaysMatch
                && occupantsMatch
                && traversableMatch;
    }
}
