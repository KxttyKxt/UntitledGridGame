package ugg.app.testmatrices;

import ugg.colors.ColorMaker;
import ugg.colors.SimpleColor;
import ugg.tiles.Tile;
import ugg.tiles.TileMatrix;

public class TestMatrices {
    private static final Tile[][] tilesArray = {
            {newTile(), newTile(),    newTile(), newTile()},
            {newTile(), playerTile(), redTile(), newTile()},
            {newTile(), redTile(),    redTile(), newTile()},
            {newTile(), newTile(),    newTile(), newTile()}
    };
    private static final TileMatrix tileMatrix = new TileMatrix(tilesArray);

    private static Tile newTile() {
        return new Tile();
    }
    private static Tile redTile() {
        return new Tile(".", ColorMaker.make(SimpleColor.RED));
    }
    private static Tile playerTile() {
        return new Tile("@player", ColorMaker.make(SimpleColor.CYAN));
    }

    private static void swapAndPrint(int[] tile1Pos, int[] tile2Pos, String header) {
        swapTiles(tile1Pos, tile2Pos);
        printMatrixWithHeader(header);
    }

    private static void swapTiles(int[] tile1Pos, int[] tile2Pos) {
        tileMatrix.swapContents(tile1Pos, tile2Pos);
    }
    private static void printMatrixWithHeader(String header) {
        System.out.printf("%n%s:%n%s%n", header, tileMatrix);
    }

    public static void main(String[] args) {
        printMatrixWithHeader("Start");

        swapAndPrint(new int[]{1, 1}, new int[]{1, 2}, "Move 1");
        swapAndPrint(new int[]{1, 2}, new int[]{1, 3}, "Move 2");

        System.out.printf("%nTransfer of Red to Empty: %b%n%s%n",
                tileMatrix.transferContents(
                    new int[]{1, 1},
                    new int[]{3, 3}
                ),
                tileMatrix
        );
        System.out.printf("%nTransfer of Player to Red: %b%n%s%n",
                tileMatrix.transferContents(
                    new int[]{1, 3},
                    new int[]{3, 3}
                ),
                tileMatrix
        );
    }
}
