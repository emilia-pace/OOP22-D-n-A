package it.unibo.dna.model.object;

import java.util.Optional;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.api.EntityFactory;
import it.unibo.dna.model.object.api.Entity.EntityType;
import it.unibo.dna.model.object.movableEntity.MovablePlatform;
import it.unibo.dna.model.object.stillEntity.impl.ActivableObjectImpl;
import it.unibo.dna.model.object.stillEntity.impl.Diamond;
import it.unibo.dna.model.object.stillEntity.impl.Door;
import it.unibo.dna.model.object.stillEntity.impl.Platform;
import it.unibo.dna.model.object.stillEntity.impl.Puddle;

/**
 * A class that implements the createEntity method from the EntityFactory
 * interface.
 */
public class EntityFactoryImpl implements EntityFactory {

    private final Vector2d defaultVector = new Vector2d(0, 0);
    private final int diamondValue = 1;

    @Override
    public Entity createEntity(final Optional<MovablePlatform> movablePlatform, final EntityType type, final Position2d... position) {
        return switch (type) {
            case BUTTON -> 
                new ActivableObjectImpl(position[0], (double) BUTTON_HEIGHT, 
                                        (double) DEF_WIDTH, movablePlatform.get(), Entity.EntityType.BUTTON);
            case LEVER -> 
                new ActivableObjectImpl(position[0], (double) DEF_HEIGHT, (double) DEF_WIDTH, movablePlatform.get(), Entity.EntityType.LEVER);
            case RED_PUDDLE -> 
                new Puddle(position[0], (double) DEF_HEIGHT, (double) PUDDLE_WIDTH, Entity.EntityType.RED_PUDDLE);
            case BLUE_PUDDLE ->
                new Puddle(position[0], (double) DEF_HEIGHT, (double) DEF_WIDTH, Entity.EntityType.BLUE_PUDDLE);
            case PURPLE_PUDDLE ->
                new Puddle(position[0], (double) DEF_HEIGHT, (double) PUDDLE_WIDTH, Entity.EntityType.PURPLE_PUDDLE);
            case ANGEL_DOOR -> 
                new Door(position[0], (double) DOOR_HEIGHT, (double) DOOR_WIDTH, Entity.EntityType.ANGEL_DOOR);
            case DEVIL_DOOR -> 
                new Door(position[0], (double) DOOR_HEIGHT, (double) DOOR_WIDTH, Entity.EntityType.DEVIL_DOOR);
            case PLATFORM -> 
                new Platform(position[0], (double) DEF_HEIGHT, (double) PLATFORM_WIDTH);
            case MOVABLEPLATFORM -> 
                new MovablePlatform(position[0], defaultVector,(double) DEF_HEIGHT, (double) PLATFORM_WIDTH, position[1]);
            case DIAMOND -> 
                new Diamond((double) DEF_HEIGHT, (double) DEF_WIDTH, diamondValue, position[0]);
            default -> 
                throw new IllegalArgumentException();
        };
    }

}
