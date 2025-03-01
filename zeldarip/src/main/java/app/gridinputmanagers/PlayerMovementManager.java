package app.gridinputmanagers;

import app.input.ScannerHandler;
import colors.ColorMaker;
import colors.SimpleColor;
import display.Displayable;
import display.SimpleDisplay;
import tiles.TileGrid;

public class PlayerMovementManager extends GridInputManager<String> {
    private final Displayable player = new SimpleDisplay("@player", ColorMaker.make(SimpleColor.CYAN));
    private int playerRow;
    private int playerCol;

    public PlayerMovementManager(TileGrid tileGrid, int startingRow, int startingCol) {
        super(new ScannerHandler(), tileGrid);
        this.playerRow = startingRow;
        this.playerCol = startingCol;

        tileGrid.addContents(player, startingRow, startingCol);
    }

    @Override
    boolean manageInput(String input) {
        switch (input.toLowerCase().trim()) {
            case "0" -> { return false; }
            case "1" -> movePlayer(1, -1);
            case "2" -> movePlayer(1, 0);
            case "3" -> movePlayer(1, 1);
            case "4" -> movePlayer(0, -1);
            case "5" -> movePlayer(0, 0);
            case "6" -> movePlayer(0, 1);
            case "7" -> movePlayer(-1, -1);
            case "8" -> movePlayer(-1, -0);
            case "9" -> movePlayer(-1, 1);

            default -> System.err.printf("Input '%s' not recognized. Please try again.%n", input);
        }

        return true;
    }

    private void movePlayer(int deltaRow, int deltaCol) {
        int targetRow = playerRow + deltaRow;
        int targetCol = playerCol + deltaCol;

        int[] playerPos = {playerRow, playerCol};
        int[] targetPos = {targetRow, targetCol};

        boolean movedSuccessfully = tileGrid.transferContents(playerPos, targetPos);

        if (movedSuccessfully) {
            playerRow = targetRow;
            playerCol = targetCol;
        }
        else {
            System.err.printf("Player could not be moved to [%d, %d].%n", targetRow, targetCol);
        }
    }

    @Override
    void displayGrid() {
        System.out.printf("%s%n%s%n", tileGrid, player);
    }
}
