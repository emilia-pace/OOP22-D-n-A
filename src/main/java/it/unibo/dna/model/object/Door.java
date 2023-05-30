package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.api.Player;

/**
 * A door that can be opend only by the corresponding player.
 */
public class Door extends  AbstractEntity {

    /*public static enum doorType {
        ANGEL_DOOR, DEVIL_DOOR;
    }*/

    public static enum doorState {
        OPEN_DOOR, CLOSED_DOOR;
    }

    //private doorType type;
    private doorState state;

    /**
     * 
     * @param pos  the position of the door
     * @param height the height of the door
     * @param width the width of the door
     * @param type the type of the door (Angel door, Devil door)
     */
    public Door(Position2d pos, double height, double width, Entity.entityType type) {
        super(pos,height,width,type);
        //this.type = type;
        this.state = doorState.CLOSED_DOOR;
    }

    /**
     * 
     * @return the type of the door (angel door, devil door)
    
    public doorType getDoorType(){
        return this.type;
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
     * it. The door can't be
     * closed after it has been opened.
     * 
     * @param c the player standing in front of the door
     */
    public void openDoor(Player c) {
        switch (c.getPlayerType()) {
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
