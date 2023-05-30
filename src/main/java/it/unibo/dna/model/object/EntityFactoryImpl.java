package it.unibo.dna.model.object;


import java.util.Optional;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.api.Entity.entityType;

public class EntityFactoryImpl implements EntityFactory{

    @Override
    public Entity createEntity(Optional<MovablePlatform> movablePlatform,entityType type, Position2d ... pos) {
        return switch(type){
            case BUTTON -> new ActivableObjectImpl(pos[0], 20.0, 30.0, movablePlatform.get(),Entity.entityType.BUTTON);
            case LEVER -> new ActivableObjectImpl(pos[0], 30.0, 30.0, movablePlatform.get(), Entity.entityType.LEVER);
            case RED_PUDDLE -> new Puddle(pos[0], 30.0, 40.0, Entity.entityType.RED_PUDDLE);
            case BLUE_PUDDLE -> new Puddle(pos[0], 30.0, 40.0, Entity.entityType.BLUE_PUDDLE);
            case PURPLE_PUDDLE -> new Puddle(pos[0], 30.0, 40.0, Entity.entityType.PURPLE_PUDDLE);
            case ANGEL_DOOR -> new Door(pos[0], 70.0, 50.0, Entity.entityType.ANGEL_DOOR);
            case DEVIL_DOOR -> new Door(pos[0], 70.0, 50.0, Entity.entityType.DEVIL_DOOR);
            case PLATFORM -> new Platform(pos[0], 30.0, 300.0);
            case MOVABLEPLATFORM -> new MovablePlatform(pos[0],new Vector2d(0,0),30.0,100.0,pos[1]);
            case DIAMOND -> new Diamond(45, 45, 1, pos[0]);
            default -> throw new IllegalArgumentException();
        };
    }

}
