package core;

import colors.Color;
import colors.ColorMaker;
import colors.SimpleColor;
import core.json.ChunkBuilderTileRegistry;
import core.json.JsonChunkBuilder;
import core.json.JsonGridBuilder;
import display.Displayable;
import display.SimpleDisplay;

import java.util.Map;

public class ZeldaAssets {
    public static JsonGridBuilder zeldaGridBuilder() {
        return JsonGridBuilder.withChunkBuilder(zeldaChunkBuilder());
    }

    private static JsonChunkBuilder zeldaChunkBuilder() {
        return JsonChunkBuilder.usingRegistry(zeldaTileRegistry());
    }

    private static ChunkBuilderTileRegistry zeldaTileRegistry() {
        return ChunkBuilderTileRegistry.createRegistryWithMap(Map.of(
                "BasicTile", basicTileBuilder(),
                "CaveTile",  caveTileBuilder(),
                "RockTile",  rockTileBuilder(),
                "TreeTile",  treeTileBuilder(),
                "WallTile",  wallTileBuilder(),

                "SecretCaveTile", secretCaveTileBuilder(),
                "SecretStairsTile", secretStairsTileBuilder(),

                "BasicTile_Octorok", octorokTileBuilder()
        ));
    }

    private static Tile.Builder basicTileBuilder() {
        Displayable basicTileDisplay = SimpleDisplay.withText(".")
                .andColor(ColorMaker.make(187, false));

        return Tile.withTileDisplay(basicTileDisplay);
    }
    private static Tile.Builder caveTileBuilder() {
        Color caveTileColor = ColorMaker.make(
                new int[]{90, 90, 90}, false);

        Displayable caveTileDisplay = SimpleDisplay.withText("0")
                .andColor(caveTileColor);

        return Tile.withTileDisplay(caveTileDisplay);
    }
    private static Tile.Builder rockTileBuilder() {
        Displayable rock = SimpleDisplay
                .withText("K")
                .andColor(ColorMaker.make(SimpleColor.GREEN));

        return Tile.withTileDisplay(rock).andTraversable(false);
    }
    private static Tile.Builder treeTileBuilder() {
        Displayable rock = SimpleDisplay
                .withText("T")
                .andColor(ColorMaker.make(SimpleColor.GREEN));

        return Tile.withTileDisplay(rock).andTraversable(false);
    }
    private static Tile.Builder wallTileBuilder() {
        Displayable wallTileDisplay = SimpleDisplay.withText("#")
                .andColor(ColorMaker.make(SimpleColor.GREEN));

        return Tile.withTileDisplay(wallTileDisplay)
                .andTraversable(false);
    }

    private static Tile.Builder secretCaveTileBuilder() {
        // NYI, looks like a wall
        Displayable wallTileDisplay = SimpleDisplay.withText("#")
                .andColor(ColorMaker.make(SimpleColor.GREEN));

        return Tile.withTileDisplay(wallTileDisplay)
                .andTraversable(false);
    }
    private static Tile.Builder secretStairsTileBuilder() {
        // NYI, looks like a tree
        Displayable wallTileDisplay = SimpleDisplay.withText("T")
                .andColor(ColorMaker.make(SimpleColor.GREEN));

        return Tile.withTileDisplay(wallTileDisplay)
                .andTraversable(false);
    }

    private static Tile.Builder octorokTileBuilder() {
        Occupant rock = Occupant.newOccupant(SimpleDisplay
                .withText("@")
                .andColor(ColorMaker.make(202, false)));

        return basicTileBuilder().andOccupant(rock);
    }
}
