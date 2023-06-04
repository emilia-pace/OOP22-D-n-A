package it.unibo.dna.graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import it.unibo.dna.GameEngine;
import it.unibo.dna.GameStateImpl;
import it.unibo.dna.model.Score;

public class MenuFactoryImpl extends JFrame implements MenuFactory {
    private int level = 1;
    Thread gameThread;
    GameEngine gameEngine;

    @Override
    public GameMenu startMenu() {
        return new GameMenu() {

            @Override
            public JFrame createMenuFrame() {
                JFrame startMenu = new JFrame();
                JButton start = getStartButton(startMenu);
                JButton guide = getGuideButton();
                JButton quit = getQuitButton();
                JPanel panel = new JPanel();
                startMenu.getContentPane().setLayout(new BoxLayout(startMenu.getContentPane(), BoxLayout.Y_AXIS));
                startMenu.setSize(800, 600);
                startMenu.getContentPane().setBackground(Color.BLACK);

                panel.add(start);
                panel.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.setAlignmentY(Component.CENTER_ALIGNMENT);
                panel.setMinimumSize(new Dimension(600, 600));
                panel.add(guide);
                panel.add(quit);
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                startMenu.getContentPane().add(panel);
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

    public JButton getStartButton(JFrame startMenu) {
        JButton startButton = new JButton("Start");
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startMenu.dispose();
                try {
                    startThread();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

        };
        startButton.addActionListener(al);

        return startButton;
    }

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

    public JButton getRestartButton(int level, JFrame gameOverMenu) {
        JButton restartButton = new JButton("Restart Level");
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameOverMenu.dispose();
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
        return new JLabel("Score: " + GameStateImpl.score.getTotal());
    }


    private void startThread() throws IOException{
        gameEngine = new GameEngine(level);
        gameThread = new Thread(gameEngine);
        gameThread.start();
        System.out.println(gameThread);
    }

    private void interruptThread() {
        System.out.println(gameThread);
        if (gameThread != null && gameThread.isAlive()) {
            gameThread.interrupt();
            gameEngine.stop();
        }
    }
    

}
  
    
    
    


