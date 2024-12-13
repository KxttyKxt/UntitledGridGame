package ugg.tiles.graph;

public enum TileNodeDirection {
    NORTH, EAST, SOUTH, WEST;

    public static TileNodeDirection fromOrdinal(int ordinal) {
        return switch (ordinal) {
            case 0 -> NORTH;
            case 1 -> EAST;
            case 2 -> SOUTH;
            case 3 -> WEST;

            default -> null;
        };
    }

    public TileNodeDirection opposite() {
        return switch (this) {
            case NORTH -> SOUTH;
            case EAST -> WEST;
            case SOUTH -> NORTH;
            case WEST -> EAST;
        };
    }
    public TileNodeDirection clockwise() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        };
    }
    public TileNodeDirection counterclockwise() {
        return switch (this) {
            case NORTH -> WEST;
            case WEST -> SOUTH;
            case SOUTH -> EAST;
            case EAST -> NORTH;
        };
    }


}