package it.unibo.dna.model.object.api;

import it.unibo.dna.common.Position2d;

public interface BoundingBox {

    boolean isCollidingWith(Position2d p, double height, double lenght);

    public Position2d getPosition();
    double getHeight();
    double getLenght();
}
