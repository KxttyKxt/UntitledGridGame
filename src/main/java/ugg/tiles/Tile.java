package ugg.tiles;

import ugg.colors.Color;
import ugg.colors.ColorMaker;
import ugg.colors.SimpleColor;

import java.util.Objects;


public class Tile {
    static final String COLORED_CONTENTS = "#";

    private String contents;
    private Color color;

    public Tile() {
        contents = "";
        color = null;
    }
    public Tile(String contents) {
        this.contents = (contents != null)
                ? contents
                : "";
    }
    public Tile(String contents, Color color) {
        this.contents = contents;
        this.color = color;
    }
    public Tile(Color color) {
        this.contents = COLORED_CONTENTS;
        this.color = color;
    }


    public void swapContentsWith(Tile that) {
        String temp = this.contents;
        this.contents = that.contents;
        that.contents = temp;
    }

    public boolean transferContentsTo(Tile that) {
        if (that.isEmpty()) {
            this.swapContentsWith(that);
            return true;
        }
        else return false;
    }
    public boolean isEmpty() {
        return this.contents.isEmpty();
    }


    static final String EMPTY_CONTENTS_DISPLAY = ColorMaker.make(SimpleColor.BRIGHT_BLACK).colorize(".");

    public String display() {
        return gatherDisplayFromContents();
    }

    private String gatherDisplayFromContents() {
        if (contents.isEmpty())
            return EMPTY_CONTENTS_DISPLAY;
        else {
            String toReturn = this.contents.substring(0,1);

            if (this.color != null)
                toReturn = this.color.colorize(toReturn);

            return toReturn;
        }
    }


    @Override
    public String toString() {
        return contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile that = (Tile) o;
        return Objects.equals(this.contents, that.contents) && Objects.equals(this.color, that.color);
    }
}
