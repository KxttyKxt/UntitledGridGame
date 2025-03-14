package tiles.json;

import tiles.Tile;

public abstract class TileGeneratorFromJson {
    public abstract Tile generateTile(String jsonName);
}
