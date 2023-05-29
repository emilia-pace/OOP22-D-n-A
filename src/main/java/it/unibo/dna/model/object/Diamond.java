package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;

/**
 * {@link Entity} that allows to increase the game score.
 */
public class Diamond extends  AbstractEntity {

    private double value = 0;

    /**
     * {@link Diamond} constructor.
     * @param h the height of the diamond
     * @param w the width of the diamond
     * @param v the value of the diamond
     * @param p the position of the diamond
     */
    public Diamond(final double h, final double w, final double v, final Position2d p) {
        super(p, h, w);
        this.value = v;
    }

    /**
     * 
     * @return the value of the diamond
     */
    public double getValue() {
        return this.value;
    }
}
