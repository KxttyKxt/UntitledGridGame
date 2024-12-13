package ugg.tiles.tiles;

public class Tile {
    String contents;

    public Tile(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return contents;
    }
}
