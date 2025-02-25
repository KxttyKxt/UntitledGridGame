package tiles;

import display.PlayerDisplay;

public class PlayerTile extends Tile {
    public PlayerTile() {
        super(defaultBaseDisplay, new PlayerDisplay());
    }
}
