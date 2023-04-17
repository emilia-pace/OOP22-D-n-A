package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;

public class Character {

    public final double JumpVelocity = 20;
    public final double StandardVelocity = 2;

    private Position2d position;
    private Vector2d vector;

    public Character(Position2d pos, Vector2d vet) {
        this.position = pos;
        this.vector = vet;
    }

    public Position2d getPosition() {
        return this.position;
    }

    public Vector2d getVector() {
        return this.vector;
    }

    public void setPosition(Position2d pos) {
        this.position = pos;
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
        this.setPosition(this.position.sum(vector));
    }

}
