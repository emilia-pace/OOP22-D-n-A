package it.unibo.dna.model.object.player;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.box.api.BoundingBox;
/**
 * An interface that models a generic entity.
 */
public interface Entity {

    /**
     * An enumeration that describes the different types of entities.
     */
    enum EntityType { 
        /**
         * Represents the player.
         */
        PLAYER,
        /**
         * Represents the door that the angel can open.
         */ 
        ANGEL_DOOR, 
        /**
         * Represents the door that the devil can open.
         */
        DEVIL_DOOR, 
        /**
         * Represents a lever.
         */
        LEVER, 
        /**
         * Represents a button.
         */
        BUTTON, 
        /**
         * Represents a puddle that only the angel can walk on.
         */
        BLUE_PUDDLE,
        /**
         * Represents a puddle that only the devil can walk on.
         */
        RED_PUDDLE, 
        /**
         * Represents a puddle that no character can walk on.
         */
        PURPLE_PUDDLE, 
        /**
         * Represents a platform.
         */
        PLATFORM, 
        /**
         * Represents a platform that can be moved by a button or a lever.
         */
        MOVABLEPLATFORM, 
        /**
         * Represents a diamond.
         */
        DIAMOND;
    }

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
