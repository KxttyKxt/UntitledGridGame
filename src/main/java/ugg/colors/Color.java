package ugg.colors;

import java.util.Objects;

public class Color {
    // Intentionally package-private
    final String ansiCode;
    Color(String ansiCode) {
        this.ansiCode = ansiCode;
    }

    public String colorize(String input) {
        return String.format("%s%s%s", ansiCode, input, ColorMaker.ansiReset);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color = (Color) o;
        return Objects.equals(ansiCode, color.ansiCode);
    }
}
