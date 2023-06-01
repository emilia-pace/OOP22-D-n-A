package it.unibo.dna.model.object;

import java.util.Optional;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.api.Entity.entityType;

/**
 * A class that implements the createEntity method from the EntityFactory
 * interface
 */
public class EntityFactoryImpl implements EntityFactory {

<<<<<<< HEAD
=======
    private double defaultButtonHeight = 20.0;
    private double defaultWidth = 30.0;
    private double defaultHeight = 4.0;
    private double defaultPuddleWidth = 40.0;
    private double defaultDoorHeight = 7.0;
    private double defaultDoorWidth = 5.0;
    private double defaultPlatformWidth = 30.0;
    private double defaultMovablePlatformWidth = 1.0;
    private double diamondDimension = 4.0;
>>>>>>> 076c3a67abad80e0aa1ebd22732339d873a1e1bd
    private Vector2d defaultVector = new Vector2d(0, 0);
    private int diamondValue = 1;

    @Override
    public Entity createEntity(Optional<MovablePlatform> movablePlatform, entityType type, Position2d... position) {
        return switch (type) {
            case BUTTON -> new ActivableObjectImpl(position[0], (double)BUTTON_HEIGHT, (double)DEF_WIDTH,
                    movablePlatform.get(), Entity.entityType.BUTTON);
            case LEVER -> new ActivableObjectImpl(position[0], (double)DEF_HEIGHT, (double)DEF_WIDTH, movablePlatform.get(),
                    Entity.entityType.LEVER);
            case RED_PUDDLE -> new Puddle(position[0], (double)DEF_HEIGHT,(double)PUDDLE_WIDTH, Entity.entityType.RED_PUDDLE);
            case BLUE_PUDDLE ->
                new Puddle(position[0], (double)DEF_HEIGHT, (double)DEF_WIDTH, Entity.entityType.BLUE_PUDDLE);
            case PURPLE_PUDDLE ->
<<<<<<< HEAD
                new Puddle(position[0], (double)DEF_HEIGHT, (double)PUDDLE_WIDTH, Entity.entityType.PURPLE_PUDDLE);
            case ANGEL_DOOR -> new Door(position[0], (double)DOOR_HEIGHT, (double)DEF_WIDTH, Entity.entityType.ANGEL_DOOR);
            case DEVIL_DOOR -> new Door(position[0], (double)DOOR_HEIGHT, (double)DEF_WIDTH, Entity.entityType.DEVIL_DOOR);
            case PLATFORM -> new Platform(position[0], (double)DEF_HEIGHT, (double)PLATFORM_WIDTH);
            case MOVABLEPLATFORM -> new MovablePlatform(position[0], defaultVector,(double)DEF_HEIGHT,
            (double)PLATFORM_WIDTH, position[1]);
            case DIAMOND -> new Diamond((double)DEF_HEIGHT, (double)DEF_HEIGHT, diamondValue, position[0]);
=======
                new Puddle(position[0], defaultHeight, defaultPuddleWidth, Entity.entityType.PURPLE_PUDDLE);
            case ANGEL_DOOR -> new Door(position[0], defaultDoorWidth, defaultDoorHeight, Entity.entityType.ANGEL_DOOR);
            case DEVIL_DOOR -> new Door(position[0], defaultDoorHeight, defaultDoorWidth, Entity.entityType.DEVIL_DOOR);
            case PLATFORM -> new Platform(position[0], defaultHeight, defaultPlatformWidth);
            case MOVABLEPLATFORM -> new MovablePlatform(position[0], defaultVector, defaultMovablePlatformWidth,
                    defaultHeight, position[1]);
            case DIAMOND -> new Diamond(diamondDimension, diamondDimension, diamondValue, position[0]);
>>>>>>> 076c3a67abad80e0aa1ebd22732339d873a1e1bd
            default -> throw new IllegalArgumentException();
        };
    }

}
