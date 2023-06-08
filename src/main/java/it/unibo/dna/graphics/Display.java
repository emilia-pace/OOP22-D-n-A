package it.unibo.dna.graphics;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.List;

import it.unibo.dna.graphics.image.ImageManager;
import it.unibo.dna.graphics.menu.api.MenuFactory;
import it.unibo.dna.input.api.InputControl;
import it.unibo.dna.input.impl.KeyboardHandler;
import it.unibo.dna.model.object.entity.api.Entity;
import it.unibo.dna.model.object.player.api.Player;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Represents the display window of the game.
 */
public class Display extends JFrame {

    /**
     * The screen size of the default toolkit.
     */
    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * The border size calculated as one-fifth of the screen height.
     */
    public static final int BORDER = (int) SCREEN_SIZE.getHeight() / 5;

    /**
     * The dimension calculated as the screen height minus the border size.
     */
    private static final int DIM = (int) SCREEN_SIZE.getHeight() - BORDER;

    /**
     * The size of each tile, calculated as the dimension divided by 100.
     */
    public static final int TILE_SIZE = DIM / 100;

    private Canvas canvas;
    private transient ImageManager imgMgr;
    private JPanel jpanel;
    private JButton pauseButton;
    private MenuFactory menuFactory;
    private transient BufferedImage backgroundImage;

    /**
     * Constructs a Display object with the specified player list.
     * 
     * @param playerList The list of players in the game.
     * @param menuFact
     */
    public Display(final List<Player> playerList, final MenuFactory menuFact, InputControl angelInputControl,
            InputControl devilInputControl) {
        this.menuFactory = menuFact;
        setTitle("D-n-A");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);

        pauseButton = new JButton("\u23F8");

        ActionListener al = new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                menuFactory.pauseMenu().createMenuFrame();
            }

        };
        pauseButton.addActionListener(al);

        jpanel = new JPanel(new BorderLayout());
        canvas = new Canvas();
        canvas.setSize(DIM, DIM);
        canvas.setFocusable(true);

        jpanel.add(pauseButton, BorderLayout.NORTH);
        jpanel.add(canvas, BorderLayout.CENTER);
        add(jpanel);
        pack();

        canvas.createBufferStrategy(3);

        setLocationRelativeTo(null);
        setVisible(true);

        imgMgr = new ImageManager(playerList);

        playerList.forEach(p -> {
            if (p.getPlayerType().equals(Player.PlayerType.ANGEL)) {
                canvas.addKeyListener(new KeyboardHandler(KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT,
                        KeyEvent.VK_UP, p, angelInputControl));
            } else {
                canvas.addKeyListener(
                        new KeyboardHandler(KeyEvent.VK_D, KeyEvent.VK_A, KeyEvent.VK_W, p, devilInputControl));
            }
        });
        loadBackgroundImage("background.jpg");
        canvas.requestFocus();

    }

    /**
     * Loads the background image from the specified resource file.
     *
     * @param string the path or name of the image resource file
     */
    private void loadBackgroundImage(final String string) {
        try {
            backgroundImage = ImageIO.read(ClassLoader.getSystemResource(string));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Renders the entities and players on the display.
     * 
     * @param entities The list of entities to render.
     * @param players  The list of players to render.
     */
    public void render(final List<Entity> entities, final List<Player> players) {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.drawImage(backgroundImage, 0, 0, canvas.getWidth(), canvas.getHeight(), this);

        entities.forEach(entity -> graphics.drawImage(imgMgr.getImage(entity),
                (int) entity.getPosition().getX() * TILE_SIZE,
                (int) entity.getPosition().getY() * TILE_SIZE, this));

        players.forEach(p -> graphics.drawImage(imgMgr.getPlayerImage(p),
                (int) p.getPosition().getX() * TILE_SIZE,
                (int) p.getPosition().getY() * TILE_SIZE, this));

        graphics.dispose();
        bufferStrategy.show();
    }

    /**
     * Returns the screen dimension in terms of tile size.
     * 
     * @return The screen dimension in tile size.
     */
    public int getScreenDimension() {
        return DIM / TILE_SIZE;
    }
}
