package tiles.json;

import tiles.Tile;

public abstract class TileGeneratorFromJson {
    public TileGeneratorFromJson() {}
    abstract Tile generateTile(String jsonName);
}
