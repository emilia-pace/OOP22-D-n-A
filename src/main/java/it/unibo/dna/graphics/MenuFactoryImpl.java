package it.unibo.dna.graphics;

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

import it.unibo.dna.GameEngine;
import it.unibo.dna.GameStateImpl;
import it.unibo.dna.model.Score;

/**
 * A concrete implementation of the {@link MenuFactory} interface.
 * Provides methods to create different menus for the game.
 */
public class MenuFactoryImpl extends JFrame implements MenuFactory {
    private int level = 1;
    Thread gameThread;
    GameEngine gameEngine;

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
                JLabel logoLabel = new JLabel(new ImageIcon("src\\main\\resources\\logo.png"));
    
                startMenu.getContentPane().setLayout(new BorderLayout());
                startMenu.setSize(800, 600);
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
    public GameMenu gameOverMenu() {
        return new GameMenu() {

            /**
             * Creates the game over menu frame.
             * @return The JFrame representing the game over menu.
             */
            @Override
            public JFrame createMenuFrame() {
                JFrame gameOverFrame = new JFrame("GameOver");
                JButton restart = getRestartButton(level, gameOverFrame);
                JButton quit = getQuitButton();
                JLabel score = getScorLabel();
                JPanel panel = new JPanel();

                panel.add(score);
                panel.add(restart);
                panel.add(quit);
                gameOverFrame.getContentPane()
                        .setLayout(new BoxLayout(gameOverFrame.getContentPane(), BoxLayout.Y_AXIS));
                gameOverFrame.setSize(800, 600);
                gameOverFrame.getContentPane().setBackground(Color.BLACK);

                gameOverFrame.getContentPane().add(panel);
                gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameOverFrame.setLocationRelativeTo(null);
                gameOverFrame.setVisible(true);

                interruptThread();

                return gameOverFrame;
            }

        };
    }

    @Override
    public GameMenu victoryMenu(Score totalScore) {
        return new GameMenu() {

            /**
             * Creates the victory menu frame.
             * @return The JFrame representing the victory menu.
             */
            @Override
            public JFrame createMenuFrame() {
                JFrame victoryFrame = new JFrame("You Won");
                JButton nextLevel = getNextLevelButton(victoryFrame);
                JButton quit = getQuitButton();
                JLabel score = getScorLabel();
                JPanel panel = new JPanel();


                panel.add(score);
                panel.add(nextLevel);
                panel.add(quit);
                victoryFrame.getContentPane().setLayout(new BoxLayout(victoryFrame.getContentPane(), BoxLayout.Y_AXIS));
                victoryFrame.setSize(800, 600);
                victoryFrame.getContentPane().setBackground(Color.BLACK);

                victoryFrame.getContentPane().add(panel);
                victoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                victoryFrame.setLocationRelativeTo(null);
                victoryFrame.setVisible(true);
                interruptThread();

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
                JButton continueButton = getContinueButton(pauseMenu);
                JButton restart = getRestartButton(level, pauseMenu);
                JButton quit = getQuitButton();
                JPanel panel = new JPanel();
    
                panel.add(continueButton);
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
    public JButton getStartButton(JFrame startMenu) {
        JButton startButton = new JButton("Start");
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startMenu.dispose();
                try {
                    startThread();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        };
        startButton.addActionListener(al);

        return startButton;
    }

    public JButton getContinueButton(JFrame pauseMenu) {
        JButton continueButton = new JButton("Continue");
        ActionListener al = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                pauseMenu.dispose();
            }

        };
        continueButton.addActionListener(al);
        return continueButton;
    }

    /**
     * Creates and returns the guide button for the start menu.
     * @return The JButton representing the guide button.
     */
    public JButton getGuideButton() {
        JButton guideButton = new JButton("Guide", null);
        ActionListener al = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFrame guideFrame = new JFrame("Guide");
                JOptionPane.showMessageDialog(guideFrame, "Angel controls: W:jump, A: left, D: right" +
                        "\n Devil controls: ↑: jump, \u2190: left, \u2192: right \n The goal of the game is to reach the doors together ");
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
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        };
        quitButton.addActionListener(al);

        return quitButton;

    }

    /**
     * Creates and returns the restart button for the game over menu.
     * @param level The current level of the game.
     * @param gameOverMenu The JFrame of the game over menu.
     * @return The JButton representing the restart button.
     */
    public JButton getRestartButton(int level, JFrame menu) {
        JButton restartButton = new JButton("Restart Level");
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    menu.dispose();

                if (gameThread.isAlive()){
                    interruptThread();
                }
                try {
                    startThread(); // Ensure gameEngine is initialized before interrupting the thread
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
     * @return The JButton representing the next level button.
     */
    public JButton getNextLevelButton(JFrame victoryFrame) {
        JButton nextLevelButton = new JButton("Next");
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                victoryFrame.dispose();
                level++;
                try {
                    startThread(); // Ensure gameEngine is initialized before interrupting the thread
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        };
        nextLevelButton.addActionListener(al);

        return nextLevelButton;
    }

    public JLabel getScorLabel() {
        return new JLabel("Score: " + GameStateImpl.getScore().getTotal());
    }

    /**
     * Starts the game thread and initializes the game engine.
     * @throws IOException if an I/O error occurs.
     */
    private void startThread() throws IOException {
        gameEngine = new GameEngine(level);
        gameThread = new Thread(gameEngine);
        gameThread.start();
        System.out.println(gameThread);
    }

    /**
     * Interrupts the game thread if it is alive and stops the game engine.
     */
    private void interruptThread() {
        System.out.println(gameThread);
        if (gameThread != null && gameThread.isAlive()) {
            gameThread.interrupt();
            gameEngine.stop();
        }
    }
}
