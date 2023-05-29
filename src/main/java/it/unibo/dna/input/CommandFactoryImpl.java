package it.unibo.dna.input;

import it.unibo.dna.input.api.Command;
import it.unibo.dna.input.api.CommandFactory;
import it.unibo.dna.model.object.api.Player.State;
import it.unibo.dna.model.EventFactoryImpl;
import it.unibo.dna.model.object.api.Player;

/**
 * Class that implements the {@link CommandFactory} interface.
 */
public class CommandFactoryImpl implements CommandFactory {

    private Player player;

    /**
     * @param p the player 
     */
    public CommandFactoryImpl(final Player p) {
        this.player = p;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command right() {
        return () -> {
            this.player.setVectorX(Player.STANDARDVELOCITY);
            this.player.getState().setY(State.STATE_RIGHT);
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command left() {
        return () -> {
            this.player.setVectorX(-Player.STANDARDVELOCITY);
            this.player.getState().setY(State.STATE_LEFT);
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command jump() {
        return () -> {
            if (!this.player.getState().getX().equals(State.STATE_JUMPING)) {
                this.player.setVectorY(-Player.JUMPVELOCITY);
                this.player.getState().setX(State.STATE_JUMPING);
                String s = (this.player.getType().equals(Player.Type.ANGEL)) ? "Angel_audio" : "Devil_audio";
                this.player.getGame().getEventQueue()
                        .addEvent(
                                new EventFactoryImpl().soundEvent(s));
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command stop() {
        return () -> {
            this.player.setVectorX(0);
            this.player.getState().setY(State.STATE_STILL);
        };
    }
}
