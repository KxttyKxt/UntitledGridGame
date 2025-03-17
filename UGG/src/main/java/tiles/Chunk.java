package tiles;

import com.google.common.collect.Maps;

import java.util.Map;

public class Chunk {
    static final String FORMAT_FOR_CELL = " %s ";
    static final String NULL_CELL = " ".repeat(FORMAT_FOR_CELL.length() - 1);

    private final Map<Point2D, Tile> tileMap;
    private final int xMax;
    private final int yMax;

    public Chunk(Tile[][] tileMatrix) {
        xMax = tileMatrix[0].length - 1;
        yMax = tileMatrix.length - 1;

        tileMap = convertToMap(tileMatrix);
    }

    private Map<Point2D, Tile> convertToMap(Tile[][] tileMatrix) {
        Map<Point2D, Tile> tileMap = Maps.newHashMap();

        for (int x = 0; x <= xMax; x++)
            for (int y = 0; y <= yMax; y++)
                tileMap.put(Point2D.of(x, y), tileMatrix[y][x]);

        return tileMap;
    }


    public boolean addOccupant(Occupant occupant, Point2D point) {
        Tile tileToAddContentsTo = tileMap.get(point);
        return tileToAddContentsTo.addContents(occupant);
    }

    public boolean transferOccupant(Point2D from, Point2D to) {
        return transferOccupant(
                tileMap.get(from),
                tileMap.get(to)
        );
    }
    private boolean transferOccupant(Tile origin, Tile destination) {
        return origin.transferOccupantTo(destination);
    }


    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();

        for (int y = 0; y <= yMax; y++) {
            for (int x = 0; x <= xMax; x++) {
                Tile tile = tileMap.get(Point2D.of(x, y));

                toReturn.append(
                        (tile == null)
                        ? NULL_CELL
                        : String.format(FORMAT_FOR_CELL, tile.display())
                );
            }

            if (y != yMax)
                toReturn.append(String.format("%n"));
        }

        return toReturn.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chunk that = (Chunk) o;
        return this.tileMap.equals(that.tileMap);
    }
}