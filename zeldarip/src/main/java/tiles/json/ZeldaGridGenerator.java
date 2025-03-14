package tiles.json;

import tiles.TileGrid;

import java.io.IOException;
import java.net.URL;

public class ZeldaGridGenerator extends GridGeneratorFromJson {
    public ZeldaGridGenerator() {
        super(new ZeldaTileGenerator());
    }

    public TileGrid constructGridFromJson() throws IOException {
        URL url = ZeldaGridGenerator.class.getResource("start-grid-json.json");
        return super.constructGridFromJson(url);
    }
}