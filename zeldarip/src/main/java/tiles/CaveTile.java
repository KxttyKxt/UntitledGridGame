package tiles;

import colors.ColorMaker;

public class CaveTile extends Tile {
    public CaveTile() {
        super("^", ColorMaker.make(255, false));
    }
}
