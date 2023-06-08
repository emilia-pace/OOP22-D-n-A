package it.unibo.dna.controller.inputcontrol.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.dna.controller.inputcontrol.api.InputControl;
import it.unibo.dna.model.command.api.Command;

/**
 * Class that implements the {@link InputControl} interface.
 */
public class InputControlImpl implements InputControl {

    final private List<Command> commandQueue = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void computeAll() {
        final List<Command> commands = new ArrayList<>(commandQueue);
        this.commandQueue.clear();
        commands.forEach(c -> c.execute());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCommand(Command command) {
        this.commandQueue.add(command);
    }

}
