package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.object.api.Player;

public class PlayerImpl extends AbstractEntity implements Player {

    private Vector2d vector;
    private State state = State.STATE_STANDING;
    private Type type;
    private Position2d oldPos;

    public PlayerImpl(Position2d pos, Vector2d vet, double height, double width, Type type) {
        super(pos, height, width);
        this.vector = vet;
        this.type = type;
        this.oldPos = pos;
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

    @Override
    public void update() {
        this.oldPos = new Position2d(this.getPosition().x, this.getPosition().y);
        this.setPosition(this.getPosition().sum(vector));
    }

    public void resetX() {
        this.setPosition(new Position2d(oldPos.x, this.getPosition().y));
    }

    public void resetY() {
        this.setPosition(new Position2d(this.getPosition().x, oldPos.y));
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    // Controllo da implementare con le collisioni
    public boolean isJumping() {
        return false;
    }

    public boolean isTurned() {
        return (this.state == State.STATE_LEFT || this.state == State.STATE_RIGHT);
    }

    @Override
    public Type getType() {
        return this.type;
    }

    public enum State {
        STATE_STANDING,
        STATE_JUMPING,
        STATE_RIGHT,
        STATE_LEFT;
    }

    public enum Type {
        DEVIL,
        ANGEL;
    }
}
