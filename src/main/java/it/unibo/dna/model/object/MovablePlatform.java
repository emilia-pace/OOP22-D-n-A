package it.unibo.dna.model.object;


import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;

/**
 * A platform that can be moved by a button or a lever.
 */
public class MovablePlatform extends AbstractMovableEntity {

    private Position2d originalPos;
    private Position2d finalPos;
    private Vector2d lastVector;

    /**
     * 
     * @param pos        the position of the platform
     * @param vet        the vector of the platform
     * @param height     the height of the platform
     * @param width      the width of the platform
     * @param finalPos   the final position of the platform
     */
    public MovablePlatform(final Position2d pos, final Vector2d vet, final double height, final double width,
                            final Position2d finalPos) { // costruttore
        super(pos, vet, height, width);
        this.originalPos = pos;
        this.finalPos = finalPos;
        this.lastVector = new Vector2d(0, 0);
    }

    /**
     * 
     * @return the final position of the platform
     */
    public Position2d getFinalPosition() {
        return this.finalPos;
    }

    /**
     * A setter for the final position of the platform.
     * 
     * @param fp the final position of the platform
     */
    public void setFinalPosition(final Position2d fp) {
        this.finalPos = fp;
    }

    /**
     * 
     * @return the original position of the platform
     */
    public Position2d getOriginalPos() {
        return this.originalPos;
    }

    /**
     * A setter for the original position fo the platform.
     * 
     * @param op the original position of the platform
     */
    public void setOriginalPos(final Position2d op) {
        this.originalPos = op;
    }

    public Vector2d getLastVector(){
        return this.lastVector;
    }

    public void setLastVector(final Vector2d v){
        this.lastVector = v;
    }

    /**
     * A method that finds the direction in which the platform needs to move.
     * 
     * @param p1 the starting position of the platform
     * @param p2 the position the platform wants to reach
     */
    public void findVector(final Position2d p1, final Position2d p2) {
        if(p2.isOnTheRight(p1)){
            this.setVectorX(+1.0);
        }else if(p1.isOnTheRight(p2)){
            this.setVectorX(-1.0);
        }else {
            this.setVectorX(0);
        }
        if(p2.isAbove(p1)){
            this.setVectorY(-1.0);
        }else if(p1.isAbove(p2)){
            this.setVectorY(1.0);
        }else{
            this.setVectorY(0);
        }
    }

    /**
     * A method that allows the platform to move from a starting point to a final
     * point.
     * 
     * @param pos1 the starting position of the platform
     * @param pos2 the final position that the platform wants to reach
     */
    public void move(final Position2d pos1, final Position2d pos2) {
        this.lastVector = this.getVector();
        findVector(pos1, pos2);
    }

    /**
     * Checks whether the platform has gone out of range on the x-axis.
     */
    public void checkHorizontal(){
        if(this.originalPos.isBetweenHorizontally(this.getFinalPosition(), this.getPosition())){
            this.setPositionX(this.getOriginalPos().x); 
        } else if (this.finalPos.isBetweenHorizontally(this.getPosition(), this.getOriginalPos())) {
            this.setPositionX(this.getFinalPosition().x);
        } else if (this.originalPos.isBetweenHorizontally(this.getPosition(), this.getFinalPosition())) {
            this.setPositionX(this.getOriginalPos().x);
        } else if (this.finalPos.isBetweenHorizontally(this.getOriginalPos(), this.getPosition())) {
            this.setPositionX(this.getFinalPosition().x);
        }
    }

    /**
     * Checks whether the platform has gone out of ranfe on the y-axis.
     */
    public void checkVertical() {
        if (this.originalPos.isBetweenVertically(this.getFinalPosition(), this.getPosition())) {
            this.setPositionY(this.getOriginalPos().y);
        } else if (this.finalPos.isBetweenVertically(this.getPosition(), this.getOriginalPos())) {
            this.setPositionY(this.getFinalPosition().y);
        } else if (this.originalPos.isBetweenVertically(this.getPosition(), this.getFinalPosition())) {
            this.setPositionY(this.getOriginalPos().y);
        } else if (this.finalPos.isBetweenVertically(this.getOriginalPos(), this.getPosition())) {
            this.setPositionY(this.getFinalPosition().y);
        }
    }

    /**
     * A method that tells if the originalPosition of the platfor is on the left, on the right,
     *  above or below the finalPosition of the platform.
     */
    public void findLimit() {
        Position2d firstPos = this.getPosition();
        checkVertical();
        checkHorizontal();
        if(!this.getPosition().equals(firstPos)){
            this.setVector(new Vector2d(0, 0));
        }
    }

}
