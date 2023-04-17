package it.unibo.dna.model.object;

import it.unibo.dna.common.Position2d;

public class Button implements GameObject{

    private Position2d pos;
    private boolean on = false;
    private MovablePlatform mp;
    private static Integer amount = 5;

    @Override
    public Position2d getPosition() {
        return pos;
    }

    @Override
    public void enable() {
        on=true;
        mp.move(amount);
    }

    @Override
    public void disable() {
        on=false;
        mp.move(-amount);
    }

    @Override
    public boolean isActivated() {
        return on;
    }
    
}
