package it.unibo.dna.input;

import it.unibo.dna.model.object.api.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandler implements KeyListener {

    private int commandRight, commandLeft, commandJump;
    private Player character;
    private CommandFactory command;

    public KeyboardHandler(int commandRight, int commandLeft, int commandJump, Player character) {
        this.commandRight = commandRight;
        this.commandLeft = commandLeft;
        this.commandJump = commandJump;
        this.character = character;
        this.command = new CommandFactory(character);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == this.commandRight) {
            command.right().execute();
        }
        if (key == this.commandLeft) {
            command.left().execute();
        }
        if (key == this.commandJump) {
            command.jump().execute();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (character.getState()) {
            case STATE_RIGHT:
                if (key == this.commandRight) {
                    command.stop().execute();
                }
                break;
            case STATE_LEFT:
                if (key == this.commandLeft) {
                    command.stop().execute();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }
}
