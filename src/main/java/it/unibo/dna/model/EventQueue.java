package it.unibo.dna.model;

import java.util.LinkedList;
import java.util.Queue;

import it.unibo.dna.Game;

/**
 * Class for a queue of {@link Event}.
 */
public class EventQueue {
    private final Queue<Event> eventQueue;

    /**
     * {@link EventQueue} constructor.
     */
    public EventQueue() {
        eventQueue = new LinkedList<>();
    }

    /**
     * Adds an {@link Event} in the queue.
     * @param event the {@Event} to add
     */
    public void addEvent(final Event event) {
        eventQueue.add(event);
    }

    /**
     * Manages all the Events in the queue.
     * @param game the game state to manage 
     */
    public void manageEvents(final Game game) {
        while (!eventQueue.isEmpty()) {
            eventQueue.poll().manage(game);
        }
    }
}
