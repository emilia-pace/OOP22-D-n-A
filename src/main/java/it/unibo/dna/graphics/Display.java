package it.unibo.dna.graphics;

import javax.swing.*;
import java.util.List;

import it.unibo.dna.input.KeyboardHandler;
import it.unibo.dna.model.object.StateObserver;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.api.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {

        public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
        public static final int TILE_SIZE = (int) SCREEN_SIZE.getHeight() / 100;

        private Canvas canvas;
        public ImageManager imgMgr;
        double width = SCREEN_SIZE.getWidth();

        public Display(List<Player> playerList) {
                setTitle("D-n-A");
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setResizable(true);

                canvas = new Canvas();
                canvas.setSize((int) (SCREEN_SIZE.getHeight()), (int) SCREEN_SIZE.getHeight());
                canvas.setFocusable(false);
                add(canvas);
                pack();

                canvas.createBufferStrategy(3);

                setLocationRelativeTo(null);
                setVisible(true);

                imgMgr = new ImageManager(playerList);

                playerList.forEach(p -> {
                        if (p.getPlayerType().equals(Player.PlayerType.ANGEL)) {
                                this.addKeyListener(new KeyboardHandler(KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT,
                                                KeyEvent.VK_UP, p));
                        } else {
                                this.addKeyListener(
                                                new KeyboardHandler(KeyEvent.VK_D, KeyEvent.VK_A, KeyEvent.VK_W, p));
                        }
                });

        }

        public void render(List<Entity> entities, List<Player> players) {
                BufferStrategy bufferStrategy = canvas.getBufferStrategy();
                Graphics graphics = bufferStrategy.getDrawGraphics();

                graphics.setColor(Color.BLACK);
                graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                entities.forEach(entity -> graphics.drawImage(imgMgr.getImage(entity),
                                (int) entity.getPosition().getX() * TILE_SIZE,
                                (int) entity.getPosition().getY() * TILE_SIZE, this));

                players.forEach(p -> graphics.drawImage(imgMgr.getPlayerImage(p),
                                (int) p.getPosition().getX() * TILE_SIZE,
                                (int) p.getPosition().getY() * TILE_SIZE, this));

                imgMgr.getObservers().forEach(o -> o.update());

                graphics.dispose();
                bufferStrategy.show();

        }
        

        public double getBoh() {
                return width;
        }
}