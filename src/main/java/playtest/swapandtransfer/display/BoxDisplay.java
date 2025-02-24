package playtest.swapandtransfer.display;

import ugg.colors.Color;
import ugg.colors.ColorMaker;
import ugg.colors.SimpleColor;
import ugg.display.SimpleDisplay;

public class BoxDisplay extends SimpleDisplay {
    public BoxDisplay() {
        super("#", ColorMaker.make(SimpleColor.RED));
    }
    public BoxDisplay(Color color) {
        super("#", color);
    }
}
