package it.unibo.dna.model;

import it.unibo.dna.common.Position2d;

public class RectBoundingBox implements BoundingBox{

    private Position2d position;
    private double height;
    private double lenght;

    public RectBoundingBox(final Position2d p, final double h, final double l){
        this.position=p;
        this.height=h;
        this.lenght=l;
    }


    public Position2d getPosition() {
        return this.position;
    }

    public void setPosition(final Position2d position) {
        this.position = position;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(final double height) {
        this.height = height;
    }

    public double getLenght() {
        return this.lenght;
    }

    public void setLenght(final double lenght) {
        this.lenght = lenght;
    }
    
    

    @Override
    public boolean isCollidingWith(final Position2d p, final double h, final double l) {
        //posizione = angolo in alto a sinistra
        return this.position.x + this.lenght >= p.x 
            && this.position.x <= p.x + l
            && this.position.y >= p.y - h
            && this.position.y - this.height <= p.y;
    }
    
}
