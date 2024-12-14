package ugg.colors;

public class Color {
    final String ansiCode;

    Color(String ansiCode) {
        this.ansiCode = ansiCode;
    }

    public String colorize(String input) {
        return String.format("%s%s%s", ansiCode, input, Colorizer.ansiReset);
    }
}
