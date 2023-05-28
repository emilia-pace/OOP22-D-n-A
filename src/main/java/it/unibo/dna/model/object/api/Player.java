package it.unibo.dna.model.object.api;

import it.unibo.dna.Game;
import it.unibo.dna.common.Pair;

/*
 * An interface rappresenting the Game Character.
 */

public interface Player extends MovableEntity {

    /**
     * The jump speed value
     */
    double JumpVelocity = 25;

    /**
     * The standard velocity of the Player
     */
    double StandardVelocity = 2;

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
    Game getGame();

    /**
     * @param game the new game of the player
     */
    void setGame(Game game);

    /*
     * An enum rappresenting the state of the Player
     */
    enum State {
        STATE_STANDING,
        STATE_JUMPING,
        STATE_RIGHT,
        STATE_LEFT,
        STATE_STILL;
    }

    /*
     * An enum rappresenting the type of the Player
     */
    enum Type {
        DEVIL,
        ANGEL;
    }

}
