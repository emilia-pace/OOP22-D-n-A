package it.unibo.dna.common;

/**
 * A class rapresenting a point in a two dimensional space.
 */

public class Position2d implements java.io.Serializable {

    private double x, y;

    /**
     * @param x the first coordinate of the position
     * @param y the second coordinate of the position
     */
    public Position2d(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the first coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the second coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * Move the position by adding a 2-dimensional vector
     * 
     * @param vector 2-dimensional vector
     * @return the new position
     */
    public Position2d sum(final Vector2d vector) {
        return new Position2d(x + vector.getX(), y + vector.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Position2d(" + x + "," + y + ")";
    }

    public boolean isOnTheRight(final Position2d p) {
        return this.x > p.getX();
    }

    public boolean isAbove(final Position2d p) {
        return this.y < p.getY();
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
        return (Double.compare(pos.getX(), this.x) == 0 && Double.compare(pos.getY(), this.y) == 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result;
        result = prime * result;
        return result;
    }

}
