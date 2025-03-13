package tiles;

import colors.ColorMaker;

// This class is used via Class.forName(<this class>).
// The class believes that it is unused because there is no hard call.
@SuppressWarnings("unused")
public class CaveTile extends Tile {
    public CaveTile() {
        super("0", ColorMaker.make(new int[]{175, 175, 175}, false));
    }
}
