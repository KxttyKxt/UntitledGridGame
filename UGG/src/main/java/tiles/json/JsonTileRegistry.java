package tiles.json;

import tiles.Tile;

import java.util.Map;

public abstract class JsonTileRegistry {
    private final Map<String, Tile.Builder> registry = initializeRegistry();

    protected abstract Map<String, Tile.Builder> initializeRegistry();

    public Tile get(String registryKey) {
        Tile.Builder registryValue = registry.get(registryKey);

        if (registryValue == null)
            throw new IllegalArgumentException(String.format("Key '%s' is not in the tile registry.", registryKey));

        return registryValue.build();
    }
}
