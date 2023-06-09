package it.unibo.dna.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.List;

import it.unibo.dna.controller.inputcontrol.api.InputControl;
import it.unibo.dna.model.object.entity.api.Entity;
import it.unibo.dna.model.object.player.api.Player;
import it.unibo.dna.view.image.ImageManager;
import it.unibo.dna.view.input.KeyboardHandler;
import it.unibo.dna.view.menu.api.MenuFactory;

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

    public static final long serialVersionUID = 4328743;
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageManager.class);
    private final Canvas canvas;
    private final transient ImageManager imgMgr;
    private final MenuFactory menuFactory;
    private transient BufferedImage backgroundImage;

    /**
     * Constructs a Display object with the specified player list.
     * 
     * @param playerList        The list of players in the game.
     * @param menuFact
     * @param angelInputControl
     * @param devilInputControl
     */
    @SuppressFBWarnings(value = "MC_OVERRIDABLE_METHOD_CALL_IN_CONSTRUCTOR", justification = "The method pack should be"
            + "called in the constructor")
    public Display(final List<Player> playerList, final MenuFactory menuFact, final InputControl angelInputControl,
            final InputControl devilInputControl) {
        this.menuFactory = menuFact;
        this.setTitle("D-n-A");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setUndecorated(true);

        final JButton pauseButton = new JButton("\u23F8");

        final ActionListener al = new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                menuFactory.pauseMenu().createMenuFrame();
            }

        };
        pauseButton.addActionListener(al);

        final JPanel jpanel = new JPanel(new BorderLayout());
        canvas = new Canvas();
        canvas.setSize(DIM, DIM);
        canvas.setFocusable(true);

        jpanel.add(pauseButton, BorderLayout.NORTH);
        jpanel.add(canvas, BorderLayout.CENTER);
        this.add(jpanel);
        this.unifyPanels();

        canvas.createBufferStrategy(3);

        this.setLocationRelativeTo(null);
        this.setVisible(true);

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
        this.loadBackgroundImage("background.jpg");
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
            LOGGER.error("IOEexception occurred", e);
        }
    }

    /**
     * Renders the entities and players on the display.
     * 
     * @param entities The list of entities to render.
     * @param players  The list of players to render.
     */
    public void render(final List<Entity> entities, final List<Player> players) {
        final BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        final Graphics graphics = bufferStrategy.getDrawGraphics();

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

    private void unifyPanels() {
        this.pack();
    }
}
