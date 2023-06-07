package it.unibo.dna.model.object.player.api;

import it.unibo.dna.model.object.movableEntity.api.MovableEntity;
import it.unibo.dna.model.object.player.State;
import it.unibo.dna.model.object.player.State.StateEnum;

/**
 * An interface rappresenting the Game Character.
 */
public interface Player extends MovableEntity {

    /**
     * The jump speed value.
     */
    double JUMPVELOCITY = 5.3;

    /**
     * The standard velocity of the Player.
     */
    double STANDARDVELOCITY = 0.55;

    /**
     * Returns the state of the player.
     *
     * @return the player's state
     */
    State getState();

    /**
     * Returns a copy of the current state.
     *
     * @return a copy of the current state
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
