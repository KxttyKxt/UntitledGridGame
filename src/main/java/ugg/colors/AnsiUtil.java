package ugg.colors;

public class AnsiUtil {
    private static final String ansiPrefix = "\u001B[";
    private static final String ansiSuffix = "m";

    protected static String input(String code) {
        return String.format("%s%s%s", ansiPrefix, code, ansiSuffix);
    }
}
