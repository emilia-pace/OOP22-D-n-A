package it.unibo.dna.input;

import it.unibo.dna.input.api.Command;
import it.unibo.dna.input.api.CommandFactory;
import it.unibo.dna.model.object.api.Player.State;
import it.unibo.dna.model.object.api.Player;

/**
 * Class that implements the {@link CommandFactory} interface.
 */
public class CommandFactoryImpl implements CommandFactory {

    private Player player;

    public CommandFactoryImpl(Player p) {
        this.player = p;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command right() {
        return () -> {
            player.setVectorX(Player.StandardVelocity);
            player.getState().setY(State.STATE_RIGHT);
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command left() {
        return () -> {
            player.setVectorX(-Player.StandardVelocity);
            player.getState().setY(State.STATE_LEFT);
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command jump() {
        return () -> {
            if (!player.getState().getX().equals(State.STATE_JUMPING)) {
                player.setVectorY(-Player.JumpVelocity);
                player.getState().setX(State.STATE_JUMPING);
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command stop() {
        return () -> {
            player.setVectorX(0);
            player.getState().setY(State.STATE_STILL);
        };
    }
}
