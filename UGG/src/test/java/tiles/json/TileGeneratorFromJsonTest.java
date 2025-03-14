package tiles.json;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tiles.Tile;

@SuppressWarnings("CanBeFinal")
class TileGeneratorFromJsonTest {

    static TileGeneratorFromJson tileGenerator = new TileGeneratorFromJson() {
        @Override
        Tile generateTile(String jsonName) {
            return switch (jsonName) {
                case "Default" -> Tile.defaultTile();
                case "A" -> Tile.withOnlyText("A");

                default -> throw new IllegalArgumentException();
            };
        }
    };

    @Test
    void generateTile_defaultTile() {
        Tile expectedTile = Tile.defaultTile();
        Tile actualTile = tileGenerator.generateTile("Default");

        Assertions.assertEquals(expectedTile, actualTile);
    }

    @Test
    void generateTile_a() {
        Tile expectedTile = Tile.withOnlyText("A");
        Tile actualTile = tileGenerator.generateTile("A");

        Assertions.assertEquals(expectedTile, actualTile);
    }

    @Test
    void generateTile_exception() {
        boolean exceptionWasThrown = false;

        try {
            tileGenerator.generateTile("not in switch");
        }
        catch (IllegalArgumentException jsonNameNotInSwitchException) {
            exceptionWasThrown = true;
        }
        finally {
            Assertions.assertTrue(exceptionWasThrown);
        }
    }
}

