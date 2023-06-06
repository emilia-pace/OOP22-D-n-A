package it.unibo.dna;

public class GameThread extends Thread {
    private GameEngine gameEngine;

    public GameThread(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }


    
    public void startThread(){
        gameEngine.run();
        start();
    }

    public void interruptThread() {
        gameEngine.stop();
        interrupt();
    }

}
