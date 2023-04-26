package it.unibo.dna.model.object;

import it.unibo.dna.common.Pair;
import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.api.BoundingBox;

public class MovablePlatform extends AbstractEntity { //cambia le posizioi, (0,0) in alto a sinistra

    private Position2d pos;
    private Position2d originalPos;
    private Position2d finalPos;
    private BoundingBox box;

    public MovablePlatform(final Position2d pos, final double height, final double width, final Position2d originaPos, final Position2d finalPos, final BoundingBox box) {//costruttore
        super(pos, height, width);
        this.originalPos=originaPos;
        this.finalPos=finalPos;   
    }

    @Override
    public Position2d getPosition() {
        return this.pos;
    }

    @Override
    public void update() {
        //TODO
    }

    @Override
    public BoundingBox getBoundingBox() {
        return this.box;
    }

    public void setPosition(Position2d p) {
        this.pos = p;
    }

    public Position2d getFinalPosition() {
        return this.finalPos;
    }

    public void setFinalPosition(Position2d fp) {
        this.finalPos = fp;
    }

    public Position2d getOriginalPos() {
        return this.originalPos;
    }

    public void setOriginalPos(Position2d op) {
        this.originalPos = op;
    }

    public Pair<Double,Double> findDirection(Position2d p1, Position2d p2){
        double differenceX = p1.x-p2.x;
        double differenceY = p1.y-p2.y;
        double horizontalMovement=0;
        double verticalMovemet=0;
        if(differenceX != 0){
            if(differenceX > 0){
                horizontalMovement=-1.0;
            }else{
                horizontalMovement=+1.0;
            }
        }
        if(differenceY != 0){
            if(differenceY < 0 ){ //o > ?
                verticalMovemet=-1.0;
            }else{
                verticalMovemet=1.0;
            }
        }
        return new Pair<>(horizontalMovement,verticalMovemet); 
    }

    public void move(Position2d startingPosition, Position2d finalPosition){
        Pair<Double,Double> p = findDirection(startingPosition, finalPosition);
        while(!pos.equals(finalPosition)){
            pos=new Position2d(pos.x+p.getX(),pos.y+p.getY());
        }
    }

}
