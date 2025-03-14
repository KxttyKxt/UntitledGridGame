package tiles.json;

import colors.ColorMaker;
import colors.SimpleColor;
import display.SimpleDisplay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tiles.Tile;

class ZeldaTileGeneratorTest {
    ZeldaTileGenerator generator = new ZeldaTileGenerator();

    Tile expectedBasicTile = Tile.withTileDisplay(SimpleDisplay.withText(".")
            .andColor(ColorMaker.make(187, false))).build();

    Tile expectedCaveTile = Tile.withTileDisplay(SimpleDisplay.withText("0")
            .andColor(ColorMaker.make(new int[]{175, 175, 175}, false))).build();

    Tile expectedWallTile = Tile.withTileDisplay(SimpleDisplay.withText("#")
            .andColor(ColorMaker.make(SimpleColor.GREEN))).andTraversable(false).build();

    Tile actualBasicTile = generator.generateTile("BasicTile");
    Tile actualCaveTile = generator.generateTile("CaveTile");
    Tile actualWallTile = generator.generateTile("WallTile");

    @Test
    void test_generateTile_basicTile() {
        Assertions.assertEquals(expectedBasicTile, actualBasicTile);
    }

    @Test
    void test_generateTile_caveTile() {
        Assertions.assertEquals(expectedCaveTile, actualCaveTile);
    }

    @Test
    void test_generateTile_wallTile() {
        Assertions.assertEquals(expectedWallTile, actualWallTile);
    }

    @Test
    void test_generateTile_null() {
        Assertions.assertNull(generator.generateTile(" "));
    }

    @Test
    void test_generateTile_exception() {
        boolean exceptionWasThrown = false;

        try {
            Tile ignored = generator.generateTile("default");
        }
        catch (IllegalArgumentException argumentDidNotMatchAnyKnownTileAliases) {
            exceptionWasThrown = true;
        }
        finally {
            Assertions.assertTrue(exceptionWasThrown);
        }
    }
}