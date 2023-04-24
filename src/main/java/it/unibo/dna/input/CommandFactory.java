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
            player.setState(State.STATE_RIGHT);
        };
    }

    public Command left() {
        return () -> {
            player.setVectorX(-Player.StandardVelocity);
            player.setState(State.STATE_LEFT);
        };
    }

    public Command jump() {
        return () -> {
            if (!player.isJumping()) {
                player.setVectorY(-Player.JumpVelocity);
                if (!player.isTurned()) {
                    player.setState(State.STATE_JUMPING);
                }
            }
        };
    }

    public Command stop() {
        return () -> {
            player.setVectorX(0);
            if (player.isJumping()) {
                player.setState(State.STATE_STANDING);
            } else {
                player.setState(State.STATE_STANDING);
            }
        };
    }
}
