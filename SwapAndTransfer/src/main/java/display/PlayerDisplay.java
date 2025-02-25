package display;

import colors.ColorMaker;
import colors.SimpleColor;

public class PlayerDisplay extends SimpleDisplay {
    public PlayerDisplay() {
        super("@", ColorMaker.make(SimpleColor.CYAN));
    }
}
