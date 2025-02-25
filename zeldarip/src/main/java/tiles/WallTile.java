package tiles;

import colors.ColorMaker;
import colors.SimpleColor;

public class WallTile extends Tile {
    public WallTile() {
        super("#", ColorMaker.make(SimpleColor.GREEN), false);
    }
}
