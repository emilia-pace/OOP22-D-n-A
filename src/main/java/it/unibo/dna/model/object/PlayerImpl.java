package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.object.api.Player;

public class PlayerImpl extends AbstractEntity implements Player {

    public final double JumpVelocity = 20;
    public final double StandardVelocity = 2;
    public final double Gravity = 4;

    private Vector2d vector;
    private State state = State.STATE_STANDING;

    public PlayerImpl(Position2d pos, Vector2d vet, double height, double width) {
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
        this.vector = new Vector2d(x, this.vector.y);
    }

    public void setVectorY(double y) {
        this.vector = new Vector2d(this.vector.x, y);
    }

    public void update() {
        if (this.vector.y < this.Gravity) {
            this.vector.sumY(StandardVelocity);
        }
        this.setPosition(this.getPosition().sum(vector));

    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public enum State {
        STATE_STANDING,
        STATE_JUMPING,
        STATE_RIGHT,
        STATE_LEFT;
    }
}
