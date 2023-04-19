package it.unibo.dna.model.object;

//import java.util.ArrayList;
//import java.util.List;


import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.RectBoundingBox;

public class Lever implements GameObject{

    private boolean state = false;
    private Position2d pos; 
    private MovablePlatform platform;
    private static int amount = 10;

    public Lever(Position2d pos){
        this.pos=pos;
    }

    @Override
    public Position2d getPosition() {
        return pos;
    }

    @Override
    public void enable() {
        state=true;
        platform.move(amount);
    }

    @Override
    public void disable() {
        state=false;
        platform.move(-amount);
    }

    @Override
    public boolean isActivated() {
        return state;
    }

    @Override
    public void setPosition(Position2d pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPosition'");
    }

    @Override
    public RectBoundingBox getBoundingBox() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBoundingBox'");
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}

    