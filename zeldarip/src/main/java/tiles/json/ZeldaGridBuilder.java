package tiles.json;

import colors.Color;
import colors.ColorMaker;
import colors.SimpleColor;
import display.Displayable;
import display.SimpleDisplay;
import tiles.Tile;
import tiles.TileGrid;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class ZeldaGridBuilder extends JsonGridBuilder {

    @Override
    protected Map<String, Tile.Builder> createMapForRegistry() {
        return Map.of(
                "BasicTile", registryBuilders.basicTile(),
                "CaveTile", registryBuilders.caveTile(),
                "WallTile", registryBuilders.wallTile()
        );
    }

    public TileGrid constructGridFromJson() throws IOException {
        URL url = ZeldaGridBuilder.class.getResource("start-grid-json.json");
        return super.constructGridFromJson(url);
    }


}

class registryBuilders {

    static Tile.Builder basicTile() {
        Displayable basicTileDisplay = SimpleDisplay.withText(".")
                .andColor(ColorMaker.make(187, false));

        return Tile.withTileDisplay(basicTileDisplay);
    }

    static Tile.Builder caveTile() {
        Color caveTileColor = ColorMaker.make(
                new int[]{175, 175, 175}, false);

        Displayable caveTileDisplay = SimpleDisplay.withText("0")
                .andColor(caveTileColor);

        return Tile.withTileDisplay(caveTileDisplay);
    }

    static Tile.Builder wallTile() {
        Displayable wallTileDisplay = SimpleDisplay.withText("#")
                .andColor(ColorMaker.make(SimpleColor.GREEN));

        return Tile.withTileDisplay(wallTileDisplay)
                .andTraversable(false);
    }
}