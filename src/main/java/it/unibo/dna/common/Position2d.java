package it.unibo.dna.common;

/**
 * A class rapresenting a point in a two dimensional space
 */

public class Position2d implements java.io.Serializable {

    public double x, y;

    /**
     * @param x the first coordinate of the position
     * @param y the second coordinate of the position
     */
    public Position2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Move the position by adding a 2-dimensional vector
     * @param v 2-dimensional vector
     * @return the new position
     */
    public Position2d sum(Vector2d vector) {
        return new Position2d(x + vector.x, y + vector.y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Position2d(" + x + "," + y + ")";
    }

    public boolean isOnTheRight(final Position2d p) {
        return this.x > p.x;
    }

    public boolean isAbove(final Position2d p) {
        return this.y < p.y;
    }

    public boolean isBetweenHorizontally(final Position2d p1, final Position2d p2) {
        return this.isOnTheRight(p1) && p2.isOnTheRight(this);
    }

    public boolean isBetweenVertically(final Position2d p1, final Position2d p2) {
        return this.isAbove(p1) && p2.isAbove(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Position2d)) {
            return false;
        }
        final Position2d pos = (Position2d) obj;
        return (Double.compare(pos.x, this.x) == 0 && Double.compare(pos.y, this.y) == 0);
    }

}
