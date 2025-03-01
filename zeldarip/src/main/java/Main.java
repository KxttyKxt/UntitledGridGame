import app.gridinputmanagers.PlayerMovementManager;
import tiles.StartGrid;

public class Main {
    public static void main(String[] args) {
        new PlayerMovementManager(new StartGrid(), 6, 5).enable();
    }
}
