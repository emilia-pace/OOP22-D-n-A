package it.unibo.dna;

import java.io.IOException;

import it.unibo.dna.gameloop.GameEngine;
import it.unibo.dna.gameloop.GameThread;
import it.unibo.dna.graphics.menu.api.MenuFactory;

public class Launcher {
    private static GameThread gameThread;
    private static GameEngine gameEngine;
    private static MenuFactory menuFactory;

    public static void main(final String[] args) throws IOException {
        gameEngine = new GameEngine(1);
        gameThread = new GameThread(gameEngine);
        gameEngine.setGameThread(gameThread);
        menuFactory = gameEngine.getMenuFactory();
        menuFactory.startMenu().createMenuFrame();
    }


}
