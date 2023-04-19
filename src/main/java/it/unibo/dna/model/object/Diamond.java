package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.api.BoundingBox;
import it.unibo.dna.model.object.api.Entity;

public class Diamond implements Entity {

    private double value = 0;
    private BoundingBox box;
    private Position2d position;

    public Diamond(final BoundingBox b, final double v, final Position2d p) {
        this.value = v;
        this.box = b;
        this.position = p;
    }

    public double getValue() {
        return this.value;
    }


    @Override
    public Position2d getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Position2d pos) {
        this.position= pos;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return this.box;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
