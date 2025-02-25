package app.input;

public class MovementInputHandler extends ScannerHandler {
    @Override
    protected boolean executeInput(String input) {
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
}
