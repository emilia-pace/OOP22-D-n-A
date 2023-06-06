package it.unibo.dna.model.object.player;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.events.impl.EventQueue;
import it.unibo.dna.model.game.api.GameState;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.movableEntity.AbstractMovableEntity;
import it.unibo.dna.model.object.player.State.StateEnum;
import it.unibo.dna.model.object.player.api.Player;

/**
 * Class that implements the {@link Player} interface.
 */
public class PlayerImpl extends AbstractMovableEntity implements Player {

    private PlayerType playerType;
    private State playerState = new State();

    /**
     * Constructs a new PlayerImpl object.
     *
     * @param pos    the position of the player
     * @param vet    the vector of the player
     * @param height the height of the player
     * @param width  the width of the player
     * @param type   the type (angel/devil) of the player
     */
    public PlayerImpl(final Position2d pos, final Vector2d vet, final double height,
            final double width, final PlayerType type) {
        super(pos, vet, height, width, Entity.EntityType.PLAYER);
        this.playerType = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        super.update();
        this.playerState.update();
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

    @Override
    public void setStateX(StateEnum stateX) {
        this.playerState.setState(stateX, this.getState().getY());
    }

    @Override
    public void setStateY(StateEnum stateY) {
        this.playerState.setState(this.getState().getX(), stateY);
    }

}
