package it.unibo.dna.model.object;

import it.unibo.dna.common.Pair;
import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.object.api.Player;

public class PlayerImpl extends EntityImpl implements Player {

    private Vector2d vector;
    private Pair<State, State> state = new Pair<>(State.STATE_STANDING, State.STATE_STILL);
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

    public Pair<State, State> getState() {
        return this.state;
    }

    public void setState(Pair<State, State> state) {
        this.state = state;
    }

    public void setStateX(State newState) {
        this.state.setX(newState);
    }

    public void setStateY(State newState) {
        this.state.setY(newState);
    }

    @Override
    public Type getType() {
        return this.type;
    }

    public enum State {
        STATE_STANDING,
        STATE_JUMPING,
        STATE_RIGHT,
        STATE_LEFT,
        STATE_STILL;
    }

    public enum Type {
        DEVIL,
        ANGEL;
    }

    @Override
    public boolean equals(Player p) {
        return this.type.equals(p.getType());
    }
}
