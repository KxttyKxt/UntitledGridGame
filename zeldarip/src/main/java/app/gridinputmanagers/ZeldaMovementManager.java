package app.gridinputmanagers;

import colors.Color;
import colors.ColorMaker;
import colors.SimpleColor;
import core.ChunkGrid;
import core.Occupant;
import core.Point2D;
import core.Tile;
import core.json.ChunkBuilderTileRegistry;
import core.json.JsonChunkBuilder;
import core.json.JsonGridBuilder;
import display.Displayable;
import display.SimpleDisplay;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class ZeldaMovementManager extends PlayerMovementManager {
    public static ZeldaMovementManager fromGridFile(URL jsonFileURL) throws IOException {
        ChunkGrid chunkGrid = chunkGrid(jsonFileURL);
        Point2D spawnPoint = Point2D.of(6, 7);
        Point2D chunkPoint = Point2D.of(0, 1);

        return new ZeldaMovementManager(chunkGrid, chunkPoint, spawnPoint);
    }

    private ZeldaMovementManager(ChunkGrid chunkGrid, Point2D chunkPoint, Point2D spawnPoint) {
        super(chunkGrid, chunkPoint, spawnPoint);
    }


    private static ChunkGrid chunkGrid(URL jsonFileURL) throws IOException {
        return JsonGridBuilder.withChunkBuilder(chunkBuilder()).constructGridFromJson(jsonFileURL);
    }

    private static JsonChunkBuilder chunkBuilder() {
        return JsonChunkBuilder.usingRegistry(tileRegistry());
    }

    private static ChunkBuilderTileRegistry tileRegistry() {
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
                new int[]{175, 175, 175}, false);

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
