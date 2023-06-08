package it.unibo.dna.model.object.api;


import java.util.Optional;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.movableentity.MovablePlatform;
import it.unibo.dna.model.object.player.Entity;

/**
 * A factory with the purpose of being able to create various types of entities.
 */
public interface EntityFactory {

    int DEF_HEIGHT = 4;
    int DEF_WIDTH = 4;
    int DIAMOND_WIDTH = 6;
    int DIAMOND_HEIGHT = 6;
    int BUTTON_HEIGHT = 2;
    int LEVER_HEIGHT = 5;
    int PUDDLE_HEIGHT = 3;
    int PUDDLE_WIDTH = 10;
    int DOOR_HEIGHT = 15;
    int DOOR_WIDTH = 10;
    int PLATFORM_WIDTH = 40;
    int PLAYER_HEIGHT = 7;
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
    Entity createEntity(Optional<MovablePlatform> movablePlatform,Entity.EntityType type, Position2d ... position); 

}
