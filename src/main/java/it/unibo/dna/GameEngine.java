package it.unibo.dna;

public class GameEngine implements Runnable {

    private Game game;

    private boolean running;
    private final double rateUpdate = 1.0d / 60.0d;

    public GameEngine(Game game) {
        this.game = game;
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
        game.render();
    }

    private void update() {
        game.update();
    }

}
