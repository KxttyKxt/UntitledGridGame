package ugg.colors;

public class ColorsDriver {
    public static void main(String[] args) {
        simpleColors();
        System.out.printf("%n%n%n");

        eightBitColors();
        System.out.printf("%n%n%n");
    }

    private static void simpleColors() {
        final int COLORS_PER_LINE = 8;
        int colorsInLine = 0;

        for (int color : SimpleColors.VALID_COLORS) {
            System.out.printf("%s ", SimpleColors.colorize("test", color));
            colorsInLine++;

            if (colorsInLine >= COLORS_PER_LINE) {
                System.out.println();
                colorsInLine = 0;
            }
        }
    }
    private static void eightBitColors() {
        for (int i = 0; i < 256; i++) {
            System.out.printf("%-3s %-3s ",
                    EightBitColors.colorize("test", i, false),
                    EightBitColors.colorize("test", i, true));

            if ((i + 1) % 16 == 0)
                System.out.println();
        }
    }
}
