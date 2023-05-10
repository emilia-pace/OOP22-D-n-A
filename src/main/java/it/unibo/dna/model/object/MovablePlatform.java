package it.unibo.dna.model.object;

import it.unibo.dna.common.Pair;
import it.unibo.dna.common.Position2d;

/**
 * A platform that can be moved by a button or a lever.
 */
public class MovablePlatform extends EntityImpl { // cambia le posizioi, (0,0) in alto a sinistra

    private Position2d originalPos;
    private Position2d finalPos;

    /**
     * 
     * @param pos        the position of the platform
     * @param height     the height of the platform
     * @param width      the width of the platform
     * @param originaPos the original position of the platform
     * @param finalPos   the final position of the platform
     */
    public MovablePlatform(final Position2d pos, final double height, final double width, final Position2d finalPos) {// costruttore
        super(pos, height, width);
        this.originalPos = pos;
        this.finalPos = finalPos;
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
    public Pair<Double, Double> findDirection(Position2d p1, Position2d p2) {
        double differenceX = p1.x - p2.x;
        double differenceY = p1.y - p2.y;
        double horizontalMovement = 0;
        double verticalMovemet = 0;
        if (differenceX != 0) {
            if (differenceX > 0) {
                horizontalMovement = -1.0;
            } else {
                horizontalMovement = +1.0;
            }
        }
        if (differenceY != 0) {
            if (differenceY < 0) { // o > ?
                verticalMovemet = -1.0;
            } else {
                verticalMovemet = 1.0;
            }
        }
        return new Pair<>(horizontalMovement, verticalMovemet);
    }

    /**
     * A method that allows the platform to move from a starting point to a final
     * point.
     * 
     * @param startingPosition the starting position of the platform
     * @param finalPosition    the final position that the platform wants to reach
     */
    public void move(Position2d startingPosition, Position2d finalPosition) {
        this.setPosition(finalPosition);
    }

}
