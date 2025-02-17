package ugg.tiles.tiles;

import ugg.colors.Color;
import ugg.colors.Colorizer;
import ugg.colors.SimpleColor;
import ugg.tiles.interfaces.Displayable;

public class Tile implements Displayable {

    // =============== Tile =================

    private String contents;
    public Tile(String contents) {
        this.contents = contents;
    }

    public void swapContentsWith(Tile that) {
        String temp = this.contents;
        this.contents = that.contents;
        that.contents = temp;
    }

    public boolean transferContentsTo(Tile that) {
        if (that.openForTransfer()) {
            that.contents = this.contents;
            this.contents = "";
            return true;
        }
        else
            return false;
    }
    public boolean openForTransfer() {
        return this.contents.isEmpty();
    }

    // ======================================


    // ============== Display ===============

    private Color color;
    public Tile(String contents, Color color) {
        this.contents = contents;
        this.color = color;
    }

    @Override
    public String display() {
        String contentsDisplay = gatherDisplayFromContents();
        return color.colorize(contentsDisplay);
    }

    private String gatherDisplayFromContents() {
        if (contents == null) {
            color = Colorizer.getColor(SimpleColor.BLACK);
            return "-";
        }
        else if (contents.isEmpty()) {
            color = Colorizer.getColor(SimpleColor.BRIGHT_BLACK);
            return ".";
        }
        else {
            return contents.substring(0, 1);
        }
    }

    // ======================================

    @Override
    public String toString() {
        return (contents != null)
                ? contents
                : "null";
    }
}
