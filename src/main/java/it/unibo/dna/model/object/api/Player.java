package it.unibo.dna.model.object.api;

import it.unibo.dna.common.Pair;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.object.PlayerImpl.State;
import it.unibo.dna.model.object.PlayerImpl.Type;

public interface Player extends Entity {

    /**
     *
     */
    public static final double JumpVelocity = 25;


    /**
     *
     */
    public static final double StandardVelocity = 2;

    /**
     * @return the player's vector
     */
    Vector2d getVector();

    /**
     * @param vet the new player's vector
     */
    void setVector(Vector2d vet);

    /**
     * @param x the first value of the player's vector
     */
    void setVectorX(double x);

    /**
     * @param y the second value of the player's vector
     */
    void setVectorY(double y);

    /**
     * Make the first value of the player's vector zero
     */
    void resetX();

    /**
     * Make the second value of the player's vector zero
     */
    void resetY();

    /**
     * @return the player's state
     */
    Pair<State, State> getState();

    /**
     * @return the player's type
     */
    Type getType();

    /**
     * 
     */
    void update();

    /**
     * Compares two player, and returns true if the players
     * have the same type, and false if not.
     * 
     * @param p player for the comparison
     * @return true if p has the same type of player
     */
    boolean equals(Player p);

}
