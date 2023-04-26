package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.RectBoundingBox;
import it.unibo.dna.model.object.PlayerImpl;
import it.unibo.dna.model.object.api.BoundingBox;
import it.unibo.dna.model.object.api.Entity;

public class Door implements Entity {

    public static enum doorType {
        ANGEL_DOOR, DEVIL_DOOR;
    }

    public static enum doorState {
        OPEN_DOOR, CLOSED_DOOR;
    }

    private doorType type;
    private Position2d pos;
    //private boolean isOpen = false;
    private doorState state = doorState.CLOSED_DOOR;
    private double height;
    private double width;
    private BoundingBox bbox;

    public Door(Position2d pos, doorType type,BoundingBox bbox) {
        this.pos = pos;
        this.type = type;
        this.bbox = bbox;
    }

    @Override
    public Position2d getPosition() {
        return pos;
    }

    public void openDoor(PlayerImpl c) {
        switch(c.getType()){
            case ANGEL: if(this.type.equals(doorType.ANGEL_DOOR)){
            isOpen=true;
            }
            case DEVIL: if(this.type.equals(doorType.DEVIL_DOOR)){
            isOpen=true;
            }
        }
        state=doorState.OPEN_DOOR;
    }

    @Override
    public void setPosition(Position2d pos) {
        this.pos=pos;
    }

    @Override
    public RectBoundingBox getBoundingBox() {
        return new RectBoundingBox(pos, width, height);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
