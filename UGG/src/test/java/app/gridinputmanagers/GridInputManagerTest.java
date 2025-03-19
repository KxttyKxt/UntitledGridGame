package app.gridinputmanagers;

import app.input.InputHandler;
import core.Chunk;
import core.ChunkGrid;
import core.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GridInputManagerTest {
    @BeforeEach
    public void resetMockManager() {
        manager = new MockGridInputManager();
    }
    MockGridInputManager manager;

    @Test
    void test_enable() {
        manager.enable();
        Assertions.assertTrue(manager.disabledCorrectly);
    }

    @Test
    void test_manageInput_empty() {
        manager.manageInput("");
        Assertions.assertTrue(manager.handledEmptyInput);
    }

    @Test
    void test_manageInput_ping() {
        manager.manageInput("ping");
        Assertions.assertTrue(manager.pong);
    }

    @Test
    void test_displayActiveChunk() {
        manager.displayActiveChunk();

        String expectedGridDisplay = String.format("Grid Display%n");
        String actualGridDisplay = manager.shownGridDisplay;

        Assertions.assertEquals(expectedGridDisplay, actualGridDisplay);
    }

    @Test
    void test_closeInputHandler() {
        manager.closeInputHandler();
        Assertions.assertTrue(manager.handlerClosed);
    }

    @Test
    void test_closeInputHandler_crashAfter() {
        boolean exceptionWasThrown = false;

        manager.closeInputHandler();
        Assertions.assertTrue(manager.handlerClosed);

        try {
            manager.manageInput("ping");
        }
        catch (IllegalStateException handlerIsClosedException) {
            exceptionWasThrown = true;
        }
        finally {
            Assertions.assertTrue(exceptionWasThrown);
        }
    }
}


class MockGridInputManager extends GridInputManager<String> {
    boolean disabledCorrectly = false;
    boolean handledEmptyInput = false;
    boolean pong = false;
    boolean handlerClosed = false;

    String shownGridDisplay = "";

    public MockGridInputManager() {
        super(
                new MockInputHandler(),
                ChunkGrid.newGrid(new Chunk[][]{{Chunk.newChunk(new Tile[][]{{Tile.defaultTile()}})}})
        );
    }

    @Override
    boolean manageInput(String input) {
        if (handlerClosed)
            throw new IllegalStateException();

        switch (input) {
            case "" -> handledEmptyInput = true;
            case "ping" -> pong = true;
            case "close" -> closeInputHandler();
            case "disable" -> {
                disabledCorrectly = true;
                return false;
            }

            default -> throw new IllegalArgumentException("Input not recognized");
        }

        return true;
    }

    @Override
    void displayActiveChunk() {
        shownGridDisplay = String.format("Grid Display%n");
        System.out.printf(shownGridDisplay);
    }

    @Override
    void closeInputHandler() {
        super.closeInputHandler();
        handlerClosed = true;
    }
}

class MockInputHandler implements InputHandler<String> {
    boolean closed = false;

    @Override
    public String handleInput() {
        if (closed) throw new IllegalStateException("Handler is closed.");
        return "disable";
    }

    @Override
    public void close() {
        closed = true;
    }
}

