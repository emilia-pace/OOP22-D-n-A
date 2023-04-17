package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.BoundingBox;

public class Diamond implements GameObject{
    
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


    @Override
    public Position2d getPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
    }


    @Override
    public void enable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enable'");
    }


    @Override
    public void disable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'disable'");
    }


    @Override
    public boolean isActivated() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isActivated'");
    }

    
}
