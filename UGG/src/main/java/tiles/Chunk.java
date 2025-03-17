package tiles;

import java.util.Objects;

public class Chunk {
    static final String FORMAT_FOR_CELL = " %s ";
    static final String NULL_CELL = " ".repeat(FORMAT_FOR_CELL.length() - 1);

    private final Tile[][] matrix;

    public Chunk(Tile[][] tilesForMatrix) {
        matrix = tilesForMatrix;
    }

    public boolean addOccupant(Occupant occupant, int row, int col) {
        Tile tileToAddContentsTo = matrix[row][col];
        return tileToAddContentsTo.addContents(occupant);
    }

    public boolean transferOccupant(int[] tile1RowCol, int[] tile2RowCol) {
        return transferOccupant(
                matrix[tile1RowCol[0]][tile1RowCol[1]],
                matrix[tile2RowCol[0]][tile2RowCol[1]]
        );
    }
    private boolean transferOccupant(Tile origin, Tile destination) {
        return origin.transferOccupantTo(destination);
    }


    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();

        for (int row = 0; row < matrix.length; row++) {
            for (Tile tile : matrix[row]) {
                if (tile == null)
                    toReturn.append(NULL_CELL);
                else
                    toReturn.append(String.format(FORMAT_FOR_CELL, tile.display()));
            }

            if (row != matrix.length - 1)
                toReturn.append(String.format("%n"));
        }

        return toReturn.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chunk that = (Chunk) o;
        return Objects.deepEquals(this.matrix, that.matrix);
    }
}