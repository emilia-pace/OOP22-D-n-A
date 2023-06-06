package it.unibo.dna.input;

import it.unibo.dna.input.api.CommandFactory;
import it.unibo.dna.model.object.player.api.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class that implements the {@link KeyListener} interface.
 */
public class KeyboardHandler implements KeyListener {

    private int commandRight, commandLeft, commandJump;
    private final Player character;
    private CommandFactory command;

    /**
     * Creates a new KeyboardHandler instance with the specified keycodes and
     * player.
     *
     * @param commandRight the keycode for the command "right"
     * @param commandLeft  the keycode for the command "left"
     * @param commandJump  the keycode for the "jump" command
     * @param character    the player linked to this keylistener
     */
    public KeyboardHandler(final int commandRight, final int commandLeft, final int commandJump,
            final Player character) {
        this.commandRight = commandRight;
        this.commandLeft = commandLeft;
        this.commandJump = commandJump;
        this.character = character;
        this.command = new CommandFactoryImpl(character);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        int key = e.getKeyCode();
        if (key == this.commandRight) {
            this.command.right().execute();
        }
        if (key == this.commandLeft) {
            this.command.left().execute();
        }
        if (key == this.commandJump) {
            this.command.jump().execute();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyReleased(final KeyEvent e) {
        int key = e.getKeyCode();
        switch (character.getState().getY()) {
            case STATE_RIGHT:
                if (key == this.commandRight) {
                    this.command.stop().execute();
                }
                break;
            case STATE_LEFT:
                if (key == this.commandLeft) {
                    this.command.stop().execute();
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
