package it.unibo.dna.model.object;

import it.unibo.dna.common.Pair;
import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.RectBoundingBox;
import it.unibo.dna.model.object.api.BoundingBox;

public class Button implements GameObject{

    private Position2d pos;
    private boolean isActive = false;
    private boolean isPressed = false;
    private boolean isPushed = false;
    //private static Integer amount = 5;
    private double height;
    private double width;
    private BoundingBox bbox;

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
    public boolean isActivated() { // dice se il bottone è abilitato o meno
        return isActive;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return new RectBoundingBox(pos, width, height);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    public void activate(PlayerImpl p){
        while(/*collisione con il bottone*/){
            Pair<Double,Double> dir = mp.findDirection(mp.getPosition(), mp.getFinalPosition());
            mp.setPosition(new Position2d(mp.getPosition().x+dir.getX(), mp.getPosition().y+dir.getY()));
        }
        mp.move(mp.getPosition(), mp.getOriginalPos());
    }
    
}
