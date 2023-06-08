package it.unibo.dna.graphics.menu.impl;

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

import it.unibo.dna.gameloop.GameEngine;
import it.unibo.dna.gameloop.GameThread;
import it.unibo.dna.graphics.menu.api.GameMenu;
import it.unibo.dna.graphics.menu.api.MenuFactory;

/**
 * A concrete implementation of the {@link MenuFactory} interface.
 * Provides methods to create different menus for the game.
 */
public class MenuFactoryImpl extends JFrame implements MenuFactory {
    private static final int MENUHEIGH = 600;
    private static final int MENUWIDTH = 800;
    private GameThread gameThread;
    private GameEngine gameEngine;


   public MenuFactoryImpl(final GameThread gameT) {
        this.gameThread = gameT;
        this.gameEngine = this.gameThread.getGameEngine();
   }

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
                JFrame startMenu = new JFrame();
                JButton start = getStartButton(startMenu);
                JButton guide = getGuideButton();
                JButton quit = getQuitButton();
                JLabel logoLabel = new JLabel(new ImageIcon(ClassLoader.getSystemResource("logo.png")));

                startMenu.getContentPane().setLayout(new BorderLayout());
                startMenu.setSize(MENUWIDTH, MENUHEIGH);
                startMenu.getContentPane().setBackground(Color.BLACK);

                JPanel logoPanel = new JPanel();
                logoPanel.setBackground(Color.BLACK);
                logoPanel.add(logoLabel);

                JPanel buttonPanel = new JPanel();
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

    @Override
    public GameMenu gameOverMenu(int lvl) {
        return new GameMenu() {

            /**
             * Creates the game over menu frame.
             * @return The JFrame representing the game over menu.
             */
            @Override
            public JFrame createMenuFrame() {
                JFrame gameOverFrame = new JFrame("GameOver");
                JButton restart = getRestartButton(gameOverFrame, lvl);
                JButton quit = getQuitButton();
                JLabel score = getScoreLabel();
                JPanel panel = new JPanel();

                panel.add(score);
                panel.add(restart);
                panel.add(quit);
                gameOverFrame.setSize(MENUWIDTH, MENUWIDTH);

                gameOverFrame.getContentPane().add(panel);
                gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameOverFrame.setLocationRelativeTo(null);
                gameOverFrame.setVisible(true);

                return gameOverFrame;
            }

        };
    }

    @Override
    public GameMenu victoryMenu(int lvl) {
        return new GameMenu() {

            /**
             * Creates the victory menu frame.
             * @return The JFrame representing the victory menu.
             */
            @Override
            public JFrame createMenuFrame() {
                JFrame victoryFrame = new JFrame("You Won");
                JButton nextLevel = getNextLevelButton(victoryFrame, lvl);
                JButton quit = getQuitButton();
                JLabel score = getScoreLabel();
                JPanel panel = new JPanel();


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

    @Override
    public GameMenu pauseMenu() {
        return new GameMenu() {
            @Override
            public JFrame createMenuFrame() {
                JFrame pauseMenu = new JFrame();
                JButton restart = getRestartButton(pauseMenu, gameEngine.getLvl());
                JButton quit = getQuitButton();
                JPanel panel = new JPanel();

                panel.add(restart);
                panel.add(quit);

                JOptionPane.showMessageDialog(pauseMenu, panel, "Pause", JOptionPane.PLAIN_MESSAGE);
                return null;
            }
        };
    }

    /**
     * Creates and returns the start button for the start menu.
     * @param startMenu The JFrame of the start menu.
     * @return The JButton representing the start button.
     */
    public JButton getStartButton(final JFrame startMenu) {
        JButton startButton = new JButton("Start");
        ActionListener al = new ActionListener() {
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
     * @return The JButton representing the guide button.
     */
    public JButton getGuideButton() {
        JButton guideButton = new JButton("Guide", null);
        ActionListener al = new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                JFrame guideFrame = new JFrame("Guide");
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
     * @return The JButton representing the quit button.
     */
    public JButton getQuitButton() {
        JButton quitButton = new JButton("Quit");
        ActionListener al = new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }

        };
        quitButton.addActionListener(al);

        return quitButton;
    }

    /**
     * Creates and returns the restart button for the game over menu.
     * @return The JButton representing the restart button.
     */
    public JButton getRestartButton(final JFrame menu, final int lvl) {
        JButton restartButton = new JButton("Restart");
        ActionListener al = new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                gameThread.interrupt();
                menu.dispose();
                try {
                    gameEngine = new GameEngine(lvl);
                    gameThread.setGameEngine(gameEngine);
                    gameEngine.setGameThread(gameThread);
                    gameThread.start();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        };
        restartButton.addActionListener(al);

        return restartButton;
    }

    /**
     * Creates and returns the next level button for the victory menu.
     * @param victoryFrame The JFrame of the victory menu.
     * @param lvl
     * @return The JButton representing the next level button.
     */
    public JButton getNextLevelButton(final JFrame victoryFrame, int lvl) {
        JButton nextLevelButton = new JButton("Next");
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                victoryFrame.dispose();
                try {
                    gameEngine = new GameEngine(lvl);
                    gameThread.setGameEngine(gameEngine);
                    gameEngine.setGameThread(gameThread);
                    gameThread.start();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        };
        nextLevelButton.addActionListener(al);

        return nextLevelButton;
    }

    private JLabel getScoreLabel() {
        return new JLabel("Score:   " + gameEngine.getScore());

    }

    @Override
    public GameMenu lastVictoryMenu() {
        return new GameMenu() {

            @Override
            public JFrame createMenuFrame() {
                JFrame victoryFrame = new JFrame("You Won");
                JButton quit = getQuitButton();
                JLabel score = getScoreLabel();
                JLabel winner = new JLabel("WINNER");
                JPanel panel = new JPanel();


                panel.add(score);
                panel.add(quit);
                panel.add(winner);
                victoryFrame.setSize(800, 600);


                victoryFrame.getContentPane().add(panel);
                victoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                victoryFrame.setLocationRelativeTo(null);
                victoryFrame.setVisible(true);

                return victoryFrame;
            }

        };
    }
    


}
