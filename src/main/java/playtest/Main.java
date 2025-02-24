package playtest;

import playtest.tiles.RainbowMatrix;
import ugg.tiles.TileMatrix;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner consoleScanner = new Scanner(System.in);
        TileMatrix rainbowMatrix = new RainbowMatrix();

        String input = "";
        while (!input.equals("q")) {
            System.out.printf("%s%n", rainbowMatrix);
            input = consoleScanner.nextLine().toLowerCase().trim();
        }
    }
}
