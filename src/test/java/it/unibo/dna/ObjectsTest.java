package it.unibo.dna;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.*;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.RectBoundingBox;
import it.unibo.dna.model.object.ActivableObjectImpl;
import it.unibo.dna.model.object.Door;
import it.unibo.dna.model.object.EntityFactoryImpl;
import it.unibo.dna.model.object.MovablePlatform;
import it.unibo.dna.model.object.api.BoundingBox;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.player.PlayerImpl;
import it.unibo.dna.model.object.player.api.Player;

public class ObjectsTest {
    private static final EntityFactoryImpl ENTITYFACTORY = new EntityFactoryImpl();
    private static final double X = 10;
    private static final double Y = 20;
    private static final Position2d POS = new Position2d(X, Y);
    private static final Position2d POS2 = new Position2d(X+X, Y+Y);
    private static final double HEIGHT = 4;
    private static final double WIDTH = 4;
    private static final BoundingBox BOX = new RectBoundingBox(POS, HEIGHT, WIDTH);
    private static final int GAMEHEIGHT = 400;
    private static final int GAMEWIDTH = 400;
    private static final GameState GAME = new GameStateImpl(GAMEWIDTH, GAMEHEIGHT, 0);
    private static final Player ANGEL = new PlayerImpl(GAME, POS, new Vector2d(0, 0), HEIGHT, WIDTH, PlayerImpl.PlayerType.ANGEL);
    private static final Player DEVIL = new PlayerImpl(GAME, POS2, new Vector2d(0, 0), HEIGHT, WIDTH, PlayerImpl.PlayerType.DEVIL);
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
        Entity ANGELDOOR = ENTITYFACTORY.createEntity(Optional.empty(), Entity.entityType.ANGEL_DOOR, POS );
        Entity DEVILDOOR = ENTITYFACTORY.createEntity(Optional.empty(), Entity.entityType.DEVIL_DOOR,POS2);
        assertTrue(((Door)ANGELDOOR).getDoorState().equals(Door.doorState.CLOSED_DOOR));
        assertTrue(((Door)DEVILDOOR).getDoorState().equals(Door.doorState.CLOSED_DOOR));
        ((Door)ANGELDOOR).openDoor(ANGEL);
        ((Door)DEVILDOOR).openDoor(DEVIL);
        assertTrue(((Door)ANGELDOOR).getDoorState().equals(Door.doorState.OPEN_DOOR));
        assertTrue(((Door)DEVILDOOR).getDoorState().equals(Door.doorState.OPEN_DOOR));
    }

    @Test
    public void testActivableObject() {
        Entity BUTTON = ENTITYFACTORY.createEntity(Optional.of(PLATFORM), Entity.entityType.BUTTON, POS);
        Entity LEVER = ENTITYFACTORY.createEntity(Optional.of(PLATFORM), Entity.entityType.LEVER, POS2);
        ((ActivableObjectImpl)BUTTON).activate();
        assertTrue(((ActivableObjectImpl)BUTTON).isActivated());
        assertTrue(((ActivableObjectImpl)BUTTON).getMovablePlatform().getPosition().equals(PLATFORM.getFinalPosition()));
        ((ActivableObjectImpl)LEVER).deactivate();
        assertFalse(((ActivableObjectImpl)LEVER).isActivated());
        assertTrue(((ActivableObjectImpl)LEVER).getMovablePlatform().getPosition().equals(PLATFORM.getOriginalPos()));
    }

    @Test
    public void testPuddle() {
        Entity REDPUDDLE = ENTITYFACTORY.createEntity(Optional.empty(), Entity.entityType.RED_PUDDLE,POS);
        Entity BLUEPUDDLE = ENTITYFACTORY.createEntity(Optional.empty(), Entity.entityType.BLUE_PUDDLE,POS);
        Entity PURPLEPUDDLE = ENTITYFACTORY.createEntity(Optional.empty(), Entity.entityType.PURPLE_PUDDLE,POS);
    }
}