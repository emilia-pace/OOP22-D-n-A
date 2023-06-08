package it.unibo.dna;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.object.EntityFactoryImpl;
import it.unibo.dna.model.object.movableentity.MovablePlatform;
import it.unibo.dna.model.object.player.Entity;
import it.unibo.dna.model.object.player.PlayerImpl;
import it.unibo.dna.model.object.player.api.Player;
import it.unibo.dna.model.object.stillentity.impl.ActivableObjectImpl;
import it.unibo.dna.model.object.stillentity.impl.Door;
import it.unibo.dna.model.object.stillentity.impl.Puddle;

class ObjectsTest {
    private static final EntityFactoryImpl ENTITYFACTORY = new EntityFactoryImpl();
    private static final double X = 10;
    private static final double Y = 20;
    private static final Position2d POS = new Position2d(X, Y);
    private static final Position2d POS2 = new Position2d(X + X, Y + Y);
    private static final Position2d POS3 = new Position2d(X + 15, Y + 15);
    private static final double HEIGHT = 4;
    private static final double WIDTH = 4;
    private static final Player ANGEL = new PlayerImpl(POS, new Vector2d(0,0), HEIGHT, WIDTH, PlayerImpl.PlayerType.ANGEL);
    private static final Player DEVIL = new PlayerImpl(POS2, new Vector2d(0, 0), HEIGHT, WIDTH, PlayerImpl.PlayerType.DEVIL);
    private static final MovablePlatform PLATFORM = new MovablePlatform(POS, new Vector2d(0, 0), HEIGHT, WIDTH, POS);
    private static final Entity BUTTON = ENTITYFACTORY.createEntity(Optional.of(PLATFORM), Entity.EntityType.BUTTON, POS);
    private static final Entity LEVER = ENTITYFACTORY.createEntity(Optional.of(PLATFORM), Entity.EntityType.LEVER, POS2);
    private static final Entity ANGELDOOR = ENTITYFACTORY.createEntity(Optional.empty(), Entity.EntityType.ANGEL_DOOR, POS);
    private static final Entity DEVILDOOR = ENTITYFACTORY.createEntity(Optional.empty(), Entity.EntityType.DEVIL_DOOR, POS2);
    private static final Entity RED_PUDDLE = ENTITYFACTORY.createEntity(Optional.empty(), Entity.EntityType.RED_PUDDLE, POS3);

    @Test
    void testMovablePlatformMethods() {
        Position2d finalPos = new Position2d(X + 100, Y + 100); //test vettore 0.5, 0.5
        PLATFORM.setFinalPosition(finalPos);
        PLATFORM.findVector(PLATFORM.getOriginalPosition(), PLATFORM.getFinalPosition());
        assertEquals(PLATFORM.getVector(), new Vector2d(+0.5, +0.5));

        finalPos = new Position2d(X + 100, Y - 100); //test vettore 0.5, -0.5
        PLATFORM.setFinalPosition(finalPos);
        PLATFORM.findVector(PLATFORM.getOriginalPosition(), PLATFORM.getFinalPosition());
        assertEquals(PLATFORM.getVector(), new Vector2d(+0.5, -0.5));

        finalPos = new Position2d(X - 100, Y + 100); //test vettore -0.5, 0.5
        PLATFORM.setFinalPosition(finalPos);
        PLATFORM.findVector(PLATFORM.getOriginalPosition(), PLATFORM.getFinalPosition());
        assertEquals(PLATFORM.getVector(), new Vector2d(-0.5, +0.5));

        finalPos = new Position2d(X - 100, Y - 100); //test vettore -0.5, -0.5
        PLATFORM.setFinalPosition(finalPos);
        PLATFORM.findVector(PLATFORM.getOriginalPosition(), PLATFORM.getFinalPosition());
        assertEquals(PLATFORM.getVector(), new Vector2d(-0.5, -0.5));

        PLATFORM.setPosition(PLATFORM.getFinalPosition());
        PLATFORM.setLastPosition();
        PLATFORM.findLimit();
        assertEquals(PLATFORM.getPosition(), PLATFORM.getFinalPosition());
    }

    @Test
    void testDoor() {
        assertEquals(((Door) ANGELDOOR).getDoorState(), Door.DoorState.CLOSED_DOOR);
        assertEquals(((Door) DEVILDOOR).getDoorState(), Door.DoorState.CLOSED_DOOR);
        ((Door) ANGELDOOR).openDoor(ANGEL);
        ((Door) DEVILDOOR).openDoor(DEVIL);
        assertEquals(((Door) ANGELDOOR).getDoorState(), Door.DoorState.OPEN_DOOR);
        assertEquals(((Door) DEVILDOOR).getDoorState(), Door.DoorState.OPEN_DOOR);
    }

    @Test
    void testActivableObject() {
        ((ActivableObjectImpl) BUTTON).activate();
        assertTrue(((ActivableObjectImpl) BUTTON).isActivated());
        assertEquals(((ActivableObjectImpl) BUTTON).getMovablePlatform().getPosition(), PLATFORM.getFinalPosition());
        ((ActivableObjectImpl) LEVER).deactivate();
        assertFalse(((ActivableObjectImpl) LEVER).isActivated());
        assertEquals(((ActivableObjectImpl) LEVER).getMovablePlatform().getPosition(), PLATFORM.getOriginalPosition());
    }

    @Test
    void testPuddles() {
        assertTrue(((Puddle) RED_PUDDLE).killPlayer(ANGEL));
        assertFalse(((Puddle) RED_PUDDLE).killPlayer(DEVIL));
    }

}
