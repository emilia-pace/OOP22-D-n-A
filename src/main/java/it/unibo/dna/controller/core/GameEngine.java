package it.unibo.dna.controller.core;

import java.io.IOException;

import it.unibo.dna.controller.inputcontrol.api.InputControl;
import it.unibo.dna.controller.inputcontrol.impl.InputControlImpl;
import it.unibo.dna.model.game.gamestate.api.GameState;
import it.unibo.dna.model.game.gamestate.impl.GameStateImpl;
import it.unibo.dna.model.game.level.Level;
import it.unibo.dna.view.Display;
import it.unibo.dna.view.menu.api.MenuFactory;
import it.unibo.dna.view.menu.impl.MenuFactoryImpl;
import it.unibo.dna.view.sound.SoundManager;

/**
 * Represents the game engine that manages the game loop and updates the game state.
 */
public class GameEngine implements Runnable {
    private Display display;
    private GameState game;
    private final Level levelConstruct;
    private boolean running;
    private final double rateUpdate = 1.0d / 60.0d;
    private static GameThread gameThread;
    private MenuFactory menuFactory;
    private final InputControl angelInputControl = new InputControlImpl();
    private final InputControl devilInputControl = new InputControlImpl();
    private final int lvl;

    /**
     * Constructs a GameEngine object and creates the Level object.
     *
     * @param lvl The level number.
     * @throws IOException if an I/O error occurs while loading the level.
     */
    public GameEngine(final int lvl) throws IOException {
        this.levelConstruct = new Level(lvl);
        this.lvl = lvl;
    }

    /**
     * Sets the game thread for the game engine and creates the MenuFactory.
     *
     * @param gameT The game thread.
     */
    public void setGameThread(final GameThread gameT) {
        gameThread = gameT;
        this.menuFactory = new MenuFactoryImpl(gameThread);
    }

    /**
     * Getter of the gameThread that runs the gameEngine.
     *
     * @return The game thread.
     */
    public static GameThread getGameThread() {
        return gameThread;
    }

    /**
     * Retrieves the current score of the game.
     *
     * @return The current score of the game.
     */
    public double getScore() {
        return game.getScore();
    }

    /**
     * Retrieves the current level number of the game.
     *
     * @return The level number of the game.
     */
    public int getLvl() {
        return this.lvl;
    }

    /**
     * Starts the game loop and keeps updating and rendering the game until stopped.
     */
    @Override
    public void run() {
        this.display = new Display(this.levelConstruct.getCharacters(), this.menuFactory,
        this.angelInputControl, this.devilInputControl);
        this.game = new GameStateImpl(display.getScreenDimension(), display.getScreenDimension(),
                this.levelConstruct.getEntities(), this.levelConstruct.getCharacters());
        running = true;
        double accumulator = 0;
        long currentTime, lastUpdate = System.currentTimeMillis();

        while (running) {
            currentTime = System.currentTimeMillis();
            final double lastTimeInSeconds = (currentTime - lastUpdate) / 1000d;
            accumulator += lastTimeInSeconds;
            lastUpdate = currentTime;

            while (accumulator >= rateUpdate) {
                if (running) {
                    this.angelInputControl.computeAll();
                    this.devilInputControl.computeAll();
                    update();
                }
                accumulator -= rateUpdate;
            }
            render();
        }
    }

    /**
     * Calls the render of the display.
     */
    private void render() {
        display.render(game.getEntities(), this.game.getCharacters());
    }

    /**
     * Updates the game state.
     */
    private void update() {
        game.update();
    }

    /**
     * Stops the game engine and releases any resources.
     */
    public void stop() {
        running = false;
        display.dispose();
    }

    /**
     * Plays an audio clip based on the specified for the event happening.
     *
     * @param string the name of the audio file.
     */
    public static void playSound(final String string) {
        (new SoundManager()).getClip(string).start();
    }

    /**
     * Retrieves the menu factory associated with the game engine.
     *
     * @return The menu factory.
     */
    public MenuFactory getMenuFactory() {
        return menuFactory;
    }

    public boolean isRunning() {
        return running;

    }
}
