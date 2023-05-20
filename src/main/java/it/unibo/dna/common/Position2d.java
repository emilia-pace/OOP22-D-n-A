package it.unibo.dna.common;

public class Position2d implements java.io.Serializable {

    public double x, y;

    public Position2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position2d sum(Vector2d v) {
        return new Position2d(x + v.x, y + v.y);
    }

    public Vector2d sub(Position2d v) {
        return new Vector2d(x - v.x, y - v.y);
    }

    public String toString() {
        return "Position2d(" + x + "," + y + ")";
    }

    public boolean isOnTheRight(final Position2d p2) {
        return this.x > p2.x;
    }

    public boolean isAbove(final Position2d p2) {
        return this.y < p2.y;
    }

    public boolean isBetweenHorizontally(final Position2d p1, final Position2d p2) {
        return this.isOnTheRight(p1) && p2.isOnTheRight(this);
    }

    public boolean isBetweenVertically(final Position2d p1, final Position2d p2) {
        return this.isAbove(p1) && p2.isAbove(this);
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Position2d)) {
            return false;
        }
        final Position2d pos = (Position2d) obj;
        return (Double.compare(pos.x, this.x) == 0 && Double.compare(pos.y, this.y) == 0);
    }

}
