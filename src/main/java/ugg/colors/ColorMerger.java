package ugg.colors;

public class ColorMerger {
    public static Color mergeColors(Color[] colors) {
        if (colors.length == 1)
            return colors[0];
        else
            return new Color(constructMergedAnsiCode(colors));
    }

    private static String constructMergedAnsiCode(Color[] colors) {
        StringBuilder returnColorAnsiCodeBuilder = new StringBuilder();

        for (Color color : colors) {
            if (color == null)
                throw new IllegalArgumentException("Color cannot be null.");
            else
                returnColorAnsiCodeBuilder.append(color.ansiCode);
        }

        return returnColorAnsiCodeBuilder.toString();
    }
}
