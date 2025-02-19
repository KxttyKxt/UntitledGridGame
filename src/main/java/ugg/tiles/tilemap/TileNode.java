package ugg.tiles.tilemap;

import ugg.tiles.tiles.Tile;

class TileNode {
    private Tile key;

    private TileNode north, northeast, east, southeast;
    private TileNode south, southwest, west, northwest;

    TileNode(Tile key) {
        this.key = key;
    }


    public Tile getTile() {
        return key;
    }

    public void setTile(Tile tile) {
        this.key = tile;
    }

    public TileNode getAdjacentNode(TileNodeDirection direction) {
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

    public void setAdjacentNode(TileNode adjacentNode, TileNodeDirection direction, boolean bidirectional) {

        switch (direction) {
            case NORTH -> this.north = adjacentNode;
            case NORTHEAST -> this.northeast = adjacentNode;
            case EAST -> this.east = adjacentNode;
            case SOUTHEAST -> this.southeast = adjacentNode;
            case SOUTH -> this.south = adjacentNode;
            case SOUTHWEST -> this.southwest = adjacentNode;
            case WEST -> this.west = adjacentNode;
            case NORTHWEST -> this.northwest = adjacentNode;
        }

        if (bidirectional && adjacentNode != null)
            adjacentNode.setAdjacentNode(this, direction.opposite(), false);
    }

    public void clearAdjacentNodes() {
        for (TileNodeDirection direction : TileNodeDirection.values()) {
            this.setAdjacentNode(null, direction, true);
        }
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

    public static String justGetKeyContents(TileNode node) {
        return (node != null && node.key != null)
                ? node.key.toString()
                : "null";
    }
}
