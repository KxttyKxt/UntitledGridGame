package tiles;

import colors.ColorMaker;

// This class is used via Class.forName(<this class>).
// The class believes that it is unused because there is no hard call.
@SuppressWarnings("unused")
public class BasicTile extends Tile {
    public BasicTile() {
        super(".", ColorMaker.make(187, false));
    }
}
