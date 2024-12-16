package ugg.tiles.tiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileTest {
    @Test
    public void test_swapContentsWith() {
        Tile tile1 = new Tile("A");
        Tile tile2 = new Tile("B");

        swapAndTest(tile1, tile2);
    }

    @Test
    public void test_swapContents_With_oneEmpty() {
        Tile tile1 = new Tile("A");
        Tile tile2 = new Tile("");

        swapAndTest(tile1, tile2);
    }

    @Test
    public void test_swapContents_With_oneNull() {
        Tile tile1 = new Tile("A");
        Tile tile2 = new Tile(null);

        swapAndTest(tile1, tile2);
    }

    private void swapAndTest(Tile tile1, Tile tile2) {
        String tile1Contents = tile1.toString();
        String tile2Contents = tile2.toString();

        tile1.swapContentsWith(tile2);

        Assertions.assertEquals(tile1Contents, tile2.toString());
        Assertions.assertEquals(tile2Contents, tile1.toString());
    }


    @Test
    public void test_transferContents_To_true() {
        Tile tile1 = new Tile("Contents");
        Tile tile2 = new Tile("");

        Assertions.assertTrue(tile1.transferContentsTo(tile2));

        Assertions.assertEquals("", tile1.toString());
        Assertions.assertEquals("Contents", tile2.toString());
    }

    @Test
    public void test_transferContents_To_false() {
        Tile tile1 = new Tile("Contents");
        Tile tile2 = new Tile("Also Contents");

        Assertions.assertFalse(tile1.transferContentsTo(tile2));

        Assertions.assertEquals("Contents", tile1.toString());
        Assertions.assertEquals("Also Contents", tile2.toString());
    }
}
