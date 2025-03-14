package tiles.json;

import colors.ColorMaker;
import colors.SimpleColor;
import display.SimpleDisplay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tiles.Tile;

class ZeldaTileRegistryTest {
    ZeldaTileRegistry registry = new ZeldaTileRegistry();

    Tile expectedBasicTile = Tile.withTileDisplay(SimpleDisplay.withText(".")
            .andColor(ColorMaker.make(187, false))).build();

    Tile expectedCaveTile = Tile.withTileDisplay(SimpleDisplay.withText("0")
            .andColor(ColorMaker.make(new int[]{175, 175, 175}, false))).build();

    Tile expectedWallTile = Tile.withTileDisplay(SimpleDisplay.withText("#")
            .andColor(ColorMaker.make(SimpleColor.GREEN))).andTraversable(false).build();

    Tile actualBasicTile = registry.get("BasicTile");
    Tile actualCaveTile = registry.get("CaveTile");
    Tile actualWallTile = registry.get("WallTile");

    @Test
    void test_get_basicTile() {
        Assertions.assertEquals(expectedBasicTile, actualBasicTile);
    }

    @Test
    void test_get_caveTile() {
        Assertions.assertEquals(expectedCaveTile, actualCaveTile);
    }

    @Test
    void test_get_wallTile() {
        Assertions.assertEquals(expectedWallTile, actualWallTile);
    }

    @Test
    void test_get_exception() {
        boolean exceptionWasThrown = false;

        try {
            Tile ignored = registry.get("default");
        }
        catch (IllegalArgumentException argumentDidNotMatchAnyKnownTileAliases) {
            exceptionWasThrown = true;
        }
        finally {
            Assertions.assertTrue(exceptionWasThrown);
        }
    }
}