package it.unibo.dna.input;

import it.unibo.dna.model.object.Character;
import it.unibo.dna.model.object.Character.State;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandler implements KeyListener {

    private int commandRight, commandLeft, commandJump;
    private Character character;

    public KeyboardHandler(int commandRight, int commandLeft, int commandJump, Character character) {
        this.commandRight = commandRight;
        this.commandLeft = commandLeft;
        this.commandJump = commandJump;
        this.character = character;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (character.getState()) {
            case STATE_JUMPING:
                if (key == this.commandRight) {
                    this.character.setVectorX(character.StandardVelocity);
                }
                if (key == this.commandLeft) {
                    this.character.setVectorX(-character.StandardVelocity);
                }
                break;
            default:
                if (key == this.commandRight) {
                    this.character.setVectorX(character.StandardVelocity);
                    this.character.setState(State.STATE_RIGHT);
                }
                if (key == this.commandLeft) {
                    this.character.setVectorX(-character.StandardVelocity);
                    this.character.setState(State.STATE_LEFT);
                }
                if (key == this.commandJump) {
                    this.character.setVectorY(-character.JumpVelocity);
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
                    this.character.setVectorX(0);
                }
                break;
            case STATE_LEFT:
                if (key == this.commandLeft) {
                    this.character.setState(State.STATE_STANDING);
                    this.character.setVectorX(0);
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
