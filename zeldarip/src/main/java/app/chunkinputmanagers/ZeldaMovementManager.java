package app.chunkinputmanagers;

import tiles.Point2D;
import tiles.json.ZeldaChunkBuilder;

import java.io.IOException;

public class ZeldaMovementManager extends PlayerMovementManager {
    public ZeldaMovementManager() throws IOException {
        super(new ZeldaChunkBuilder().constructGridFromJson(), Point2D.of(7, 6));
    }
}
