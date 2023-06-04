package it.unibo.dna.model.object.api;

import it.unibo.dna.common.Position2d;
/**
 * An interface that models a generic entity.
 */
public interface Entity {

    /**
     * 
     */
    enum EntityType { PLAYER, ANGEL_DOOR, DEVIL_DOOR, LEVER, BUTTON, BLUE_PUDDLE,
                     RED_PUDDLE, PURPLE_PUDDLE, PLATFORM, MOVABLEPLATFORM, DIAMOND};

    /**
     * @return the position of the entity
     */
    Position2d getPosition();

    /**
     * Set the position of the entity.
     * @param pos
     */
    void setPosition(Position2d pos);

    /**
     * @return the bounding box of the entity
     */
    BoundingBox getBoundingBox();

    /**
     * Set the x-axis value of the position.
     * @param x the x-axis value
     */
    void setPositionX(Double x);

    /**
     * Set the y-axis value of the position.
     * @param y the y-axis value
     */
    void setPositionY(Double y);

    /**
     * A getter for the type of the entity.
     * @return the type of the entity
     */
    Entity.EntityType getType();

}
