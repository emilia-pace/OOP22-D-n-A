package it.unibo.dna.graphics;

import javax.swing.*;
import java.util.List;

import it.unibo.dna.input.KeyboardHandler;
import it.unibo.dna.model.object.MyObserver;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.api.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {

        private Canvas canvas;
        public ImageManager imgMgr;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();

        public MyObserver obsAngel;
        public MyObserver obsDevil;

        public Display(List<Player> playerList) {
                setTitle("D-n-A");
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setResizable(false);

                canvas = new Canvas();
                canvas.setSize((int) (screenSize.getHeight()), (int) screenSize.getHeight());
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

        public void render(List<Entity> entities) {
                BufferStrategy bufferStrategy = canvas.getBufferStrategy();
                Graphics graphics = bufferStrategy.getDrawGraphics();

                graphics.setColor(Color.BLACK);
                graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                for (Entity entity : entities) {
                        imgMgr.getImage(entity);
                        graphics.drawImage(imgMgr.getImage(entity), (int) entity.getPosition().getX(),
                                        (int) entity.getPosition().getY(), this);
                }

                graphics.dispose();
                bufferStrategy.show();

                imgMgr.getObservers().forEach(o -> o.update());

        }

        public double getBoh() {
                return width;
        }
}