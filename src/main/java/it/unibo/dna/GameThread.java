package it.unibo.dna;

import java.io.IOException;

import it.unibo.dna.graphics.MenuFactory;
import it.unibo.dna.graphics.MenuFactoryImpl;
import it.unibo.dna.model.game.impl.GameStateImpl;

public class GameThread extends Thread {
    private GameEngine gameEngine;
    private MenuFactory menuFactory = new MenuFactoryImpl();
    int level = 1;

    public GameThread(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.gameEngine.setGameThread(this);
    }

    public void startGame(){
        menuFactory.startMenu();
    }

    public void victoryGame(){
        gameEngine.stop();
        interrupt();
        level++;
        menuFactory.victoryMenu(GameStateImpl.getScore());
    }

    public void losingGame() {
        gameEngine.stop();
        interrupt();
        menuFactory.gameOverMenu();
    }

    public void nextLevel() throws IOException {
        gameEngine = new GameEngine(level);
        start();
    }

    public void restartLevel() throws IOException {
        gameEngine.stop();
        interrupt();
        gameEngine = new GameEngine(level);
        start();
    }

    

}
