package it.unibo.dna.model.object.player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import it.unibo.dna.common.Pair;

/**
 * A Class rappresenting the State of a Player.
 */
public class State {

    private StateEnum stateX;
    private StateEnum stateY;
    private PropertyChangeListener listener;

    /**
     * stateX indicate if the player is touching the floor or is jumping
     * stateY indicates whether the player is facing front, right, or left
     */
    public State() {
        stateX = StateEnum.STATE_STANDING;
        stateY = StateEnum.STATE_STILL;
    }

    /**
     * @return the first state
     */
    public StateEnum getX() {
        return stateX;
    }

    /**
     * @return the second state
     */
    public StateEnum getY() {
        return stateY;
    }

    /**
     * Sets the first state and notifies the StateObserver of the change.
     * 
     * @param stateX the new first state
     */
    public void setStateX(final StateEnum stateX) {
        this.notifyListener(this, null, this.stateX, this.stateX = stateX);
    }

    /**
     * Sets the second state and notifies the observer of the change.
     * 
     * @param stateY the new second state
     */
    public void setStateY(final StateEnum stateY) {
        this.notifyListener(this, null, this.stateY, this.stateY = stateY);
    }

    /**
     * @return a Pair of state
     */
    public Pair<StateEnum, StateEnum> getPairState() {
        return new Pair<>(stateX, stateY);
    }

    /**
     * Notify the observer of an event.
     * 
     * @param object   the observable object
     * @param property the name of the property
     * @param oldValue the old value
     * @param newValue the new new value
     */
    private void notifyListener(final Object object, final String property, final StateEnum oldValue,
            final StateEnum newValue) {
        listener.propertyChange(new PropertyChangeEvent(object, property, oldValue, newValue));
    }

    /**
     * @param newListener the new listener
     */
    public void addChangeListener(final PropertyChangeListener newListener) {
        this.listener = newListener;
    }

    /**
     * An enumeration describing the different states of the Player.
     */
    public enum StateEnum {
        /**
         * when the player is on a platform.
         */
        STATE_STANDING,
        /**
         * when the player is jumping.
         */
        STATE_JUMPING,
        /**
         * when the player goes right.
         */
        STATE_RIGHT,
        /**
         * when the player goes left.
         */
        STATE_LEFT,
        /**
         * when the player is still.
         */
        STATE_STILL;
    }

}
