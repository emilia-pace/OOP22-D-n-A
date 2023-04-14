package it.unibo.dna.model.object;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;

import it.unibo.dna.common.Position2d;

public class Lever implements Object{

    private boolean state = false;
    private Position2d pos; 

    public Lever(Position2d pos){
        this.pos=pos;
    }

    @Override
    public Position2d getPosition2d() {
        return pos;
    }

    @Override
    public void enable() {
        state=true;
    }

    @Override
    public void disable() {
        state=false;
    }
    @Override
        public boolean isActivated() {
            // TODO Auto-generated method stub
           throw new UnsupportedOperationException("Unimplemented method 'isActivated'");
    }
}

    