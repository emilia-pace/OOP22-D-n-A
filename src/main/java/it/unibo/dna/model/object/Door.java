package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.api.Player;

/**
 * A door that can be opend only by the corresponding player.
 */
public class Door extends  AbstractEntity {

    public static enum doorType {
        ANGEL_DOOR, DEVIL_DOOR;
    }

    public static enum doorState {
        OPEN_DOOR, CLOSED_DOOR;
    }

    private doorType type;
    private doorState state;

    /**
     * 
     * @param pos  the position of the door
     * @param height the height of the door
     * @param width the width of the door
     * @param type the type of the door (Angel door, Devil door)
     */
    public Door(Position2d pos, double height, double width, doorType type) {
        super(pos,height,width);
        this.type = type;
        this.state = doorState.CLOSED_DOOR;
    }

    public doorType getDoorType(){
        return this.type;
    }

    public doorState getDoorState() {
        return this.state;
    }

    /**
     * A method that opens the door if the correct player is standing in front of
     * it. The door can't be
     * closed after it has been opened.
     * 
     * @param c the player standing in front of the door
     */
    public void openDoor(Player c) {
        switch (c.getType()) {
            case ANGEL -> {
                if (this.type.equals(doorType.ANGEL_DOOR)) {
                    this.state = doorState.OPEN_DOOR;
                }
            }
            case DEVIL -> {
                if (this.type.equals(doorType.DEVIL_DOOR)) {
                    this.state = doorState.OPEN_DOOR;
                }
            }
        }
    }

}
