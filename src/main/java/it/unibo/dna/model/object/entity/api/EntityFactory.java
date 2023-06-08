package it.unibo.dna.model.object.entity.api;


import java.util.Optional;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.movableentity.impl.MovablePlatform;

/**
 * A factory with the purpose of being able to create various types of entities.
 */
public interface EntityFactory {

    /**
     * A constant for the height of some entities.
     */
    int DEF_HEIGHT = 4;
    /**
     * A constant for the width of some entities.
     */
    int DEF_WIDTH = 4;
    /**
     * A constant for the width of the diamond.
     */
    int DIAMOND_WIDTH = 6;
    /**
     * A constant for the height of the diamond.
     */
    int DIAMOND_HEIGHT = 6;
    /**
     * A constant for the height of the button.
     */
    int BUTTON_HEIGHT = 2;
    /**
     * A constant for the height of the lever.
     */
    int LEVER_HEIGHT = 5;
    /**
     * A constant for the height of the puddle.
     */
    int PUDDLE_HEIGHT = 3;
    /**
     * A constant for the width of the puddle.
     */
    int PUDDLE_WIDTH = 10;
    /**
     * A constant for the height of the door.
     */
    int DOOR_HEIGHT = 15;
    /**
     * A constant for the width of the door.
     */
    int DOOR_WIDTH = 10;
    /**
     * A constant for the width of the platforms.
     */
    int PLATFORM_WIDTH = 40;
    /**
     * A constant for the height od the player.
     */
    int PLAYER_HEIGHT = 7;
    /**
     * A constant for the width of the player.
     */
    int PLAYER_WIDTH = 6;

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
    Entity createEntity(Optional<MovablePlatform> movablePlatform, Entity.EntityType type, Position2d... position); 

}
