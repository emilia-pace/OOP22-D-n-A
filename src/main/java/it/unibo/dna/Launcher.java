package it.unibo.dna;

import java.io.IOException;

public class Launcher {
    static GameThread gameThread;

    public static void main(String[] args) throws IOException {
        gameThread = new GameThread(new GameEngine(1));
        gameThread.startGame();
    }


}
