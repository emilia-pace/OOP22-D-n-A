package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.PlayerImpl.Type;
import it.unibo.dna.model.object.api.BoundingBox;
import it.unibo.dna.model.object.api.Player;

/**
 * A puddle with the following characteristics:
 * - PURPLE: kills both the Angel and the Devil if they fall in it.
 * - BLUE: kills the Devil if it falls in it. Does nothing to the Angel.
 * - RED: kills the Angel if it falls in it. Does nothing to the Devil.
 */
public class Puddle extends AbstractEntity {

    public static enum puddleType {
        PURPLE, BLUE, RED
    }

    private Position2d pos;
    private puddleType type;
    private BoundingBox box;

    /**
     * 
     * @param pos the position of the puddle
     * @param height the height of the puddle
     * @param width the width of the puddle
     * @param type the type of the puddle (PURPLE,BLUE,RED)
     * @param box the {@link BoundingBox} of the puddle
     */
    public Puddle(final Position2d pos, final double height, final double width, final puddleType type, final BoundingBox box) {
        super(pos, height, width);
        this.type=type;
        this.box=box;
    }

    /**
     * 
     * @return the type of the puddle
     */
    public puddleType getPuddleType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position2d getPosition() {
        return this.pos;
    }

    /**
     * Checks wether the player that is touching the puddle should die.
     * @param type the type of the puddle
     * @param character the {@link Player} touching the puddle
     */
    public void checkForMatch(final puddleType type, final Player character) {
        switch (type) {
            case PURPLE -> {
                        /*the character that fell in the puddle dies.*/ 
                        }
            case BLUE -> {
                        if(character.getType().equals(Type.DEVIL)){
                        //character dies
                        }/*altrimenti tutto ok*/ 
                    }
            case RED -> {
                        if(character.getType().equals(Type.ANGEL)){
                        //angel dies
                        }
                    }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(Position2d pos) {
        this.pos=pos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BoundingBox getBoundingBox() {
        return this.box;
    }

}
