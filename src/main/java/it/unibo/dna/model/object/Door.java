package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.PlayerImpl;
import it.unibo.dna.model.object.api.Entity;

public class Door implements Entity {
    public static enum doorType {
        ANGEL_DOOR, DEVIL_DOOR
    };

    private doorType type;
    private Position2d pos;
    private boolean isOpen = false;

    public Door(Position2d pos, doorType type) {
        this.pos = pos;
        this.type = type;
    }

    @Override
    public Position2d getPosition() {
        return pos;
    }

    public void openDoor(PlayerImpl c) {
        /*
         * switch(c.getType){
         * case ANGEL: if(this.type.equals(ANGEL_DOOR)){
         * isOpen=true;
         * }
         * case DEVIL: if(this.type.equals(DEVIL_DOOR)){
         * isOpen=true;
         * }
         * }
         */
    }

}
