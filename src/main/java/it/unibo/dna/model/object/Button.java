package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;

public class Button implements GameObject{

    private Position2d pos;
    private boolean isActive = false;
    private boolean isPressed = false;
    private boolean isPushed = false;
    private static Integer amount = 5;

    private MovablePlatform mp;

    public Button(Position2d pos, MovablePlatform mp) {
        this.pos=pos;
        this.mp=mp;
    }

    @Override
    public Position2d getPosition() {
        return pos;
    }

    public void setPosition(Position2d position) {
        pos=position;
    }

    public MovablePlatform getMovablePlatform() {
        return mp;
    }

    public void setMovablePlatform(MovablePlatform m) {
        mp=m;
    }

    @Override
    public void enable() { //abilita il bottone, e tutte le sue funzionalità
        if(!isActive){
            isActive=true;
        }
    }

    @Override
    public void disable() { // disabilita il bottone, e tutte le sue funzionalità
        if(isActive){
            isActive=false;
        }
    }

    @Override
    public boolean isActivated() { // dice se il bottone è abilitato o meno
        return isActive;
    }

    public void push(){ //bottone spinto. La piattaforma si muove fintanto che il bottone sta venendo spinto. 
                        //Quando si scende dal bottone, la piattaforma torna alla sua pos originale:
        while(isPushed && !mp.getPosition().equals(mp.getFinalPosition())){
            mp.setPosition(new Position2d(mp.getPosition().x+1, mp.getPosition().x));
        }

    }

    public void returnToOriginalPosition(){ 
        while(!isPushed && !mp.getPosition().equals(mp.getOriginalPos())){
            mp.setPosition(new Position2d(mp.getPosition().x-1, mp.getPosition().y));
        }
    }
    
}
