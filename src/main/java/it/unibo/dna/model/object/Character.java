package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;

public class Character extends EntityImpl {

    public final double JumpVelocity = 20;
    public final double StandardVelocity = 2;

    private Vector2d vector;

    public Character(Position2d pos, Vector2d vet, double height, double width) {
        super(pos, height, width);
        this.vector = vet;
    }

    public Vector2d getVector() {
        return this.vector;
    }

    public void setVector(Vector2d vet) {
        this.vector = vet;
    }

    public void setVectorX(double x) {
        this.vector = new Vector2d(x, this.vector.getY());
    }

    public void setVectorY(double y) {
        this.vector = new Vector2d(this.vector.getX(), y);
    }

    public void update() {
        this.setPosition(this.getPosition().sum(vector));
    }
}
