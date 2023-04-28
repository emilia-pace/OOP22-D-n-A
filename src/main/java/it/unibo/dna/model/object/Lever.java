package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.api.BoundingBox;

/**
 * A lever that moves a platform when it gets switched on, and moves the platform back to its original 
 * position when it gets switched off.
 */
public class Lever implements GameObject{

    public static enum leverState {
        SWITCHED_ON,SWITCHED_OFF;
    }

    private boolean isActive = true;
    private boolean isSwitchedOn = false;
    private leverState state = leverState.SWITCHED_OFF;
    private Position2d pos; 
    private MovablePlatform platform;
    private BoundingBox box;

    /**
     * 
     * @param pos the position of the lever
     * @param platform the {@link MovablePlatform} controlled by the lever
     * @param box the {@link BoundingBox} of the door
     */
    public Lever(final Position2d pos, final MovablePlatform platform, final BoundingBox box){
        this.pos = pos;
        this.platform = platform;
        this.box = box;
    }

    @Override
    public Position2d getPosition() {
        return this.pos;
    }

    /**
     * 
     * @return wether the lever is switched on
     */
    public boolean isSwitchedOn() {
        return this.isSwitchedOn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isActivated() {
        return this.isActive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(Position2d pos) {
        this.pos=pos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BoundingBox getBoundingBox() {
        return this.box;
    }

    /**
     * A method to push the lever. The {@link MovablePlatform} linked to the lever will move towards its final
     * position.
     */
    public void activate() { //switch on
        if(isActive){
            platform.move(platform.getOriginalPos(),platform.getFinalPosition());
            state=leverState.SWITCHED_ON;
        }
    }

    /**
     * A method to pull the lever. The {@link MovablePlatform} will move towards its 
     * original position.
     */
    public void deactivate() { //switch off
        if(isActive){
            platform.move(platform.getFinalPosition(),platform.getOriginalPos());
            state=leverState.SWITCHED_OFF;
        }
    }
}

    