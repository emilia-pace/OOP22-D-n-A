package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.api.BoundingBox;

/**
 * A lever that moves a platform when it gets switched on, and moves the platform back to its original 
 * position when it gets switched off.
 */
public class Lever extends AbstractEntity implements GameObject{

    public static enum leverState {
        SWITCHED_ON,SWITCHED_OFF;
    }

    private boolean isActive = true;
    private leverState state = leverState.SWITCHED_OFF;
    private MovablePlatform platform;

    /**
     * 
     * @param pos the position of the lever
     * @param platform the {@link MovablePlatform} controlled by the lever
     * @param box the {@link BoundingBox} of the door
     */
    public Lever(final Position2d pos, final Double height, final Double width,  final MovablePlatform platform){
        super(pos,height,width);
        this.platform = platform;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isActivated() {
        return this.isActive;
    }

    /**
     * A method to push the lever. The {@link MovablePlatform} linked to the lever will move towards its final
     * position.
     */
    public void activate() { //switch on
        if(!isActive){
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

    