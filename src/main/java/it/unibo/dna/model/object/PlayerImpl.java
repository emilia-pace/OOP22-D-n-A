package it.unibo.dna.model.object;

import it.unibo.dna.Game;
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
    private Game game;

    /**
     * @param pos    the position of the player
     * @param vet    the vector of the player
     * @param height the height of the player
     * @param width  the width of the player
     * @param type   the type (angel/devil) of the player
     */
    public PlayerImpl(Game game, Position2d pos, Vector2d vet, double height, double width, Type type) {
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
    public boolean equals(final Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        final Player p = (Player) obj;
        return this.type.equals(p.getType());
    }

    public Game getGame() {
        return this.game;
    }
    public void setGame(Game game){
        this.game = game;
    } 
}
