package it.unibo.dna.model.events.api;

import java.util.List;

import it.unibo.dna.model.object.movableentity.MovablePlatform;
import it.unibo.dna.model.object.player.Entity;
import it.unibo.dna.model.object.player.api.Player;
import it.unibo.dna.model.object.stillentity.impl.ActivableObjectImpl;
import it.unibo.dna.model.object.stillentity.impl.Diamond;
import it.unibo.dna.model.object.stillentity.impl.Door;
import it.unibo.dna.model.object.stillentity.impl.Puddle;

/**
 * Interface of a factory that creates {@link Event}.
 */
public interface EventFactory {

    /**
     * Models the collision event with a platform.
     * @param platform the hit platform 
     * @param player the player
     * @return the new event
     */
    Event hitPlatformEvent(Entity platform, Player player);

     /**
     * Models the collision event with a movable platform.
     * @param movablePlatform the hit movable platform 
     * @param player the player
     * @return the new event
     */
    Event hitMovablePlatformEvent(MovablePlatform movablePlatform, Player player);


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
     * @param button the hit button
     * @param player the player
     * @return the new event
     */
    Event hitButtonEvent(ActivableObjectImpl button, Player player);

    /**
     * Models the collision event with a door.
     * @param door the hit door
     * @param player the player
     * @param entities the list of {@link Entity}
     * @return the new event
     */
    Event hitDoorEvent(Door door, Player player);

    /**
     * Models the collision event with a lever.
     * @param lever the hit lever
     * @param player the player
     * @return the new event
     */
    Event hitLeverEvent(ActivableObjectImpl lever, Player player);

    /**
     * Models the collision event with a diamond.
     * @param d the hit diamond
     * @return the new event
     */
    Event hitDiamondEvent(Diamond d);

    /**
     * Models the successful completion of the level.
     * @return the new event
     */
    Event victoryEvent();

    /**
     * Models the failure of the level.
     * @return the new event
     */
    Event gameOverEvent();
}
