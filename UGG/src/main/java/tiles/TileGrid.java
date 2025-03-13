package tiles;

import display.Displayable;

public class TileGrid {
    static final String FORMAT_FOR_CELL = " %s ";
    static final String NULL_CELL = " ".repeat(FORMAT_FOR_CELL.length() - 1);

    private final Tile[][] matrix;

    TileGrid(Tile[][] tilesForMatrix) {
        matrix = tilesForMatrix;
    }

    public void addContents(Displayable contents, int row, int col) {
        Tile tileToAddContentsTo = matrix[row][col];
        tileToAddContentsTo.addContents(contents);
    }

    public boolean transferContents(int[] tile1RowCol, int[] tile2RowCol) {
        if (coordsAreOutOfBounds(tile1RowCol, tile2RowCol)) return false;

        else return transferContents(
                matrix[tile1RowCol[0]][tile1RowCol[1]],
                matrix[tile2RowCol[0]][tile2RowCol[1]]
        );
    }
    private boolean transferContents(Tile origin, Tile destination) {
        return origin.transferContentsTo(destination);
    }

    private boolean coordsAreOutOfBounds(int[] tile1RowCol, int[] tile2RowCol) {
        return tile1RowCol[0] < 0 || tile1RowCol[0] >= matrix.length
            || tile1RowCol[1] < 0 || tile1RowCol[1] >= matrix[0].length
            || tile2RowCol[0] < 0 || tile2RowCol[0] >= matrix.length
            || tile2RowCol[1] < 0 || tile2RowCol[1] >= matrix[0].length;
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
}