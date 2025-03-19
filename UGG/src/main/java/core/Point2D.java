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

    public boolean withinRange(Point2D upperBounds) {
        if (x < 0 || y < 0)
            return false;
        else
            return x <= upperBounds.x() && y <= upperBounds.y();
    }

    public Point2D outOfRangeDelta(Point2D upperBounds) {
        int rangeDeltaX = 0;
        int rangeDeltaY = 0;

        if (x > upperBounds.x)
            rangeDeltaX = 1;
        else if (x < 0)
            rangeDeltaX = -1;

        if (y > upperBounds.y)
            rangeDeltaY = 1;
        else if (y < 0)
            rangeDeltaY = -1;

        return Point2D.of(rangeDeltaX, rangeDeltaY);
    }


    @Override
    public String toString() {
        return String.format("x:%d, y:%d", x, y);
    }
}
