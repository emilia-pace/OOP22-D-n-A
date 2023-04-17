package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;

public class MovablePlatform implements Entity{

    private Position2d pos;

    @Override
    public Position2d getPosition() {
        return pos;
    }

    public void move(double amount){
        for(int i=0;i<amount;i++){
            pos=new Position2d(pos.x+amount, pos.y);
        }
    }
    
}
