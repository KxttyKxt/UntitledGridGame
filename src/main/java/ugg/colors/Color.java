package ugg.colors;

public class Color {
    // Intentionally package-private
    final String ansiCode;
    Color(String ansiCode) {
        this.ansiCode = ansiCode;
    }

    public String colorize(String input) {
        return String.format("%s%s%s", ansiCode, input, Colorizer.ansiReset);
    }
}
