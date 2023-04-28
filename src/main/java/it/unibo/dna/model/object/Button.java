package it.unibo.dna.model.object;

import it.unibo.dna.common.Pair;
import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.RectBoundingBox;
import it.unibo.dna.model.object.api.BoundingBox;

/**
 * {@link Button} that moves its corresponding platform when pushed by the player.
 */
public class Button implements GameObject{

    private Position2d pos;
    private double height;
    private double width;
    private BoundingBox box;
    private boolean isActive=false;

    private MovablePlatform mp;


    /**
     * 
     * @param pos the position of the button
     * @param mp the {@link MovablePlatform} that the button moves
     * @param box the {@link BoundingBox} that expresses the height and width of the button
     */
    public Button(final Position2d pos, final MovablePlatform mp, final BoundingBox box) {
        this.pos = pos;
        this.mp = mp;
        this.box = box;
    }


    /**
     * {@inheritDoc Position2d}
     */
    @Override
    public Position2d getPosition() {
        return this.pos;
    }

    /** 
     * @return the position of the button
     */
    public void setPosition(Position2d position) {
        this.pos=position;
    }

    /** 
     * @return the {@link MovablePlatform} controlled by the button
     */
    public MovablePlatform getMovablePlatform() {
        return this.mp;
    }

    /** 
     * A setter for the MovablePlatform controlled by the button.
     */
    public void setMovablePlatform(MovablePlatform m) {
        this.mp=m;
    }

    /** 
     * {@inheritDoc BoudingBox}
     */
    @Override
    public BoundingBox getBoundingBox() {
        return this.box;
    }

    /** 
     * A method that moves the platform of the button as long as it is being pressed. 
     */
    public void activate(){
        while(/*collisione con il bottone*/){
            isActive=true;
            Pair<Double,Double> dir = mp.findDirection(mp.getPosition(), mp.getFinalPosition());
            mp.setPosition(new Position2d(mp.getPosition().x+dir.getX(), mp.getPosition().y+dir.getY()));
        }
        isActive=false;
        mp.move(mp.getPosition(), mp.getOriginalPos());
    }

    /**
     * @return wether the button is being pushed
     */
    @Override
    public boolean isActivated() {
        return this.isActive;
    }
    
}
