package it.unibo.dna.controller.core;

import java.io.IOException;

import it.unibo.dna.view.menu.api.MenuFactory;

/**
 * The main launcher class for starting the game.
 */
public final class Launcher {
    private static GameThread gameThread;
    private static GameEngineImpl gameEngine;
    private static MenuFactory menuFactory;

    private Launcher() {
    }

    /**
     * The main entry point of the game.
     * 
     * @param args command-line arguments.
     * @throws IOException if an I/O error occurs while loading the game.
     */
    public static void main(final String[] args) throws IOException {
        gameEngine = new GameEngineImpl(1);
        gameThread = new GameThread(gameEngine);
        GameEngineImpl.setGameThread(gameThread);
        menuFactory = gameEngine.getMenuFactory();
        menuFactory.startMenu().createMenuFrame();
    }
}
