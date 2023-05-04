package it.unibo.dna.input;

import it.unibo.dna.input.api.Command;
import it.unibo.dna.model.object.PlayerImpl.State;
import it.unibo.dna.model.object.api.Player;

public class CommandFactory {

    private Player player;

    public CommandFactory(Player p) {
        this.player = p;
    }

    public Command right() {
        return () -> {
            player.setVectorX(Player.StandardVelocity);
            player.setStateY(State.STATE_RIGHT);
        };
    }

    public Command left() {
        return () -> {
            player.setVectorX(-Player.StandardVelocity);
            player.setStateY(State.STATE_LEFT);
        };
    }

    public Command jump() {
        return () -> {
            if (!player.getState().getX().equals(State.STATE_JUMPING)) {
                player.setVectorY(-Player.JumpVelocity);
                player.setStateX(State.STATE_JUMPING);
            }
        };
    }

    public Command stop() {
        return () -> {
            player.setVectorX(0);
            player.setStateY(State.STATE_STILL);
        };
    }
}
