package it.unibo.dna.controller.core;

import java.io.IOException;

/**
 * The GameThread class represents a thread that manages the game execution.
 * It interacts with the GameEngine to start, stop, and handle game events.
 */
public class GameThread extends Thread {
    private GameEngine gameEngine;
    private int lvl;

    /**
     * Constructs a GameThread object with the specified GameEngine.
     *
     * @param gameEngine the GameEngine instance to associate with the thread
     * @throws IOException if an I/O error occurs.
     */
    public GameThread(final GameEngine gameEngine) throws IOException {
        this.gameEngine = gameEngine;
        this.gameEngine.setGameThread(this);
    }

    /**
     * Sets the GameEngine instance associated with the thread.
     *
     * @param gameEngine the GameEngine instance to set.
     */
    public void setGameEngine(final GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    /**
     * Retrieves the GameEngine instance associated with the thread.
     *
     * @return the associated GameEngine instance.
     */
    public GameEngine getGameEngine() {
        return this.gameEngine;
    }

    /**
     * {@inheritDoc}
     * 
     * Starts the game thread.
     * Creates a new thread and starts the execution of the game engine.
     */
    @Override
    public void start() {
        new Thread(gameEngine).start();
    }

    /**
     * Handles the victory event in the game.
     * If the current level is the last level, it creates the last victory menu.
     * Otherwise, it increments the level, creates the victory menu for the next level,
     * interrupts the previous thread, and stops the previous game engine.
     */
    public void victoryGame() {
        lvl = gameEngine.getLvl();
        if (lvl == 3) {
            this.gameEngine.getMenuFactory().lastVictoryMenu().createMenuFrame();
        } else {
            lvl++;
            this.gameEngine.getMenuFactory().victoryMenu(lvl).createMenuFrame();
            this.interrupt();
            this.gameEngine.stop();
        }
    }

    /**
     * Handles the game loss event.
     * It creates the game over menu for the current level,
     * interrupts the previous thread, and stops the previous game engine.
     */
    public void loosingGame() {
        lvl = gameEngine.getLvl();
        this.gameEngine.getMenuFactory().gameOverMenu(lvl).createMenuFrame();
        this.interrupt();
        this.gameEngine.stop();
    }

    public void interruptGame() {
        this.interrupt();
        if (gameEngine.isRunning()) {
            this.gameEngine.stop();
        }
    }
}
