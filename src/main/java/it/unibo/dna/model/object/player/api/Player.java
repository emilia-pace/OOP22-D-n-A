package it.unibo.dna.model.object.player.api;

import it.unibo.dna.model.events.impl.EventQueue;
import it.unibo.dna.model.game.api.GameState;
import it.unibo.dna.model.object.movableEntity.api.MovableEntity;
import it.unibo.dna.model.object.player.State;
import it.unibo.dna.model.object.player.State.StateEnum;

/*
 * An interface rappresenting the Game Character.
 */
public interface Player extends MovableEntity {

    /**
     * The jump speed value.
     */
    double JUMPVELOCITY = 4.5;

    /**
     * The standard velocity of the Player.
     */
    double STANDARDVELOCITY = 0.4;

    /**
     * Returns the state of the player.
     *
     * @return the player's state
     */
    State getState();

    void setStateX(StateEnum stateX);

    void setStateY(StateEnum stateY);

    /**
     * Returns the type of the player.
     *
     * @return the player's type
     */
    PlayerType getPlayerType();


    /*
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
