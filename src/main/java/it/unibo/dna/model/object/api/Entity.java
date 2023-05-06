package it.unibo.dna.model.object.api;

import it.unibo.dna.common.Position2d;

public interface Entity {

    /**
     * @return the position of the entity
     */
    public Position2d getPosition();

    /**
     * Set the position of the entity
     * @param pos
     */
    public void setPosition(Position2d pos);

    /**
     * @return the bounding box of the entity
     */
    public BoundingBox getBoundingBox();

}
