package it.unibo.dna.model.object;


import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;

/**
 * A platform that can be moved by a button or a lever.
 */
public class MovablePlatform extends AbstractMovableEntity {

    private Position2d originalPos;
    private Position2d finalPos;
    private Position2d lastPos;
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
                            final Position2d finalPos) {
        super(pos, vet, height, width);
        this.originalPos = pos;
        this.lastPos = pos;
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
     * A method that finds the direction in which the platform needs to move, 
     * and sets the vector accordingly.
     * 
     * @param p1 the starting position of the platform
     * @param p2 the position the platform wants to reach
     */
    public void findVector(final Position2d p1, final Position2d p2) {
        double x = 0.0;
        double y = 0.0;
        if(p1.x != p2.x){
            x = p2.isOnTheRight(p1) ? +1.0 : -1.0;
        }
        if(p1.y != p2.y){
            y = p2.isAbove(p1) ? -1.0 : +1.0;
        } 
        this.setVector(new Vector2d(x, y));
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
     * Saves the last position had by the platform, in order not to lose it once the position of 
     * the platform has been updated.
     */
    public void setLastPosition() {
        this.lastPos = this.getPosition();
    }

    /*
     * Checks whether the platform position is between its original position and its final position.
     */
    private boolean isBetweenRange() {
        double maxX = Math.max(this.originalPos.x,this.finalPos.x);
        double minX =  Math.min(this.originalPos.x,this.originalPos.y);
        double maxY = Math.max(this.originalPos.y,this.finalPos.y);
        double minY = Math.min(this.originalPos.y,this.finalPos.y);
        return this.getPosition().x >= minX && this.getPosition().x <= maxX && this.getPosition().y <= maxY && this.getPosition().y >= minY;
    }

    /**
     * A method that tells if the originalPosition of the platfor is on the left, on the right,
     *  above or below the finalPosition of the platform.
     */
    public void findLimit() {
        if(!isBetweenRange()){
            this.setPosition(this.lastPos);
            this.setVector(new Vector2d(0, 0));
        }
    }

}
