package tiles;

import colors.Color;
import display.BoxDisplay;

public class BoxTile extends Tile {
    public BoxTile() {
        super(defaultBaseDisplay, new BoxDisplay());
    }
    public BoxTile(Color color) {
        super(defaultBaseDisplay, new BoxDisplay(color));
    }
}
