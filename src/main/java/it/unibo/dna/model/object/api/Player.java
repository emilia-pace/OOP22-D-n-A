package it.unibo.dna.model.object.api;

import it.unibo.dna.common.Pair;

public interface Player extends MovableEntity {

    /**
     *
     */
    public static final double JumpVelocity = 25;

    /**
     *
     */
    public static final double StandardVelocity = 2;

    /**
     * Make the first value of the player's vector zero
     */
    void resetX();

    /**
     * Make the second value of the player's vector zero
     */
    void resetY();

    /**
     * @return the player's state
     */
    Pair<State, State> getState();

    /**
     * @return the player's type
     */
    Type getType();

    /**
     * Compares two player, and returns true if the players
     * have the same type, and false if not.
     * 
     * @param p player for the comparison
     * @return true if p has the same type of player
     */
    boolean equals(Object p);

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
