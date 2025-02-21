package ugg.tiles;

public class TileMatrix {

    private final Tile[][] matrix;

    public TileMatrix(int rows, int columns) {
        if (rows <= 0 || columns <= 0)
            throw new IllegalArgumentException("TileMatrix must have at least 1 row and column.");

        matrix = new Tile[rows][columns];

        for (int row = 0; row < rows; row++)
            for (int col = 0; col < columns; col++)
                matrix[row][col] = new Tile();
    }
    public TileMatrix(Tile[][] tilesForMatrix) {
        matrix = tilesForMatrix;
    }

    public void setTile(Tile tileToSet, int row, int column) {
        matrix[row][column] = tileToSet;
    }

    public Tile getTile(int row, int column) {
        return matrix[row][column];
    }

    public boolean addTile(Tile tileToAdd, int row, int column) {
        if (!matrix[row][column].isEmpty())
            return false;
        else {
            setTile(tileToAdd, row, column);
            return true;
        }
    }

    public Tile removeTile(int row, int column) {
        Tile toRemove = matrix[row][column];
        matrix[row][column] = new Tile();

        if (toRemove.isEmpty())
            return null;
        else
            return toRemove;
    }


    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();

        for (int row = 0; row < matrix.length; row++) {
            for (Tile tile : matrix[row]) {
                toReturn.append(String.format(
                        "[ %s ]",
                        (tile == null) ? "." : tile.display()
                ));
            }

            if (row != matrix.length - 1)
                toReturn.append(String.format("%n"));
        }

        return toReturn.toString();
    }
}