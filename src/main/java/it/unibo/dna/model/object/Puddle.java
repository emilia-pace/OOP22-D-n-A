package it.unibo.dna.model.object;

import java.awt.Menu;
import java.util.Optional;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.graphics.MenuFactory;
import it.unibo.dna.graphics.MenuFactoryImpl;
import it.unibo.dna.model.object.api.Player;
//import it.unibo.dna.model.object.api.Player.Type;

/**
 * A puddle with the following characteristics:
 * - PURPLE: kills both the Angel and the Devil if they fall in it.
 * - BLUE: kills the Devil if it falls in it. Does nothing to the Angel.
 * - RED: kills the Angel if it falls in it. Does nothing to the Devil.
 */
public class Puddle extends AbstractEntity {

    private MenuFactory menuFactory = new MenuFactoryImpl();
    private Optional<Player> player = Optional.empty();

    /**
     * 
     * @param position    the position of the puddle
     * @param height the height of the puddle
     * @param width  the width of the puddle
     * @param type   the type of the puddle (PURPLE,BLUE,RED)
     */
    public Puddle(final Position2d position, final double height, final double width, final entityType type) {
        super(position, height, width, type);
    }

    /**
     * Checks whether there should be a game over.
     * 
     * @param type      the type of the puddle
     * @param character the {@link Player} touching the puddle
     */
    public void killPlayer( final Player character) {
        if(player.isEmpty()){
            player = Optional.of(character);
            switch (this.getType()) {
                case PURPLE_PUDDLE -> {
                    menuFactory.gameOverMenu().createMenuFrame();
                }
                case BLUE_PUDDLE -> {
                    if (character.getPlayerType().equals(Player.PlayerType.DEVIL)) {
                        menuFactory.gameOverMenu().createMenuFrame();;
                    }
                }
                case RED_PUDDLE -> {
                    if (character.getPlayerType().equals(Player.PlayerType.ANGEL)) {
                        menuFactory.gameOverMenu();
                    }
                }
                default -> throw new IllegalArgumentException();
            }
        }
    }
}
