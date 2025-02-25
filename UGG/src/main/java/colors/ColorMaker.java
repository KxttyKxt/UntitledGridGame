package colors;

public class ColorMaker {
    private static final String ansiPrefix = "\u001B[";
    private static final String ansiSuffix = "m";

    // Intentionally package-private
    static final String ansiReset = "\u001B[0m";

    // Simple Colors, 1-bit/2-bit
    public static Color make(SimpleColor simpleColor) {
        return new Color(parse(simpleColor));
    }
    private static String parse(SimpleColor simpleColor) {
        return String.format(
                "%s%s%s",
                ansiPrefix,
                simpleColor.toColorID(),
                ansiSuffix
        );
    }

    // 8-bit Colors
    public static Color make(int colorID, boolean background) {
        validateColor(colorID);
        return new Color(parse(colorID, background));
    }
    private static void validateColor(int colorID) {
        if (colorID > 255 || colorID < 0)
            throw new IllegalArgumentException(String.format(
                    "The color ID provided '%d' is not in range [0-255].", colorID));
    }
    private static String parse(int colorID, boolean background) {
        return String.format(
                "%s%d;5;%d%s",
                ansiPrefix,
                bgID(background),
                colorID,
                ansiSuffix
        );
    }

    // 24-bit Colors
    public static Color make(int[] rgb, boolean background) {
        validateColor(rgb);
        return new Color(parse(rgb, background));
    }
    private static void validateColor(int[] rgb) {
        if (rgb.length != 3)
            throw new IllegalArgumentException(String.format(
                    "RGB value array is not the correct length; is %d, must be 3", rgb.length));

        validateColor(rgb[0]);
        validateColor(rgb[1]);
        validateColor(rgb[2]);
    }
    private static String parse(int[] rgb, boolean background) {
        return String.format(
                "%s%d;2;%d;%d;%d%s",
                ansiPrefix,
                bgID(background),
                rgb[0], rgb[1], rgb[2],
                ansiSuffix
        );
    }

    // Sub-method
    private static int bgID(boolean background) {
        return (background) ? 48 : 38;
    }
}
