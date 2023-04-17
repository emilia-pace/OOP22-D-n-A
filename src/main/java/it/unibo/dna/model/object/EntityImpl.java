package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.RectBoundingBox;

public abstract class EntityImpl implements Entity {

    private Position2d position;
    private RectBoundingBox box;

    public EntityImpl(Position2d pos, double height, double width) {
        this.position = pos;
        box = new RectBoundingBox(pos, height, width);
    }

    public Position2d getPosition() {
        return this.position;
    }

    public void setPosition(Position2d pos) {
        this.position = pos;
    }

    public RectBoundingBox getBoundingBox() {
        return this.box;
    }

}
