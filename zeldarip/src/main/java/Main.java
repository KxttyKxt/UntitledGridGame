import app.gridinputmanagers.PlayerMovementManager;
import tiles.TileGridJsonFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        new PlayerMovementManager(
                TileGridJsonFactory.constructGridFromJson(
                        Main.class.getResource("start-grid-json.json")),
                6, 5
        ).enable();
    }
}
