package tiles;

import colors.Color;
import colors.ColorMaker;
import colors.SimpleColor;
import display.Displayable;
import display.SimpleDisplay;


public class Tile implements Displayable {
    private final Displayable tileDisplay;
    private Displayable contentsDisplay;
    private final boolean traversable;

    static final String defaultTileDisplayText = ".";
    static final Color defaultTileDisplayColor = ColorMaker.make(SimpleColor.BRIGHT_BLACK);
    static final Displayable defaultTileDisplay = new SimpleDisplay(defaultTileDisplayText, defaultTileDisplayColor);

    private Tile(Displayable tileDisplay, Displayable contentsDisplay, boolean traversable) {
        this.tileDisplay = tileDisplay;
        this.contentsDisplay = contentsDisplay;
        this.traversable = traversable;
    }

    public Tile(Displayable tileDisplay, boolean traversable) {
        this(tileDisplay, null, traversable);
    }

    public Tile(Displayable tileDisplay) {
        this(tileDisplay, null, true);
    }

    public Tile(String baseString, Color baseColor) {
        this(new SimpleDisplay(baseString, baseColor));
    }


    void addContents(Displayable contents) {
        if (this.contentsAreEmpty()) {
            this.setContentsDisplay(contents);
        }
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
        if (!this.contentsAreEmpty() && !that.contentsAreEmpty()) {
            Displayable temp = this.contentsDisplay;
            this.contentsDisplay = that.contentsDisplay;
            that.contentsDisplay = temp;
        }
        else if (this.contentsAreEmpty()) {
            this.contentsDisplay = that.contentsDisplay;
            that.contentsDisplay = null;
        }
        else { // that.contentsAreEmpty(), or both are empty
            that.contentsDisplay = this.contentsDisplay;
            this.contentsDisplay = null;
        }
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
    public String toString() {
        if (contentsDisplay == null)
            return tileDisplay.toString();
        else
            return contentsDisplay.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile that = (Tile) o;
        boolean matchingTileDisplay = this.tileDisplay.equals(that.tileDisplay);
        boolean matchingContents;

        if (this.contentsDisplay == null && that.contentsDisplay == null)
            matchingContents = true;
        else if (this.contentsDisplay != null && that.contentsDisplay != null)
            matchingContents = this.contentsDisplay.equals(that.contentsDisplay);
        else // One is null and the other is not
            matchingContents = false;

        return matchingTileDisplay && matchingContents;
    }
}
