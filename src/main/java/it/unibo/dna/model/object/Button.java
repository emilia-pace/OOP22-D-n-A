package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.RectBoundingBox;

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

    @Override
    public RectBoundingBox getBoundingBox() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBoundingBox'");
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}
