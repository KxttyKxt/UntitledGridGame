package ugg.tiles.graph.nodes;

import ugg.tiles.tiles.Tile;
import ugg.tiles.graph.TileNodeDirection;

// This class and its subclasses are intentionally package-private

abstract class TileNode {
    Tile key;

    TileNode north;
    TileNode east;
    TileNode south;
    TileNode west;

    TileNode(Tile key) {
        this.key = key;
    }


    TileNode getAdjacentNode(TileNodeDirection direction) {
        return switch (direction) {
            case NORTH -> this.north;
            case EAST -> this.east;
            case SOUTH -> this.south;
            case WEST -> this.west;
        };
    }

    void setAdjacentNode(TileNode nodeToAdd, TileNodeDirection direction, boolean bidirectional) {
        switch (direction) {
            case NORTH -> this.north = nodeToAdd;
            case EAST -> this.east = nodeToAdd;
            case SOUTH -> this.south = nodeToAdd;
            case WEST -> this.west = nodeToAdd;
        };

        if (bidirectional)
            nodeToAdd.setAdjacentNode(this, direction.opposite(), false);
    }

    void clearAdjacentNodes() {
        north = east = south = west = null;
    }


    @Override
    public String toString() {
        return String.format(
                """
                
                ** %s **
                north: %s
                east:  %s
                south: %s
                west:  %s
                """,
                justGetKeyContents(this),
                justGetKeyContents(north),
                justGetKeyContents(east),
                justGetKeyContents(south),
                justGetKeyContents(west)
        );
    }

    static String justGetKeyContents(TileNode node) {
        return (node != null && node.key != null)
                ? node.key.toString()
                : "null";
    }
}
