package tiles.json;

import colors.Color;
import colors.ColorMaker;
import colors.SimpleColor;
import display.Displayable;
import display.SimpleDisplay;
import tiles.Chunk;
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
                "WallTile", wallTileBuilder()
        );
    }

    public Chunk constructGridFromJson() throws IOException {
        URL url = ZeldaChunkBuilder.class.getResource("start-grid-json.json");
        return super.constructGridFromJson(url);
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

    static Tile.Builder wallTileBuilder() {
        Displayable wallTileDisplay = SimpleDisplay.withText("#")
                .andColor(ColorMaker.make(SimpleColor.GREEN));

        return Tile.withTileDisplay(wallTileDisplay)
                .andTraversable(false);
    }
}
