package it.unibo.dna.model.game.api;

import java.util.List;

import it.unibo.dna.model.box.api.BoundingBox;
import it.unibo.dna.model.events.impl.EventQueue;
import it.unibo.dna.model.object.player.Entity;
import it.unibo.dna.model.object.player.api.Player;

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
     * Adds a new {@link Entity} in the game.
     * 
     * @param e the {@link Entity} to add
     */
    void addEntity(Entity e);

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
     * @return the list of characters of the game
     */
    List<Player> getCharacters();

    /**
     * 
     * @return the list of {@link Event}
     */
    EventQueue getEventQueue();
}
