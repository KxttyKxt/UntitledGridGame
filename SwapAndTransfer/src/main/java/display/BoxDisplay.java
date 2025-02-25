package display;

import colors.Color;
import colors.ColorMaker;
import colors.SimpleColor;

public class BoxDisplay extends SimpleDisplay {
    public BoxDisplay() {
        super("#", ColorMaker.make(SimpleColor.RED));
    }
    public BoxDisplay(Color color) {
        super("#", color);
    }
}
