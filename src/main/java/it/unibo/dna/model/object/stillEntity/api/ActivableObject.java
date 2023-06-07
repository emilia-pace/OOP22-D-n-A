package it.unibo.dna.model.object.stillEntity.api;

import it.unibo.dna.model.object.player.Entity;

/**
 * A class that allows some Entities to be activated.
 */
public interface ActivableObject extends Entity {

    /**
     * Activates the Gameobject.
     */
    void activate();

    /**
     * 
     * @return True if the GameObject is activated.
     */
    boolean isActivated();
    
}
