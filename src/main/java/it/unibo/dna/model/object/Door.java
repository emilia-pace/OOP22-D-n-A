package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.api.Player;

/**
 * A door that can be opend only by the corresponding player.
 */
public class Door extends  AbstractEntity {

    public static enum doorState {
        OPEN_DOOR, CLOSED_DOOR;
    }

    private doorState state;

    /**
     * 
     * @param position  the position of the Door
     * @param height the height of the Door
     * @param width the width of the Door
     * @param type the type of the Door (Angel door, Devil door)
     */
    public Door(Position2d position, double height, double width, Entity.entityType type) {
        super(position,height,width,type);
        this.state = doorState.CLOSED_DOOR;
    }

    /**
     * 
     * @return the state of the door (open, closed)
     */
    public doorState getDoorState() {
        return this.state;
    }

    /**
     * A method that opens the door if the correct player is standing in front of
     * it. The door can't be closed after it has been opened.
     * 
     * @param player the player standing in front of the door
     */
    public void openDoor(Player player) {
        switch (player.getPlayerType()) {
            case ANGEL -> {
                if (this.getType().equals(entityType.ANGEL_DOOR)) {
                    this.state = doorState.OPEN_DOOR;
                }
            }
            case DEVIL -> {
                if (this.getType().equals(entityType.DEVIL_DOOR)) {
                    this.state = doorState.OPEN_DOOR;
                }
            }
            default -> throw new IllegalArgumentException();
        }
    }

}
