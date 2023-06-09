package it.unibo.dna.controller.core;

import it.unibo.dna.view.menu.api.MenuFactory;

/**
 * The GameEngine interface represents the game engine that manages the game loop and updates the game state.
 */
public interface GameEngine {

    /**
     * Retrieves the current score of the game.
     *
     * @return The current score of the game.
     */
    double getScore();

    /**
     * Retrieves the current level number of the game.
     *
     * @return The level number of the game.
     */
    int getLvl();

    /**
     * Starts the game loop and keeps updating and rendering the game until stopped.
     */
    void run();

    /**
     * Stops the game engine and releases any resources.
     */
    void stop();

    /**
     * Retrieves the menu factory associated with the game engine.
     *
     * @return The menu factory.
     */
    MenuFactory getMenuFactory();

    /**
     * Checks if the game engine is currently running.
     *
     * @return true if the game engine is running, false otherwise.
     */
    boolean isRunning();

}
