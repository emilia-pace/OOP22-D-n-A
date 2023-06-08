package it.unibo.dna.input;

import it.unibo.dna.GameEngine;
import it.unibo.dna.input.api.Command;
import it.unibo.dna.input.api.CommandFactory;
import it.unibo.dna.model.object.player.State.StateEnum;
import it.unibo.dna.model.object.player.api.Player;
import it.unibo.dna.model.object.player.api.Player.PlayerType;

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
    @SuppressWarnings("mutable")
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
            this.player.setStateY(StateEnum.STATE_RIGHT);
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command left() {
        return () -> {
            this.player.setVectorX(-Player.STANDARDVELOCITY);
            this.player.setStateY(StateEnum.STATE_LEFT);
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command jump() {
        return () -> {
            if (!this.player.getStateCopy().getX().equals(StateEnum.STATE_JUMPING)) {
                this.player.setVectorY(-Player.JUMPVELOCITY);
                this.player.setStateX(StateEnum.STATE_JUMPING);
                if (this.player.getPlayerType().equals(PlayerType.ANGEL)) {
                    GameEngine.playSound("angel_jump");
                } else {
                    GameEngine.playSound("devil_jump");
                }

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
            this.player.setStateY(StateEnum.STATE_STILL);
        };
    }
}
