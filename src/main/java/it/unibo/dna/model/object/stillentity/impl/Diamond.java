package it.unibo.dna.model.object.stillentity.impl;

import it.unibo.dna.model.common.Position2d;
import it.unibo.dna.model.object.entity.api.Entity;

/**
 * {@link Entity} that allows to increase the game score.
 */
public class Diamond extends  AbstractEntity {

    private final double value;

    /**
     * {@link Diamond} constructor.
     * @param h the height of the diamond
     * @param w the width of the diamond
     * @param v the value of the diamond
     * @param p the position of the diamond
     */
    public Diamond(final double h, final double w, final double v, final Position2d p) {
        super(p, h, w, Entity.EntityType.DIAMOND);
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
