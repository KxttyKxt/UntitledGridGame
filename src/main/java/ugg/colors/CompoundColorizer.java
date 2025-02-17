package ugg.colors;

public class CompoundColorizer {
    public static Color getCompoundColor(Color[] colors) {
        if (colors.length == 1)
            return colors[0];

        StringBuilder returnColorAnsiCodeBuilder = new StringBuilder();

        for (Color color : colors) {
            if (color == null)
                throw new IllegalArgumentException("Color cannot be null.");
            else
                returnColorAnsiCodeBuilder.append(color.ansiCode);
        }

        return new Color(returnColorAnsiCodeBuilder.toString());
    }
}
