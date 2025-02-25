package tiles;

import colors.Color;
import colors.ColorMaker;
import colors.SimpleColor;

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
