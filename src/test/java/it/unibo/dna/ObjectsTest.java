package it.unibo.dna;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.RectBoundingBox;
import it.unibo.dna.model.object.MovablePlatform;
import it.unibo.dna.model.object.Platform;
import it.unibo.dna.model.object.PlayerImpl;
import it.unibo.dna.model.object.api.BoundingBox;
import it.unibo.dna.model.object.api.Player;

public class ObjectsTest {
    private static final double X = 10;
    private static final double Y = 20;
    private static final Position2d POS = new Position2d(X, Y);
    private static final double HEIGHT = 4;
    private static final double WIDTH = 4;
    private static final BoundingBox BOX = new RectBoundingBox(POS, HEIGHT, WIDTH);
    private static final int GAMEHEIGHT = 400;
    private static final int GAMEWIDTH = 400;
    private static final GameState GAME = new GameStateImpl(GAMEWIDTH, GAMEHEIGHT, 0);
    private static final Player CHARACTER = new PlayerImpl(GAME, POS, new Vector2d(0, 0), HEIGHT, WIDTH, PlayerImpl.PlayerType.ANGEL);
    private static final MovablePlatform PLATFORM = new MovablePlatform(POS, new Vector2d(0, 0), HEIGHT, WIDTH, POS);

    @Test
    public void testMovablePlatformMethods(){
        Position2d finalPos = new Position2d(X+100, Y+100); //test vettore 1,1
        PLATFORM.setFinalPosition(finalPos);
        PLATFORM.findVector(PLATFORM.getOriginalPos(),PLATFORM.getFinalPosition());
        assertTrue(PLATFORM.getVector().equals(new Vector2d(+1, +1)));

        finalPos = new Position2d(X+100, Y-100);//test vettore 1,-1
        PLATFORM.setFinalPosition(finalPos);
        PLATFORM.findVector(PLATFORM.getOriginalPos(),PLATFORM.getFinalPosition());
        assertTrue(PLATFORM.getVector().equals(new Vector2d(+1, -1)));

        finalPos = new Position2d(X-100, Y+100);//test vettore -1,1
        PLATFORM.setFinalPosition(finalPos);
        PLATFORM.findVector(PLATFORM.getOriginalPos(),PLATFORM.getFinalPosition());
        assertTrue(PLATFORM.getVector().equals(new Vector2d(-1, +1)));

        finalPos = new Position2d(X-100, Y-100);//test vettore -1,-1
        PLATFORM.setFinalPosition(finalPos);
        PLATFORM.findVector(PLATFORM.getOriginalPos(),PLATFORM.getFinalPosition());
        assertTrue(PLATFORM.getVector().equals(new Vector2d(-1, -1)));

        PLATFORM.setPosition(PLATFORM.getFinalPosition());
        PLATFORM.setLastPosition();
        PLATFORM.findLimit();
        assertTrue(PLATFORM.getPosition().equals(PLATFORM.getFinalPosition()));
    }

    @Test
    public void testDoor() {
            
    }
}