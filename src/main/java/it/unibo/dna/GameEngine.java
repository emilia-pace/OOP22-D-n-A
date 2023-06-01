package it.unibo.dna;

import java.io.IOException;

import it.unibo.dna.graphics.Display;
import it.unibo.dna.model.Level;

public class GameEngine implements Runnable {
    Display display = new Display();
    

    private GameState game;
    private Level level;

    boolean running;
    private final double rateUpdate = 1.0d / 60.0d;

    public GameEngine(int lvl) throws IOException {
        this.level = new Level(lvl);
        this.game = new GameStateImpl(display.getWidth(), display.getWidth(), this.level);
    }

    @Override
    public void run() {
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

    private void render() {
        display.render(game.getEntities());
        
    }

    private void update() {
        game.update();
    }

}
