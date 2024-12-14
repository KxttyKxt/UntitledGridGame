package ugg.colors;

public class EightBitColors extends AnsiUtil {

    public static String colorize(String input, int colorID, boolean background) {
        if (!isValidColor(colorID)) {
            throw new IllegalArgumentException(String.format(
                    "The color ID provided '%d' is not a valid 8-bit color ID.", colorID
            ));
        }
        else
            return String.format("%s%s%s", inputColor(colorID, background), input, ansiReset);
    }
    private static String inputColor(int colorID, boolean background) {
        int bgID = (background) ? 48 : 38;
        return input(String.format("%d;5;%d", bgID, colorID));
    }

    private static boolean isValidColor(int colorID) {
        return colorID >= 0 && colorID < 256;
    }
}
