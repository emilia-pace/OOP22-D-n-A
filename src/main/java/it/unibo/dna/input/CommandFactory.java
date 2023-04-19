package it.unibo.dna.input;

import it.unibo.dna.input.api.Command;
import it.unibo.dna.model.object.api.Player;

public class CommandFactory {

    private Player player;

    public CommandFactory(Player p) {
        this.player = p;
    }

    public Command right() {
        return new Command() {

            @Override
            public void execute() {
                player.setVectorX(Player.StandardVelocity);
            }

        };
    }

    public Command left() {
        return new Command() {

            @Override
            public void execute() {
                player.setVectorX(-Player.StandardVelocity);
            }

        };
    }

    public Command jump() {
        return new Command() {

            @Override
            public void execute() {
                player.setVectorY(-Player.JumpVelocity);
            }

        };
    }

    public Command stop() {
        return new Command() {

            @Override
            public void execute() {
                player.setVectorX(0);
            }

        };
    }

}
