package app.gridinputmanagers;

import app.input.ScannerHandler;
import colors.ColorMaker;
import colors.SimpleColor;
import core.Chunk;
import core.ChunkGrid;
import core.Occupant;
import core.Point2D;
import display.SimpleDisplay;

public class PlayerMovementManager extends GridInputManager<String> {
    private final Occupant player = Occupant.newOccupant(
            SimpleDisplay.withText("@player")
            .andColor(ColorMaker.make(SimpleColor.CYAN))
    );

    private Point2D playerPositionInChunk;
    private Point2D chunkPositionInGrid;

    public PlayerMovementManager(ChunkGrid chunkGrid, Point2D spawnPoint, Point2D chunkPoint) {
        super(new ScannerHandler(), chunkGrid);

        playerPositionInChunk = spawnPoint;
        chunkPositionInGrid = chunkPoint;

        currentChunk().addOccupant(player, spawnPoint);
    }

    @Override
    boolean manageInput(String input) {
        switch (input.toLowerCase().trim()) {
            case "7" -> movePlayer(Point2D.of(-1, -1)); // Left & Up
            case "8" -> movePlayer(Point2D.of(0, -1));  // Up
            case "9" -> movePlayer(Point2D.of(1, -1));  // Right & Up

            case "4" -> movePlayer(Point2D.of(-1, 0));  // Left
            case "5" -> movePlayer(Point2D.of(0, 0));   // Nowhere
            case "6" -> movePlayer(Point2D.of(1, 0));   // Right

            case "1" -> movePlayer(Point2D.of(-1, 1));  // Left & Down
            case "2" -> movePlayer(Point2D.of(0, 1));   // Down
            case "3" -> movePlayer(Point2D.of(1, 1));   // Right & Down

            case "0" -> { return false; }               // Quit

            default -> System.err.printf("Input '%s' not recognized. Please try again.%n", input);
        }

        return true;
    }


    private void movePlayer(Point2D delta) {
        Point2D targetPoint = playerPositionInChunk.delta(delta);

        if (targetPoint.withinRange(chunkUpperBounds()))
            movePlayerWithinChunk(targetPoint);
        else
            movePlayerAcrossChunks(targetPoint);
    }

    private void movePlayerWithinChunk(Point2D targetPoint) {
        boolean movedSuccessfully = currentChunk()
                .transferOccupant(playerPositionInChunk, targetPoint);

        if (movedSuccessfully)
            playerPositionInChunk = targetPoint;
        else
            System.err.printf("Could not move player to Tile at target point (%s)%n", targetPoint);
    }

    private void movePlayerAcrossChunks(Point2D targetPoint) {
        Point2D chunkDelta = targetPoint.outOfRangeDelta(chunkUpperBounds());
        Point2D newChunkPoint = chunkPositionInGrid.delta(chunkDelta);
        Chunk newChunk = chunkGrid.getChunk(newChunkPoint);

        Point2D chunkSizes = currentChunk().ranges();
        Point2D adjustedTargetPoint;
        int adjustedTargetX = targetPoint.x();
        int adjustedTargetY = targetPoint.y();

        if (chunkDelta.x() == 1)
            adjustedTargetX -= chunkSizes.x();
        else if (chunkDelta.x() == -1)
            adjustedTargetX += chunkSizes.x();

        if (chunkDelta.y() == 1)
            adjustedTargetY -= chunkSizes.y();
        else if (chunkDelta.y() == -1)
            adjustedTargetY += chunkSizes.y();

        adjustedTargetPoint = Point2D.of(adjustedTargetX, adjustedTargetY);

        boolean newChunkIsNull = newChunk == null;
        boolean movedSuccessfully = !newChunkIsNull && currentChunk().transferOccupantAcrossChunks(
                playerPositionInChunk, newChunk, adjustedTargetPoint);

        if (movedSuccessfully) {
            chunkPositionInGrid = chunkPositionInGrid.delta(chunkDelta);
            playerPositionInChunk = adjustedTargetPoint;
        }
        else {
            if (newChunkIsNull) System.err.printf(
                    "No chunk found at grid point (%s).%n",
                    newChunkPoint
            );
            else System.err.printf(
                    "Chunk was found at point (%s), but could not move to Tile at target point (%s).%n",
                    newChunkPoint, adjustedTargetPoint
            );
        }
    }


    private Chunk currentChunk() {
        return chunkGrid.getChunk(chunkPositionInGrid);
    }

    private Point2D chunkUpperBounds() {
        return currentChunk().upperBounds();
    }

    @Override
    void displayActiveChunk() {
        System.out.printf("%s%n%s%n", currentChunk(), player);
    }
}