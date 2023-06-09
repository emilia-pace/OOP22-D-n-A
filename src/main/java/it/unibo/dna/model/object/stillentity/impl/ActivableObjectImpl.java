package it.unibo.dna.model.object.stillentity.impl;

import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.dna.model.common.Position2d;
import it.unibo.dna.model.object.entity.impl.AbstractEntity;
import it.unibo.dna.model.object.movableentity.impl.MovablePlatform;
import it.unibo.dna.model.object.player.api.Player;
import it.unibo.dna.model.object.stillentity.api.ActivableObject;

/**
 * An ActivableObject that moves its platform when activated by the player.
 * It can be either a Lever or a Button.
 * The Lever moves the {@link MovablePlatform} when it is touched by the player.
 * The Button moves the {@link MovablePlatform} while it is being touched by the player.
 */
public class ActivableObjectImpl extends  AbstractEntity implements ActivableObject {

    private boolean isActive; /*True when the platform is moving towards its final position*/
    private Optional<Player> player = Optional.empty(); /*The player that is touching the ActivableObject*/
    private final MovablePlatform movablePlatform;

    /**
     * @param pos the position of the ActivableObject
     * @param height the height of the ActivableObject
     * @param width the width of the ActivableObject
     * @param movablePlatform the {@link MovablePlatform} that the ActivableObject moves
     * @param type the type of the EntityType of the ActivableObject (it can be BUTTON or LEVER)
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "cannot pass a copy of the MovablePlatform," 
        + "it needs to be modified")
    public ActivableObjectImpl(final Position2d pos, final Double height, final Double width, 
                                final MovablePlatform movablePlatform, final EntityType type) {
       super(pos, height, width, type);
       this.movablePlatform = movablePlatform;
    }

    /**
     * @param player the {@link Player} that has touched the button
     */
    public void setPlayer(final Player player) {
        this.player = Optional.of(player);
    }

    /**
     * @return the Player that has touched the button
     */
    public Optional<Player> getPlayer() {
        return this.player;
    }

    /**
     * Resets the player.
     */
    public void resetPlayer() {
        this.player = Optional.empty();
    }

    /** 
     * @return the {@link MovablePlatform} controlled by the ActivableObject
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "cannot pass a copy of the MovablePlatform, "
        + "it needs to be modified")
    public MovablePlatform getMovablePlatform() {
        return this.movablePlatform;
    }

    /** 
     * A method that moves the MovablePlatform from its starting position towards its final position.
     */
    @Override
    public void activate() {
        this.isActive = true;
        movablePlatform.move(movablePlatform.getOriginalPosition(), movablePlatform.getFinalPosition());
    }

    /**
     * A method that moves the MovablePlatform from its final position towards its starting position. 
     */
    public void deactivate() {
        this.isActive = false;
        movablePlatform.move(movablePlatform.getFinalPosition(), movablePlatform.getOriginalPosition());
    }

    /**
     * @return whether the MovablePlatform is supposed to be moving from its current position towards its final position.
     */
    @Override
    public boolean isActivated() {
        return this.isActive;
    }
}
