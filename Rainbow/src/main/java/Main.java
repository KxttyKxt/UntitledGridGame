import tiles.RainbowGrid;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner consoleScanner = new Scanner(System.in);
        RainbowGrid rainbowMatrix = new RainbowGrid();

        String input = "";
        while (!input.equals("q")) {
            System.out.printf("%s%n", rainbowMatrix);
            input = consoleScanner.nextLine().toLowerCase().trim();
        }

        consoleScanner.close();
        System.exit(0);
    }
}
