package it.unibo.dna.controller.core;

import java.io.IOException;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * The GameThread class represents a thread that manages the game execution.
 * It interacts with the GameEngine to start, stop, and handle game events.
 */
public class GameThread extends Thread {
    private GameEngineImpl gameEngine;
    private int lvl;

    /**
     * Constructs a GameThread object with the specified GameEngine.
     *
     * @param gameEngine the GameEngine instance to associate with the thread
     * @throws IOException if an I/O error occurs.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", 
    justification =  "the gameEngine field is intentionally exposed to allow initialization with the current game engine")
    public GameThread(final GameEngineImpl gameEngine) throws IOException {
        this.gameEngine = gameEngine;
        GameEngineImpl.setGameThread(this);
    }

    /**
     * Sets the GameEngine instance associated with the thread.
     *
     * @param gameEngine the GameEngine instance to set.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", 
    justification =  "the gameEngine field is intentionally exposed to allow initialization with the current game engine")
    public void setGameEngine(final GameEngineImpl gameEngine) {
        this.gameEngine = gameEngine;
    }

    /**
     * Retrieves the GameEngine instance associated with the thread.
     *
     * @return the associated GameEngine instance.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", 
    justification =  "the gameEngine field is intentionally exposed to allow initialization with the current game engine")
    public GameEngineImpl getGameEngine() {
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
        }

        this.interrupt();
        this.gameEngine.stop();
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

    /**
     * Interrupts the game thread and stops the associated game engine if it is running.
     */
    public void interruptGame() {
        this.interrupt();
        if (this.gameEngine.isRunning()) {
            this.gameEngine.stop();
        }
    }
}
