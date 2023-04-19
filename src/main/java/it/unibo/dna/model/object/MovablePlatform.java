package it.unibo.dna.model.object;

import it.unibo.dna.common.Pair;
import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.api.Entity;

public class MovablePlatform extends AbstractEntity { //cambia le posizioi, (0,0) in alto a sinistra

    private Position2d pos;
    private Position2d originalPos;
    private Position2d finalPos;

    public MovablePlatform(Position2d pos, double height, double width) {//costruttore
        super(pos, height, width);
        this.pos=pos;
        //this.height=height;
        //this.width=width;
    }

    @Override
    public Position2d getPosition() {
        return pos;
    }

    @Override
    public void update() {
        
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
            if(differenceY < 0 ){ //o > ?
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
        }
    }

    /*
    FUNZIONE GENERICA PER MUOVERSI DA UN PUNTO DI PARTENZA AD UN PUNTO DI ARRIVO. MENO RIPETITVA DI AVERNE 2, TUTTAVIA NECESSITA DI ARGOMENTI
    public void movement(Position2d startingPosition, Position2d finalPosition){
        Pair<Double,Double> p = findDirection(startingPosition, finalPosition);
        while(!pos.equals(finalPosition)){
            pos=new Position2d(pos.x+p.getX(),pos.y+p.getY());
        }
    }
    */
    

}
