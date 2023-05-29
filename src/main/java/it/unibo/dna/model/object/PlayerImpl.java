package it.unibo.dna.model.object;

import it.unibo.dna.GameState;
import it.unibo.dna.common.Pair;
import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.object.api.Player;

/**
 * Class that implements the {@link Player} interface.
 */
public class PlayerImpl extends AbstractMovableEntity implements Player {

    private Pair<State, State> state = new Pair<>(State.STATE_STANDING, State.STATE_STILL);
    private Type type;
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
            final double width, final Type type) {
        super(pos, vet, height, width);
        this.type = type;
        this.game = game;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<State, State> getState() {
        return this.state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type getType() {
        return this.type;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public GameState getGame() {
        return this.game;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGame(final GameState game) {
        this.game = game;
    }

}
