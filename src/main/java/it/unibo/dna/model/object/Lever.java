package it.unibo.dna.model.object;

//import java.util.ArrayList;
//import java.util.List;


import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.RectBoundingBox;

public class Lever implements GameObject{

    public static enum leverState {
        SWITCHED_ON,SWITCHED_OFF;
    }

    private boolean isActive = true;
    private boolean isSwitchedOn = false;
    private leverState state = leverState.SWITCHED_OFF;
    private Position2d pos; 
    private MovablePlatform platform;

    public Lever(Position2d pos, MovablePlatform platform){
        this.pos=pos;
        this.platform=platform;
    }

    @Override
    public Position2d getPosition() {
        return pos;
    }
    
    public boolean isSwitchedOn() {
        return isSwitchedOn;
    }

    @Override
    public void enable() {
        isActive=true;
    }

    @Override
    public void disable() {
        isActive=false;
    }

    @Override
    public boolean isActivated() {
        return isActive;
    }

    @Override
    public void setPosition(Position2d pos) {
        this.pos=pos;
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

    public void switchOn() {
        if(isActive){
            platform.move();
            state=leverState.SWITCHED_ON;
        }
    }

    public void switchOff() {
        if(isActive){
            platform.returnToOriginalPosition();
            state=leverState.SWITCHED_OFF;
        }
    }
}

    