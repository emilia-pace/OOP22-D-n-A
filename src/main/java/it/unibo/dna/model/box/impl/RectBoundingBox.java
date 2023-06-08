package it.unibo.dna.model.box.impl;

import it.unibo.dna.model.box.api.BoundingBox;
import it.unibo.dna.model.common.Position2d;

/**
 * Class that implements the {@link BoundingBox} interface with a rectangular
 * shape.
 */
public class RectBoundingBox implements BoundingBox {

    private Position2d position;
    private double height;
    private double width;


    /**
     * {@link RectBoundingBox} constructor.
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
     * {@inheritDoc}
     */
    @Override
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
        return this.position.getX() + this.width >= p.getX()
                && this.position.getX() <= p.getX() + w
                && this.position.getY() + this.height >= p.getY()
                && this.position.getY() <= p.getY() + h;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sideCollision(final Position2d p, final double h, final double w) {
        return this.position.getY() + this.height >= p.getY()
                && this.position.getY() <= p.getY() + h
                && (this.position.getX() + this.width <= p.getX() || this.position.getX() >= p.getX() + w);
    }


}
