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
    private BoundingBox box;

    private MovablePlatform mp;

    public Button(final Position2d pos, final MovablePlatform mp, final BoundingBox box) {
        this.pos = pos;
        this.mp = mp;
        this.box = box;
    }

    @Override
    public Position2d getPosition() {
        return this.pos;
    }

    public void setPosition(Position2d position) {
        this.pos=position;
    }

    public MovablePlatform getMovablePlatform() {
        return this.mp;
    }

    public void setMovablePlatform(MovablePlatform m) {
        this.mp=m;
    }

    @Override
    public boolean isActivated() { // dice se il bottone è abilitato o meno
        return this.isActive;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return this.box;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    public void activate(){
        while(/*collisione con il bottone*/){
            Pair<Double,Double> dir = mp.findDirection(mp.getPosition(), mp.getFinalPosition());
            mp.setPosition(new Position2d(mp.getPosition().x+dir.getX(), mp.getPosition().y+dir.getY()));
        }
        mp.move(mp.getPosition(), mp.getOriginalPos());
    }
    
}
