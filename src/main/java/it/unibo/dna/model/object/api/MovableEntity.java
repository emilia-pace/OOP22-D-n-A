package it.unibo.dna.model.object.api;

import it.unibo.dna.common.Vector2d;

/**
 * An Interface that models an Entity that is able to move throught the use of a Vector.
 */
public interface MovableEntity extends Entity{
    
    /**
     * @return the vector of the entity.
     */
    Vector2d getVector();
    
    /**
     * Sets the vector of the Entity.
     * @param vec the vector to be set
     */
    void setVector(Vector2d vec);

    /**
     * Sets the x-axis value of the vector.
     * @param x the x-axis value
     */
    void setVectorX(double x);

    /**
     * Sets the y-axis value of the vector.
     * @param y the y-axis value
     */
    void setVectorY(double y);

    /**
     * Resets the x-axis of the vector.
     */
    //void resetX(); DA GUARDARE

    /**
     * Resets the y-axis of the vector.
     */
    //void resetY(); DA GUARDARE

    /**
     * 
     */
    void update();

}
