package it.unibo.dna;


public class Launcher {
    public static void main(String[] args) {
        new Thread(new GameEngine(new Game(800, 600))).start();
    }
}
