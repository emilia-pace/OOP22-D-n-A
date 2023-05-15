package it.unibo.dna;

import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;

import org.junit.jupiter.api.*;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.input.CommandFactoryImpl;
import it.unibo.dna.input.api.CommandFactory;
import it.unibo.dna.model.object.PlayerImpl;
import it.unibo.dna.model.object.PlayerImpl.State;
import it.unibo.dna.model.object.api.Player;

class MovementTest {

    private static final Position2d START_POSITION = new Position2d(0, 0);
    private static final Vector2d START_VECTOR = new Vector2d(1, 0);
    private static final double HEIGHT = 10.0;
    private static final double WIDTH = 10.0;
    private static final PlayerImpl.Type TYPE = PlayerImpl.Type.ANGEL;
    private final Player player = new PlayerImpl(START_POSITION, START_VECTOR, HEIGHT, WIDTH, TYPE);
    private final CommandFactory command = new CommandFactoryImpl(this.player);

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
        final Vector2d expectedVectorRight = new Vector2d(Player.StandardVelocity, 0);
        final PlayerImpl.State expectedStateRight = new Pair<>(PlayerImpl.State.STATE_STANDING,
                PlayerImpl.State.STATE_RIGHT);
        this.command.right();
        assertEquals(expectedVectorRight, this.player.getVector());
        assertEquals(expectedStateRight, this.player.getState());

        final Vector2d expectedVectorLeft = new Vector2d(-Player.StandardVelocity, 0);
        final PlayerImpl.State expectedStateLeft = new Pair<>(PlayerImpl.State.STATE_STANDING,
                PlayerImpl.State.STATE_LEFT);
        this.command.left();
        assertEquals(expectedVectorLeft, this.player.getVector());
        assertEquals(expectedStateLeft, this.player.getState());

        final Vector2d expectedVectorJump = new Vector2d(0, -Player.JumpVelocity);
        final PlayerImpl.State expectedStateJump = new Pair<>(PlayerImpl.State.STATE_JUMPING,
                PlayerImpl.State.STATE_STILL);
        this.command.stop();
        this.command.jump();
        assertEquals(expectedVectorJump, this.player.getVector());
        assertEquals(expectedStateJump, this.player.getState());

    }
}