package it.unibo.dna.model.object.player.api;

import it.unibo.dna.model.object.movableentity.api.MovableEntity;
import it.unibo.dna.model.object.player.impl.State;
import it.unibo.dna.model.object.player.impl.State.StateEnum;

/**
 * An interface rappresenting the Game Character.
 */
public interface Player extends MovableEntity {

    /**
     * The jump speed value.
     */
    double JUMPVELOCITY = 5.6;

    /**
     * The standard velocity of the Player.
     */
    double STANDARDVELOCITY = 0.64;

    /**
     * Returns the state of the player.
     *
     * @return the state of the player
     */
    State getState();

    /**
     * Returns a copy of the current player's state.
     *
     * @return a copy of the current player's state
     */
    State getStateCopy();

    /**
     * Sets the first state of the player.
     *
     * @param stateX the first state to set
     */
    void setStateX(StateEnum stateX);

    /**
     * Sets the second state of the player.
     *
     * @param stateY the second state to set
     */
    void setStateY(StateEnum stateY);

    /**
     * Returns the type of the player.
     *
     * @return the player's type
     */
    PlayerType getPlayerType();

    /**
     * An enum rappresenting the type of the Player.
     */
    enum PlayerType {
        /**
         * Represents when the player is a devil.
         */
        DEVIL,
        /**
         * Represents when the player is an angel.
         */
        ANGEL;
    }

}
