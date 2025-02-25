package tiles;

import display.RainbowDisplay;

public class RainbowTile extends Tile {
    public RainbowTile(int startingIndex) {
        super(new RainbowDisplay(startingIndex));
    }
}