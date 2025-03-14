package display;

import colors.Color;

public class SimpleDisplay implements Displayable {

    public static Builder withText(String text) {
        return new Builder(text);
    }

    public static final class Builder {
        private final String text;
        private Color color;

        public Builder(String text) {
            this.text = text;
        }

        public SimpleDisplay andColor(Color color) {
            this.color = color;
            return new SimpleDisplay(this);
        }
    }


    public static SimpleDisplay emptyDisplay() {
        return SimpleDisplay.withText(emptyTextFormat).andColor(null);
    }
    static final String emptyTextFormat = "-";

    public static SimpleDisplay withOnlyText(String text) {
        return SimpleDisplay.withText(text).andColor(null);
    }

    private final String text;
    private final Color color;

    private SimpleDisplay(Builder builder) {
        if (builder.text == null || builder.text.isEmpty()) {
            this.text = emptyTextFormat;
        }
        else {
            this.text = builder.text;
        }

        this.color = builder.color;
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
