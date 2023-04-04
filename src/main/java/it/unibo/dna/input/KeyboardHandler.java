package it.unibo.dna.input;

import it.unibo.dna.model.object.Character;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandler implements KeyListener {

    private boolean MovingLeft = false;

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
        if (key == this.commandRight) {
            this.character.setVector(this.character.getVector().sumX(this.character.StandardVelocity));
            MovingLeft = false;
        }
        if (key == this.commandLeft) {
            this.character.setVector(this.character.getVector().sumX(-this.character.StandardVelocity));
            MovingLeft = true;
        }
        if (key == this.commandJump) {
            this.character.setVector(this.character.getVector().sumY(-this.character.JumpVelocity));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == this.commandRight && !MovingLeft) {
            this.character.setVectorX(0);
        }
        if (key == KeyEvent.VK_LEFT && MovingLeft) {
            this.character.setVectorX(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }
}
