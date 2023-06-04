package it.unibo.dna.model.object;

import it.unibo.dna.model.object.api.Entity;

/**
 * a class that allows some Entities to be activated or deactivated.
 */
public interface GameObject extends Entity {

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
