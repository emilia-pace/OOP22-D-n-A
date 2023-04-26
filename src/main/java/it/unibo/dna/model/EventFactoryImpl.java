package it.unibo.dna.model;

import it.unibo.dna.model.object.Button;
import it.unibo.dna.model.object.Diamond;
import it.unibo.dna.model.object.Door;
import it.unibo.dna.model.object.Lever;

public class EventFactoryImpl implements EventFactory {

    @Override
    public Event hitButtonEvent(Button b) {
        return game -> {
            b.activate();
        };
    }

    @Override
    public Event hitDoorEvent(Door d, Player p) {
        return game -> {
            d.openDoor(p);
        };
    }

    @Override
    public Event hitLeverEvent(Lever l) {
        return game -> {
            if(l.isActivated()){
                l.deactivate();
            }else{
                l.activate();
            }
        };
    }

    @Override
    public Event hitDiamondEvent(Diamond d, Score s) {
        return game -> {
            game.removeEntity(d);
            s.addScore(d.getValue());
        };
    }
    
}
