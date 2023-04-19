package it.unibo.dna.model.object.api;

import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.object.PlayerImpl.State;

public interface Player {

    public Vector2d getVector();

    public void setVector(Vector2d vet);

    public void setVectorX(double x);

    public void setVectorY(double y);

    public State getState();

    public void setState(State state);

}
