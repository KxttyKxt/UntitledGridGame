package ugg.tiles.graph;

import ugg.tiles.graph.nodes.SimpleTileNode;
import ugg.tiles.graph.nodes.TileNode;
import ugg.tiles.tiles.Tile;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>This class stores a complex collection of {@code TileNode}s.</p>
 * A {@code TileMatrixGraph} is a matrix in that it uses coordinates to link tiles.
 * <p>
 * A {@code TileMatrixGraph} is a graph in that TileNodes effectively harbor their own adjacency lists.
 */
public class TileMatrixGraph {

    // ========== Matrix Map ==========

    private final Map<CoordinatePair, TileNode> coordinatedTileMap = new HashMap<>();

    public boolean addTile(int x, int y, Tile tile) {
        if (coordinatedTileMap.get(new CoordinatePair(x, y)) != null)
            return false;

        coordinatedTileMap.put(new CoordinatePair(x, y), new SimpleTileNode(tile));
        return true;
    }
    public void removeTile(int x, int y) {
        coordinatedTileMap.remove(new CoordinatePair(x, y));
    }

    public Tile getTile(int x, int y) {
        return getTileNode(x, y).getKey();
    }
    public void setTile(int x, int y, Tile tile) {
        TileNode tileNode = coordinatedTileMap.get(new CoordinatePair(x, y));

        if (tileNode == null)
            coordinatedTileMap.put(new CoordinatePair(x, y), new SimpleTileNode(tile));
        else
            tileNode.setKey(tile);
    }

    public int size() {
        return coordinatedTileMap.size();
    }
    public boolean isEmpty() {
        return coordinatedTileMap.isEmpty();
    }

    public TileNode getTileNode(int x, int y) {
        return coordinatedTileMap.get(new CoordinatePair(x, y));
    }
    public void linkAllTileNodesByCoordinates() {
        coordinatedTileMap.forEach((coordinatePair, tileNode) -> {
            for (int i = 0; i < TileNodeDirection.values().length; i++) {

                TileNodeDirection currentDirection = TileNodeDirection.fromOrdinal(i);

                assert currentDirection != null;
                TileNode adjacentTileNode =
                        coordinatedTileMap.get(coordinatePair.relativeCoordinates(currentDirection));

                tileNode.setAdjacentNode(
                        adjacentTileNode, currentDirection, true
                );
            }
        });
    }

    // ================================

}

record CoordinatePair (int x, int y) {

    CoordinatePair relativeCoordinates(TileNodeDirection direction) {
        return switch (direction) {
            case NORTH -> new CoordinatePair(x, y + 1);
            case NORTHEAST -> new CoordinatePair(x + 1, y + 1);
            case EAST -> new CoordinatePair(x + 1, y);
            case SOUTHEAST -> new CoordinatePair(x + 1, y - 1);
            case SOUTH -> new CoordinatePair(x, y - 1);
            case SOUTHWEST -> new CoordinatePair(x - 1, y - 1);
            case WEST -> new CoordinatePair(x - 1, y);
            case NORTHWEST -> new CoordinatePair(x - 1, y + 1);
        };
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinatePair that = (CoordinatePair) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}