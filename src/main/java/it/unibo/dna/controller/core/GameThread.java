package it.unibo.dna.controller.core;

import java.io.IOException;

public class GameThread extends Thread {
    private GameEngine gameEngine;
    private int lvl;

    public GameThread(final GameEngine gEngine) throws IOException {
        this.gameEngine = gEngine;
        this.gameEngine.setGameThread(this);
    }

    public void setGameEngine(final GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public GameEngine getGameEngine() {
        return this.gameEngine;
    }

    public void start() {
        new Thread(gameEngine).start();
    }

    public void victoryGame() {
        lvl = gameEngine.getLvl();
        if(lvl == 3){
            this.gameEngine.getMenuFactory().lastVictoryMenu().createMenuFrame();
        } else {
        lvl++;
        this.gameEngine.getMenuFactory().victoryMenu(lvl).createMenuFrame();
        this.interrupt();
        gameEngine.stop();
        }
    }

    public void loosingGame() {
        lvl = gameEngine.getLvl();
        this.gameEngine.getMenuFactory().gameOverMenu(lvl).createMenuFrame();
        this.interrupt();
        gameEngine.stop();
    }

}
