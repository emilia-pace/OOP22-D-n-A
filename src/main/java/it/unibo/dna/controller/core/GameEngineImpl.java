package it.unibo.dna.controller.core;

import java.io.IOException;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
public class GameEngineImpl implements Runnable, GameEngine {
    private Display display;
    private GameState game;
    private final Level levelConstruct;
    private boolean running;
    private static final double RATEUPDATE = 1.0d / 60.0d;
    private static GameThread gameThread;
    private static MenuFactory menuFactory;
    private final InputControl angelInputControl = new InputControlImpl();
    private final InputControl devilInputControl = new InputControlImpl();
    private final int lvl;

    /**
     * Constructs a GameEngine object for the specified level.
     *
     * @param lvl The level number.
     * @throws IOException if an I/O error occurs while loading the level.
     */
    public GameEngineImpl(final int lvl) throws IOException {
        this.levelConstruct = new Level(lvl);
        this.lvl = lvl;
    }

    /**
     * Sets the game thread for the game engine and creates the MenuFactory.
     *
     * @param gameT The game thread.
     */
    public static final void setGameThread(final GameThread gameT) {
        gameThread = gameT;
        menuFactory = new MenuFactoryImpl(gameThread);
    }

    /**
     * Retrieves the game thread associated with the game engine.
     *
     * @return The game thread.
     */
    @SuppressFBWarnings(value = "MS_EXPOSE_REP",
    justification =  "the game thread field is intentionally exposed because it shouldn't create new Gamethread")
    public static final GameThread getGameThread() {
        return gameThread;
    }

    /**
     * Retrieves the current score of the game.
     *
     * @return The current score of the game.
     */
    @Override
    public double getScore() {
        return this.game.getScore();
    }

    /**
     * Retrieves the current level number of the game.
     *
     * @return The level number of the game.
     */
    @Override
    public int getLvl() {
        return this.lvl;
    }

    /**
     * Starts the game loop and keeps updating and rendering the game until stopped.
     */
    @Override
    @SuppressFBWarnings(value = "FL_MATH_USING_FLOAT_PRECISION", 
    justification =  "accumulator has to be a double because its compared to rate supdate")
    public void run() {
        this.display = new Display(this.levelConstruct.getCharacters(), menuFactory,
                this.angelInputControl, this.devilInputControl);
        this.game = new GameStateImpl(display.getScreenDimension(), display.getScreenDimension(),
                this.levelConstruct.getEntities(), this.levelConstruct.getCharacters());
        this.running = true;
        double accumulator = 0;
        long currentTime, lastUpdate = System.currentTimeMillis();

        while (running) {
            currentTime = System.currentTimeMillis();
            final double lastTimeInSeconds = (currentTime - lastUpdate) / 1000d;
            accumulator += lastTimeInSeconds;
            lastUpdate = currentTime;

            while (accumulator >= RATEUPDATE) {
                if (running) {
                    this.angelInputControl.computeAll();
                    this.devilInputControl.computeAll();
                    this.update();
                }
                accumulator -= RATEUPDATE;
            }
            this.render();
        }
    }

    /**
     * Calls the render method of the display to update the game's visual representation.
     */
    private void render() {
        display.render(this.game.getEntities(), this.game.getCharacters());
    }

    /**
     * Updates the game state.
     */
    private void update() {
        this.game.update();
    }

    /**
     * Stops the game engine and releases any resources.
     */
    @Override
    public void stop() {
        this.running = false;
        this.display.dispose();
    }

    /**
     * Plays an audio clip based on the specified event.
     *
     * @param audioFileName The name of the audio file.
     */
    public static void playSound(final String audioFileName) {
        (new SoundManager()).getClip(audioFileName).start();
    }

    /**
     * Retrieves the menu factory associated with the game engine.
     *
     * @return The menu factory.
     */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", 
    justification =  "the menufactory field is intentionally exposed because it shouldn't create a new menuFactory")
    public MenuFactory getMenuFactory() {
        return menuFactory;
    }

    /**
     * Checks if the game engine is currently running.
     *
     * @return true if the game engine is running, false otherwise.
     */
    @Override
    public boolean isRunning() {
        return running;
    }
}
