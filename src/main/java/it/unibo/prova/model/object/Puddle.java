package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;

public class Puddle implements Entity{

    public static enum puddleType {PURPLE, BLUE, RED}


    private Position2d pos;
    private puddleType type;

    public Puddle(Position2d pos, puddleType type){
        this.pos=pos;
        this.type=type;
    }

    public puddleType getPuddleType(){
        return type;
    }


    @Override
    public Position2d getPosition() {
        return pos;
    }

    public void checkForMatch(puddleType type, Character character){
        switch (type){
            case PURPLE: //the character that fell in the puddle dies. 
            case BLUE: /*if(character.getType().equals(characterEnum.DEVIL)){
                character dies
            }//altrimenti tutto ok*/
            case RED: /*if(character.getType().equals(characterEnum.ANGEL)){
                angel dies
            }*/
        }
    }
    
}
