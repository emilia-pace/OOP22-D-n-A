package it.unibo.dna.model;

import it.unibo.dna.common.Position2d;

public class RectBoundingBox implements BoundingBox{

    private Position2d position;
    private double height;
    private double lenght;

    public RectBoundingBox(Position2d p, double h, double l){
        this.position=p;
        this.height=h;
        this.lenght=l;
    }


    @Override
    public boolean isCollidingWith(Position2d p, double h, double l) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
