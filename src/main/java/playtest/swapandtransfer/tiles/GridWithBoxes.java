package playtest.swapandtransfer.tiles;

import ugg.colors.Color;
import ugg.colors.ColorMaker;
import ugg.colors.SimpleColor;
import ugg.tiles.Tile;
import ugg.tiles.TileGrid;

public class GridWithBoxes extends TileGrid {
    private static final Color altBoxColor = ColorMaker.make(SimpleColor.GREEN);

    public GridWithBoxes() {
        super(new Tile[][]{
                {new Tile(), new Tile(),    new Tile(),       new Tile(),               new Tile()},
                {new Tile(), new BoxTile(), new Tile(),       new BoxTile(altBoxColor), new Tile()},
                {new Tile(), new Tile(),    new Tile(),       new Tile(),               new Tile()},
                {new Tile(), new Tile(),    new PlayerTile(), new Tile(),               new Tile()},
                {new Tile(), new Tile(),    new Tile(),       new Tile(),               new Tile()},
        });
    }
}
