package app.input;

public interface InputHandler {
    boolean handle();

    String receiveInput();
    boolean executeInput(String input);

    void close();
}
