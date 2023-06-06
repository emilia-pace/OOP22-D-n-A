package it.unibo.dna;

import java.io.IOException;

import it.unibo.dna.graphics.MenuFactory;
import it.unibo.dna.graphics.MenuFactoryImpl;
import it.unibo.dna.model.Score;

public class GameThread extends Thread {
    private GameEngine gameEngine;

    public GameThread(GameEngine gEngine) throws IOException {
        this.gameEngine = gEngine;
        this.gameEngine.setGameThread(this);
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void start() {
        new Thread(gameEngine).start();
    }

    public void victoryGame() {
        interrupt();
        gameEngine.stop();
        this.gameEngine.getMenuFactory().victoryMenu(Score.getTotal()).createMenuFrame();
    }

    public void loosingGame() {
        interrupt();
        gameEngine.stop();
        this.gameEngine.getMenuFactory().gameOverMenu().createMenuFrame();
    }
    

}
