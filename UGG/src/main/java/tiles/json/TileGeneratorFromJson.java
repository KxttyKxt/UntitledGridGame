package tiles.json;

import tiles.Tile;

public abstract class TileGeneratorFromJson {
    abstract Tile generateTile(String jsonName);
}
