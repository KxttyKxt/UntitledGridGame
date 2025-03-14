package tiles.json;

import colors.Color;
import colors.ColorMaker;
import colors.SimpleColor;
import display.Displayable;
import display.SimpleDisplay;
import tiles.Tile;

public class ZeldaTileGenerator extends TileGeneratorFromJson {

    public Tile generateTile(String jsonTileName) {
        return switch (jsonTileName) {
            case "BasicTile" -> basicTile();
            case "CaveTile" -> caveTile();
            case "WallTile" -> wallTile();

            case " " -> null;

            default -> throw new IllegalArgumentException(String.format(
                    "%s is not a known tile.", jsonTileName
            ));
        };
    }


    private Tile basicTile() {
        Displayable basicTileDisplay = SimpleDisplay.withText(".")
                .andColor(ColorMaker.make(187, false));

        return Tile.withTileDisplay(basicTileDisplay).build();
    }

    private Tile caveTile() {
        Color caveTileColor = ColorMaker.make(
                new int[]{175, 175, 175}, false);

        Displayable caveTileDisplay = SimpleDisplay.withText("0")
                .andColor(caveTileColor);
        
        return Tile.withTileDisplay(caveTileDisplay).build();
    }
    
    private Tile wallTile() {
        Displayable wallTileDisplay = SimpleDisplay.withText("#")
                .andColor(ColorMaker.make(SimpleColor.GREEN));

        return Tile.withTileDisplay(wallTileDisplay)
                .andTraversable(false)
                .build();
    }

}
