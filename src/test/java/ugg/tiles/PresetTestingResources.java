package ugg.tiles;

import ugg.colors.Color;
import ugg.colors.ColorMaker;
import ugg.colors.ColorMerger;
import ugg.colors.SimpleColor;

public class PresetTestingResources {

    static final String COLORED_CONTENTS = "#";

    static final Color RED = ColorMaker.make(SimpleColor.RED);
    static final Color GREEN = ColorMaker.make(SimpleColor.GREEN);
    static final Color YELLOW = ColorMaker.make(SimpleColor.YELLOW);
    static final Color MAGENTA = ColorMaker.make(SimpleColor.MAGENTA);
    static final Color BG_RED = ColorMaker.make(SimpleColor.BG_RED);
    static final Color MERGED_COLOR = ColorMerger.merge(new Color[]{BG_RED, GREEN});

    static final Tile tileA = new Tile("A");
    static final Tile tileB = new Tile("B");
    static final Tile tileC = new Tile("C");
    static final Tile tileD = new Tile("D");

    static final Tile emptyTile = new Tile("");
    static final Tile nullTile = new Tile(null);

    static final Tile uncoloredTile = new Tile(COLORED_CONTENTS, null);
    static final Tile redTile = new Tile(COLORED_CONTENTS, RED);
    static final Tile greenTile = new Tile(COLORED_CONTENTS, GREEN);
    static final Tile yellowTile = new Tile(COLORED_CONTENTS, YELLOW);
    static final Tile magentaTile = new Tile(COLORED_CONTENTS, MAGENTA);
    static final Tile bgRedTile = new Tile(COLORED_CONTENTS, BG_RED);
    static final Tile mergedColorTile = new Tile(COLORED_CONTENTS, MERGED_COLOR);
}
