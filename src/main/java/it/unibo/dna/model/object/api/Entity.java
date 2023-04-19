package it.unibo.dna.model.object.api;

import it.unibo.dna.common.Position2d;

public interface Entity {
    public Position2d getPosition();

    public void setPosition(Position2d pos);

    public BoundingBox getBoundingBox();

    public void update();
}
