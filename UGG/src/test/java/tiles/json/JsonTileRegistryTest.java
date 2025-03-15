package tiles.json;

import display.SimpleDisplay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tiles.Tile;

import java.util.Map;

@SuppressWarnings("CanBeFinal")
class JsonTileRegistryTest {

    static JsonTileRegistry tileRegistry = JsonTileRegistry.createRegistryWithMap(Map.of(
            "A", Tile.withTileDisplay(SimpleDisplay.withOnlyText("A")),
            "B", Tile.withTileDisplay(SimpleDisplay.withOnlyText("B"))
    ));

    @Test
    void get_a() {
        Tile expectedTile = Tile.withOnlyText("A");
        Tile actualTile = tileRegistry.get("A");

        Assertions.assertEquals(expectedTile, actualTile);
    }

    @Test
    void get_b() {
        Tile expectedTile = Tile.withOnlyText("B");
        Tile actualTile = tileRegistry.get("B");

        Assertions.assertEquals(expectedTile, actualTile);
    }

    @Test
    void get_exception() {
        boolean exceptionWasThrown = false;

        try {
            tileRegistry.get("not in registry");
        }
        catch (NullPointerException registryReturnedNullForUnmappedKeyException) {
            exceptionWasThrown = true;
        }
        finally {
            Assertions.assertTrue(exceptionWasThrown);
        }
    }

    @Test
    void get_notSame() {
        Tile tile1 = tileRegistry.get("A");
        Tile tile2 = tileRegistry.get("A");

        Assertions.assertNotSame(tile1, tile2);
    }
}

