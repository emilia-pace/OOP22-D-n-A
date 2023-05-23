package it.unibo.dna.common;

/**
 * A class rappresenting a vector in 2-dimensional space that describe
 * the direction and velocity of a movement
 */

public class Vector2d implements java.io.Serializable {

    public double x, y;

    /**
     * @param x the first coordinate of the vector
     * @param y the second coordinate of the vector
     */
    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Change the first coordinate of the vector by adding
     * a double number
     * 
     * @param d the double number to be summed
     */
    public void sumX(double d) {
        x += d;
    }

    /**
     * Change the second coordinate of the vector by adding
     * a double number
     * 
     * @param d the double number to be summed
     */
    public void sumY(double d) {
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
}
