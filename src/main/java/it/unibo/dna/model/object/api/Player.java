package it.unibo.dna.model.object.api;

import it.unibo.dna.common.Pair;

/*
 * An interface rappresenting the Game Character
 */

public interface Player extends MovableEntity {

    /**
     * The jump speed value
     */
    public static final double JumpVelocity = 25;

    /**
     * The standard velocity of the Player
     */
    public static final double StandardVelocity = 2;

    /**
     * @return the player's state
     */
    Pair<State, State> getState();

    /**
     * @return the player's type
     */
    Type getType();

    /**
     * {@inheritDoc}
     */
    @Override
    boolean equals(Object p);

    /*
     * An enum rappresenting the state of the Player
     */
    public enum State {
        STATE_STANDING,
        STATE_JUMPING,
        STATE_RIGHT,
        STATE_LEFT,
        STATE_STILL;
    }

    /*
     * An enum rappresenting the type of the Player
     */
    public enum Type {
        DEVIL,
        ANGEL;
    }

}
