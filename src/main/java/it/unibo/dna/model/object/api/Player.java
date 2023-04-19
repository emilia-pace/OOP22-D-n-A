package it.unibo.dna.model.object.api;

import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.object.PlayerImpl.State;
import it.unibo.dna.model.object.PlayerImpl.Type;

public interface Player extends Entity{

    public Vector2d getVector();

    public void setVector(Vector2d vet);

    public void setVectorX(double x);

    public void setVectorY(double y);

    public State getState();

    public void setState(State state);

    public Type getType();

}
