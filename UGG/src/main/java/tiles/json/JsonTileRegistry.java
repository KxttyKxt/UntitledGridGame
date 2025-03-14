package tiles.json;

import tiles.Tile;

import java.util.HashMap;

public abstract class JsonTileRegistry {
    HashMap<String, Tile.Builder> registry;

    protected abstract HashMap<String, Tile.Builder> initializeRegistry();

    public JsonTileRegistry() {
        registry = initializeRegistry();
    }

    public Tile get(String registryKey) {
        Tile.Builder registryValue = registry.get(registryKey);

        if (registryValue == null)
            throw new IllegalArgumentException(String.format("Key '%s' is not in the tile registry.", registryKey));

        return registryValue.build();
    }
}
