package app.input;

import java.util.Scanner;

public class ScannerHandler implements InputHandler<String> {
    private final Scanner consoleScanner = new Scanner(System.in);

    @Override
    public String handleInput() {
        return scanConsoleInput();
    }
    public String handleInput(String prompt) {
        return scanConsoleInput(prompt);
    }

    private String scanConsoleInput() {
        return consoleScanner.nextLine();
    }
    private String scanConsoleInput(String prompt) {
        System.out.printf("%s", prompt);
        return scanConsoleInput();
    }

    @Override
    public void close() {
        consoleScanner.close();
    }
}
