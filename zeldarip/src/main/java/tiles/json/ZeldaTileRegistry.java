package tiles.json;

import colors.Color;
import colors.ColorMaker;
import colors.SimpleColor;
import display.Displayable;
import display.SimpleDisplay;
import tiles.Tile;

import java.util.HashMap;

public class ZeldaTileRegistry extends JsonTileRegistry {

    @Override
    protected HashMap<String, Tile.Builder> initializeRegistry() {
        HashMap<String, Tile.Builder> registry = new HashMap<>();
        registry.put("BasicTile", basicTileBuilder());
        registry.put("CaveTile", caveTileBuilder());
        registry.put("WallTile", wallTileBuilder());

        return registry;
    }

    private Tile.Builder basicTileBuilder() {
        Displayable basicTileDisplay = SimpleDisplay.withText(".")
                .andColor(ColorMaker.make(187, false));

        return Tile.withTileDisplay(basicTileDisplay);
    }

    private Tile.Builder caveTileBuilder() {
        Color caveTileColor = ColorMaker.make(
                new int[]{175, 175, 175}, false);

        Displayable caveTileDisplay = SimpleDisplay.withText("0")
                .andColor(caveTileColor);
        
        return Tile.withTileDisplay(caveTileDisplay);
    }
    
    private Tile.Builder wallTileBuilder() {
        Displayable wallTileDisplay = SimpleDisplay.withText("#")
                .andColor(ColorMaker.make(SimpleColor.GREEN));

        return Tile.withTileDisplay(wallTileDisplay)
                .andTraversable(false);
    }

}
