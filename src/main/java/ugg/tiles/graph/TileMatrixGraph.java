package ugg.tiles.graph;

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

    private final Map<CoordinatePair, TileNode> map = new HashMap<>();

    public boolean addTile(int x, int y, Tile tile) {
        if (map.get(new CoordinatePair(x, y)) != null)
            return false;

        map.put(new CoordinatePair(x, y), new TileNode(tile));
        return true;
    }
    public void removeTile(int x, int y) {
        map.remove(new CoordinatePair(x, y));
    }

    public Tile getTile(int x, int y) {
        return getTileNode(x, y).getKey();
    }
    public void setTile(int x, int y, Tile tile) {
            TileNode tileNode = map.get(new CoordinatePair(x, y));

        if (tileNode == null)
            map.put(new CoordinatePair(x, y), new TileNode(tile));
        else
            tileNode.setKey(tile);
    }

    TileNode getTileNode(int x, int y) {
        return map.get(new CoordinatePair(x, y));
    }

    public int size() {
        return map.size();
    }
    public boolean isEmpty() {
        return map.isEmpty();
    }

    public void linkAllTileNodesByCoordinates() {
        map.forEach((coordinatePair, tileNode) -> {
            for (int i = 0; i < TileNodeDirection.values().length; i++) {

                TileNodeDirection currentDirection = TileNodeDirection.fromOrdinal(i);

                TileNode adjacentTileNode =
                        map.get(coordinatePair.relativeCoordinates(currentDirection));

                tileNode.setAdjacentNode(
                        adjacentTileNode, currentDirection, true
                );
            }
        });
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}