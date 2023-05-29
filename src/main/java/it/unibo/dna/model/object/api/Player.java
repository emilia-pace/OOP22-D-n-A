package it.unibo.dna.model.object.api;

import it.unibo.dna.GameState;
import it.unibo.dna.common.Pair;

/*
 * An interface rappresenting the Game Character.
 */

public interface Player extends MovableEntity {

    /**
     * The jump speed value
     */
    double JUMPVELOCITY = 25;

    /**
     * The standard velocity of the Player
     */
    double STANDARDVELOCITY = 2;

    /**
     * @return the player's state
     */
    Pair<State, State> getState();

    /**
     * @return the player's type
     */
    Type getType();

    /**
     * @return the game of the Player
     */
    GameState getGame();

    /**
     * @param game the new game of the player
     */
    void setGame(GameState game);

    /**
     * An enum rappresenting the state of the Player
     */
    enum State {
        /**
         * when player is on a platform
         */
        STATE_STANDING,
        /**
         * when player is jumping
         */
        STATE_JUMPING,
        /**
         * when player goes right
         */
        STATE_RIGHT,
        /**
         * when player goes left
         */
        STATE_LEFT,
        /**
         * when player is still
         */
        STATE_STILL;
    }

    /*
     * An enum rappresenting the type of the Player
     */
    enum Type {
        /**
         * when player is a devil
         */
        DEVIL,
        /**
         * when player is an angel
         */
        ANGEL;
    }

}
