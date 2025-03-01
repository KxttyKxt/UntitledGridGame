package app.gridinputmanagers;

import app.input.InputHandler;
import tiles.TileGrid;

public abstract class GridInputManager<T> {
    private final InputHandler<T> inputHandler;
    TileGrid tileGrid;

    public GridInputManager(InputHandler<T> inputHandler, TileGrid tileGrid) {
        this.inputHandler = inputHandler;
        this.tileGrid = tileGrid;
    }

    public void enable() {
        boolean enabled = true;

        while (enabled) {
            displayGrid();
            T input = receiveInput();
            enabled = manageInput(input);
        }

        closeInputHandler();
    }

    private T receiveInput() {
        return inputHandler.handleInput();
    }

    abstract boolean manageInput(T input);
    abstract void displayGrid();

    void closeInputHandler() {
        inputHandler.close();
    }
}
