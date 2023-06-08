package it.unibo.dna.model.object.stillentity.impl;

import java.util.Optional;

import it.unibo.dna.model.common.Position2d;
import it.unibo.dna.model.object.entity.api.Entity;
import it.unibo.dna.model.object.player.api.Player;

/**
 * A door that can be opend only by the corresponding player.
 */
public class Door extends  AbstractEntity {

    /**
     * An enumeration that describes two states the door can be in.
     */
    public enum DoorState {
        /**
         * Describes that the door is open.
         */
        OPEN_DOOR, 
        /**
         * Describes that the door is closed.
         */
        CLOSED_DOOR;
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

    /**
     * A method that returns the player that is opening the door.
     * @return the player opening the door.
     */
    public Optional<Player> getPlayer() {
        return this.player;
    }

    /**
     * A method that empties the player.
     */
    public void resetPlayer() {
        this.player = Optional.empty();
    }

    /**
     * A method that closes the door.
     */
    public void closeDoor() {
        this.state = DoorState.CLOSED_DOOR;
    }
}
