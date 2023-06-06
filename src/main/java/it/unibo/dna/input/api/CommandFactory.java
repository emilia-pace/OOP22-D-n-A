package it.unibo.dna.input.api;

/**
 * Interface of a factory that creates {@link Command}s that can
 * be given to the Player.
 */

public interface CommandFactory {

    /**
     * Returns a command that changes the vector of the Player to the right.
     *
     * @return a command that changes the vector of the Player to the right
     */
    Command right();

    /**
     * Returns a command that changes the vector of the Player to the left.
     *
     * @return a command that changes the vector of the Player to the left
     */
    Command left();

    /**
     * Returns a command that changes the vector of the Player upwards.
     *
     * @return a command that changes the vector of the Player upwards
     */
    Command jump();

    /**
     * Returns a command that resets the first coordinate of the Player's vector.
     *
     * @return a command that resets the first coordinate of the Player's vector
     */
    Command stop();

}
