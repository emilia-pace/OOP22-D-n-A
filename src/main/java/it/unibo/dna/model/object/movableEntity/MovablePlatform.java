package it.unibo.dna.model.object.movableEntity;



import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.object.api.Entity;

/**
 * A platform that can be moved by a button or a lever.
 */
public class MovablePlatform extends AbstractMovableEntity {

    private Position2d originalPosition;
    private Position2d finalPosition;
    private Position2d lastPosition;
    private Vector2d previousVector;

    /**
     * 
     * @param position        the position of the platform
     * @param vector        the vector of the platform
     * @param height     the height of the platform
     * @param width      the width of the platform
     * @param finalPosition   the final position of the platform
     */
    public MovablePlatform(final Position2d position, final Vector2d vector, final double height, final double width,
                            final Position2d finalPosition) {
        super(position, vector, height, width, Entity.EntityType.MOVABLEPLATFORM);
        this.originalPosition = position;
        this.lastPosition = position;
        this.finalPosition = finalPosition;
        this.previousVector = new Vector2d(0, 0);
    }

    /**
     * 
     * @return the final position of the MovablePlatform
     */
    public Position2d getFinalPosition() {
        return this.finalPosition;
    }

    /**
     * A setter for the final position of the platform.
     * @param finalPosition the final position of the platform
     */
    public void setFinalPosition(final Position2d finalPosition) {
        this.finalPosition = finalPosition;
    }

    /**
     * 
     * @return the original position of the platform
     */
    public Position2d getOriginalPosition() {
        return this.originalPosition;
    }

    /**
     * A setter for the original position fo the platform.
     * 
     * @param originalPosition the original position of the platform
     */
    public void setOriginalPosition(final Position2d originalPosition) {
        this.originalPosition = originalPosition;
    }

    /**
     * A getter for the last vector the platform had.
     * @return the last vector of the platform.
     */
    public Vector2d getPreviousVector() {
        return this.previousVector;
    }

    /**
     * A setter for the last vector of the movableplatform
     * @param previousVector the vector to be set
     */
    public void setPreviousVector(final Vector2d previousVector) {
        this.previousVector = previousVector;
    }

    /**
     * A method that finds the direction in which the platform needs to move, 
     * and sets the vector accordingly.
     * 
     * @param position1 the starting position of the platform
     * @param position2 the position the platform wants to reach
     */
    public void findVector(final Position2d position1, final Position2d position2) {
        double x = 0.0;
        double y = 0.0;
        if (position1.getX() != position2.getX()) {
            x = position2.isOnTheRight(position1) ? +0.5 : -0.5;
        }
        if (position1.getY() != position2.getY()) {
            y = position2.isAbove(position1) ? -0.5 : +0.5;
        } 
        this.setVector(new Vector2d(x, y));
    }

    /**
     * A method that allows the platform to move from a starting point to a final
     * point.
     * 
     * @param position1 the starting position of the platform
     * @param position2 the final position that the platform wants to reach
     */
    public void move(final Position2d position1, final Position2d position2) {
        this.previousVector = this.getVector();
        findVector(position1, position2);
    }

    /**
     * Saves the last position had by the platform, in order not to lose it once the position of 
     * the platform has been updated.
     */
    public void setLastPosition() {
        this.lastPosition = this.getPosition();
    }

    /**
     * Checks whether the platform position is between its original position and its final position.
     * @return false it the platform has gone out of range. 
     */
    public boolean isBetweenRange() {
        double maxX = Math.max(this.originalPosition.getX(), this.finalPosition.getX());
        double minX =  Math.min(this.originalPosition.getX(), this.originalPosition.getY());
        double maxY = Math.max(this.originalPosition.getY(), this.finalPosition.getY());
        double minY = Math.min(this.originalPosition.getY(), this.finalPosition.getY());
        return this.getPosition().getX() >= minX && this.getPosition().getX() <= maxX 
            && this.getPosition().getY() <= maxY && this.getPosition().getY() >= minY;
    }

    /**
     * A method that tells if the originalPosition of the platfor is on the left, on the right,
     *  above or below the finalPosition of the platform.
     */
    public void findLimit() {
        if (!isBetweenRange()) {
            this.setPosition(this.lastPosition);
            this.setVector(new Vector2d(0, 0));
        }
    }

    /**
     * Updates the position of the movablePlatform.
     */
    public void movablePlatformUpdate() {
        this.setLastPosition();
        this.update();
        this.findLimit();
    }

}
