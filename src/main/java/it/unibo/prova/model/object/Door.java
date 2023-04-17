package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;

public class Door implements Entity{

    private Position2d pos;

    public Door(Position2d pos){
        
    }

    @Override
    public Position2d getPosition() {
        return pos;
    }

    public void openDoor(){
        
    }
    
}
