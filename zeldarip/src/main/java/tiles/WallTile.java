package tiles;

import colors.ColorMaker;
import colors.SimpleColor;

// This class is used via Class.forName(<this class>).
// The class believes that it is unused because there is no hard call.
@SuppressWarnings("unused")
public class WallTile extends Tile {
    public WallTile() {
        super("#", ColorMaker.make(SimpleColor.GREEN), false);
    }
}
