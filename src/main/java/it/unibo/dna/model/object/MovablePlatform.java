package it.unibo.dna.model.object;


import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;

/**
 * A platform that can be moved by a button or a lever.
 */
public class MovablePlatform extends MovableEntityImpl {

    private Position2d originalPos;
    private Position2d finalPos;
    private boolean isComingBack;

    /**
     * 
     * @param pos        the position of the platform
     * @param height     the height of the platform
     * @param width      the width of the platform
     * @param originaPos the original position of the platform
     * @param finalPos   the final position of the platform
     */
    public MovablePlatform(final Position2d pos, final Vector2d vet, final double height, final double width, final Position2d finalPos) {// costruttore
        super(pos, vet, height, width);
        this.originalPos = pos;
        this.finalPos = finalPos;
        this.isComingBack = false;
    }

    public boolean platformIsComingBack(){
        return this.isComingBack;
    }

    public void setIsComingBack(final boolean b){
        this.isComingBack = b;
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
    public void setFinalPosition(Position2d fp) {
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
    public void setOriginalPos(Position2d op) {
        this.originalPos = op;
    }

    /**
     * A method that finds the direction in which the platform needs to move.
     * 
     * @param p1 the starting position of the platform
     * @param p2 the position the platform wants to reach
     * @return a pair of coordinates that explain the movement the platform must do.
     */
    public void findVector(Position2d p1, Position2d p2) {
        double differenceX = p1.x - p2.x;
        double differenceY = p1.y - p2.y;
        if(differenceX > 0){
            this.setVectorX(-1.0);
        }else if(differenceX < 0){
            this.setVectorX(+1.0);
        }else{
            this.setVectorX(0);
        }
        if (differenceY < 0) {
            this.setVectorY(+1.0);
        }else if(differenceY > 0){
            this.setVectorY(-1.0);
        }else{
            this.setVectorY(0);
        }
    }

    /**
     * A method that allows the platform to move from a starting point to a final
     * point.
     * 
     * @param startingPosition the starting position of the platform
     * @param finalPosition    the final position that the platform wants to reach
     */
    public void move(final Position2d pos1, final Position2d pos2) {
            findVector(pos1, pos2);
    }

    public void update(){
        if(this.getPosition().equals(this.getOriginalPos()) || this.getPosition().equals(this.getFinalPosition())){
            this.setVector(new Vector2d(0, 0));
        }
        super.update();
    }

}
