package it.unibo.dna.model.object.player.impl;

import it.unibo.dna.model.common.Position2d;
import it.unibo.dna.model.common.Vector2d;
import it.unibo.dna.model.object.entity.api.Entity;
import it.unibo.dna.model.object.movableentity.impl.AbstractMovableEntity;
import it.unibo.dna.model.object.player.api.Player;
import it.unibo.dna.model.object.player.impl.State.StateEnum;

/**
 * Class that implements the {@link Player} interface.
 */
public class PlayerImpl extends AbstractMovableEntity implements Player {

    private final PlayerType playerType;
    private final State playerState = new State();

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
    @SuppressWarnings(value = "MVEI")
    public State getState() {
        return this.playerState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State getStateCopy() {
        return new State(this.playerState.getX(), this.playerState.getY());
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
    public void setStateX(final StateEnum stateX) {
        this.playerState.setState(stateX, this.getState().getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStateY(final StateEnum stateY) {
        this.playerState.setState(this.getState().getX(), stateY);
    }

}
