package ugg.colors;

public class SimpleColors extends AnsiUtil {

    public static String colorize(String input, int colorID) {
        if (!isValidColor(colorID)) {
            throw new IllegalArgumentException(String.format(
                    "The colorID provided '%d' is not a valid Simple Color.", colorID
            ));
        }

        return String.format("%s%s%s", inputColor(colorID), input, ansiReset);
    }
    private static String inputColor(int colorID) {
        return input(String.valueOf(colorID));
    }

    public static final int BLACK = 30;
    public static final int RED = 31;
    public static final int GREEN = 32;
    public static final int YELLOW = 33;
    public static final int BLUE = 34;
    public static final int MAGENTA = 35;
    public static final int CYAN = 36;
    public static final int WHITE = 37;

    public static final int BRIGHT_BLACK = 90;
    public static final int BRIGHT_RED = 91;
    public static final int BRIGHT_GREEN = 92;
    public static final int BRIGHT_YELLOW = 93;
    public static final int BRIGHT_BLUE = 94;
    public static final int BRIGHT_MAGENTA = 95;
    public static final int BRIGHT_CYAN = 96;
    public static final int BRIGHT_WHITE = 97;

    public static final int BG_BLACK = 40;
    public static final int BG_RED = 41;
    public static final int BG_GREEN = 42;
    public static final int BG_YELLOW = 43;
    public static final int BG_BLUE = 44;
    public static final int BG_MAGENTA = 45;
    public static final int BG_CYAN = 46;
    public static final int BG_WHITE = 47;

    public static final int BG_BRIGHT_BLACK = 100;
    public static final int BG_BRIGHT_RED = 101;
    public static final int BG_BRIGHT_GREEN = 102;
    public static final int BG_BRIGHT_YELLOW = 103;
    public static final int BG_BRIGHT_BLUE = 104;
    public static final int BG_BRIGHT_MAGENTA = 105;
    public static final int BG_BRIGHT_CYAN = 106;
    public static final int BG_BRIGHT_WHITE = 107;

    public static final int DEFAULT = 39;

    public static final int[] VALID_COLORS = {
            BLACK, RED, GREEN, YELLOW,BLUE, MAGENTA, CYAN, WHITE,

            BRIGHT_BLACK, BRIGHT_RED, BRIGHT_GREEN, BRIGHT_YELLOW,
            BRIGHT_BLUE, BRIGHT_MAGENTA, BRIGHT_CYAN, BRIGHT_WHITE,

            BG_BLACK, BG_RED, BG_GREEN, BG_YELLOW, BG_BLUE, BG_MAGENTA, BG_CYAN, BG_WHITE,

            BG_BRIGHT_BLACK, BG_BRIGHT_RED, BG_BRIGHT_GREEN, BG_BRIGHT_YELLOW,
            BG_BRIGHT_BLUE, BG_BRIGHT_MAGENTA, BG_BRIGHT_CYAN, BG_BRIGHT_WHITE,

            DEFAULT
    };

    private static boolean isValidColor(int colorID) {
        for (int validColor : VALID_COLORS)
            if (colorID == validColor)
                return true;

        return false;
    }
}
