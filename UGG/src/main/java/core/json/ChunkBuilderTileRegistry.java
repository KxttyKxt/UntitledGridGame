package core.json;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import core.Tile;

import java.util.Map;

public class ChunkBuilderTileRegistry {

    public static ChunkBuilderTileRegistry createRegistryWithMap(Map<String, Tile.Builder> registry) {
        return new ChunkBuilderTileRegistry(registry);
    }


    private final ImmutableMap<String, Tile.Builder> registry;

    private ChunkBuilderTileRegistry(Map<String, Tile.Builder> registry) {
        this.registry = ImmutableMap.copyOf(registry);
    }


    public Tile get(String registryKey) {
        return Preconditions.checkNotNull(registry.get(registryKey)).build();
    }
}
