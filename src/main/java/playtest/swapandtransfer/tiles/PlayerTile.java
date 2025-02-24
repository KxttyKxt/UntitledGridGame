package playtest.swapandtransfer.tiles;

import playtest.swapandtransfer.display.PlayerDisplay;
import ugg.tiles.Tile;

public class PlayerTile extends Tile {
    public PlayerTile() {
        super(defaultBaseDisplay, new PlayerDisplay());
    }
}
