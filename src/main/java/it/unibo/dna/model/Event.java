package it.unibo.dna.model;

import it.unibo.dna.Game;

/**
 * Functional interface for event management.
 */
public interface Event {

    /**
     * 
     * @param game the game state to manage
     */
    void manage(Game game);
}
