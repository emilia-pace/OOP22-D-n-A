package it.unibo.dna.input;

import it.unibo.dna.input.api.Command;
import it.unibo.dna.input.api.CommandFactory;
import it.unibo.dna.model.events.impl.EventFactoryImpl;
import it.unibo.dna.model.object.player.State.StateEnum;
import it.unibo.dna.model.object.player.api.Player;

/**
 * Class that implements the {@link CommandFactory} interface.
 */
public class CommandFactoryImpl implements CommandFactory {

    private final Player player;

    /**
     * Creates a new CommandFactoryImpl instance with the specified player.
     *
     * @param player the player
     */
    public CommandFactoryImpl(final Player player) {
        this.player = player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command right() {
        return () -> {
            this.player.setVectorX(Player.STANDARDVELOCITY);
            this.player.getState().setStateY(StateEnum.STATE_RIGHT);
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command left() {
        return () -> {
            this.player.setVectorX(-Player.STANDARDVELOCITY);
            this.player.getState().setStateY(StateEnum.STATE_LEFT);
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command jump() {
        return () -> {
            if (!this.player.getState().getX().equals(StateEnum.STATE_JUMPING)) {
                this.player.setVectorY(-Player.JUMPVELOCITY);
                this.player.getState().setStateX(StateEnum.STATE_JUMPING);
                String s = (this.player.getPlayerType().equals(Player.PlayerType.ANGEL)) ? "Angel_audio"
                        : "Devil_audio";
                this.player.getGameEventQueue()
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
            this.player.getState().setStateY(StateEnum.STATE_STILL);
        };
    }
}
