package it.unibo.dna.model;

import java.util.LinkedList;
import java.util.Queue;

import it.unibo.dna.GameState;

/**
 * Class for a queue of {@link Event}.
 */
public class EventQueue {
    private final Queue<Event> events;

    /**
     * {@link EventQueue} constructor.
     */
    public EventQueue() {
        events = new LinkedList<>();
    }

    /**
     * Adds an {@link Event} in the queue.
     * @param event the {@Event} to add
     */
    public void addEvent(final Event event) {
        events.add(event);
    }

    /**
     * Manages all the Events in the queue.
     * @param game the game state to manage 
     */
    public void manageEvents(final GameState game) {
        while (!events.isEmpty()) {
            events.poll().manage(game);
        }
    }
}
