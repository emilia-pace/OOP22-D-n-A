package it.unibo.dna.model.object;

import java.util.Optional;
import it.unibo.dna.common.Pair;
import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.RectBoundingBox;
import it.unibo.dna.model.object.api.BoundingBox;
import it.unibo.dna.model.object.api.Player;

/**
 * {@link Button} that moves its corresponding platform when pushed by the player.
 */
public class ActivableObject extends AbstractEntity implements GameObject {

    public static enum Activator {
        BUTTON, LEVER
    }

    private boolean isActive=false;
    public Activator type;
    private Optional<Player> player = Optional.empty();
    private MovablePlatform mp;

    /**
     * @param pos the position of the button
     * @param height the height of the button
     * @param width the width of the button
     * @param mp the {@link MovablePlatform} that the button moves
     */
    public ActivableObject(final Position2d pos, final Double height, final Double width, final Activator type, final MovablePlatform mp) {
       super(pos,height,width);
       this.type=type;
       this.mp=mp;
    }

    /**
     * @param p the {@link Player} that has touched the button
     */
    public void setPlayer(final Player p){
        this.player = Optional.of(p);
    }

    /**
     * @return the player that has touched the button
     */
    public Optional<Player> getPlayer(){
        return this.player;
    }

    /**
     * resets the player.
     */
    public void resetPlayer(){
        this.player = Optional.empty();
    }

    /** 
     * @return the {@link MovablePlatform} controlled by the button
     */
    public MovablePlatform getMovablePlatform() {
        return this.mp;
    }

    /** 
     * A setter for the MovablePlatform controlled by the button.
     */
    public void setMovablePlatform(MovablePlatform m) {
        this.mp=m;
    }

    /** 
     * A method that moves the platform of the button as long as it is being pressed. 
     */
    public void activate(){
        this.isActive = true;
        mp.move(mp.getOriginalPos(),mp.getFinalPosition());
    }

    public void deactivate(){
        this.isActive = false;
        mp.move(mp.getFinalPosition(),mp.getOriginalPos());
    }

    /**
     * @return wether the button is being pushed
     */
    @Override
    public boolean isActivated() {
        return this.isActive;
    }
    
}
