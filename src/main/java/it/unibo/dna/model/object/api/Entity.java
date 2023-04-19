package it.unibo.dna.model.object.api;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.RectBoundingBox;

public interface Entity {
    public Position2d getPosition();

    public void setPosition(Position2d pos);

    public RectBoundingBox getBoundingBox();

    public void update();
}
