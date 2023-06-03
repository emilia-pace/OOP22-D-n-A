package it.unibo.dna.model.object.player.api;

import it.unibo.dna.GameState;
import it.unibo.dna.model.EventQueue;
import it.unibo.dna.model.object.movableEntity.api.MovableEntity;
import it.unibo.dna.model.object.player.State;

/*
 * An interface rappresenting the Game Character.
 */

public interface Player extends MovableEntity {

    /**
     * The jump speed value.
     */
    double JUMPVELOCITY = 5;

    /**
     * The standard velocity of the Player.
     */
    double STANDARDVELOCITY = 0.4;

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
    EventQueue getGameEventQueue();

    /**
     * @param game the new player's GameState
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
