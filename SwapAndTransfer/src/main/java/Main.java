import tiles.GridWithBoxes;

public class Main {
    private static final GridWithBoxes gridWithBoxes = new GridWithBoxes();
    private static final int[] initialRedBoxPos = {1, 1};
    private static final int[] initialGreenBoxPos = {1, 3};

    public static void main(String[] args) {
        printGridWithHeader("Start");

        gridWithBoxes.swapContents(initialRedBoxPos, initialGreenBoxPos);
        printGridWithHeader("Swap Boxes");

        gridWithBoxes.swapContents(initialRedBoxPos, initialGreenBoxPos);
        printGridWithHeader("Swap Boxes Again");

        boolean mostRecentTransferResult = gridWithBoxes.transferContents(new int[]{3, 2}, new int[]{3, 1});
        printGridWithHeader(String.format("Transfer Player Left [%b]", mostRecentTransferResult));

        mostRecentTransferResult = gridWithBoxes.transferContents(new int[]{3, 1}, new int[]{2, 1});
        printGridWithHeader(String.format("Transfer Player Up [%b]", mostRecentTransferResult));

        mostRecentTransferResult = gridWithBoxes.transferContents(new int[]{2, 1}, new int[]{1, 1});
        printGridWithHeader(String.format("Transfer Player Up [%b]%n(box is in the way)", mostRecentTransferResult));

        gridWithBoxes.swapContents(new int[]{2, 1}, initialRedBoxPos);
        printGridWithHeader(String.format("Swap Player Up%n(swap player with red box)"));
    }

    private static void printGridWithHeader(String header) {
        System.out.printf("%n%s%n%s%n", header, gridWithBoxes);
    }
}
