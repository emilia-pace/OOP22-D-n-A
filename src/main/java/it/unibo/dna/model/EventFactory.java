package it.unibo.dna.model;

public interface EventFactory {
    
    Event hitBorderEvent();
    Event hitButtonEvent();
    Event hitDoorEvent();
    Event hitLeverEvent();
    Event hitDiamondEvent();
}
