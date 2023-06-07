package it.unibo.dna;

import java.io.IOException;

import it.unibo.dna.graphics.Display;
import it.unibo.dna.graphics.MenuFactory;
import it.unibo.dna.graphics.MenuFactoryImpl;
import it.unibo.dna.graphics.SoundFactoryImpl;
import it.unibo.dna.model.Level;
import it.unibo.dna.model.game.api.GameState;
import it.unibo.dna.model.game.impl.GameStateImpl;

/**
 * Represents the game engine that manages the game loop and updates the game
 * state.
 */
public class GameEngine implements Runnable {
    private Display display;
    private GameState game;
    private Level level;
    private boolean running;
    private final double rateUpdate = 1.0d / 50.0d;
    private static GameThread gameThread;
    private MenuFactory menuFactory;


    /**
     * Constructs a GameEngine object with the specified level number.
     *
     * @param lvl The level number.
     * @throws IOException if an I/O error occurs while loading the level.
     */
    public GameEngine(int lvl) throws IOException {
        this.level = new Level(lvl);
    }

    public void setGameThread(GameThread gameT) {
        gameThread = gameT;
        this.menuFactory = new MenuFactoryImpl(gameThread);
    }

    public static GameThread getGameThread() {
        return gameThread;
    }

    /**
     * Starts the game loop and keeps updating and rendering the game until stopped.
     */
    @Override
    public void run() {
        this.display = new Display(this.level.getCharacters(), this.menuFactory);
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
                update();
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
        display.dispose();
        running = false;
    }

    public static void playSound(String string) {
        switch (string) {
            case "angel_jump":
                (new SoundFactoryImpl()).jumpAngelClip().start();
                break;
            case "devil_jump":
                (new SoundFactoryImpl()).jumpDevilClip().start();
                break;
            case "diamond":
                (new SoundFactoryImpl()).diamondClip().start();
                break;
            case "game_over":
                (new SoundFactoryImpl()).gameOverClip().start();
                break;
            case "victory":
                (new SoundFactoryImpl()).victoryClip().start();
                break;
            default:
        }
    }
    
    public MenuFactory getMenuFactory() {
        return menuFactory;
    }
}
