package playtest.rainbow;

import playtest.rainbow.tiles.RainbowGrid;
import ugg.tiles.TileGrid;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner consoleScanner = new Scanner(System.in);
        TileGrid rainbowMatrix = new RainbowGrid();

        String input = "";
        while (!input.equals("q")) {
            System.out.printf("%s%n", rainbowMatrix);
            input = consoleScanner.nextLine().toLowerCase().trim();
        }

        consoleScanner.close();
        System.exit(0);
    }
}
