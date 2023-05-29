package it.unibo.dna;

import java.util.List;

import it.unibo.dna.model.EventQueue;
import it.unibo.dna.model.object.api.BoundingBox;
import it.unibo.dna.model.object.api.Entity;

/**
 * Interface that models the state of the game.
 */
public interface GameState {
    
    /**
     * Updates the state of the game.
     */
    public void update();

    public void render();

    /**
     * 
     * @return the {@link BoundingBox}
     */
    public BoundingBox getBoundingBox();

    /**
     * 
     * @param boundingBox the {@link BoundingBox}
     */
    public void setBoundingBox(final BoundingBox boundingBox);

    /**
     * Adds a new {@link Entity} in the game.
     * 
     * @param e the {@link Entity} to add
     */
    public void addEntity(final Entity e);

    /**
     * Removes an {@link Entity} from the game.
     * 
     * @param e the {@link Entity} to remove
     */
    public void removeEntity(final Entity e);

    /**
     * 
     * @return the list of {@link Entity} of the game
     */
    public List<Entity> getEntities();

    /**
     * 
     * @return the list of {@link Event}
     */
    public EventQueue getEventQueue();
}
