package app.input;

import java.util.Scanner;

public class ScannerHandler implements InputHandler<String> {
    private final Scanner consoleScanner = new Scanner(System.in);

    @Override
    public String handleInput() {
        return scanConsoleInput();
    }

    private String scanConsoleInput() {
        return consoleScanner.nextLine();
    }

    @Override
    public void close() {
        consoleScanner.close();
    }
}
