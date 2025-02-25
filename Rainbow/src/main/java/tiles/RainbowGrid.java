package tiles;

public class RainbowGrid extends TileGrid {
    public RainbowGrid() {
        super(new Tile[][]{
                {new RainbowTile(0), new RainbowTile(1), new RainbowTile(2), new RainbowTile(3)},
                {new RainbowTile(1), new RainbowTile(2), new RainbowTile(3), new RainbowTile(4)},
                {new RainbowTile(2), new RainbowTile(3), new RainbowTile(4), new RainbowTile(0)},
                {new RainbowTile(3), new RainbowTile(4), new RainbowTile(0), new RainbowTile(1)}
        });
    }
}
