package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.RectBoundingBox;
import it.unibo.dna.model.object.api.BoundingBox;
import it.unibo.dna.model.object.api.Entity;

public class EntityImpl implements Entity {

    private Position2d position;
    private double height;
    private double width;

    public EntityImpl(Position2d pos, double height, double width) {
        this.position = pos;
        this.height = height;
        this.width = width;
    }

    public Position2d getPosition() {
        return this.position;
    }

    public void setPosition(Position2d pos) {
        this.position = pos;
    }

    public BoundingBox getBoundingBox() {
        return new RectBoundingBox(this.position, this.height, this.width);
    }

}
