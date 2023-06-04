package it.unibo.dna.common;

/**
 * A class rappresenting a vector in 2-dimensional space that describe
 * the direction and velocity of a movement.
 */

public class Vector2d  {

    private double x, y;

    /**
     * @param x the first coordinate of the vector
     * @param y the second coordinate of the vector
     */
    public Vector2d(final double x, final double y) {
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
     * Change the first coordinate of the vector by adding
     * a double number.
     * 
     * @param d the double number to be summed
     */
    public void sumX(final double d) {
        x += d;
    }

    /**
     * Change the second coordinate of the vector by adding
     * a double number.
     * 
     * @param d the double number to be summed
     */
    public void sumY(final double d) {
        y += d;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Vector2d(" + x + "," + y + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Vector2d)) {
            return false;
        }
        final Vector2d vec = (Vector2d) obj;
        return (Double.compare(vec.x, this.x) == 0 && Double.compare(vec.y, this.y) == 0);
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
