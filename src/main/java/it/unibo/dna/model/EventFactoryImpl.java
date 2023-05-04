package it.unibo.dna.model;

import it.unibo.dna.model.object.Button;
import it.unibo.dna.model.object.Diamond;
import it.unibo.dna.model.object.Door;
import it.unibo.dna.model.object.Lever;
import it.unibo.dna.model.object.api.Player;

/**
 * Class that implements the {@link EventFactory} interface.
 */
public class EventFactoryImpl implements EventFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitButtonEvent(Button b) {
        return game -> {
            b.activate();
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitDoorEvent(Door d, Player p) {
        return game -> {
            d.openDoor(p);
            p.resetY();
            if(p.getBoundingBox().isCollidingWith(d.getPosition(), d.getBoundingBox().getHeight(), d.getBoundingBox().getWidth())){
                p.resetX();
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitLeverEvent(Lever l) {
        return game -> {
            if (l.isActivated()) {
                l.deactivate();
            } else {
                l.activate();
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
