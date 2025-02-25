package app.input;

import java.util.Scanner;

public abstract class ScannerHandler implements InputHandler {
    private final Scanner consoleScanner = new Scanner(System.in);

    @Override
    public boolean handleInput() {
        String input = scanConsoleInput();
        return executeInput(input);
    }
    public boolean handleInput(String prompt) {
        String input = scanConsoleInput(prompt);
        return executeInput(input);
    }

    private String scanConsoleInput() {
        return consoleScanner.nextLine();
    }
    private String scanConsoleInput(String prompt) {
        System.out.printf("%s", prompt);
        return scanConsoleInput();
    }

    protected abstract boolean executeInput(String input);

    @Override
    public void close() {
        consoleScanner.close();
    }
}
