package tiles.json;

import tiles.TileGrid;

import java.io.IOException;
import java.net.URL;

public class ZeldaGridBuilder extends JsonGridBuilder {
    public ZeldaGridBuilder() {
        super(new ZeldaTileRegistry());
    }

    public TileGrid constructGridFromJson() throws IOException {
        URL url = ZeldaGridBuilder.class.getResource("start-grid-json.json");
        return super.constructGridFromJson(url);
    }
}