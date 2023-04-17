package it.unibo.dna.model;

import it.unibo.dna.common.Position2d;

public interface BoundingBox {

    boolean isCollidingWith(Position2d p, double height, double lenght);

}
