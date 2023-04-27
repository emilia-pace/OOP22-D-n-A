package it.unibo.dna.model;

import it.unibo.dna.model.object.Button;
import it.unibo.dna.model.object.Diamond;
import it.unibo.dna.model.object.Door;
import it.unibo.dna.model.object.Lever;

/**
 * Class that implements the {@link EventFactory} interface.
 */
public class EventFactoryImpl implements EventFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitButtonEvent(Button b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hitButtonEvent'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitDoorEvent(Door d) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hitDoorEvent'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitLeverEvent(Lever l) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hitLeverEvent'");
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
