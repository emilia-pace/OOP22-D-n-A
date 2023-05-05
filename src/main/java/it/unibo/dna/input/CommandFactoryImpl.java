package it.unibo.dna.input;

import it.unibo.dna.input.api.Command;
import it.unibo.dna.input.api.CommandFactory;
import it.unibo.dna.model.object.PlayerImpl.State;
import it.unibo.dna.model.object.api.Player;

public class CommandFactoryImpl implements CommandFactory {

    private Player player;

    public CommandFactoryImpl(Player p) {
        this.player = p;
    }

    @Override
    public Command right() {
        return () -> {
            player.setVectorX(Player.StandardVelocity);
            player.getState().setY(State.STATE_RIGHT);
        };
    }

    @Override
    public Command left() {
        return () -> {
            player.setVectorX(-Player.StandardVelocity);
            player.getState().setY(State.STATE_LEFT);
        };
    }

    @Override
    public Command jump() {
        return () -> {
            if (!player.getState().getX().equals(State.STATE_JUMPING)) {
                player.setVectorY(-Player.JumpVelocity);
                player.getState().setX(State.STATE_JUMPING);
            }
        };
    }

    @Override
    public Command stop() {
        return () -> {
            player.setVectorX(0);
            player.getState().setY(State.STATE_STILL);
        };
    }
}
