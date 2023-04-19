package it.unibo.dna.model.object;

import it.unibo.dna.common.Pair;
import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.api.Entity;

public class MovablePlatform implements Entity {

    private Position2d pos;
    private Position2d originalPos;
    private Position2d finalPos;

    @Override
    public Position2d getPosition() {
        return pos;
    }

    public void setPosition(Position2d p) {
        pos = p;
    }

    public Position2d getFinalPosition() {
        return finalPos;
    }

    public void setFinalPosition(Position2d fp) {
        finalPos = fp;
    }

    public Position2d getOriginalPos() {
        return originalPos;
    }

    public void setOriginalPos(Position2d op) {
        originalPos = op;
    }

<<<<<<< HEAD
    private Pair<Double,Double> findDirection(Position2d p1, Position2d p2){
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
            if(differenceY > 0 ){
                verticalMovemet=-1.0;
            }else{
                verticalMovemet=1.0;
            }
        }
        return new Pair<>(horizontalMovement,verticalMovemet); 
    }

    public void move() { //tra move() e returnToOriginalPosition() c'è un'evidente ripetizione. 
        Pair<Double,Double> p = findDirection(originalPos,finalPos);
        while(!pos.equals(finalPos)){
            pos=new Position2d(pos.x+p.getX(), pos.y+p.getY());
        }
    }

    public void returnToOriginalPosition() {
        Pair<Double,Double> p = findDirection(pos, originalPos);
        while(!pos.equals(originalPos)){
            pos=new Position2d(pos.x+p.getX(), pos.y+p.getY());
=======
    public void move(double amount) {
        for (int i = 0; i < amount; i++) {
            pos = new Position2d(pos.x + amount, pos.y);
>>>>>>> bf6c33123c2e9408324c6dcb828f07f673820f17
        }
    }

}
