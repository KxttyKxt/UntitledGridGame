package tiles.json;

import colors.Color;
import colors.ColorMaker;
import colors.SimpleColor;
import display.Displayable;
import display.SimpleDisplay;
import tiles.Chunk;
import tiles.Occupant;
import tiles.Tile;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class ZeldaChunkBuilder extends JsonChunkBuilder {

    @Override
    protected Map<String, Tile.Builder> createMapForRegistry() {
        return Map.of(
                "BasicTile", basicTileBuilder(),
                "CaveTile", caveTileBuilder(),
                "RockTile", rockTileBuilder(),
                "SecretCaveTile", secretCaveTileBuilder(),
                "WallTile", wallTileBuilder()
        );
    }

    public Chunk constructChunkFromJson() throws IOException {
        URL url = ZeldaChunkBuilder.class.getResource("chunk/north-of-start.json");
        return super.constructChunkFromJson(url);
    }


    static Tile.Builder basicTileBuilder() {
        Displayable basicTileDisplay = SimpleDisplay.withText(".")
                .andColor(ColorMaker.make(187, false));

        return Tile.withTileDisplay(basicTileDisplay);
    }

    static Tile.Builder caveTileBuilder() {
        Color caveTileColor = ColorMaker.make(
                new int[]{175, 175, 175}, false);

        Displayable caveTileDisplay = SimpleDisplay.withText("0")
                .andColor(caveTileColor);

        return Tile.withTileDisplay(caveTileDisplay);
    }

    static Tile.Builder rockTileBuilder() {
        Displayable rockDisplay = SimpleDisplay.withText("@")
                .andColor(ColorMaker.make(SimpleColor.GREEN));

        Occupant rock = Occupant.newOccupant(rockDisplay);

       return basicTileBuilder().andOccupant(rock);
    }

    static Tile.Builder secretCaveTileBuilder() {
        // For now, non-functional
        return wallTileBuilder();
    }

    static Tile.Builder wallTileBuilder() {
        Displayable wallTileDisplay = SimpleDisplay.withText("#")
                .andColor(ColorMaker.make(SimpleColor.GREEN));

        return Tile.withTileDisplay(wallTileDisplay)
                .andTraversable(false);
    }
}
