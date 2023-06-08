package it.unibo.dna.model.object.stillentity.api;

import it.unibo.dna.model.object.entity.api.Entity;

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
