package core;

public record Point2D(
        int x, int y
) {
    public static Point2D of(int x, int y) {
        return new Point2D(x, y);
    }
    public Point2D delta(Point2D delta) {
        return new Point2D(this.x + delta.x, this.y + delta.y);
    }

    @Override
    public String toString() {
        return String.format("x:%d, y:%d", x, y);
    }
}
