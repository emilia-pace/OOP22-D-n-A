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
            System.out.println("Il player " + p.getType() + " ha toccato la porta");
            if (p.getPosition().y + p.getBoundingBox().getHeight() <= d.getPosition().y)
             {
            p.setVectorY(0);
            }
            System.out.println(
                    "Il player è in posizione: " + p.getPosition() + " e il vettore " + p.getVector()
                            + " \ne la porta è in posizone " + d.getPosition());
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

}
