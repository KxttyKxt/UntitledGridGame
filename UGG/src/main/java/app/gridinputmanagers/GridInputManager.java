package app.gridinputmanagers;

import app.input.InputHandler;
import core.ChunkGrid;

public abstract class GridInputManager<T> {
    private final InputHandler<T> inputHandler;
    ChunkGrid chunkGrid;

    public GridInputManager(InputHandler<T> inputHandler, ChunkGrid chunkGrid) {
        this.inputHandler = inputHandler;
        this.chunkGrid = chunkGrid;
    }

    public void enable() {
        boolean enabled = true;

        while (enabled) {
            displayActiveChunk();
            T input = receiveInput();
            enabled = manageInput(input);
        }

        closeInputHandler();
    }

    private T receiveInput() {
        return inputHandler.handleInput();
    }

    abstract boolean manageInput(T input);
    abstract void displayActiveChunk();

    void closeInputHandler() {
        inputHandler.close();
    }
}
