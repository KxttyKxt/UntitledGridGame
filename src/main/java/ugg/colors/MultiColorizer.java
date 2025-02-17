package ugg.colors;

public class MultiColorizer {
    public static String colorize(Color[] colors, String input) {
        for (Color color : colors)
            if (color != null)
                input = color.colorize(input);

        return input;
    }
}
