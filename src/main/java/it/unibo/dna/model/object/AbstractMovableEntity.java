package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.object.api.MovableEntity;

/**
 * Abstract Class that implements the {@link MovableEntity} interface.
 */
public abstract class AbstractMovableEntity extends AbstractEntity implements MovableEntity {

    Vector2d vector;

    /**
     * @param pos    the position of the Entity
     * @param vet    the start vector of the Entity
     * @param height the height of the Entity
     * @param width  the width of the Entity
     */
    public AbstractMovableEntity(Position2d pos, Vector2d vet, double height, double width) {
        super(pos, height, width);
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
    public void setVector(Vector2d vet) {
        this.vector = vet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVectorX(double x) {
        this.vector = new Vector2d(x, this.vector.y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVectorY(double y) {
        this.vector = new Vector2d(this.vector.x, y);
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
