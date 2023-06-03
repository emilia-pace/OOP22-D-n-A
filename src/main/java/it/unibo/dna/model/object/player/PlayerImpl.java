package it.unibo.dna.model.object.player;

import it.unibo.dna.GameState;
import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.EventQueue;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.movableEntity.AbstractMovableEntity;
import it.unibo.dna.model.object.player.api.Player;

/**
 * Class that implements the {@link Player} interface.
 */
public class PlayerImpl extends AbstractMovableEntity implements Player {

    private PlayerType playerType;
    private State playerState = new State();
    private GameState game;

    /**
     * @param game   the game of the player
     * @param pos    the position of the player
     * @param vet    the vector of the player
     * @param height the height of the player
     * @param width  the width of the player
     * @param type   the type (angel/devil) of the player
     */
    public PlayerImpl(final GameState game, final Position2d pos, final Vector2d vet, final double height,
            final double width, final PlayerType type) {
        super(pos, vet, height, width, Entity.entityType.PLAYER);
        this.playerType = type;
        this.game = game;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State getState() {
        return this.playerState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerType getPlayerType() {
        return this.playerType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EventQueue getGameEventQueue() {
        return this.game.getEventQueue();
    }

    @Override
    public void setGame(GameState game) {
        this.game = game;
    }

}
