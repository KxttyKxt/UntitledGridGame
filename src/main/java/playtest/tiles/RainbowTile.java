package playtest.tiles;

import playtest.display.RainbowDisplay;
import ugg.tiles.Tile;

public class RainbowTile extends Tile {
    public RainbowTile(int startingIndex) {
        super(new RainbowDisplay(startingIndex));
    }
}