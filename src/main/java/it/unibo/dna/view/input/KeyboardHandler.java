package it.unibo.dna.view.input;

import it.unibo.dna.controller.inputcontrol.api.InputControl;
import it.unibo.dna.model.command.api.CommandFactory;
import it.unibo.dna.model.command.impl.CommandFactoryImpl;
import it.unibo.dna.model.object.player.api.Player;
import it.unibo.dna.model.object.player.impl.State;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Class that implements the {@link KeyListener} interface.
 */
public class KeyboardHandler implements KeyListener {

    private final int commandRight, commandLeft, commandJump;
    private final State state;
    private final CommandFactory command;
    private final InputControl inputControl;

    /**
     * Creates a new KeyboardHandler instance with the specified keycodes and
     * player.
     *
     * @param commandRight the keycode for the command "right"
     * @param commandLeft  the keycode for the command "left"
     * @param commandJump  the keycode for the "jump" command
     * @param character    the player linked to this keylistener
     * @param inputControl the input control instance
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "The inputControl field is intentionally"
            + "exposed to allow initialization with the current inputControl.")
    public KeyboardHandler(final int commandRight, final int commandLeft, final int commandJump,
            final Player character, final InputControl inputControl) {
        this.commandRight = commandRight;
        this.commandLeft = commandLeft;
        this.commandJump = commandJump;
        this.state = character.getState();
        this.command = new CommandFactoryImpl(character);
        this.inputControl = inputControl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        final int key = e.getKeyCode();
        if (key == this.commandRight) {
            this.inputControl.addCommand(this.command.right());
        }
        if (key == this.commandLeft) {
            this.inputControl.addCommand(this.command.left());
        }
        if (key == this.commandJump) {
            this.inputControl.addCommand(this.command.jump());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyReleased(final KeyEvent e) {
        final int key = e.getKeyCode();
        switch (state.getY()) {
            case STATE_RIGHT:
                if (key == this.commandRight) {
                    this.inputControl.addCommand(this.command.stop());
                }
                break;
            case STATE_LEFT:
                if (key == this.commandLeft) {
                    this.inputControl.addCommand(this.command.stop());
                }
                break;
            default:
                break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyTyped(final KeyEvent e) {
    }
}
