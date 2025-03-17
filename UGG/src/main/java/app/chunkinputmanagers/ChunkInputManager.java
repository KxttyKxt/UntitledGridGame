package app.chunkinputmanagers;

import app.input.InputHandler;
import tiles.Chunk;

public abstract class ChunkInputManager<T> {
    private final InputHandler<T> inputHandler;
    Chunk chunk;

    public ChunkInputManager(InputHandler<T> inputHandler, Chunk chunk) {
        this.inputHandler = inputHandler;
        this.chunk = chunk;
    }

    public void enable() {
        boolean enabled = true;

        while (enabled) {
            displayChunk();
            T input = receiveInput();
            enabled = manageInput(input);
        }

        closeInputHandler();
    }

    private T receiveInput() {
        return inputHandler.handleInput();
    }

    abstract boolean manageInput(T input);
    abstract void displayChunk();

    void closeInputHandler() {
        inputHandler.close();
    }
}
