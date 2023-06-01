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

    private double defaultButtonHeight = 20.0;
    private double defaultWidth = 30.0;
    private double defaultHeight = 30.0;
    private double defaultPuddleWidth = 40.0;
    private double defaultDoorHeight = 7.0;
    private double defaultDoorWidth = 5.0;
    private double defaultPlatformWidth = 30.0;
    private double defaultMovablePlatformWidth = 1.0;
    private double diamondDimension = 4.0;
    private Vector2d defaultVector = new Vector2d(0, 0);
    private int diamondValue = 1;

    @Override
    public Entity createEntity(Optional<MovablePlatform> movablePlatform, entityType type, Position2d... position) {
        return switch (type) {
            case BUTTON -> new ActivableObjectImpl(position[0], defaultButtonHeight, defaultWidth,
                    movablePlatform.get(), Entity.entityType.BUTTON);
            case LEVER -> new ActivableObjectImpl(position[0], defaultHeight, defaultWidth, movablePlatform.get(),
                    Entity.entityType.LEVER);
            case RED_PUDDLE -> new Puddle(position[0], defaultHeight, defaultPuddleWidth, Entity.entityType.RED_PUDDLE);
            case BLUE_PUDDLE ->
                new Puddle(position[0], defaultHeight, defaultPuddleWidth, Entity.entityType.BLUE_PUDDLE);
            case PURPLE_PUDDLE ->
                new Puddle(position[0], defaultHeight, defaultPuddleWidth, Entity.entityType.PURPLE_PUDDLE);
            case ANGEL_DOOR -> new Door(position[0], defaultDoorHeight, defaultDoorWidth, Entity.entityType.ANGEL_DOOR);
            case DEVIL_DOOR -> new Door(position[0], defaultDoorHeight, defaultDoorWidth, Entity.entityType.DEVIL_DOOR);
            case PLATFORM -> new Platform(position[0], defaultPlatformWidth, defaultHeight);
            case MOVABLEPLATFORM -> new MovablePlatform(position[0], defaultVector, defaultMovablePlatformWidth,
                    defaultHeight, position[1]);
            case DIAMOND -> new Diamond(diamondDimension, diamondDimension, diamondValue, position[0]);
            default -> throw new IllegalArgumentException();
        };
    }

}
