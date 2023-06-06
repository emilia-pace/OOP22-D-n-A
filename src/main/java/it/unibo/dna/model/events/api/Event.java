package it.unibo.dna.model.events.api;

import it.unibo.dna.model.game.api.GameState;

/**
 * Functional interface for event management.
 */
public interface Event {

    /**
     * 
     * @param game the game state to manage
     */
    void manage(GameState game);
}
