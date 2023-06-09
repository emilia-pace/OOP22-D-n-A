package it.unibo.dna.model.box.api;

import it.unibo.dna.model.common.Position2d;

/** 
 * Interface for the boundaries of an {@link Entity}.
*/
public interface BoundingBox {

    /**
     * Checks if the {@link Entity} modelled by this box is colliding with another {@link Entity}.
     * @param p the position of the other {@link Entity}
     * @param height the height of the other {@link Entity}
     * @param width the width of the other {@link Entity}
     * @return true if the entities are colliding
     */
    boolean isCollidingWith(Position2d p, double height, double width);

    /**
     * Checks if the collision beetween the two entities is on the left or the right side.
     * @param p the position of the other {@link Entity}
     * @param height the height of the other {@link Entity}
     * @param width the width of the other {@link Entity}
     * @return true if the collision is on the left or the right side
     */
    boolean sideCollision(Position2d p, double height, double width);

    /**
     * 
     * @return the position (upper-left corner) of the box
     */
    Position2d getPosition();

    /**
     * 
     * @param pos the position to set
     */
    void setPosition(Position2d pos);

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
