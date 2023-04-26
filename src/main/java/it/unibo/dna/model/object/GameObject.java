package it.unibo.dna.model.object;

import it.unibo.dna.model.object.api.Entity;

//import javax.swing.BoundedRangeModel;

//import it.unibo.dna.common.Position2d;
//import it.unibo.dna.model.BoundingBox;

public interface GameObject extends Entity {
    public void activate();

    public boolean isActivated();
    // public boolean isTouched();
}