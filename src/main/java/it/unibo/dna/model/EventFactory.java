package it.unibo.dna.model;

import it.unibo.dna.model.object.Button;
import it.unibo.dna.model.object.Diamond;
import it.unibo.dna.model.object.Door;
import it.unibo.dna.model.object.Lever;
import it.unibo.dna.model.object.api.Player;

public interface EventFactory {
    
    Event hitButtonEvent(Button b);
    Event hitDoorEvent(Door d, Player p);
    Event hitLeverEvent(Lever l);
    Event hitDiamondEvent(Diamond d, Score s);
}
