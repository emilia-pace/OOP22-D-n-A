package it.unibo.dna.model.object.api;

import it.unibo.dna.common.Position2d;

public interface Entity {

    enum entityType { PLAYER, ANGEL_DOOR, DEVIL_DOOR, LEVER, BUTTON, BLUE_PUDDLE, RED_PUDDLE, PURPLE_PUDDLE, PLATFORM, MOVABLEPLATFORM, DIAMOND};

    /**
     * @return the position of the entity
     */
    public Position2d getPosition();

    /**
     * Set the position of the entity
     * 
     * @param pos
     */
    public void setPosition(Position2d pos);

    /**
     * @return the bounding box of the entity
     */
    public BoundingBox getBoundingBox();

    /**
     * Set the x-axis value of the position.
     * 
     * @param x the x-axis value
     */
    public void setPositionX(Double x);

    /**
     * Set the y-axis value of the position.
     * 
     * @param y the y-axis value
     */
    public void setPositionY(Double y);

    public Entity.entityType getType();

}
