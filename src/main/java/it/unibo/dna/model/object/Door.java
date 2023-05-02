package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.RectBoundingBox;
import it.unibo.dna.model.object.api.BoundingBox;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.api.Player;

/**
 * A door that can be opend only by the corresponding player.
 */
public class Door implements Entity {

    public static enum doorType {
        ANGEL_DOOR, DEVIL_DOOR;
    }

    public static enum doorState {
        OPEN_DOOR, CLOSED_DOOR;
    }

    private doorType type;
    private Position2d pos;
    private doorState state = doorState.CLOSED_DOOR;
    private BoundingBox box;

    /**
     * 
     * @param pos  the position of the door
     * @param type the type of the door (Angel door, Devil door)
     * @param box  the {@link BoundingBox} of the door
     */
    public Door(Position2d pos, doorType type, double height, double width) {
        this.pos = pos;
        this.type = type;
        this.box = new RectBoundingBox(pos, height, width);
    }

    @Override
    public Position2d getPosition() {
        return pos;
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

    /**
     * A setter for the position of the door.
     */
    @Override
    public void setPosition(Position2d pos) {
        this.pos = pos;
    }

    /**
     * @return the position of the door.
     */
    @Override
    public BoundingBox getBoundingBox() {
        return this.box;
    }

}
