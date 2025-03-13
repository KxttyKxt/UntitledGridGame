package tiles;

import colors.Color;
import colors.ColorMaker;
import colors.SimpleColor;
import display.Displayable;
import display.SimpleDisplay;


public class Tile implements Displayable {
    private final Displayable base;
    private Displayable contents;
    private boolean traversable = true;

    static final Displayable defaultTileDisplay = new SimpleDisplay(".", ColorMaker.make(SimpleColor.BRIGHT_BLACK));

    public Tile(Displayable base, boolean traversable) {
        this.base = base;
        this.contents = null;
        this.traversable = traversable;
    }
    public Tile(Displayable base) {
        this.base = base;
        this.contents = null;
    }
    public Tile(String baseString, Color baseColor, boolean traversable) {
        this(new SimpleDisplay(baseString, baseColor), traversable);
    }
    public Tile(String baseString, Color baseColor) {
        this(new SimpleDisplay(baseString, baseColor));
    }

    void addContents(Displayable contents) {
        if (this.contentsAreEmpty()) {
            this.setContents(contents);
        }
    }
    private void setContents(Displayable contents) {
        this.contents = contents;
    }

    void swapContentsWith(Tile that) {
        if (!this.contentsAreEmpty() && !that.contentsAreEmpty()) {
            Displayable temp = this.contents;
            this.contents = that.contents;
            that.contents = temp;
        }
        else if (this.contentsAreEmpty()) {
            this.contents = that.contents;
            that.contents = null;
        }
        else { // that.contentsAreEmpty(), or both are empty
            that.contents = this.contents;
            this.contents = null;
        }
    }
    boolean transferContentsTo(Tile that) {
        if (!this.contentsAreEmpty() && that.contentsAreEmpty() && that.traversable) {
            this.swapContentsWith(that);
            return true;
        }
        else return false;
    }

    boolean contentsAreEmpty() {
        return contents == null;
    }


    @Override
    public String display() {
        if (contentsAreEmpty())
            return base.display();
        else
            return contents.display();
    }

    @Override
    public String toString() {
        if (contents == null)
            return base.toString();
        else
            return contents.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile that = (Tile) o;
        boolean matchingBase = this.base.equals(that.base);
        boolean matchingContents;

        if (this.contents == null && that.contents == null)
            matchingContents = true;
        else if (this.contents != null && that.contents != null)
            matchingContents = this.contents.equals(that.contents);
        else // One is null and the other is not
            matchingContents = false;

        return matchingBase && matchingContents;
    }
}
