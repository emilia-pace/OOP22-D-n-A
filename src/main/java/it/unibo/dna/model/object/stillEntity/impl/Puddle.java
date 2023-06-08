package it.unibo.dna.model.object.stillentity.impl;


import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.player.api.Player;

/**
 * A puddle with the following characteristics:
 * - PURPLE: kills both the Angel and the Devil if they fall in it.
 * - BLUE: kills the Devil if it falls in it. Does nothing to the Angel.
 * - RED: kills the Angel if it falls in it. Does nothing to the Devil.
 */
public class Puddle extends AbstractEntity {

    /**
     * 
     * @param position    the position of the puddle
     * @param height the height of the puddle
     * @param width  the width of the puddle
     * @param type   the type of the puddle (PURPLE,BLUE,RED)
     */
    public Puddle(final Position2d position, final double height, final double width, final EntityType type) {
        super(position, height, width, type);
    }

    /**
     * Checks whether there should be a game over.
     * 
     * @param character the {@link Player} touching the puddle
     * @return True if there should be a game over, otherwise returns False
     */
    public boolean killPlayer(final Player character) {
        switch (this.getType()) {
            case PURPLE_PUDDLE -> {
                return true;
            }
            case BLUE_PUDDLE -> {
                if (character.getPlayerType().equals(Player.PlayerType.DEVIL)) {
                    return true;
                }
            }
            case RED_PUDDLE -> {
                if (character.getPlayerType().equals(Player.PlayerType.ANGEL)) {      
                    return true;
                }
            }
            default -> throw new IllegalArgumentException();
        }
        return false;
    }
}
