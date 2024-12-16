package ugg.tiles.tiles;

public class Tile {
    private String contents;

    public Tile(String contents) {
        this.contents = contents;
    }


    public void swapContents(Tile that) {
        String temp = this.contents;
        this.contents = that.contents;
        that.contents = temp;
    }


    @Override
    public String toString() {
        return (contents != null)
                ? contents
                : "null";
    }
}
