package it.unibo.dna.model.object.api;

import it.unibo.dna.common.Pair;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.object.PlayerImpl.State;
import it.unibo.dna.model.object.PlayerImpl.Type;

public interface Player extends Entity {

    public static final double JumpVelocity = 25;
    public static final double StandardVelocity = 2;

    public Vector2d getVector();

    public void setVector(Vector2d vet);

    public void setVectorX(double x);

    public void setVectorY(double y);

    public Pair<State, State> getState();

    public void setState(Pair<State, State> state);

    public void setStateX(State newState);

    public void setStateY(State newState);

    public Type getType();

    public void update();

    public void resetX();

    public void resetY();

    public boolean equals(Player p);

}
