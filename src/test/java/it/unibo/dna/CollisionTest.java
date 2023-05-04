package it.unibo.dna;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.RectBoundingBox;
import it.unibo.dna.model.object.PlayerImpl;
import it.unibo.dna.model.object.PlayerImpl.Type;
import it.unibo.dna.model.object.api.BoundingBox;
import it.unibo.dna.model.object.api.Player;

public class CollisionTest {

    private static final Position2d POS = new Position2d(0, 0);
    private static final double HEIGHT = 4;
    private static final double WIDTH = 4;
    private static final BoundingBox BOX = new RectBoundingBox(POS, HEIGHT, WIDTH);
    private static final double DIM = 100;
    /*private static final Game GAME;
    private static final Player CHARACTER = new PlayerImpl(POS, new Vector2d(0, 0), HEIGHT, WIDTH, Type.ANGEL);*/

    /**
     * test the collision between rectangular boxes
     */
    @Test
    public void testRectCollision() {
        //(0,0) angolo in alto a sx
        assertTrue(BOX.isCollidingWith(POS, HEIGHT, WIDTH));
        assertTrue(BOX.isCollidingWith(POS, HEIGHT / 2, WIDTH / 2));
        assertTrue(BOX.isCollidingWith(new Position2d(WIDTH, HEIGHT), HEIGHT, WIDTH));
        assertTrue(BOX.isCollidingWith(new Position2d(WIDTH, 0), HEIGHT, WIDTH));
        assertTrue(BOX.isCollidingWith(new Position2d(0, HEIGHT), HEIGHT, WIDTH));

        assertFalse(BOX.isCollidingWith(new Position2d(WIDTH + 1, HEIGHT + 1), HEIGHT, WIDTH));
        assertFalse(BOX.isCollidingWith(new Position2d(WIDTH + 1, 0), HEIGHT / 2, WIDTH / 2));
        

    }

    /**
     * test the collision between the character and the borders
     
    @Test
    public void testBordersCollision() {
        //(0,0) angolo in alto a sx
        final double eastBorderX = GAME.getBoundingBox().getWidth();
        final double westBorderX = 0;
        final double northBorderY = 0;
        final double southBorderY = GAME.getBoundingBox().getHeight();

        CHARACTER.setPosition(new Position2d(eastBorderX - WIDTH - 1, 1));
        assertFalse(GAME.checkBorders(CHARACTER));
        CHARACTER.setPosition(new Position2d(eastBorderX - WIDTH, 1));
        assertTrue(GAME.checkBorders(CHARACTER));

        CHARACTER.setPosition(new Position2d(westBorderX + 1, 1));
        assertFalse(GAME.checkBorders(CHARACTER));
        CHARACTER.setPosition(new Position2d(westBorderX, 1));
        assertTrue(GAME.checkBorders(CHARACTER));

        CHARACTER.setPosition(new Position2d(1, northBorderY + 1));
        assertFalse(GAME.checkBorders(CHARACTER));
        CHARACTER.setPosition(new Position2d(1, northBorderY));
        assertTrue(GAME.checkBorders(CHARACTER));

        CHARACTER.setPosition(new Position2d(1, southBorderY + HEIGHT - 1));
        assertFalse(GAME.checkBorders(CHARACTER));
        CHARACTER.setPosition(new Position2d(1, southBorderY - HEIGHT));
        assertTrue(GAME.checkBorders(CHARACTER));
    }*/
}
