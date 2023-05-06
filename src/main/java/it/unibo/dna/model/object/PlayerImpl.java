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

    /**
     * @param pos    the position of the player
     * @param vet    the vector of the player
     * @param height the height of the player
     * @param width  the width of the player
     * @param type   the type (angel/devil) of the player
     */
    public PlayerImpl(Position2d pos, Vector2d vet, double height, double width, Type type) {
        super(pos, height, width);
        this.vector = vet;
        this.type = type;
        this.oldPos = pos;
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
        this.oldPos = new Position2d(this.getPosition().x, this.getPosition().y);
        this.setPosition(this.getPosition().sum(vector));
    }

    @Override
    public void resetX() {
        this.setPosition(new Position2d(oldPos.x, this.getPosition().y));
    }

    @Override
    public void resetY() {
        this.setPosition(new Position2d(this.getPosition().x, oldPos.y));
    }

    @Override
    public Pair<State, State> getState() {
        return this.state;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public boolean equals(Player p) {
        return this.type.equals(p.getType());
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

}
