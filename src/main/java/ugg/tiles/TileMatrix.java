package ugg.tiles;

public class TileMatrix {

    private final Tile[][] matrix;

    public TileMatrix(int rows, int columns) {
        if (rows <= 0 || columns <= 0)
            throw new IllegalArgumentException("TileMatrix must have at least 1 row and column.");

        matrix = new Tile[rows][columns];
    }


    public void setTile(Tile tileToSet, int row, int column) {
        matrix[row][column] = tileToSet;
    }

    public Tile getTile(int row, int column) {
        return matrix[row][column];
    }

    public boolean addTile(Tile tileToAdd, int row, int column) {
        if (matrix[row][column] != null)
            return false;

        setTile(tileToAdd, row, column);
        return true;
    }

    public Tile removeTile(int row, int column) {
        Tile toReturn = matrix[row][column];
        matrix[row][column] = null;

        return toReturn;
    }
}