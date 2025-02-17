package ugg.tiles.tiles;

import ugg.colors.Color;
import ugg.colors.Colorizer;
import ugg.colors.MultiColorizer;
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

    private Color fgColor;
    private Color bgColor;

    @Override
    public String display() {
        Color[] colors = {fgColor, bgColor};
        String toColorize = gatherDisplayFromContents();

        return MultiColorizer.colorize(colors, toColorize);
    }

    private String gatherDisplayFromContents() {
        if (contents == null) {
            fgColor = Colorizer.getColor(SimpleColor.BLACK);
            bgColor = null;
            return "-";
        }
        else if (contents.isEmpty()) {
            fgColor = Colorizer.getColor(SimpleColor.BRIGHT_BLACK);
            bgColor = null;
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
