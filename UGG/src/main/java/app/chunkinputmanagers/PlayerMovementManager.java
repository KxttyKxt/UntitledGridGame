package app.chunkinputmanagers;

import app.input.ScannerHandler;
import colors.ColorMaker;
import colors.SimpleColor;
import display.SimpleDisplay;
import tiles.Chunk;
import tiles.Occupant;
import tiles.Point2D;

public class PlayerMovementManager extends ChunkInputManager<String> {
    private final Occupant player = Occupant.newOccupant(
            SimpleDisplay.withText("@player")
            .andColor(ColorMaker.make(SimpleColor.CYAN))
    );

    private Point2D playerPosition;

    public PlayerMovementManager(Chunk chunk, Point2D spawnPoint) {
        super(new ScannerHandler(), chunk);

        chunk.addOccupant(player, spawnPoint);
        playerPosition = spawnPoint;
    }

    @Override
    boolean manageInput(String input) {
        switch (input.toLowerCase().trim()) {
            case "7" -> movePlayer(Point2D.of(-1, -1)); // Left & Up
            case "8" -> movePlayer(Point2D.of(0, -1)); // Up
            case "9" -> movePlayer(Point2D.of(1, -1));  // Right & Up

            case "4" -> movePlayer(Point2D.of(-1, 0));  // Left
            case "5" -> movePlayer(Point2D.of(0, 0));   // Nowhere
            case "6" -> movePlayer(Point2D.of(1, 0));   // Right

            case "1" -> movePlayer(Point2D.of(-1, 1));  // Left & Down
            case "2" -> movePlayer(Point2D.of(0, 1));   // Down
            case "3" -> movePlayer(Point2D.of(1, 1));   // Right & Down

            case "0" -> { return false; }   // Quit

            default -> System.err.printf("Input '%s' not recognized. Please try again.%n", input);
        }

        return true;
    }

    private void movePlayer(Point2D delta) {
        Point2D targetPosition = playerPosition.delta(delta);

        boolean movedSuccessfully = false;

        try {
            movedSuccessfully = chunk.transferOccupant(playerPosition, targetPosition);
        }
        catch (NullPointerException ignoredTargetPosNullOrOutOfBoundsException) {

        }
        finally {
            if (movedSuccessfully) {
                playerPosition = targetPosition;
            } else
                System.err.printf("Player could not be moved to [%s].%n", targetPosition);
        }
    }

    @Override
    void displayChunk() {
        System.out.printf("%s%n%s%n", chunk, player);
    }
}
