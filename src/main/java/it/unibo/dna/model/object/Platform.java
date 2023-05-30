package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.api.Entity;

public class Platform extends  AbstractEntity{

    public Platform(final Position2d pos, final double height, final double width) {
        super(pos, height, width, Entity.entityType.PLATFORM);
    }
    
}