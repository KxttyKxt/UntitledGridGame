package app.input;

import java.util.Scanner;

public class MovementInputHandler implements InputHandler {
    private final Scanner consoleScanner = new Scanner(System.in);

    @Override
    public boolean handleInput() {
        String input = receiveInput();
        return executeInput(input);
    }
    public boolean handleInput(String prompt) {
        String input = receiveInput(prompt);
        return executeInput(input);
    }

    private String receiveInput() {
        return consoleScanner.nextLine().toLowerCase().trim();
    }
    private String receiveInput(String prompt) {
        System.out.printf("%s%n", prompt);
        return receiveInput();
    }

    private boolean executeInput(String input) {
        switch (input) {
            case "1", "2", "3", "4", "5", "6", "7", "8", "9" ->
                    System.out.printf("move: %s%n", input); // send to movement method

            case "help" -> System.out.printf("help message %n"); // print help message

            default -> {
                System.out.printf("Input '%s' not recognized.%n", input);
                return false;
            } // input isn't recognized
        }

        return true;
    }

    @Override
    public void close() {
        consoleScanner.close();
    }
}
