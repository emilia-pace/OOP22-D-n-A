package it.unibo.dna.input.api;

public interface CommandFactory {

    /**
     * @return
     */
    Command right();

    /**
     * @return
     */
    Command left();

    /**
     * @return
     */
    Command jump();

    /**
     * @return
     */
    Command stop();

}