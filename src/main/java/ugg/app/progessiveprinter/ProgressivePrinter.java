package ugg.app.progessiveprinter;

import java.io.PrintStream;

public class ProgressivePrinter {
    private long millisBetweenCharacters;
    private int charLimitPerLine;
    private final PrintStream printStream;

    ProgressivePrinter() {
        millisBetweenCharacters = 25;
        printStream = System.out;
        charLimitPerLine = 100;
    }
    void setMillis(long millis) {
        millisBetweenCharacters = millis;
    }
    void setCharLimitPerLine(int limit) {
        charLimitPerLine = limit;
    }

    synchronized private void printAndWait(String toPrint) throws InterruptedException {
        for (int i = 0; i < toPrint.length(); i++) {
            if ((i + 1) % charLimitPerLine == 0)
                printStream.printf("%n");

            printStream.print(toPrint.charAt(i));
            wait(millisBetweenCharacters);
        }
    }
    void print(String toPrint) {
        try {
            printAndWait(toPrint);
        }
        catch (InterruptedException printingWasInterruptedException) {
            printingWasInterruptedException.printStackTrace(System.err);
        }
    }


    public static void main(String[] args) {
        ProgressivePrinter progPrinter = new ProgressivePrinter();
        progPrinter.print(String.format("I am a really long string so that I can actually have a chance to see that it prints progressively in case it decides to lag or something because gradle is building.......%n"));

        progPrinter.setMillis(50);
        progPrinter.print(String.format("%nAnd this message is really slow compared to the previous one.%n"));

        progPrinter.setMillis(25);
        progPrinter.setCharLimitPerLine(20);
        progPrinter.print(String.format("%nAnd this message is fast again, but it is split across many lines due to a lowered character limit per line!%n"));

        progPrinter.setCharLimitPerLine(100);
        progPrinter.print(String.format("%nThere. Everything is in order again! Enjoy some copy pasta!%n"));

        progPrinter.print(String.format("%n\"The FitnessGram™ Pacer Test is a multistage aerobic capacity test that progressively gets more difficult as it continues. The 20 meter pacer test will begin in 30 seconds. Line up at the start. The running speed starts slowly, but gets faster each minute after you hear this signal. [beep] A single lap should be completed each time you hear this sound. [ding] Remember to run in a straight line, and run as long as possible. The second time you fail to complete a lap before the sound, your test is over. The test will begin on the word start. On your mark, get ready, start.\"%n"));

        System.out.printf("%n"); // Line break before terminal shows pause dialog
    }
}
