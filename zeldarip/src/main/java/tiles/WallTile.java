package tiles;

import colors.ColorMaker;
import colors.SimpleColor;
import display.SimpleDisplay;

// This class is used via Class.forName(<this class>).
// The class believes that it is unused because there is no hard call.
@SuppressWarnings("unused")
public class WallTile extends Tile {
    public WallTile() {
        super(new SimpleDisplay("#", ColorMaker.make(SimpleColor.GREEN)), false);
    }
}
