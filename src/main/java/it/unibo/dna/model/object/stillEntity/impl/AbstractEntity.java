package it.unibo.dna.model.object.stillentity.impl;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.box.api.BoundingBox;
import it.unibo.dna.model.box.impl.RectBoundingBox;
import it.unibo.dna.model.object.player.Entity;

/**
 * Abstract Class that implements {@link Entity} interface.
 */
public abstract class AbstractEntity implements Entity {

    private final BoundingBox box;
    private final Entity.EntityType type;

    /**
     * Constructs a new AbstractEntity object.
     *
     * @param pos    the position of the entity
     * @param height the height of the entity
     * @param width  the width of the entity
     * @param type   the entity type
     */
    public AbstractEntity(final Position2d pos, final double height, final double width, final Entity.EntityType type) {
        box = new RectBoundingBox(pos, height, width);
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position2d getPosition() {
        return box.getPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Position2d pos) {
        this.box.setPosition(pos);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BoundingBox getBoundingBox() {
        return new RectBoundingBox(this.box.getPosition(), this.box.getHeight(), this.box.getWidth());
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity.EntityType getType() {
        return this.type;
    }
}
