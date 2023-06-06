package it.unibo.dna.model;

/**
 * Class that manages the score of the game.
 */
public class Score {
 
    private static double total;

    /**
     * {@link Score} constructor.
     * @param value the value of the diamond
     */
    public Score(final Double value) {
        total = value;
    }

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
     * @return the value of the current score
     */
    public static double addScore(final double value) {
        return total + value;
    }

    /**
     * reset the score.
     */
    public static void resetScore() {
        total = 0;
    }
}
