package app.input;

public interface InputHandler<T> {
    T handleInput();
    void close();
}