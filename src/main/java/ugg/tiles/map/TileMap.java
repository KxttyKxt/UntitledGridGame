package ugg.tiles.map;

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
public class TileMap {

    // ========== Map Manipulation ==========

    private final Map<CoordinatePair, TileNode> map = new HashMap<>();

    public int size() {
        return map.size();
    }
    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean addTile(int x, int y, Tile tile) {
        if (map.get(new CoordinatePair(x, y)) != null)
            return false;

        map.put(new CoordinatePair(x, y), new TileNode(tile));
        updateTileNodeLinks();

        return true;
    }
    public void removeTile(int x, int y) {
        map.remove(new CoordinatePair(x, y)).clearAdjacentNodes();
    }

    public Tile getTile(int x, int y) {
        return getTileNode(x, y).getTile();
    }
    public void setTile(int x, int y, Tile tile) {
            TileNode tileNode = map.get(new CoordinatePair(x, y));

        if (tileNode == null)
            map.put(new CoordinatePair(x, y), new TileNode(tile));
        else
            tileNode.setTile(tile);
    }

    TileNode getTileNode(int x, int y) {
        return map.get(new CoordinatePair(x, y));
    }

    public void updateTileNodeLinks() {
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

    // ======================================


    // ============== Pathing ===============

    // Moving is Instantaneous, Non-contiguous, directly to destination
    public boolean moveTileContentsByCoords(int x, int y, TileNodeDirection[] directions) {
        TileNode sourceNode = getTileNode(x, y);
        TileNode destinationNode;

        CoordinatePair currentCoords = new CoordinatePair(x, y);
        for (TileNodeDirection direction : directions)
            currentCoords = currentCoords.relativeCoordinates(direction);

        destinationNode = map.get(currentCoords);

        return sourceNode.getTile().transferContentsTo(destinationNode.getTile());
    }

    // Moving is Tile-by-Tile, Contiguous
    public boolean moveTileContentsContiguously(int x, int y, TileNodeDirection[] directions) {
        if (!checkForContiguousPath(x, y, directions))
            return false;

        TileNode currentNode = getTileNode(x, y);

        for (TileNodeDirection direction : directions) {
            TileNode nextNode = currentNode.getAdjacentNode(direction);
            currentNode.getTile().transferContentsTo(nextNode.getTile());
            currentNode = nextNode;
        }

        return true;
    }
    // Checks every tile in the directions one at a time if they are all open for transfer.
    public boolean checkForContiguousPath(int x, int y, TileNodeDirection[] directions) {
        TileNode contiguousNode = getTileNode(x, y);
        try {
            for (TileNodeDirection direction : directions) {
                contiguousNode = contiguousNode.getAdjacentNode(direction);

                if (!contiguousNode.getTile().openForTransfer())
                    return false;
            }

            return true;
        }
        catch (NullPointerException TileNodeNotFoundException) {
            return false;
        }
    }

    // ======================================


    // ============== Display ===============



    // ======================================
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