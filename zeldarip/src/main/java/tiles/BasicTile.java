package tiles;

import colors.ColorMaker;

public class BasicTile extends Tile {
    public BasicTile() {
        super(Tile.defaultBaseText, ColorMaker.make(187, false));
    }
}
