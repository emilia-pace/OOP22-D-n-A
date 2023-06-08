package it.unibo.dna;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.dna.common.Pair;
import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.input.api.CommandFactory;
import it.unibo.dna.input.impl.CommandFactoryImpl;
import it.unibo.dna.model.object.player.api.Player;
import it.unibo.dna.model.object.player.impl.PlayerImpl;
import it.unibo.dna.model.object.player.impl.State.StateEnum;

/**
 * Test class for testing the movement of the player.
 */
class MovementTest {

    private static final Position2d START_POSITION = new Position2d(0, 0);
    private static final Vector2d START_VECTOR = new Vector2d(1, 0);
    private static final double HEIGHT = 10.0;
    private static final double WIDTH = 10.0;
    private static final PlayerImpl.PlayerType TYPE = PlayerImpl.PlayerType.ANGEL;
    private final Player player = new PlayerImpl(START_POSITION, START_VECTOR, HEIGHT, WIDTH, TYPE);
    private final CommandFactory command = new CommandFactoryImpl(this.player);

    /**
     * Tests the update method of the player.
     */
    @Test
    void testPlayerUpdate() {
        final Position2d expectedPosition = new Position2d(0, 0).sum(START_VECTOR);
        this.player.update();
        assertEquals(expectedPosition, this.player.getPosition());
    }

    /**
     * Tests the commands of the player.
     * The commands change the vector and the state of the player.
     */
    @Test
    void testCommand() {
        /**
         * Test the command "right"
         */
        final Vector2d expectedVectorRight = new Vector2d(Player.STANDARDVELOCITY, 0);
        final Pair<StateEnum, StateEnum> expectedStateRight = new Pair<>(StateEnum.STATE_STANDING,
                StateEnum.STATE_RIGHT);
        this.command.right().execute();
        assertEquals(expectedVectorRight, this.player.getVector());
        assertEquals(expectedStateRight, this.player.getStateCopy().getPairState());

        /**
         * Test the command "left"
         */
        final Vector2d expectedVectorLeft = new Vector2d(-Player.STANDARDVELOCITY, 0);
        final Pair<StateEnum, StateEnum> expectedStateLeft = new Pair<>(StateEnum.STATE_STANDING,
                StateEnum.STATE_LEFT);
        this.command.left().execute();
        assertEquals(expectedVectorLeft, this.player.getVector());
        assertEquals(expectedStateLeft, this.player.getStateCopy().getPairState());

        /**
         * Test the command "jump"
         */
        final Vector2d expectedVectorJump = new Vector2d(0, -Player.JUMPVELOCITY);
        final Pair<StateEnum, StateEnum> expectedStateJump = new Pair<>(StateEnum.STATE_JUMPING,
                StateEnum.STATE_STILL);
        this.command.stop().execute();
        this.command.jump().execute();
        assertEquals(expectedVectorJump, this.player.getVector());
        assertEquals(expectedStateJump, this.player.getStateCopy().getPairState());

    }
}
