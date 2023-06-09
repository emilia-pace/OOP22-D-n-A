package it.unibo.dna.view.menu.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import it.unibo.dna.controller.core.GameEngine;
import it.unibo.dna.controller.core.GameThread;
import it.unibo.dna.view.image.ImageManager;
import it.unibo.dna.view.menu.api.GameMenu;
import it.unibo.dna.view.menu.api.MenuFactory;

/**
 * A concrete implementation of the {@link MenuFactory} interface.
 * Provides methods to create different menus for the game.
 */
public class MenuFactoryImpl extends JFrame implements MenuFactory {
    public static final long serialVersionUID = 4328743;
    private static final int MENUHEIGH = 600;
    private static final int MENUWIDTH = 800;
    private final GameThread gameThread;
    private GameEngine gameEngine;
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageManager.class);

    /**
     * Constructs a MenuFactoryImpl object with the specified GameThread and sets variable gameEngine.
     *
     * @param gameThread the GameThread instance to associate with the factory.
     */
    public MenuFactoryImpl(final GameThread gameThread) {
        this.gameThread = gameThread;
        this.gameEngine = this.gameThread.getGameEngine();
    }

    /**
     * Creates the start menu.
     *
     * @return The GameMenu representing the start menu.
     */
    @Override
    public GameMenu startMenu() {
        return new GameMenu() {
            /**
             * Creates the start menu frame.
             *
             * @return The JFrame representing the start menu.
             */
            @Override
            public JFrame createMenuFrame() {
                final JFrame startMenu = new JFrame();
                final JButton start = getStartButton(startMenu);
                final JButton guide = getGuideButton();
                final JButton quit = getQuitButton(startMenu);
                final JLabel logoLabel = new JLabel(new ImageIcon(ClassLoader.getSystemResource("logo.png")));

                startMenu.getContentPane().setLayout(new BorderLayout());
                startMenu.setSize(MENUWIDTH, MENUHEIGH);
                startMenu.getContentPane().setBackground(Color.BLACK);

                final JPanel logoPanel = new JPanel();
                logoPanel.setBackground(Color.BLACK);
                logoPanel.add(logoLabel);

                final JPanel buttonPanel = new JPanel();
                buttonPanel.setBackground(Color.BLACK);
                buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
                buttonPanel.add(start);
                buttonPanel.add(guide);
                buttonPanel.add(quit);

                startMenu.getContentPane().add(logoPanel, BorderLayout.NORTH);
                startMenu.getContentPane().add(buttonPanel, BorderLayout.CENTER);

                startMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                startMenu.setLocationRelativeTo(null);
                startMenu.setVisible(true);

                return startMenu;
            }
        };
    }

    /**
     * Creates the pause menu.
     *
     * @return The GameMenu representing the pause menu.
     */
    @Override
    public GameMenu pauseMenu() {
        return new GameMenu() {
            /**
             * Creates the pause menu frame.
             *
             * @return null because the pause menu is a MessageDialog.
             */
            @Override
            public JFrame createMenuFrame() {
                final JFrame pauseMenu = new JFrame();
                final JButton restart = getRestartButton(pauseMenu, gameEngine.getLvl());
                final JButton quit = getQuitButton(pauseMenu);
                final JPanel panel = new JPanel();

                panel.add(restart);
                panel.add(quit);

                JOptionPane.showMessageDialog(pauseMenu, panel, "Pause", JOptionPane.PLAIN_MESSAGE);
                return null;
            }
        };
    }

    /**
     * Creates the game over menu for the specified level.
     *
     * @param lvl the level of the game
     * @return The GameMenu representing the game over menu.
     */
    @Override
    public GameMenu gameOverMenu(final int lvl) {
        return new GameMenu() {
            /**
             * Creates the game over menu frame.
             *
             * @return The JFrame representing the game over menu.
             */
            @Override
            public JFrame createMenuFrame() {
                final JFrame gameOverFrame = new JFrame("GameOver");
                final JButton restart = getRestartButton(gameOverFrame, lvl);
                final JButton quit = getQuitButton(gameOverFrame);
                final JLabel score = getScoreLabel();
                final JPanel panel = new JPanel();

                panel.add(score);
                panel.add(restart);
                panel.add(quit);
                gameOverFrame.setSize(MENUWIDTH, MENUHEIGH);

                gameOverFrame.getContentPane().add(panel);
                gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameOverFrame.setLocationRelativeTo(null);
                gameOverFrame.setVisible(true);

                return gameOverFrame;
            }

        };
    }

    /**
     * Creates the victory menu for the specified level.
     *
     * @param lvl the next level.
     * @return The GameMenu representing the victory menu.
     */
    @Override
    public GameMenu victoryMenu(final int lvl) {
        return new GameMenu() {
            /**
             * Creates the victory menu frame.
             *
             * @return The JFrame representing the victory menu.
             */
            @Override
            public JFrame createMenuFrame() {
                final JFrame victoryFrame = new JFrame("You Won");
                final JButton nextLevel = getNextLevelButton(victoryFrame, lvl);
                final JButton quit = getQuitButton(victoryFrame);
                final JLabel score = getScoreLabel();
                final JPanel panel = new JPanel();

                panel.add(score);
                panel.add(nextLevel);
                panel.add(quit);
                victoryFrame.setSize(MENUWIDTH, MENUHEIGH);

                victoryFrame.getContentPane().add(panel);
                victoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                victoryFrame.setLocationRelativeTo(null);
                victoryFrame.setVisible(true);

                return victoryFrame;
            }

        };
    }

    /**
     * Creates victory menu of the last level.
     *
     * @return The GameMenu representing victory menu of the last level.
     */
    @Override
    public GameMenu lastVictoryMenu() {
        return new GameMenu() {
            /**
             * Creates the victory menu frame of the last game.
             *
             * @return The JFrame representing the last victory menu.
             */
            @Override
            public JFrame createMenuFrame() {
                final JFrame victoryFrame = new JFrame("You Won");
                final JButton quit = getQuitButton(victoryFrame);
                final JLabel score = getScoreLabel();
                final JLabel winner = new JLabel("WINNER");
                final JPanel panel = new JPanel();

                panel.add(score);
                panel.add(quit);
                panel.add(winner);
                victoryFrame.setSize(MENUWIDTH, MENUHEIGH);


                victoryFrame.getContentPane().add(panel);
                victoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                victoryFrame.setLocationRelativeTo(null);
                victoryFrame.setVisible(true);

                return victoryFrame;
            }

        };
    }

    /**
     * Creates and returns the start button for the start menu.
     *
     * @param startMenu The JFrame of the start menu.
     * @return The JButton that starts the first level.
     */
    public JButton getStartButton(final JFrame startMenu) {
        final JButton startButton = new JButton("Start");
        final ActionListener al = new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                startMenu.dispose();
                gameThread.start();
            }

        };
        startButton.addActionListener(al);

        return startButton;
    }

    /**
     * Creates and returns the guide button for the start menu.
     *
     * @return The JButton that opens the messageDialog explaining how to play the game.
     */
    public JButton getGuideButton() {
        final JButton guideButton = new JButton("Guide", null);
        final ActionListener al = new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                final JFrame guideFrame = new JFrame("Guide");
                JOptionPane.showMessageDialog(guideFrame, "Angel controls: W:jump, A: left, D: right"
                        + "\n Devil controls: ↑: jump, \u2190: left, \u2192: right \n"
                        + "The goal of the game is to reach the doors together ");
            }

        };
        guideButton.addActionListener(al);
        return guideButton;
    }

    /**
     * Creates and returns the quit button for the menus.
     *
     * @param frame the frame of the menu that has the button.
     * @return The JButton that closes the game.
     */
    public JButton getQuitButton(final JFrame frame) {
        final JButton quitButton = new JButton("Quit");
        final ActionListener al = new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!gameThread.isInterrupted()) {
                    gameThread.interruptGame();
                }
                frame.dispose();
            }

        };
        quitButton.addActionListener(al);

        return quitButton;
    }

    /**
     * Creates and returns the restart button for the game over menu.
     *
     * @param menu The JFrame of the game over menu.
     * @param lvl The number of the current level.
     * @return The JButton that restarts the current level.
     */
    public JButton getRestartButton(final JFrame menu, final int lvl) {
        final JButton restartButton = new JButton("Restart");
        final ActionListener al = new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                gameThread.interruptGame();
                menu.dispose();
                try {
                    gameEngine = new GameEngine(lvl);
                    gameThread.setGameEngine(gameEngine);
                    gameEngine.setGameThread(gameThread);
                    gameThread.start();
                } catch (IOException e1) {
                    LOGGER.error("IOEexception occurred", e1);
                }

            }
        };
        restartButton.addActionListener(al);

        return restartButton;
    }

    /**
     * Creates and returns the next level button for the victory menu.
     *
     * @param victoryFrame The JFrame of the victory menu.
     * @param lvl The number of the next level.
     * @return The JButton that starts the next level.
     */
    public JButton getNextLevelButton(final JFrame victoryFrame, final int lvl) {
        final JButton nextLevelButton = new JButton("Next");
        final ActionListener al = new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                victoryFrame.dispose();
                try {
                    gameEngine = new GameEngine(lvl);
                    gameThread.setGameEngine(gameEngine);
                    gameEngine.setGameThread(gameThread);
                    gameThread.start();
                } catch (IOException e1) {
                    LOGGER.error("IOEexception occurred", e1);
                }
            }
        };
        nextLevelButton.addActionListener(al);

        return nextLevelButton;
    }

    /**
     * Creates and returns the score label.
     *
     * @return The JLabel with the score written.
     */
    private JLabel getScoreLabel() {
        return new JLabel("Score:   " + gameEngine.getScore());
    }
}
