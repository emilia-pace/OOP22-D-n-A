package it.unibo.dna.controller.inputcontrol.api;

import it.unibo.dna.model.command.api.Command;

/**
 * The InputControl interface provides methods for managing a queue of commands
 * and executing them.
 */
public interface InputControl {

    /**
     * Executes all the commands in the command queue.
     */
    void computeAll();

    /**
     * Adds a command to the command queue.
     *
     * @param command The command to be added.
     */
    void addCommand(Command command);

}
