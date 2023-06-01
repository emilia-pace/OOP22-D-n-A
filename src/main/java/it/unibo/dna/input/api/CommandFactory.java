package it.unibo.dna.input.api;

/**
 * Interface of a Factory that creates {@link Command} that can
 * be given to the Player.
 */

public interface CommandFactory {

    /**
     * @return a command that changes the vector
     *         of the Player to the right
     */
    Command right();

    /**
     * @return a command that changes the vector
     *         of the Player to the left
     */
    Command left();

    /**
     * @return a command that changes the vector
     *         of the Player to upwards
     */
    Command jump();

    /**
     * @return a command that reset the first coordinate
     *         of the Player's vector
     */
    Command stop();

}
