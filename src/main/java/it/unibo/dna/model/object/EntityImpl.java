package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.RectBoundingBox;
import it.unibo.dna.model.object.api.BoundingBox;
import it.unibo.dna.model.object.api.Entity;

public class EntityImpl implements Entity {

    private Position2d position;
    private double height;
    private double width;

    /**
     * @param pos    the position of the entity
     * @param height the height of the entity
     * @param width  the width of the entity
     */
    public EntityImpl(Position2d pos, double height, double width) {
        this.position = pos;
        this.height = height;
        this.width = width;
    }

    @Override
    public Position2d getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Position2d pos) {
        this.position = pos;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return new RectBoundingBox(this.position, this.height, this.width);
    }

    @Override
    public void setPositionX(Double x) {
        this.setPosition(new Position2d(x, this.getPosition().y));
    }

    @Override
    public void setPositionY(Double y) {
        this.setPosition(new Position2d(this.getPosition().x, y));
    }

}
