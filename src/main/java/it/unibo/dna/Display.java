package it.unibo.dna;

import javax.swing.*;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.input.KeyboardHandler;
import it.unibo.dna.model.object.Door;
import it.unibo.dna.model.object.PlayerImpl;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {

        private Canvas canvas;
        public PlayerImpl angel = new PlayerImpl(new Position2d(0, 0), new Vector2d(0, 0), 20, 20,
                        PlayerImpl.Type.ANGEL);
        public PlayerImpl devil = new PlayerImpl(new Position2d(300, 0), new Vector2d(0, 0), 20, 20,
                        PlayerImpl.Type.DEVIL);
        public Door door = new Door(new Position2d(300, 450),
                        20, 200,Door.doorType.DEVIL_DOOR);
        public Door door2 = new Door(new Position2d(100, 250),
                        20, 200,Door.doorType.ANGEL_DOOR);

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

                this.addKeyListener(new KeyboardHandler(KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, KeyEvent.VK_UP, angel));
                this.addKeyListener(new KeyboardHandler(KeyEvent.VK_D, KeyEvent.VK_A, KeyEvent.VK_W, devil));

        }

        public void render(Game game) {
                BufferStrategy bufferStrategy = canvas.getBufferStrategy();
                Graphics graphics = bufferStrategy.getDrawGraphics();

                graphics.setColor(Color.BLACK);
                graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                graphics.setColor(Color.LIGHT_GRAY);
                graphics.fillRect((int) angel.getPosition().x, (int) angel.getPosition().y,
                                (int) angel.getBoundingBox().getHeight(),
                                (int) angel.getBoundingBox().getWidth());
                graphics.fillRect((int) door2.getPosition().x, (int) door2.getPosition().y,
                                (int) door2.getBoundingBox().getWidth(),
                                (int) door2.getBoundingBox().getHeight());

                graphics.setColor(Color.RED);
                graphics.fillRect((int) devil.getPosition().x, (int) devil.getPosition().y,
                                (int) devil.getBoundingBox().getHeight(),
                                (int) devil.getBoundingBox().getWidth());

                graphics.fillRect((int) door.getPosition().x, (int) door.getPosition().y,
                                (int) door.getBoundingBox().getWidth(),
                                (int) door.getBoundingBox().getHeight());

                graphics.dispose();
                bufferStrategy.show();

        }
}
