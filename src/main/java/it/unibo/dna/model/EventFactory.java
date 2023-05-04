package it.unibo.dna.model;

import it.unibo.dna.model.object.Diamond;
import it.unibo.dna.model.object.Door;
import it.unibo.dna.model.object.Platform;
import it.unibo.dna.model.object.api.Player;

/**
 * Interface of a factory that creates {@link Event}.
 */
public interface EventFactory {

    /**
     * Models the collision event with a platform.
     * @param l the hit door
     * @return the new event
     */
    Event hitPlatformEvent(Platform pt, Player p);

    /**
     * Models the collision event with the horizontal borders.
     * @return the new event
     */
    Event hitBorderXEvent(Player p);

    /**
     * Models the collision event with the vertical borders.
     * @return the new event
     */
    Event hitBorderYEvent(Player p);

    /**
     * Models the collision event with a button.
     * @param b the hit button
     * @return the new event
     */
    Event hitButtonEvent();

    /**
     * Models the collision event with a door.
     * @param l the hit door
     * @return the new event
     */
    Event hitDoorEvent(Door d, Player p);

    /**
     * Models the collision event with a lever.
     * @param l the hit lever
     * @return the new event
     */
    Event hitLeverEvent();

    /**
     * Models the collision event with a diamond.
     * @param d the hit diamond
     * @param s the actual score
     * @return the new event
     */
    Event hitDiamondEvent(Diamond d, Score s);
}
