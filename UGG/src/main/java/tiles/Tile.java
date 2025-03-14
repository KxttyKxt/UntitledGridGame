package tiles;

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
        private Displayable contentsDisplay;
        private boolean traversable = true;

        public Builder(Displayable tileDisplay) {
            this.tileDisplay = tileDisplay;
        }

        public Builder andContentsDisplay(Displayable contentsDisplay) {
            this.contentsDisplay = contentsDisplay;
            return this;
        }

        public Builder andTraversable(boolean traversable) {
            this.traversable = traversable;
            return this;
        }

        public Tile build() {
            return new Tile(tileDisplay, contentsDisplay, traversable);
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

    private final Displayable tileDisplay;
    private Displayable contentsDisplay;
    private final boolean traversable;

    private Tile(Displayable tileDisplay, Displayable contentsDisplay, boolean traversable) {
        this.tileDisplay = tileDisplay;
        this.contentsDisplay = contentsDisplay;
        this.traversable = traversable;
    }


    boolean addContents(Displayable contents) {
        if (this.contentsAreEmpty()) {
            this.setContentsDisplay(contents);
            return true;
        }

        return false;
    }

    private void setContentsDisplay(Displayable contentsDisplay) {
        this.contentsDisplay = contentsDisplay;
    }

    boolean transferContentsTo(Tile that) {
        if (!this.contentsAreEmpty() && that.contentsAreEmpty() && that.traversable) {
            this.swapContentsWith(that);
            return true;
        }
        else return false;
    }

    private void swapContentsWith(Tile that) {
        Displayable temp = this.contentsDisplay;
        this.contentsDisplay = that.contentsDisplay;
        that.contentsDisplay = temp;
    }

    private boolean contentsAreEmpty() {
        return contentsDisplay == null;
    }


    @Override
    public String display() {
        if (contentsAreEmpty())
            return tileDisplay.display();
        else
            return contentsDisplay.display();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile that = (Tile) o;
        boolean tileDisplaysMatch = this.tileDisplay.equals(that.tileDisplay);
        boolean contentsDisplaysMatch;
        boolean traversableMatch = this.traversable == that.traversable;

        if (this.contentsDisplay == null && that.contentsDisplay == null)
            contentsDisplaysMatch = true;
        else if (this.contentsDisplay == null)
            contentsDisplaysMatch = false;
        else if (that.contentsDisplay == null)
            contentsDisplaysMatch = false;
        else
            contentsDisplaysMatch = this.contentsDisplay.equals(that.contentsDisplay);


        return tileDisplaysMatch
                && contentsDisplaysMatch
                && traversableMatch;
    }
}
