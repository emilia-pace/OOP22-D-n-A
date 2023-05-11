package it.unibo.dna.model.object;

import java.util.Optional;
import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.api.Player;

/**
 * An ActivableObject that moves its corresponding platform when pushed by the player.
 * It can be either a Lever or a Button.
 * The lever moves the {@link MovablePlatform} when it is touched by the player.
 * The Button moves the {@link MovablePlatform} while it is being touched by the player.
 */
public class ActivableObject extends EntityImpl implements GameObject {

    public static enum Activator {
        BUTTON, LEVER
    }

    private boolean isActive=false;
    public Activator type;
    private Optional<Player> player = Optional.empty();
    private MovablePlatform mp;

    /**
     * @param pos the position of the ActivableObject
     * @param height the height of the ActivableObject
     * @param width the width of the ActivableObject
     * @param type the type of the ActivableObject
     * @param mp the {@link MovablePlatform} that the ActivableObject moves
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
     * @return wether the ActivableObject is a Button or a Lever
     */
    public Activator getType(){
        return this.type;
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
    public void setMovablePlatform(final MovablePlatform m) {
        this.mp=m;
    }

    /** 
     * A methods that moves the platform from its starting position towards its final position.
     */
    public void activate(){
        this.isActive = true;
        mp.move(this.mp.getOriginalPos(),mp.getFinalPosition());
    }

    /**
     * A method that moves the platform from its final position towards its starting position. 
     */
    public void deactivate(){
        this.isActive = false;
        mp.move(mp.getFinalPosition(),mp.getOriginalPos());
    }

    /**
     * @return wether the platform is supposed to be moving.
     */
    @Override
    public boolean isActivated() {
        return this.isActive;
    }
    
}
