package ugg.tiles;

public class TileGrid {

    static final String FORMAT_FOR_CELL = "[ %s ]";
    static final String NULL_CELL = " ".repeat(5);
    static final String EMPTY_CELL = String.format(FORMAT_FOR_CELL, new Tile().display());

    private final Tile[][] matrix;

    public TileGrid(int rows, int columns) {
        if (rows <= 0 || columns <= 0)
            throw new IllegalArgumentException("TileGrid must have at least 1 row and column.");

        matrix = new Tile[rows][columns];

        for (int row = 0; row < rows; row++)
            for (int col = 0; col < columns; col++)
                matrix[row][col] = new Tile();
    }
    public TileGrid(Tile[][] tilesForMatrix) {
        matrix = tilesForMatrix;
    }

    public void swapContents(int[] tile1RowCol, int[] tile2RowCol) {
        swapContents(
                matrix[tile1RowCol[0]][tile1RowCol[1]],
                matrix[tile2RowCol[0]][tile2RowCol[1]]
        );
    }
    void swapContents(Tile tile1, Tile tile2) {
        confirmInMatrix(tile1);
        confirmInMatrix(tile2);

        tile1.swapContentsWith(tile2);
    }

    public boolean transferContents(int[] tile1RowCol, int[] tile2RowCol) {
        return transferContents(
                matrix[tile1RowCol[0]][tile1RowCol[1]],
                matrix[tile2RowCol[0]][tile2RowCol[1]]
        );
    }
    boolean transferContents(Tile origin, Tile destination) {
        confirmInMatrix(origin);
        confirmInMatrix(destination);

        return origin.transferContentsTo(destination);
    }


    private void confirmInMatrix(Tile tile) {
        if (!matrixContains(tile))
            throw new IllegalArgumentException(String.format("Tile %s is not in matrix.", tile));
    }

    private boolean matrixContains(Tile tile) {
        for (Tile[] row : matrix)
            for (Tile cell : row)
                if (tile == cell)
                    return true;

        return false;
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