package ugg.tiles.tiles;

public class Tile {
    private String contents;

    public Tile(String contents) {
        this.contents = contents;
    }


    public void swapContentsWith(Tile that) {
        String temp = this.contents;
        this.contents = that.contents;
        that.contents = temp;
    }

    public boolean transferContentsTo(Tile that) {
        if (that.openForTransfer()) {
            that.contents = this.contents;
            this.contents = "";
            return true;
        }
        else
            return false;
    }

    public boolean openForTransfer() {
        return this.contents.isEmpty();
    }

    @Override
    public String toString() {
        return (contents != null)
                ? contents
                : "null";
    }
}
