package it.unibo.dna.model.game.score;

/**
 * Class that manages the score of the game.
 */
public class Score {
 
    private static double total = 0;

    /**
     * 
     * @return the total score
     */
    public static double getTotal() {
        return total;
    }

    /**
     * 
     * @param value the value of the total score
     */
    public static void setTotal(final double value) {
        total = value;
    }

    /**
     * @param value the value to add to the total score
     */
    public static void addScore(final double value) {
        total = total + value;
    }

    /**
     * reset the score.
     */
    public static void resetScore() {
        total = 0;
    }
}
