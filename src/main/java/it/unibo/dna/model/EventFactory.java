package it.unibo.dna.model;

import it.unibo.dna.model.object.ActivableObject;
import it.unibo.dna.model.object.Diamond;
import it.unibo.dna.model.object.Door;
import it.unibo.dna.model.object.MovablePlatform;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.api.Player;

/**
 * Interface of a factory that creates {@link Event}.
 */
public interface EventFactory {

    /**
     * Models the collision event with a platform.
     * @param pt the hit platform 
     * @param p the player
     * @return the new event
     */
    Event hitPlatformEvent(Entity pt, Player p);

     /**
     * Models the collision event with a movable platform.
     * @param pt the hit movable platform 
     * @param p the player
     * @return the new event
     */
    Event hitMovablePlatformEvent(MovablePlatform pt, Player p);


    /**
     * Models the collision event with the horizontal borders.
     * @param p the player
     * @return the new event
     */
    Event hitBorderXEvent(Player p);

    /**
     * Models the collision event with the vertical borders.
     * @param p the player
     * @return the new event
     */
    Event hitBorderYEvent(Player p);

    /**
     * Models the collision event with a button.
     * @param o the hit button
     * @param p the player
     * @return the new event
     */
    Event hitButtonEvent(ActivableObject o, Player p);

    /**
     * Models the collision event with a door.
     * @param d the hit door
     * @param p the player
     * @return the new event
     */
    Event hitDoorEvent(Door d, Player p);

    /**
     * Models the collision event with a lever.
     * @param o the hit lever
     * @param p the player
     * @return the new event
     */
    Event hitLeverEvent(ActivableObject o, Player p);

    /**
     * Models the collision event with a diamond.
     * @param d the hit diamond
     * @param s the actual score
     * @return the new event
     */
    Event hitDiamondEvent(Diamond d, Score s);

    Event soundEvent(String s);
}
