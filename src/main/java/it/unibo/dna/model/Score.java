package it.unibo.dna.model;

/**
 * Class that manages the score of the game.
 */
public class Score {
 
    private double total;

    /**
     * {@link Score} constructor.
     */
    public Score(Double value) {
        this.total = value;
    }

    /**
     * 
     * @return the total score
     */
    public double getTotal() {
        return this.total;
    }

    /**
     * 
     * @param value the value of the total score
     */
    public void setTotal(final double value) {
        this.total = value;
    }

    /**
     * 
     * @param value the value to add to the total score
     */
    public double addScore(final double value) {
        return this.total + value;
    }

    /**
     * reset the score.
     */
    public void resetScore() {
        this.total = 0;
    }
}
