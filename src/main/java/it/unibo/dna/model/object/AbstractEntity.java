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
    public AbstractEntity(final Position2d pos, final double height, final double width) {
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
    public void setPosition(final Position2d pos) {
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
    public void setPositionX(final Double x) {
        this.setPosition(new Position2d(x, this.getPosition().getY()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPositionY(final Double y) {
        this.setPosition(new Position2d(this.getPosition().getX(), y));
    }

}
