package it.unibo.dna;

import javax.swing.*;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.input.KeyboardHandler;
import it.unibo.dna.model.object.Character;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {

    private Canvas canvas;

    public Character character = new Character(new Position2d(0, 0), new Vector2d(0, 0), 100, 100);

    public Display(final int width, final int height) {
        setTitle("D-n-A");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        canvas = new Canvas();
        canvas.setSize(width, height);
        canvas.setFocusable(false);
        add(canvas);
        pack();

        canvas.createBufferStrategy(3);

        setLocationRelativeTo(null);
        setVisible(true);

        this.addKeyListener(new KeyboardHandler(KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, KeyEvent.VK_UP, character));

    }

    public void render(Game game) {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        graphics.setColor(Color.RED);
        graphics.fillRect((int) this.character.getPosition().x, (int) this.character.getPosition().y,
                (int) this.character.getBoundingBox().getHeight(), (int) this.character.getBoundingBox().getLenght());

        graphics.dispose();
        bufferStrategy.show();

    }
}
