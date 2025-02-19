package ugg.colors;

public class ColorMerger {
    public static Color mergeColors(Color[] colors) {
        if (colors.length == 1)
            return colors[0];
        else
            return new Color(constructMergedAnsiCode(colors));
    }

    private static String constructMergedAnsiCode(Color[] colors) {
        deduplicateArrayOfColors(colors);
        StringBuilder returnColorAnsiCodeBuilder = new StringBuilder();

        for (Color color : colors)
            if (color != null)
                returnColorAnsiCodeBuilder.append(color.ansiCode);

        return returnColorAnsiCodeBuilder.toString();
    }

    private static void deduplicateArrayOfColors(Color[] colors) {
        for (int i = 0; i < colors.length; i++) {
            Color currentColorToDeduplicate = colors[i];

            for (int j = i + 1; j < colors.length; j++) {
                Color currentColorToCheckFor = colors[j];

                if (currentColorToCheckFor != null && currentColorToCheckFor.equals(currentColorToDeduplicate))
                    colors[j] = null;
            }
        }

    }
}