package it.unibo.dna.model.object;

import it.unibo.dna.model.BoundingBox;

public class Diamond extends Object{
    
    private double value = 0;
    private BoundingBox box;

    public Diamond(final BoundingBox b, final double v){
        this.value = v;
        this.box = b;
    }


    public double getValue() {
        return this.value;
    }

    public BoundingBox getBox() {
        return this.box;
    }

    
}
