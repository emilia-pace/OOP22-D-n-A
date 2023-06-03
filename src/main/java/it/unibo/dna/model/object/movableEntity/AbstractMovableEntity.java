package it.unibo.dna.model.object.movableEntity;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.object.AbstractEntity;
import it.unibo.dna.model.object.movableEntity.api.MovableEntity;

/**
 * Abstract Class that implements the {@link MovableEntity} interface.
 */
public abstract class AbstractMovableEntity extends AbstractEntity implements MovableEntity {

    private Vector2d vector;

    /**
     * @param pos    the position of the Entity
     * @param vet    the start vector of the Entity
     * @param height the height of the Entity
     * @param width  the width of the Entity
     * @param type   the type of the Entity
     */
    public AbstractMovableEntity(final Position2d pos, final Vector2d vet, final double height, final double width,
            final entityType type) {
        super(pos, height, width, type);
        this.vector = vet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2d getVector() {
        return this.vector;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVector(final Vector2d vet) {
        this.vector = vet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVectorX(final double x) {
        this.vector = new Vector2d(x, this.vector.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVectorY(final double y) {
        this.vector = new Vector2d(this.vector.getX(), y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetX() {
        this.setVectorX(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetY() {
        this.setVectorY(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.setPosition(this.getPosition().sum(vector));
    }

}
