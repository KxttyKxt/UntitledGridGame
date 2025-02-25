package display;

import colors.Color;

public class SimpleDisplay implements Displayable {
    private final String text;
    private final Color color;

    public SimpleDisplay(String text, Color color) {
        if (text == null || text.isEmpty()) {
            this.text = "-";
        }
        else {
            this.text = text;
        }

        this.color = color;
    }

    @Override
    public String display() {
        String symbol = text.substring(0, 1);

        if (color != null)
            return color.colorize(symbol);
        else
            return symbol;
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleDisplay that = (SimpleDisplay) o;
        boolean matchingText = this.text.equals(that.text);
        boolean matchingColor;

        if (this.color == null && that.color == null)
            matchingColor = true;
        else if (this.color != null && that.color != null)
            matchingColor = this.color.equals(that.color);
        else
            matchingColor = false;

        return matchingText && matchingColor;
    }
}
