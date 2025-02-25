package colors;

public enum SimpleColor {
    BLACK, RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE,

    BRIGHT_BLACK, BRIGHT_RED, BRIGHT_GREEN, BRIGHT_YELLOW,
    BRIGHT_BLUE, BRIGHT_MAGENTA, BRIGHT_CYAN, BRIGHT_WHITE,

    BG_BLACK, BG_RED, BG_GREEN, BG_YELLOW,
    BG_BLUE, BG_MAGENTA, BG_CYAN, BG_WHITE,

    BG_BRIGHT_BLACK, BG_BRIGHT_RED, BG_BRIGHT_GREEN, BG_BRIGHT_YELLOW,
    BG_BRIGHT_BLUE, BG_BRIGHT_MAGENTA, BG_BRIGHT_CYAN, BG_BRIGHT_WHITE,

    DEFAULT, BG_DEFAULT;

    int toColorID() {
        return switch (this) {
            case BLACK -> 30;
            case RED -> 31;
            case GREEN -> 32;
            case YELLOW -> 33;
            case BLUE -> 34;
            case MAGENTA -> 35;
            case CYAN -> 36;
            case WHITE -> 37;

            case BRIGHT_BLACK -> 90;
            case BRIGHT_RED -> 91;
            case BRIGHT_GREEN -> 92;
            case BRIGHT_YELLOW -> 93;
            case BRIGHT_BLUE -> 94;
            case BRIGHT_MAGENTA -> 95;
            case BRIGHT_CYAN -> 96;
            case BRIGHT_WHITE -> 97;

            case BG_BLACK -> 40;
            case BG_RED -> 41;
            case BG_GREEN -> 42;
            case BG_YELLOW -> 43;
            case BG_BLUE -> 44;
            case BG_MAGENTA -> 45;
            case BG_CYAN -> 46;
            case BG_WHITE -> 47;

            case BG_BRIGHT_BLACK -> 100;
            case BG_BRIGHT_RED -> 101;
            case BG_BRIGHT_GREEN -> 102;
            case BG_BRIGHT_YELLOW -> 103;
            case BG_BRIGHT_BLUE -> 104;
            case BG_BRIGHT_MAGENTA -> 105;
            case BG_BRIGHT_CYAN -> 106;
            case BG_BRIGHT_WHITE -> 107;

            case DEFAULT -> 39;
            case BG_DEFAULT -> 49;
        };
    }
}
