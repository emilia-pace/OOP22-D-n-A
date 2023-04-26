package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.RectBoundingBox;
import it.unibo.dna.model.object.api.BoundingBox;
import it.unibo.dna.model.object.api.Entity;

public abstract class AbstractEntity implements Entity {

    private Position2d position;
    private BoundingBox box;

    public AbstractEntity(Position2d pos, double height, double width) {
        this.position = pos;
        box = new RectBoundingBox(pos, height, width);
    }

    public Position2d getPosition() {
        return this.position;
    }

    public void setPosition(Position2d pos) {
        this.position = pos;
    }

    public BoundingBox getBoundingBox() {
        return this.box;
    }

}
