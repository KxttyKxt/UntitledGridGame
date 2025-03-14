package app.gridinputmanagers;

import tiles.json.ZeldaGridGenerator;

import java.io.IOException;

public class ZeldaMovementManager extends PlayerMovementManager {

    public ZeldaMovementManager() throws IOException {
        super(new ZeldaGridGenerator().constructGridFromJson(), 6, 7);
    }
}
