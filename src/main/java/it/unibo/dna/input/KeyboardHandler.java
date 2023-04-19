package it.unibo.dna.input;

import it.unibo.dna.model.object.PlayerImpl;
import it.unibo.dna.model.object.PlayerImpl.State;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandler implements KeyListener {

    private int commandRight, commandLeft, commandJump;
    private PlayerImpl character;
    private CommandFactory command;

    public KeyboardHandler(int commandRight, int commandLeft, int commandJump, PlayerImpl character) {
        this.commandRight = commandRight;
        this.commandLeft = commandLeft;
        this.commandJump = commandJump;
        this.character = character;
        this.command = new CommandFactory(character);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (character.getState()) {
            case STATE_JUMPING:
                if (key == this.commandRight) {
                    command.right();
                }
                if (key == this.commandLeft) {
                    command.left();
                }
                break;
            default:
                if (key == this.commandRight) {
                    command.right();
                    this.character.setState(State.STATE_RIGHT);
                }
                if (key == this.commandLeft) {
                    command.left();
                    this.character.setState(State.STATE_LEFT);
                }
                if (key == this.commandJump) {
                    command.jump();
                    this.character.setState(State.STATE_JUMPING);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (character.getState()) {
            case STATE_RIGHT:
                if (key == this.commandRight) {
                    this.character.setState(State.STATE_STANDING);
                    command.stop();
                }
                break;
            case STATE_LEFT:
                if (key == this.commandLeft) {
                    this.character.setState(State.STATE_STANDING);
                    command.stop();
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
