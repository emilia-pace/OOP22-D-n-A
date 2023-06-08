package it.unibo.dna.gameloop;

import java.io.IOException;

import it.unibo.dna.graphics.Display;
import it.unibo.dna.graphics.menu.api.MenuFactory;
import it.unibo.dna.graphics.menu.impl.MenuFactoryImpl;
import it.unibo.dna.graphics.sound.SoundManager;
import it.unibo.dna.input.api.InputControl;
import it.unibo.dna.input.impl.InputControlImpl;
import it.unibo.dna.model.game.gamestate.api.GameState;
import it.unibo.dna.model.game.gamestate.impl.GameStateImpl;
import it.unibo.dna.model.game.level.Level;

/**
 * Represents the game engine that manages the game loop and updates the game
 * state.
 */
public class GameEngine implements Runnable {
    private Display display;
    private GameState game;
    private Level level;
    private boolean running;
    private final double rateUpdate = 1.0d / 60.0d;
    private static GameThread gameThread;
    private MenuFactory menuFactory;
    private InputControl angelInputControl = new InputControlImpl();
    private InputControl devilInputControl = new InputControlImpl();

    /**
     * Constructs a GameEngine object with the specified level number.
     *
     * @param lvl The level number.
     * @throws IOException if an I/O error occurs while loading the level.
     */
    public GameEngine(final int lvl) throws IOException {
        this.level = new Level(lvl);
    }

    public void setGameThread(final GameThread gameT) {
        gameThread = gameT;
        this.menuFactory = new MenuFactoryImpl(gameThread);
    }

    public static GameThread getGameThread() {
        return gameThread;
    }

    public double getScore() {
        return game.getScore();
    }

    /**
     * Starts the game loop and keeps updating and rendering the game until stopped.
     */
    @Override
    public void run() {
        this.display = new Display(this.level.getCharacters(), this.menuFactory, this.angelInputControl, this.devilInputControl);
        this.game = new GameStateImpl(display.getScreenDimension(), display.getScreenDimension(),
                this.level.getEntities(), this.level.getCharacters());
        running = true;
        double accumulator = 0;
        long currentTime, lastUpdate = System.currentTimeMillis();

        while (running) {
            currentTime = System.currentTimeMillis();
            double lastTimeInSeconds = (currentTime - lastUpdate) / 1000d;
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
     * Renders the game state on the display.
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
     * Plays an audio clip based on the specified file name or path.
     * 
     * @param string the name or path of the audio file
     */
    public static void playSound(final String string) {
        (new SoundManager()).getClip(string).start();
    }

    public MenuFactory getMenuFactory() {
        return menuFactory;
    }
}
