package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.api.Player;
//import it.unibo.dna.model.object.api.Player.Type;

/**
 * A puddle with the following characteristics:
 * - PURPLE: kills both the Angel and the Devil if they fall in it.
 * - BLUE: kills the Devil if it falls in it. Does nothing to the Angel.
 * - RED: kills the Angel if it falls in it. Does nothing to the Devil.
 */
public class Puddle extends AbstractEntity {

    /**
     * 
     * @param pos    the position of the puddle
     * @param height the height of the puddle
     * @param width  the width of the puddle
     * @param type   the type of the puddle (PURPLE,BLUE,RED)
     */
    public Puddle(final Position2d pos, final double height, final double width, final entityType type) {
        super(pos, height, width, type);
    }

    /**
     * Checks wether the player that is touching the puddle should die.
     * 
     * @param type      the type of the puddle
     * @param character the {@link Player} touching the puddle
     */
    public void killPlayer( final Player character) {
        switch (this.getType()) {
            case PURPLE_PUDDLE -> {
                /* the character that fell in the puddle dies. */
            }
            case BLUE_PUDDLE -> {
                if (character.getPlayerType().equals(Player.PlayerType.DEVIL)) {
                    // character dies
                } /* altrimenti tutto ok */
            }
            case RED_PUDDLE -> {
                if (character.getPlayerType().equals(Player.PlayerType.ANGEL)) {
                    // angel dies
                }
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
