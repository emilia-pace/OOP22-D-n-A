package it.unibo.dna.model.object.stillentity.impl;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.entity.api.Entity;

/**
 * A still platform.
 */
public class Platform extends  AbstractEntity {

    /**
     * 
     * @param position the position of the Platform
     * @param height the height of the Platform
     * @param width the width of the Platform
     */
    public Platform(final Position2d position, final double height, final double width) {
        super(position, height, width, Entity.EntityType.PLATFORM);
    }

}
