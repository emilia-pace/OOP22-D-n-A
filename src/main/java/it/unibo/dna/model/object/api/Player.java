package it.unibo.dna.model.object.api;

import it.unibo.dna.GameState;
import it.unibo.dna.common.Pair;
import it.unibo.dna.model.object.State;

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
    State getState();

    /**
     * @return the player's type
     */
    PlayerType getPlayerType();

    /**
     * @return the game of the Player
     */
    GameState getGame();

    /**
     * @param game the new game of the player
     */
    void setGame(GameState game);

    /*
     * An enum rappresenting the type of the Player
     */
    enum PlayerType {
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
