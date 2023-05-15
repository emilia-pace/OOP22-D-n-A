package it.unibo.dna.model.object;

import it.unibo.dna.common.Pair;
import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.object.api.Player;

public class PlayerImpl extends MovableEntityImpl implements Player {

    private Pair<State, State> state = new Pair<>(State.STATE_STANDING, State.STATE_STILL);
    private Type type;

    /**
     * @param pos    the position of the player
     * @param vet    the vector of the player
     * @param height the height of the player
     * @param width  the width of the player
     * @param type   the type (angel/devil) of the player
     */
    public PlayerImpl(Position2d pos, Vector2d vet, double height, double width, Type type) {
        super(pos, vet, height, width);
        this.type = type;
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

    @Override
    public void resetX() {
        this.setVectorX(0);
    }

    @Override
    public void resetY() {
        this.setVectorY(0);
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
