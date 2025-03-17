package app.chunkinputmanagers;

import tiles.json.ZeldaChunkBuilder;

import java.io.IOException;

public class ZeldaMovementManager extends PlayerMovementManager {

    public ZeldaMovementManager() throws IOException {
        super(new ZeldaChunkBuilder().constructGridFromJson(), 6, 7);
    }
}
