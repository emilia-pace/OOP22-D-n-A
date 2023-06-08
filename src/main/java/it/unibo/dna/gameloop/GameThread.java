package it.unibo.dna.gameloop;

import java.io.IOException;

public class GameThread extends Thread {
    private GameEngine gameEngine;

    public GameThread(GameEngine gEngine) throws IOException {
        this.gameEngine = gEngine;
        this.gameEngine.setGameThread(this);
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public GameEngine getGameEngine() {
        return this.gameEngine;
    }

    public void start() {
        new Thread(gameEngine).start();
    }

    public void victoryGame() {
        this.interrupt();
        gameEngine.stop();
        this.gameEngine.getMenuFactory().victoryMenu(gameEngine.getScore()).createMenuFrame();
    }

    public void loosingGame() {
        interrupt();
        gameEngine.stop();
        this.gameEngine.getMenuFactory().gameOverMenu().createMenuFrame();
    }

}
