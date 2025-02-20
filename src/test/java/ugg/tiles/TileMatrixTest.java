package ugg.tiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TileMatrixTest {
    @BeforeAll
    public static void initializeTiles() {
        tileA = new Tile("A");
        tileB = new Tile("B");
    }
    static TileMatrix tileMatrix;
    static Tile tileA, tileB;

    private void tileMatrixSizeOne() {
        tileMatrix = new TileMatrix(1, 1);
    }
    private void tileMatrixSizeOne(Tile tileToSet) {
        tileMatrixSizeOne();
        tileMatrix.setTile(tileToSet, 0, 0);
    }


    @Test
    public void test_constructor_nonPositiveValuesException_rows() {
        boolean errorWasThrown = false;

        try {
            tileMatrix = new TileMatrix(-1, 2);
        }
        catch (IllegalArgumentException arraySizesCannotBeNegativeException) {
            errorWasThrown = true;
        }

        Assertions.assertTrue(errorWasThrown);
    }

    @Test
    public void test_constructor_nonPositiveValuesException_columns() {
        boolean errorWasThrown = false;

        try {
            tileMatrix = new TileMatrix(2, 0);
        }
        catch (IllegalArgumentException arraySizesCannotBeNegativeException) {
            errorWasThrown = true;
        }

        Assertions.assertTrue(errorWasThrown);
    }

    @Test
    public void test_constructor_nonPositiveValuesException_both() {
        boolean errorWasThrown = false;

        try {
            tileMatrix = new TileMatrix(0, -1);
        }
        catch (IllegalArgumentException arraySizesCannotBeNegativeException) {
            errorWasThrown = true;
        }

        Assertions.assertTrue(errorWasThrown);
    }

    @Test
    public void test_constructor_nonPositiveValuesException_noException() {
        boolean errorWasThrown = false;

        try {
            tileMatrixSizeOne();
        }
        catch (IllegalArgumentException arraySizesCannotBeNegativeException) {
            errorWasThrown = true;
        }

        Assertions.assertFalse(errorWasThrown);
    }


    @Test
    public void test_setAndGetTile_oneTile() {
        tileMatrixSizeOne(tileA);
        Assertions.assertEquals(tileA, tileMatrix.getTile(0, 0));
    }

    @Test
    public void test_setAndGetTile_twoTiles() {
        tileMatrix = new TileMatrix(1, 2);

        tileMatrix.setTile(tileA, 0, 0);
        tileMatrix.setTile(tileB, 0, 1);

        Assertions.assertEquals(tileA, tileMatrix.getTile(0, 0));
        Assertions.assertEquals(tileB, tileMatrix.getTile(0, 1));
    }

    @Test
    public void test_getTile_null() {
        tileMatrixSizeOne();
        Assertions.assertNull(tileMatrix.getTile(0, 0));
    }


    @Test
    public void test_addTile_true() {
        tileMatrixSizeOne();
        Assertions.assertTrue(tileMatrix.addTile(tileA, 0, 0));
    }

    @Test
    public void test_addTile_false() {
        tileMatrixSizeOne();
        tileMatrix.setTile(tileA, 0, 0);

        Assertions.assertFalse(tileMatrix.addTile(tileB, 0, 0));
    }


    @Test
    public void test_removeTile_returnNull() {
        tileMatrixSizeOne();
        Assertions.assertNull(tileMatrix.removeTile(0, 0));
    }

    @Test
    public void test_removeTile_returnTileA() {
        tileMatrixSizeOne(tileA);

        Tile expectedTileRemoved = tileA;
        Tile actualTileRemoved = tileMatrix.removeTile(0, 0);

        Assertions.assertEquals(expectedTileRemoved, actualTileRemoved);
    }

    @Test
    public void test_removeTile_ensureIndexIsNowNull() {
        tileMatrixSizeOne(tileA);
        Assertions.assertEquals(tileA, tileMatrix.getTile(0, 0));

        tileMatrix.removeTile(0, 0);
        Assertions.assertNull(tileMatrix.getTile(0, 0));
    }
}
