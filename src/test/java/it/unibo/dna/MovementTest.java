package it.unibo.dna;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.junit.jupiter.api.*;

import it.unibo.dna.common.Pair;
import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.input.CommandFactoryImpl;
import it.unibo.dna.input.api.CommandFactory;
import it.unibo.dna.model.object.PlayerImpl;
import it.unibo.dna.model.object.StateObserver;
import it.unibo.dna.model.object.State.StateEnum;
import it.unibo.dna.model.object.api.Player;

class MovementTest {

    private static final Position2d START_POSITION = new Position2d(0, 0);
    private static final Vector2d START_VECTOR = new Vector2d(1, 0);
    private static final double HEIGHT = 10.0;
    private static final double WIDTH = 10.0;
    private final GameState game;
    private static final PlayerImpl.PlayerType TYPE = PlayerImpl.PlayerType.ANGEL;
    private final Player player = new PlayerImpl(this.game, START_POSITION, START_VECTOR, HEIGHT, WIDTH, TYPE);
    private final CommandFactory command = new CommandFactoryImpl(this.player);
    private final StateObserver observer = new StateObserver(player.getState(), player.getPlayerType(), (int) HEIGHT,
            (int) WIDTH, 1);

    /**
     * 
     */
    @Test
    void testPlayerUpdate() {
        final Position2d expectedPosition = new Position2d(0, 0).sum(START_VECTOR);
        this.player.update();
        assertEquals(expectedPosition, this.player.getPosition());
    }

    /**
     * 
     */
    @Test
    void testCommand() {
        final Vector2d expectedVectorRight = new Vector2d(Player.STANDARDVELOCITY, 0);
        final Pair<StateEnum, StateEnum> expectedStateRight = new Pair<>(StateEnum.STATE_STANDING,
                StateEnum.STATE_RIGHT);
        this.command.right().execute();
        assertEquals(expectedVectorRight, this.player.getVector());
        assertEquals(expectedStateRight, this.player.getState().getState());

        final Vector2d expectedVectorLeft = new Vector2d(-Player.STANDARDVELOCITY, 0);
        final Pair<StateEnum, StateEnum> expectedStateLeft = new Pair<>(StateEnum.STATE_STANDING,
                StateEnum.STATE_LEFT);
        this.command.left().execute();
        assertEquals(expectedVectorLeft, this.player.getVector());
        assertEquals(expectedStateLeft, this.player.getState().getState());

        final Vector2d expectedVectorJump = new Vector2d(0, -Player.JUMPVELOCITY);
        final Pair<StateEnum, StateEnum> expectedStateJump = new Pair<>(StateEnum.STATE_JUMPING,
                StateEnum.STATE_STILL);
        this.command.stop().execute();
        this.command.jump().execute();
        assertEquals(expectedVectorJump, this.player.getVector());
        assertEquals(expectedStateJump, this.player.getState().getState());

    }
}