package it.unibo.dna;

import java.io.IOException;

import it.unibo.dna.graphics.MenuFactory;
import it.unibo.dna.graphics.MenuFactoryImpl;

public class Launcher {
    private static GameThread gameThread;
    private static GameEngine gameEngine;
    static MenuFactory menuFactory;

    public static void main(String[] args) throws IOException {
        gameEngine = new GameEngine(1);
        gameThread = new GameThread(gameEngine);
        gameEngine.setGameThread(gameThread);
        menuFactory = gameEngine.getMenuFactory();
        menuFactory.startMenu().createMenuFrame();
    }


}
