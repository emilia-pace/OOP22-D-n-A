package it.unibo.dna.model.object;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import it.unibo.dna.common.Pair;

public class State {

    private StateEnum stateX;
    private StateEnum stateY;
    private PropertyChangeListener listener;

    public State() {
        stateX = StateEnum.STATE_STANDING;
        stateY = StateEnum.STATE_STILL;
    }

    public StateEnum getX() {
        return stateX;
    }

    public StateEnum getY() {
        return stateY;
    }

    public void setStateX(StateEnum stateX) {
        this.notifyListeners(this, null, this.stateX, this.stateX = stateX);
    }

    public void setStateY(StateEnum stateY) {
        this.notifyListeners(this, null, this.stateY, this.stateY = stateY);
    }

    public Pair<StateEnum, StateEnum> getPairState() {
        return new Pair<>(stateX, stateY);
    }

    private void notifyListeners(Object object, String property, StateEnum oldValue, StateEnum newValue) {
        listener.propertyChange(new PropertyChangeEvent(object, property, oldValue, newValue));

    }

    public void addChangeListener(PropertyChangeListener newListener) {
        this.listener = newListener;
    }

    public PropertyChangeListener getListener() {
        return this.listener;
    }

    public enum StateEnum {
        /**
         * when player is on a platform
         */
        STATE_STANDING,
        /**
         * when player is jumping
         */
        STATE_JUMPING,
        /**
         * when player goes right
         */
        STATE_RIGHT,
        /**
         * when player goes left
         */
        STATE_LEFT,
        /**
         * when player is still
         */
        STATE_STILL;
    }

}
