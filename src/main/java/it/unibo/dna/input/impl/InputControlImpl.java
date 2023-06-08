package it.unibo.dna.input.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.dna.input.api.Command;
import it.unibo.dna.input.api.InputControl;

/**
 * Class that implements the {@link InputControl} interface.
 */
public class InputControlImpl implements InputControl {

    final List<Command> commandQueue = new ArrayList<>();

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
