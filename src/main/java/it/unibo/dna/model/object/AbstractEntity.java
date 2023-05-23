package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.RectBoundingBox;
import it.unibo.dna.model.object.api.BoundingBox;
import it.unibo.dna.model.object.api.Entity;

/**
 * Abstract Class that implements {@link MovableEntity} interface.
 */
public abstract class AbstractEntity implements Entity {

    private Position2d position;
    private double height;
    private double width;

    /**
     * @param pos    the position of the entity
     * @param height the height of the entity
     * @param width  the width of the entity
     */
    public AbstractEntity(Position2d pos, double height, double width) {
        this.position = pos;
        this.height = height;
        this.width = width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position2d getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(Position2d pos) {
        this.position = pos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BoundingBox getBoundingBox() {
        return new RectBoundingBox(this.position, this.height, this.width);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPositionX(Double x) {
        this.setPosition(new Position2d(x, this.getPosition().y));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPositionY(Double y) {
        this.setPosition(new Position2d(this.getPosition().x, y));
    }

}
