package ugg.tiles.tiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileTest {
    @Test
    public void test_swapContents() {
        Tile tile1 = new Tile("A");
        Tile tile2 = new Tile("B");

        swapAndTest(tile1, tile2);
    }

    @Test
    public void test_swapContents_oneEmpty() {
        Tile tile1 = new Tile("A");
        Tile tile2 = new Tile("");

        swapAndTest(tile1, tile2);
    }

    @Test
    public void test_swapContents_oneNull() {
        Tile tile1 = new Tile("A");
        Tile tile2 = new Tile(null);

        swapAndTest(tile1, tile2);
    }

    private void swapAndTest(Tile tile1, Tile tile2) {
        String tile1Contents = tile1.toString();
        String tile2Contents = tile2.toString();

        tile1.swapContents(tile2);

        Assertions.assertEquals(tile1Contents, tile2.toString());
        Assertions.assertEquals(tile2Contents, tile1.toString());
    }
}
