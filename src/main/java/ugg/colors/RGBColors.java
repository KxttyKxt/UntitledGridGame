package ugg.colors;

public class RGBColors extends AnsiUtil {

    public static String colorize(String input, int[] rgb, boolean background) {
        RGBTriad thisColor = new RGBTriad(rgb[0], rgb[1], rgb[2]);

        if (!thisColor.isValidColor()) {
            throw new IllegalArgumentException(String.format(
                    "The color provided '%s' is not a valid 8-bit color ID.", thisColor
            ));
        }
        else
            return String.format("%s%s%s", inputColor(thisColor, background), input, ansiReset);
    }
    private static String inputColor(RGBTriad color, boolean background) {
        int bgID = (background) ? 48 : 38;

        return input(String.format("%d;2;%d;%d;%d", bgID, color.r(), color.g(), color.b()));
    }
}

record RGBTriad(int r, int g, int b) {
    boolean isValidColor() {
        return r >= 0 && r < 256
                && g >= 0 && g < 256
                && b >= 0 && b < 256;
    }
}