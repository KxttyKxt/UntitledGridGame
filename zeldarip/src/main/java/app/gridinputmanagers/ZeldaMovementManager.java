package app.gridinputmanagers;

import tiles.json.ZeldaGridBuilder;

import java.io.IOException;

public class ZeldaMovementManager extends PlayerMovementManager {

    public ZeldaMovementManager() throws IOException {
        super(new ZeldaGridBuilder().constructGridFromJson(), 6, 7);
    }
}
