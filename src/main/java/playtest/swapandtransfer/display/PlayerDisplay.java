package playtest.swapandtransfer.display;

import ugg.colors.ColorMaker;
import ugg.colors.SimpleColor;
import ugg.display.SimpleDisplay;

public class PlayerDisplay extends SimpleDisplay {
    public PlayerDisplay() {
        super("@", ColorMaker.make(SimpleColor.CYAN));
    }
}
