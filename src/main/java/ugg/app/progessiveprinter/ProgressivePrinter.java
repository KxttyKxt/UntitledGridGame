package ugg.app.progessiveprinter;

import java.io.PrintStream;

public class ProgressivePrinter {
    private final PrintStream printStream;
    private long millisBetweenCharacters;
    private int charLimitPerLine;
    private boolean closed = false;

    public ProgressivePrinter() {
        printStream = System.out;
        millisBetweenCharacters = 25;
        charLimitPerLine = 100;
    }
    public ProgressivePrinter(PrintStream printStream, int charLimitPerLine, long millisBetweenCharacters) {
        this.printStream = printStream;
        this.charLimitPerLine = charLimitPerLine;
        this.millisBetweenCharacters = millisBetweenCharacters;
    }

    public void setMillis(long millis) {
        millisBetweenCharacters = millis;
    }
    public void setCharLimitPerLine(int limit) {
        charLimitPerLine = limit;
    }

    public void print(String toPrint) {
        throwExceptionIfClosed();

        try {
            printAndWait(toPrint);
        }
        catch (InterruptedException printingWasInterruptedException) {
            printingWasInterruptedException.printStackTrace(System.err);
        }
    }
    private synchronized void printAndWait(String toPrint) throws InterruptedException {
        for (int i = 0; i < toPrint.length(); i++) {
            if ((i + 1) % charLimitPerLine == 0)
                printStream.printf("%n");

            printStream.print(toPrint.charAt(i));
            wait(millisBetweenCharacters);
        }
    }

    public void close() {
        printStream.close();
        closed = true;
    }
    private void throwExceptionIfClosed() {
        if (closed)
            throw new IllegalStateException("ProgressivePrinter is closed");
    }


    // Driver for testing
    public static void main(String[] args) {
        ProgressivePrinter progPrinter = new ProgressivePrinter();
        progPrinter.print(String.format("I am a really long string so that I can actually have a chance to see that it prints progressively in case it decides to lag or something because gradle is building.......%n"));

        progPrinter.setMillis(150);
        progPrinter.print(String.format("%nAnd this message is really slow compared to the previous one.%n"));

        progPrinter.setMillis(25);
        progPrinter.setCharLimitPerLine(20);
        progPrinter.print(String.format("%nAnd this message is fast again, but it is split across many lines due to a lowered character limit per line!%n"));

        progPrinter.setCharLimitPerLine(100);
        progPrinter.print(String.format("%nThere. Everything is in order again! Enjoy some copy pasta!%n"));

        progPrinter.print(String.format("%n\"The FitnessGram™ Pacer Test is a multistage aerobic capacity test that progressively gets more difficult as it continues. The 20 meter pacer test will begin in 30 seconds. Line up at the start. The running speed starts slowly, but gets faster each minute after you hear this signal. [beep] A single lap should be completed each time you hear this sound. [ding] Remember to run in a straight line, and run as long as possible. The second time you fail to complete a lap before the sound, your test is over. The test will begin on the word start. On your mark, get ready, start.\"%n"));

        ProgressivePrinter errPrinter = new ProgressivePrinter(System.err, 100, 100);
        errPrinter.print(String.format("%nYou can choose any PrintStream, including the error stream!%n"));
        errPrinter.print(String.format("Although, you probably can't tell this is an error message since it's not red...%n"));

        progPrinter.print(String.format("%nNow let's go out with a bang!"));

        System.out.printf("%n"); // Line break before terminal shows pause dialog

        progPrinter.close();
        progPrinter.print("This will crash at runtime because the printer is closed.");
    }

}
