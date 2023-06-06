package it.unibo.dna.model;

import java.util.List;

import it.unibo.dna.model.object.ActivableObjectImpl;
import it.unibo.dna.model.object.Diamond;
import it.unibo.dna.model.object.Door;
import it.unibo.dna.model.object.MovablePlatform;
import it.unibo.dna.model.object.Puddle;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.player.api.Player;

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
     * @param score the score
     * @return the new event
     */
    Event hitDoorEvent(Door door, Player player, Score score, List<Entity> entities);

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
     * @param s the actual score
     * @return the new event
     */
    Event hitDiamondEvent(Diamond d, Score s);

    /**
     * Models the collision event with a puddle.
     * @param puddle the hit puddle
     * @param player the player that touched the puddle
     * @return the new event
     */
    Event hitPuddleEvent(Puddle puddle, Player player, Score score);

    /**
     * @param s
     * @return
     */
    Event soundEvent(String s);

    Event victoryEvent(Score score);

    Event gameOverEvent(Score score);
}
