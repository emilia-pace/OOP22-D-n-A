package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.RectBoundingBox;
import it.unibo.dna.model.object.PlayerImpl.Type;
import it.unibo.dna.model.object.api.BoundingBox;
import it.unibo.dna.model.object.api.Entity;

public class Puddle extends AbstractEntity {

    public static enum puddleType {
        PURPLE, BLUE, RED
    }

    private Position2d pos;
    private puddleType type;
    private double height;
    private double width;
    //private BoundingBox bbox;

    public Puddle(Position2d pos, double height, double width,puddleType type) {
        super(pos, height, width);
        this.type=type;
    }

    public puddleType getPuddleType() {
        return type;
    }

    @Override
    public Position2d getPosition() {
        return pos;
    }

    public void checkForMatch(puddleType type, PlayerImpl character) {
        switch (type) {
            case PURPLE: // the character that fell in the puddle dies.
            case BLUE: if(character.getType().equals(Type.DEVIL)){
                        //character dies
                        }//altrimenti tutto ok
            case RED: if(character.getType().equals(Type.ANGEL)){
                        //angel dies
                        }
        }
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
