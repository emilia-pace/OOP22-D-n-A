package it.unibo.dna.model.object;

import ch.qos.logback.classic.spi.PlatformInfo;

//import java.util.ArrayList;
//import java.util.List;


import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.RectBoundingBox;
import it.unibo.dna.model.object.api.BoundingBox;

public class Lever implements GameObject{

    public static enum leverState {
        SWITCHED_ON,SWITCHED_OFF;
    }

    private boolean isActive = true;
    private boolean isSwitchedOn = false;
    private leverState state = leverState.SWITCHED_OFF;
    private Position2d pos; 
    private MovablePlatform platform;
    private double height;
    private double width;
    //private BoundingBox bbox;

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
    public boolean isActivated() {
        return isActive;
    }

    @Override
    public void setPosition(Position2d pos) {
        this.pos=pos;
    }

    @Override
    public RectBoundingBox getBoundingBox() {
        return new RectBoundingBox(pos, width, height);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    public void activate() { //switch on
        if(isActive){
            platform.move(platform.getOriginalPos(),platform.getFinalPosition());
            state=leverState.SWITCHED_ON;
        }
    }

    public void deactivate() { //switch off
        if(isActive){
            platform.move(platform.getFinalPosition(),platform.getOriginalPos());
            state=leverState.SWITCHED_OFF;
        }
    }
}

    