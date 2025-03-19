package app.gridinputmanagers;

import core.ChunkGrid;
import core.Point2D;
import core.ZeldaAssets;

import java.io.IOException;
import java.net.URL;

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
        return ZeldaAssets.zeldaGridBuilder().constructGridFromJson(jsonFileURL);
    }

}
