package it.unibo.dna.model.object.api;


import java.util.Optional;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.movableEntity.MovablePlatform;

/**
 * A factory with the purpose of being able to create various types of entities.
 */
public interface EntityFactory {

    static int DEF_HEIGHT = 4;
    static int DEF_WIDTH = 4;
    static int BUTTON_HEIGHT = 2;
    static int LEVER_HEIGHT = 3;
    static int PUDDLE_WIDTH = 20;
    static int DOOR_HEIGHT = 15;
    static int DOOR_WIDTH = 10;
    static int PLATFORM_WIDTH = 40;
    static int PLAYER_HEIGHT = 5;
    static int PLAYER_WIDTH = 4;

    /**
     * A method that creates an Entity of a wanted type.
     * @param movablePlatform the optional MovablePlatform that the Entity can control. 
     * It shoudl be present if a BUTTON or a LEVER is being created
     * @param type the type of the Entity
     * @param position the position of the Entity. 
     * If two positions are passed as parameters, the first is the originalPosition, and the second is the FinalPosition.
     * Two positions should be passed if the Entity being created is of the type MOVABLEPLATFORM
     * @return the Entity created
     */
    public Entity createEntity(Optional<MovablePlatform> movablePlatform,Entity.EntityType type, Position2d ... position); 

}
