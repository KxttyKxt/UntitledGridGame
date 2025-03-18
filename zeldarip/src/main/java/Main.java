import app.gridinputmanagers.ZeldaMovementManager;

import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        URL jsonFileURL = Main.class.getResource("core/json/grid/overworld.json");
        ZeldaMovementManager.fromGridFile(jsonFileURL).enable();
    }
}
