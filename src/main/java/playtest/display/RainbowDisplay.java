package playtest.display;

import ugg.colors.Color;
import ugg.colors.ColorMaker;
import ugg.colors.SimpleColor;
import ugg.display.Displayable;

public class RainbowDisplay implements Displayable {
    Color[] colorSequence;
    int colorIndex = 0;

    String text;

    public RainbowDisplay(Color[] colorSequence, String text) {
        this.colorSequence = colorSequence;
        this.text = text;
    }
    public RainbowDisplay(int startingIndex) {
        this(new Color[]{
                ColorMaker.make(SimpleColor.RED),
                ColorMaker.make(SimpleColor.YELLOW),
                ColorMaker.make(SimpleColor.GREEN),
                ColorMaker.make(SimpleColor.BLUE),
                ColorMaker.make(SimpleColor.MAGENTA),
        }, ".rainbow");
        colorIndex = startingIndex % colorSequence.length;
    }

    @Override
    public String display() {
        if (colorIndex >= colorSequence.length)
            colorIndex = 0;

        return colorSequence[colorIndex++].colorize(text.substring(0, 1));
    }
}
