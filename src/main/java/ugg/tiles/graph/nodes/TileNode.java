package ugg.tiles.graph.nodes;

import ugg.tiles.graph.TileNodeDirection;
import ugg.tiles.tiles.Tile;

// This class and its subclasses are intentionally package-private

abstract class TileNode {
    Tile key;

    TileNode north, northeast, east, southeast;
    TileNode south, southwest, west, northwest;

    TileNode(Tile key) {
        this.key = key;
    }


    TileNode getAdjacentNode(TileNodeDirection direction) {
        return switch (direction) {
            case NORTH -> this.north;
            case NORTHEAST -> this.northeast;
            case EAST -> this.east;
            case SOUTHEAST -> this.southeast;
            case SOUTH -> this.south;
            case SOUTHWEST -> this.southwest;
            case WEST -> this.west;
            case NORTHWEST -> northwest;
        };
    }

    void setAdjacentNode(TileNode nodeToAdd, TileNodeDirection direction, boolean bidirectional) {
        switch (direction) {
            case NORTH -> this.north = nodeToAdd;
            case NORTHEAST -> this.northeast = nodeToAdd;
            case EAST -> this.east = nodeToAdd;
            case SOUTHEAST -> this.southeast = nodeToAdd;
            case SOUTH -> this.south = nodeToAdd;
            case SOUTHWEST -> this.southwest = nodeToAdd;
            case WEST -> this.west = nodeToAdd;
            case NORTHWEST -> this.northwest = nodeToAdd;
        }

        if (bidirectional)
            nodeToAdd.setAdjacentNode(this, direction.opposite(), false);
    }

    void clearAdjacentNodes() {
        north = northeast = east = southeast = south = southwest = west = northwest = null;
    }


    @Override
    public String toString() {
        return String.format(
                """
                
                ** %s **
                north:     %s
                northeast: %s
                east:      %s
                southeast: %s
                south:     %s
                southwest: %s
                west:      %s
                northwest: %s
                """,
                justGetKeyContents(this),
                justGetKeyContents(north),
                justGetKeyContents(northeast),
                justGetKeyContents(east),
                justGetKeyContents(southeast),
                justGetKeyContents(south),
                justGetKeyContents(southwest),
                justGetKeyContents(west),
                justGetKeyContents(northwest)
        );
    }

    static String justGetKeyContents(TileNode node) {
        return (node != null && node.key != null)
                ? node.key.toString()
                : "null";
    }
}
