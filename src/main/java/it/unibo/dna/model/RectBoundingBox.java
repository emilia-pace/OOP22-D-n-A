package it.unibo.dna.model;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.api.BoundingBox;

/**
 * Class that implements the {@link BoundingBox} interface with a rectangular
 * shape.
 */
public class RectBoundingBox implements BoundingBox {

    private Position2d position;
    private double height;
    private double width;

    /**
     * 
     * @param p the position of the box
     * @param h the height of the box
     * @param w the width of the box
     */
    public RectBoundingBox(final Position2d p, final double h, final double w) {
        this.position = p;
        this.height = h;
        this.width = w;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position2d getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getHeight() {
        return this.height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getWidth() {
        return this.width;
    }

    /**
     * 
     * @param position
     */
    public void setPosition(final Position2d position) {
        this.position = position;
    }

    /**
     * 
     * @param height the height of the box
     */
    public void setHeight(final double height) {
        this.height = height;
    }

    /**
     * 
     * @param width the width of the box
     */
    public void setWidth(final double width) {
        this.width = width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCollidingWith(final Position2d p, final double h, final double w) {
        return this.position.x + this.width >= p.x
                && this.position.x <= p.x + w
                && this.position.y + this.height >= p.y
                && this.position.y <= p.y + h;
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sideCollision(final Position2d p, final double h, final double w) {
        double eps = this.position.x + this.width - p.x;
        double eps1 = this.position.x - (p.x + w);

        return this.isCollidingWith(p, h, w) 
                && (Math.abs(eps) <= Double.MIN_NORMAL || Math.abs(eps1) <= Double.MIN_NORMAL);

    }


}
