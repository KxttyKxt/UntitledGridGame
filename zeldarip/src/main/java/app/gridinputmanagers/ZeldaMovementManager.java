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

        return new ZeldaMovementManager(chunkGrid, spawnPoint);
    }

    private ZeldaMovementManager(ChunkGrid chunkGrid, Point2D spawnPoint) {
        super(chunkGrid, spawnPoint);
    }


    private static ChunkGrid chunkGrid(URL jsonFileURL) throws IOException {
        return JsonGridBuilder.withChunkBuilder(chunkBuilder()).constructGridFromJson(jsonFileURL);
    }

    private static JsonChunkBuilder chunkBuilder() {
        return JsonChunkBuilder.usingRegistry(tileRegistry());
    }

    private static ChunkBuilderTileRegistry tileRegistry() {
        return ChunkBuilderTileRegistry.createRegistryWithMap(Map.of(
                "BasicTile",      basicTileBuilder(),
                "CaveTile",       caveTileBuilder(),
                "RockTile",       rockTileBuilder(),
                "SecretCaveTile", secretCaveTileBuilder(),
                "WallTile",       wallTileBuilder()
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
        Occupant rock = Occupant.newOccupant(SimpleDisplay
                .withText("@")
                .andColor(ColorMaker.make(SimpleColor.GREEN)));

        return basicTileBuilder().andOccupant(rock);
    }
    private static Tile.Builder secretCaveTileBuilder() {
        // NYI, looks like a wall
        Displayable wallTileDisplay = SimpleDisplay.withText("#")
                .andColor(ColorMaker.make(SimpleColor.GREEN));

        return Tile.withTileDisplay(wallTileDisplay)
                .andTraversable(false);
    }
    private static Tile.Builder wallTileBuilder() {
        Displayable wallTileDisplay = SimpleDisplay.withText("#")
                .andColor(ColorMaker.make(SimpleColor.GREEN));

        return Tile.withTileDisplay(wallTileDisplay)
                .andTraversable(false);
    }
}
