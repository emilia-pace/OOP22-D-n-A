package it.unibo.dna.model.object.player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import it.unibo.dna.common.Pair;

/**
 * A Class rappresenting the State of a Player.
 */
public class State {

    private final int MAXFRAME = 10;

    private int frame = 0;
    private int imageIndex = 0;

    private StateEnum stateX;
    private StateEnum stateY;
    private List<PropertyChangeListener> listeners = new ArrayList<>();

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
        this.notifyListeners(this, "changeX", this.stateX, this.stateX = stateX);
    }

    /**
     * Sets the second state and notifies the observer of the change.
     * 
     * @param stateY the new second state
     */
    public void setStateY(final StateEnum stateY) {
        this.notifyListeners(this, "changeY", this.stateY, this.stateY = stateY);
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
    private void notifyListeners(final Object object, final String property, final StateEnum oldValue,
            final StateEnum newValue) {
        listeners.forEach(e -> e.propertyChange(new PropertyChangeEvent(object, property, oldValue, newValue)));
    }

    /**
     * @param newListener the new listener
     */
    public void addChangeListeners(final PropertyChangeListener newListener) {
        this.listeners.add(newListener);
    }

    /**
     * @param listener the listener to remove
     */
    public void removeChangeListeners(final PropertyChangeListener listener) {
        if (this.listeners.contains(listener)) {
            this.listeners.remove(listener);
        }
    }

    /**
     * A method that control the change of images when the player is walking to the
     * right or to the left.
     */
    public void update() {
        this.frame++;
        if (frame >= this.MAXFRAME) {
            this.imageIndex = (this.imageIndex == 0) ? 1 : 0;
            this.frame = 0;
            if (this.getX().equals(StateEnum.STATE_STANDING)
                    && (this.getY().equals(StateEnum.STATE_LEFT)
                            || this.getY().equals(StateEnum.STATE_RIGHT))) {
                this.notifyListeners(this, "changeY", this.getY(), this.getY());
            }
        }
    }

    /**
     * @return the value of imageIndex
     */
    public int getImageIndex() {
        return this.imageIndex;
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
