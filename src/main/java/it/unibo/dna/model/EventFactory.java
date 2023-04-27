package it.unibo.dna.model;

import it.unibo.dna.model.object.Button;
import it.unibo.dna.model.object.Diamond;
import it.unibo.dna.model.object.Door;
import it.unibo.dna.model.object.Lever;

/**
 * Interface of a factory that creates {@link Event}.
 */
public interface EventFactory {
    
    /**
     * Models the collision event with a button.
     * @param b the hit button
     * @return the new event
     */
    Event hitButtonEvent(Button b);

    /**
     * Models the collision event with a door.
     * @param d the hit door
     * @return the new event
     */
    Event hitDoorEvent(Door d);

    /**
     * Models the collision event with a lever.
     * @param l the hit lever
     * @return the new event
     */
    Event hitLeverEvent(Lever l);

    /**
     * Models the collision event with a diamond.
     * @param d the hit diamond
     * @param s the actual score
     * @return the new event
     */
    Event hitDiamondEvent(Diamond d, Score s);
}
