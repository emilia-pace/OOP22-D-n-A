package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.api.BoundingBox;

public class Platform extends AbstractEntity{

    public Platform(final Position2d pos, final double height, final double width, final BoundingBox box) {
        super(pos, height, width);
    }
    
}