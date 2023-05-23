package it.unibo.dna;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import ch.qos.logback.classic.joran.action.ReceiverAction;
import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.RectBoundingBox;
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
    private static final Game GAME = new Game(GAMEWIDTH, GAMEHEIGHT, 0);
    private static final Player CHARACTER = new PlayerImpl(POS,new Vector2d(0, 0), HEIGHT, WIDTH, PlayerImpl.Type.ANGEL);

    
}