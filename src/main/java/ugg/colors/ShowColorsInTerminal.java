package ugg.colors;

public class ShowColorsInTerminal {
    public static void main(String[] args) {
        System.out.println();
        printAllSimpleColors();
        System.out.printf("%n%n%n");
        printAllEightBitColors();
    }

    private static void printAllSimpleColors() {
        for (int i = 0; i < SimpleColor.values().length; i++) {
            Color simpleColor = Colorizer.getColor(SimpleColor.values()[i]);
            System.out.printf("%s ", simpleColor.colorize("test"));

            if ((i + 1) % 8 == 0)
                System.out.println();
        }
    }

    private static void printAllEightBitColors() {
        for (int i = 0; i < 256; i++) {
            Color eightBitColorFG = Colorizer.getColor(i, false);
            Color eightBitColorBG = Colorizer.getColor(i, true);

            System.out.printf(
                    "%s %s ",
                    eightBitColorFG.colorize("test"),
                    eightBitColorBG.colorize("test")
            );

            if ((i + 1) % 16 == 0)
                System.out.println();
        }
    }
}
