package it.unibo.dna.model.command.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.dna.controller.core.GameEngine;
import it.unibo.dna.model.command.api.Command;
import it.unibo.dna.model.command.api.CommandFactory;
import it.unibo.dna.model.object.player.api.Player;
import it.unibo.dna.model.object.player.api.Player.PlayerType;
import it.unibo.dna.model.object.player.impl.State.StateEnum;

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
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "The player field is intentionally exposed to allow initialization with the current player.")
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
                    GameEngine.playSound("Angel_audio");
                } else {
                    GameEngine.playSound("Devil_audio");
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
