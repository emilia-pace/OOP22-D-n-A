package it.unibo.dna.model.object;


import java.util.Optional;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.api.Entity;

/**
 * A factory with the purpose of being able to create various types of entities.
 */
public interface EntityFactory {
    
    public static int DEF_HEIGHT = 4;
    public static int DEF_WIDTH = 4;
    public static int BUTTON_HEIGHT = 2;
    public static int LEVER_HEIGHT = 3;
    public static int PUDDLE_WIDTH = 20;
    public static int DOOR_HEIGHT = 15;
    public static int DOOR_WIDTH = 10;
    public static int PLATFORM_WIDTH = 30;
    public static int PLAYER_HEIGHT = 5;
    public static int PLAYER_WIDTH = 4;
    /**
     * A method that creates an Entity of a wanted type.
     * @param movablePlatform the optional MovablePlatform that the Entity can control. It shoudl be present if a BUTTON or a LEVER is being created
     * @param type the type of the Entity
     * @param position the position of the Entity. If two positions are passed as parameters, the first is the originalPosition, and the second is the FinalPosition.
     * Two positions should be passed if the Entity being created is of the type MOVABLEPLATFORM
     * @return the Entity created
     */
    public Entity createEntity(Optional<MovablePlatform> movablePlatform,Entity.entityType type, Position2d ... position); 

}
