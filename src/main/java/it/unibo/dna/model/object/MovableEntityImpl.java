package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.object.api.MovableEntity;

public class MovableEntityImpl extends EntityImpl implements MovableEntity {

    Vector2d vector;

    public MovableEntityImpl(Position2d pos, Vector2d vet, double height, double width) {
        super(pos, height, width);
        this.vector = vet;
    }

    @Override
    public Vector2d getVector() {
        return this.vector;
    }

    @Override
    public void setVector(Vector2d vet) {
        this.vector = vet;
    }

    @Override
    public void setVectorX(double x) {
        this.vector = new Vector2d(x, this.vector.y);
    }

    @Override
    public void setVectorY(double y) {
        this.vector = new Vector2d(this.vector.x, y);
    }

    @Override
    public void update() {
        this.setPosition(this.getPosition().sum(vector));
    }

}
