package playtest.swapandtransfer.tiles;

import playtest.swapandtransfer.display.BoxDisplay;
import ugg.colors.Color;
import ugg.tiles.Tile;

public class BoxTile extends Tile {
    public BoxTile() {
        super(defaultBaseDisplay, new BoxDisplay());
    }
    public BoxTile(Color color) {
        super(defaultBaseDisplay, new BoxDisplay(color));
    }
}
