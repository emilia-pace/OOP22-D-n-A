package it.unibo.dna;

import java.util.List;

import it.unibo.dna.model.EventQueue;
import it.unibo.dna.model.object.api.BoundingBox;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.api.Player;

/**
 * Interface that models the state of the game.
 */
public interface GameState {

    /**
     * Updates the state of the game.
     */
    void update();

    /**
     * 
     * @return the {@link BoundingBox}
     */
    BoundingBox getBoundingBox();

    /**
     * 
     * @param boundingBox the {@link BoundingBox}
     */
    void setBoundingBox(BoundingBox boundingBox);

    /**
     * Adds a new {@link Entity} in the game.
     * 
     * @param e the {@link Entity} to add
     */
    void addEntity(Entity e);

    List<Player> getCharacters();

    /**
     * Removes an {@link Entity} from the game.
     * 
     * @param e the {@link Entity} to remove
     */
    void removeEntity(Entity e);

    /**
     * 
     * @return the list of {@link Entity} of the game
     */
    List<Entity> getEntities();

    /**
     * 
     * @return the list of {@link Event}
     */
    EventQueue getEventQueue();
}
