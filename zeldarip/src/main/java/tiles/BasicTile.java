package tiles;

import colors.ColorMaker;
import colors.SimpleColor;

public class BasicTile extends Tile {
    public BasicTile() {
        super(Tile.defaultBaseText, ColorMaker.make(SimpleColor.BG_BRIGHT_YELLOW));
    }
}
