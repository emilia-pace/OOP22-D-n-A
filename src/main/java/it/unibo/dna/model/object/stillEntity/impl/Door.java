package it.unibo.dna.model.object.stillEntity.impl;

import java.util.Optional;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.player.api.Player;

/**
 * A door that can be opend only by the corresponding player.
 */
public class Door extends  AbstractEntity {

    public enum DoorState {
        OPEN_DOOR, CLOSED_DOOR;
    }

    private DoorState state;
    private Optional<Player> player = Optional.empty();

    /**
     * 
     * @param position  the position of the Door
     * @param height the height of the Door
     * @param width the width of the Door
     * @param type the type of the Door (Angel door, Devil door)
     */
    public Door(final Position2d position, final double height, final double width, final Entity.EntityType type) {
        super(position, height, width, type);
        this.state = DoorState.CLOSED_DOOR;
    }

    /**
     * 
     * @return the state of the door (open, closed)
     */
    public DoorState getDoorState() {
        return this.state;
    }

    /**
     * A method that opens the door if the correct player is standing in front of
     * it. The door can't be closed after it has been opened.
     * 
     * @param player the player standing in front of the door
     */
    public void openDoor(final Player player) {
        if (this.state.equals(DoorState.CLOSED_DOOR)) {
            switch (player.getPlayerType()) {
                case ANGEL -> {
                    if (this.getType().equals(EntityType.ANGEL_DOOR)) {
                        this.state = DoorState.OPEN_DOOR;
                        this.player = Optional.of(player);
                    }
                }
                case DEVIL -> {
                    if (this.getType().equals(EntityType.DEVIL_DOOR)) {
                        this.state = DoorState.OPEN_DOOR;
                        this.player = Optional.of(player);
                    }
                }
                default -> throw new IllegalArgumentException();
            }
        }
    }

    public Optional<Player> getPlayer() {
        return this.player;
    }

    public void resetPlayer() {
        this.player = Optional.empty();
    }

    public void closeDoor() {
        this.state = DoorState.CLOSED_DOOR;
    }
}
