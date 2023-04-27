package it.unibo.dna.model.object.api;

import it.unibo.dna.common.Position2d;

/** 
 * Interface for the boundaries of an {@link Entity}.
*/
public interface BoundingBox {

    /**
     * Checks if the {@link Entity} modelled by this box is colliding with another {@link Entity}.
     * @param p the position of the other {@link Entity}
     * @param height the height of the other {@link Entity}
     * @param width the width of the other {@link Entity}
     * @return
     */
    boolean isCollidingWith(Position2d p, double height, double width);

    /**
     * 
     * @return the position (upper-left corner) of the box
     */
    public Position2d getPosition();

    /**
     * 
     * @return the height of the box
     */
    double getHeight();

    /**
     * 
     * @return the width of the box
     */
    double getWidth();
}
