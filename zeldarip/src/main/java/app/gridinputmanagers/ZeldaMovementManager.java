package app.gridinputmanagers;

import tiles.Chunk;
import tiles.ChunkGrid;
import tiles.Point2D;
import tiles.json.ZeldaChunkBuilder;

import java.io.IOException;

public class ZeldaMovementManager extends PlayerMovementManager {
    public static ZeldaMovementManager initialize() throws IOException {
        Chunk chunk = new ZeldaChunkBuilder().constructChunkFromJson();
        ChunkGrid grid = ChunkGrid.newGrid(new Chunk[][]{{chunk}});

        return new ZeldaMovementManager(grid);
    }

    private ZeldaMovementManager(ChunkGrid grid) {
        super(grid, Point2D.of(7, 6));
    }
}
