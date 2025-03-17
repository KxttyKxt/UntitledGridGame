package app.chunkinputmanagers;

import app.input.InputHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tiles.Chunk;
import tiles.Tile;

class ChunkInputManagerTest {
    @BeforeEach
    public void resetMockManager() {
        manager = new MockChunkInputManager();
    }
    MockChunkInputManager manager;

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
    void test_displayChunk() {
        manager.displayChunk();

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


class MockChunkInputManager extends ChunkInputManager<String> {
    boolean disabledCorrectly = false;
    boolean handledEmptyInput = false;
    boolean pong = false;
    boolean handlerClosed = false;

    String shownGridDisplay = "";

    public MockChunkInputManager() {
        super(
                new MockInputHandler(),
                new Chunk(new Tile[][]{{Tile.defaultTile()}})
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
    void displayChunk() {
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

