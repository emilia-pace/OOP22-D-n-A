package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.RectBoundingBox;
import it.unibo.dna.model.object.api.BoundingBox;
import it.unibo.dna.model.object.api.Entity;

/**
 * Abstract Class that implements {@link Entity} interface.
 */
public abstract class AbstractEntity implements Entity {

    private BoundingBox box;
    private Entity.entityType type;

    /**
     * @param pos    the position of the entity
     * @param height the height of the entity
     * @param width  the width of the entity
     */
    public AbstractEntity(final Position2d pos, final double height, final double width, Entity.entityType type) {
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
        return this.box;
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

    public Entity.entityType getType(){
        return this.type;
    }
}
