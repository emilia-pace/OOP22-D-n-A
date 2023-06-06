package it.unibo.dna.common;

/**
 * A class representing a point in a 2-dimensional space.
 */
public class Position2d {

    private double x, y;

    /**
     * Constructs a new {@code Position2d} object with the specified coordinates.
     *
     * @param x the first coordinate of the position
     * @param y the second coordinate of the position
     */
    public Position2d(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the first coordinate of the position.
     *
     * @return the first coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the second coordinate of the position.
     *
     * @return the second coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * Moves the position by adding a 2-dimensional vector.
     *
     * @param vector the 2-dimensional vector
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
