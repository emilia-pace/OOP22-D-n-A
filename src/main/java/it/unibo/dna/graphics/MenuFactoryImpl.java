package it.unibo.dna.graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.dna.Game;
import it.unibo.dna.GameEngine;
import it.unibo.dna.model.Score;

public class MenuFactoryImpl extends JFrame implements MenuFactory {
    private int level = 0;
    Score totalScore;

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
                panel.setMinimumSize(new Dimension(600,600));
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
                gameOverFrame.getContentPane().setLayout(new BoxLayout(gameOverFrame.getContentPane(), BoxLayout.Y_AXIS));
                gameOverFrame.setSize(800, 600);
                gameOverFrame.getContentPane().setBackground(Color.BLACK);

                gameOverFrame.getContentPane().add(panel);
                gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameOverFrame.setLocationRelativeTo(null);
                gameOverFrame.setVisible(true);



                return gameOverFrame;
            }
            
        };
    }

    @Override
    public GameMenu victoryMenu(Score totalScore) {
        return new GameMenu() {
            
        @Override
            public JFrame createMenuFrame() {
                JFrame gameOverFrame = new JFrame("You Won");
                JButton nextLevel = getNextLevelButton(gameOverFrame);
                JButton quit = getQuitButton();
                JLabel score = getScorLabel();
                JPanel panel = new JPanel();

                panel.add(score);
                panel.add(nextLevel);
                panel.add(quit);
                gameOverFrame.getContentPane().setLayout(new BoxLayout(gameOverFrame.getContentPane(), BoxLayout.Y_AXIS));
                gameOverFrame.setSize(800, 600);
                gameOverFrame.getContentPane().setBackground(Color.BLACK);

                gameOverFrame.getContentPane().add(panel);
                gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameOverFrame.setLocationRelativeTo(null);
                gameOverFrame.setVisible(true);



                return gameOverFrame;
            }
            
        };
    }



    public JButton getStartButton(JFrame startMenu) {
        JButton startButton = new JButton("Start");
        ActionListener al= new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startMenu.setVisible(false);
                new Thread(new GameEngine(new Game(800, 600, level))).start();
            }
            
        };
        startButton.addActionListener(al);

        return startButton;
    }

    public JButton getGuideButton() {
        JButton guideButton = new JButton("Guide", null);
        ActionListener al= new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFrame guideFrame = new JFrame("Guide");
                JLabel angelLabel = new JLabel("Angel controls: W:jump, A: left, D: right ");
                JLabel deviLabel = new JLabel("Devil controls: ↑: jump, \u2190: left, \u2192: right");
                JLabel goalLabel = new JLabel("The goal of the game is to reach the doors together");

                angelLabel.setBounds(50,50,500,300);
                deviLabel.setBounds(50,70,500,300);
                goalLabel.setBounds(50,100,500,300);

                guideFrame.add(angelLabel);
                guideFrame.add(deviLabel);
                guideFrame.add(goalLabel);
                guideFrame.setSize(500, 500);
                guideFrame.setResizable(false);
                guideFrame.setVisible(true);
                
            }
            
        };
        guideButton.addActionListener(al);
        return guideButton;
    }

    public JButton getQuitButton() {
        JButton quitButton = new JButton("Quit");
        ActionListener al= new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
            
        };
        quitButton.addActionListener(al);

        return quitButton;
        
    }

    public JButton getRestartButton(int level, JFrame gameOverMenu) {
        JButton restartButton = new JButton("Restart Level");
        ActionListener al= new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameOverMenu.setVisible(false);
                new Thread(new GameEngine(new Game(800, 600, level))).start();
            }
            
        };
        restartButton.addActionListener(al);

        return restartButton;
    }

    public JButton getNextLevelButton(JFrame victoryMenu){
        JButton restartButton = new JButton("Restart");
        ActionListener al= new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                victoryMenu.setVisible(false);
                level++;
                new Thread(new GameEngine(new Game(800, 600, level))).start();
            }
            
        };
        restartButton.addActionListener(al);

        return restartButton;
    }

    public JLabel getScorLabel(){
        return new JLabel("Score: %d"+ totalScore);
    }

    
}