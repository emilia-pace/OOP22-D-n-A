package it.unibo.dna;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.RectBoundingBox;
import it.unibo.dna.model.object.api.BoundingBox;
import it.unibo.dna.model.object.api.Player;
import it.unibo.dna.model.object.api.Player.PlayerType;
import it.unibo.dna.model.object.PlayerImpl;

/**
 * Class for testing collisions.
 */
public class CollisionTest {
    private static final double X = 10;
    private static final double Y = 20;
    private static final Position2d POS = new Position2d(X, Y);
    private static final double HEIGHT = 4;
    private static final double WIDTH = 4;
    private static final BoundingBox BOX = new RectBoundingBox(POS, HEIGHT, WIDTH);
    private static final int GAMEHEIGHT = 400;
    private static final int GAMEWIDTH = 400;
    private static final GameStateImpl GAME = new GameStateImpl(GAMEWIDTH, GAMEHEIGHT, 0);
    private static final Player CHARACTER = new PlayerImpl(GAME, POS, new Vector2d(0, 0), HEIGHT, WIDTH, PlayerType.ANGEL);

    /**
     * test the collision between rectangular boxes.
     */
    @Test
    protected void testRectCollision() {
        //(0,0) angolo in alto a sx
        assertTrue(BOX.isCollidingWith(POS, HEIGHT, WIDTH));
        assertTrue(BOX.isCollidingWith(POS, HEIGHT / 2, WIDTH / 2));
        assertTrue(BOX.isCollidingWith(new Position2d(X - WIDTH, Y), HEIGHT, WIDTH));
        assertTrue(BOX.isCollidingWith(new Position2d(X, Y - HEIGHT), HEIGHT, WIDTH));
        assertTrue(BOX.isCollidingWith(new Position2d(X + WIDTH, Y), HEIGHT, WIDTH));
        assertTrue(BOX.isCollidingWith(new Position2d(X, Y + HEIGHT), HEIGHT, WIDTH));

        assertFalse(BOX.isCollidingWith(new Position2d(X, 0), HEIGHT, WIDTH));
        assertFalse(BOX.isCollidingWith(new Position2d(0, Y), HEIGHT, WIDTH));
        assertFalse(BOX.isCollidingWith(new Position2d(X + WIDTH + 1, Y), HEIGHT, WIDTH));
        assertFalse(BOX.isCollidingWith(new Position2d(X, Y + HEIGHT + 1), HEIGHT, WIDTH));
        assertFalse(BOX.isCollidingWith(new Position2d(X - WIDTH - 1, Y), HEIGHT, WIDTH));
        assertFalse(BOX.isCollidingWith(new Position2d(X, Y - HEIGHT - 1), HEIGHT, WIDTH));
    }

    /**
     * test if the collision is on the left or the right side.
     */
    @Test
    protected void testSideCollision() {
        //(0,0) angolo in alto a sx
        assertTrue(BOX.sideCollision(new Position2d(X + WIDTH, Y), HEIGHT, WIDTH));
        assertTrue(BOX.sideCollision(new Position2d(X - WIDTH, Y), HEIGHT, WIDTH));
        assertTrue(BOX.sideCollision(new Position2d(X + WIDTH, Y - HEIGHT), HEIGHT, WIDTH));
        assertTrue(BOX.sideCollision(new Position2d(X - WIDTH, Y + HEIGHT), HEIGHT, WIDTH));
        assertTrue(BOX.sideCollision(new Position2d(X + WIDTH + 1, Y - 1), HEIGHT, WIDTH));

        assertFalse(BOX.sideCollision(POS, HEIGHT, WIDTH));
        assertFalse(BOX.sideCollision(new Position2d(X, Y - HEIGHT), HEIGHT, WIDTH));
        assertFalse(BOX.sideCollision(new Position2d(X, Y + HEIGHT), HEIGHT, WIDTH));
        assertFalse(BOX.sideCollision(new Position2d(X + WIDTH / 2, Y + HEIGHT), HEIGHT, WIDTH));
        assertFalse(BOX.sideCollision(new Position2d(X + WIDTH - 1, Y), HEIGHT / 2, WIDTH / 2));

    }

    /**
    * test the collision between the character and the borders.
    */
    @Test
    protected void testBordersCollision() {
        //(0,0) angolo in alto a sx
        final double eastBorderX = GAME.getBoundingBox().getWidth();
        final double westBorderX = 0;
        final double northBorderY = 0;
        final double southBorderY = GAME.getBoundingBox().getHeight();

        CHARACTER.setPosition(new Position2d(eastBorderX - WIDTH - 1, 1));
        assertFalse(GAME.checkVerticalBorders(CHARACTER.getPosition().getX(), CHARACTER.getBoundingBox().getWidth()));
        CHARACTER.setPosition(new Position2d(eastBorderX - WIDTH, 1));
        assertTrue(GAME.checkVerticalBorders(CHARACTER.getPosition().getX(), CHARACTER.getBoundingBox().getWidth()));

        CHARACTER.setPosition(new Position2d(westBorderX + 1, 1));
        assertFalse(GAME.checkVerticalBorders(CHARACTER.getPosition().getX(), CHARACTER.getBoundingBox().getWidth()));
        CHARACTER.setPosition(new Position2d(westBorderX, 1));
        assertTrue(GAME.checkVerticalBorders(CHARACTER.getPosition().getX(), CHARACTER.getBoundingBox().getWidth()));

        CHARACTER.setPosition(new Position2d(1, northBorderY + 1));
        assertFalse(GAME.checkHorizontalBorders(CHARACTER.getPosition().getY(), CHARACTER.getBoundingBox().getHeight()));
        CHARACTER.setPosition(new Position2d(1, northBorderY));
        assertTrue(GAME.checkHorizontalBorders(CHARACTER.getPosition().getY(), CHARACTER.getBoundingBox().getHeight()));

        CHARACTER.setPosition(new Position2d(1, southBorderY - HEIGHT - 1));
        assertFalse(GAME.checkHorizontalBorders(CHARACTER.getPosition().getY(), CHARACTER.getBoundingBox().getHeight()));
        CHARACTER.setPosition(new Position2d(1, southBorderY - HEIGHT));
        assertTrue(GAME.checkHorizontalBorders(CHARACTER.getPosition().getY(), CHARACTER.getBoundingBox().getHeight()));
    }
}
