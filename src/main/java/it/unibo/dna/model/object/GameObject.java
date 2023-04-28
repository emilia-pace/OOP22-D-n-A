package it.unibo.dna.model.object;

import it.unibo.dna.model.object.api.Entity;

public interface GameObject extends Entity {
    
    public void activate();
    public boolean isActivated();
}