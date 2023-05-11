package it.unibo.dna.model;

import it.unibo.dna.model.object.ActivableObject;
import it.unibo.dna.model.object.Diamond;
import it.unibo.dna.model.object.Door;
import it.unibo.dna.model.object.MovablePlatform;
import it.unibo.dna.model.object.PlayerImpl.State;
import it.unibo.dna.model.object.api.Player;
import it.unibo.dna.model.object.api.Entity;

/**
 * Class that implements the {@link EventFactory} interface.
 */
public class EventFactoryImpl implements EventFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitPlatformEvent(Entity pt, Player p) {
        return game -> {
            p.resetY();
            if (p.getBoundingBox().isCollidingWith(pt.getPosition(), pt.getBoundingBox().getHeight(),
                    pt.getBoundingBox().getWidth())) {
                p.resetX();
            }
            p.getState().setX(State.STATE_STANDING);

        };
    }

    public Event hitMovablePlatformEvent(MovablePlatform pt, Player p){
        return game -> {

            this.hitPlatformEvent(pt, p).manage(game);
            p.getVector().sumX(pt.getVector().x);
            p.getVector().sumY(pt.getVector().y);
            
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitButtonEvent(ActivableObject o, Player p) {
        return game -> {
            if (o.getPlayer().isEmpty()) {
                System.out.println(o.getMovablePlatform().getPosition());
                o.setPlayer(p);
                o.activate();
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitDoorEvent(Door d, Player p) {
        return game -> {
            d.openDoor(p);
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitLeverEvent(ActivableObject o, Player p) {
        return game -> {
            System.out.println(o.getMovablePlatform().getPosition());
            if (o.getPlayer().isEmpty()) {
                if (o.isActivated()) {
                    o.deactivate();
                } else {
                    o.activate();
                }
                o.setPlayer(p);
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitDiamondEvent(Diamond d, Score s) {
        return game -> {
            game.removeEntity(d);
            s.addScore(d.getValue());
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitBorderXEvent(Player p) {
        return game -> {
            p.resetY();
            p.getState().setX(State.STATE_STANDING);
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitBorderYEvent(Player p) {
        return game -> {
            p.resetX();
        };
    }

}
