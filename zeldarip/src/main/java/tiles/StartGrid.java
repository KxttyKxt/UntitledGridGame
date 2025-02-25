package tiles;

public class StartGrid extends TileGrid {
    public StartGrid() {
        super(new Tile[][]{
                { new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new BasicTile(), new BasicTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile() },
                { new WallTile(), new WallTile(), new WallTile(), new WallTile(), new CaveTile(), new WallTile(), new WallTile(), new BasicTile(), new BasicTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile() },
                { new WallTile(), new WallTile(), new WallTile(), new WallTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile() },
                { new WallTile(), new WallTile(), new WallTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile() },
                { new WallTile(), new WallTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile() },
                { new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile() },
                { new WallTile(), new WallTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new WallTile(), new WallTile() },
                { new WallTile(), new WallTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new WallTile(), new WallTile() },
                { new WallTile(), new WallTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new BasicTile(), new WallTile(), new WallTile() },
                { new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile(), new WallTile() }
        });
    }
}
