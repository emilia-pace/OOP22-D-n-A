package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;

public class MovablePlatform implements Entity{

    private Position2d pos;
    private Position2d originalPos;
    private Position2d finalPos;

    @Override
    public Position2d getPosition() {
        return pos;
    }

    public void setPosition(Position2d p) {
        pos=p;
    }

    public Position2d getFinalPosition() {
        return finalPos;
    }

    public void setFinalPosition(Position2d fp) {
        finalPos=fp;
    }

    public Position2d getOriginalPos() {
        return originalPos;
    }

    public void setOriginalPos(Position2d op){
        originalPos=op;
    }

    public void move(double amount){
        for(int i=0;i<amount;i++){
            pos=new Position2d(pos.x+amount, pos.y);
        }
    }
    
}
