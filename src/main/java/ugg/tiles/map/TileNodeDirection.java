package ugg.tiles.map;

public enum TileNodeDirection {
    NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST;

    public static TileNodeDirection fromOrdinal(int ordinal) {
        return switch (ordinal) {
            case 0 -> NORTH;
            case 1 -> NORTHEAST;
            case 2 -> EAST;
            case 3 -> SOUTHEAST;
            case 4 -> SOUTH;
            case 5 -> SOUTHWEST;
            case 6 -> WEST;
            case 7 -> NORTHWEST;

            default -> throw new IndexOutOfBoundsException(String.format("ordinal '%d' is out of range.", ordinal));
        };
    }


    public TileNodeDirection opposite() {
        return fromOrdinal((this.ordinal() + 4) % 8);
    }

    public TileNodeDirection clockwise() {
        return fromOrdinal((this.ordinal() + 1) % 8);
    }

    public TileNodeDirection counterclockwise() {
        return fromOrdinal((this.ordinal() + 7) % 8);
    }


}