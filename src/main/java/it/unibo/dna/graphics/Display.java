package it.unibo.dna.graphics;

import javax.swing.*;

import it.unibo.dna.Game;
import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.input.KeyboardHandler;
import it.unibo.dna.model.object.ActivableObject;
import it.unibo.dna.model.object.MovablePlatform;
import it.unibo.dna.model.object.Platform;
import it.unibo.dna.model.object.PlayerImpl;
import it.unibo.dna.model.object.api.Player;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {

        private Canvas canvas;
        public ManageImage mi = new ManageImage();
        public PlayerImpl angel = new PlayerImpl(new Position2d(100, 500), new Vector2d(0, 0), 40, 30,
                        PlayerImpl.Type.ANGEL);
        public PlayerImpl devil = new PlayerImpl(new Position2d(200, 500), new Vector2d(0, 0), 40, 30,
                        PlayerImpl.Type.DEVIL);
        public Platform p1 = new Platform(new Position2d(400, 550), 30.0, 300.0);
        public Platform p2 = new Platform(new Position2d(90, 450), 30.0, 300.0);
        public MovablePlatform mp1 = new MovablePlatform(new Position2d(200, 230), new Vector2d(0, 0), 30, 100,
                        new Position2d(200, 100));
        public MovablePlatform mp2 = new MovablePlatform(new Position2d(340, 350), new Vector2d(0, 0), 30, 100,
                        new Position2d(390, 200));
        public ActivableObject lever = new ActivableObject(new Position2d(120, 420), 30.0, 30.0,
                        ActivableObject.Activator.LEVER, mp1);
        public ActivableObject button = new ActivableObject(new Position2d(500, 550), 30.0, 30.0,
                        ActivableObject.Activator.BUTTON, mp2);
        JLabel boh = new JLabel("\u2194");

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

                graphics.drawImage(
                                mi.playerChooseImage(this.angel).getScaledInstance(
                                                (int) angel.getBoundingBox().getWidth(),
                                                (int) angel.getBoundingBox().getHeight(), Image.SCALE_DEFAULT),
                                (int) angel.getPosition().x, (int) angel.getPosition().y, this);
                graphics.drawImage(
                                mi.playerChooseImage(this.devil).getScaledInstance(
                                                (int) devil.getBoundingBox().getWidth(),
                                                (int) devil.getBoundingBox().getHeight(), Image.SCALE_DEFAULT),
                                (int) devil.getPosition().x, (int) devil.getPosition().y, this);
                graphics.setColor(Color.WHITE);
                graphics.drawImage(mi.PlatformImage().getScaledInstance(
                                                (int) p1.getBoundingBox().getWidth(),
                                                (int) p1.getBoundingBox().getHeight(),Image.SCALE_DEFAULT),
                                (int) p1.getPosition().x,(int) p1.getPosition().y, this);
                
                graphics.drawImage(mi.PlatformImage().getScaledInstance(
                                                (int) p2.getBoundingBox().getWidth(),
                                                (int) p2.getBoundingBox().getHeight(),Image.SCALE_DEFAULT),
                                (int) p2.getPosition().x,(int) p2.getPosition().y, this);
                graphics.setColor(Color.GREEN);
                graphics.drawImage(mi.MovablePlatformImage().getScaledInstance(
                                                (int) mp1.getBoundingBox().getWidth(),
                                                (int) mp1.getBoundingBox().getWidth(),Image.SCALE_DEFAULT),
                                (int) mp1.getPosition().x, (int) mp1.getPosition().y, this);
                graphics.drawImage(mi.MovablePlatformImage().getScaledInstance(
                                        (int) mp2.getBoundingBox().getWidth(),
                                        (int) mp2.getBoundingBox().getWidth(),Image.SCALE_DEFAULT),
                        (int) mp2.getPosition().x, (int) mp2.getPosition().y, this);
                graphics.fillRect((int) mp2.getPosition().x, (int) mp2.getPosition().y,
                                (int) mp2.getBoundingBox().getWidth(),
                                (int) mp2.getBoundingBox().getHeight());
                graphics.drawImage(
                                mi.ActivableObjectChooseImage(this.lever).getScaledInstance(
                                        (int) lever.getBoundingBox().getWidth(),
                                        (int) lever.getBoundingBox().getHeight(), Image.SCALE_DEFAULT),
                                (int) lever.getPosition().x, (int) lever.getPosition().y,this);
                graphics.setColor(Color.MAGENTA);
                graphics.fillRect((int) button.getPosition().x, (int) button.getPosition().y,
                                (int) button.getBoundingBox().getWidth(),
                                (int) button.getBoundingBox().getHeight());

                graphics.dispose();
                bufferStrategy.show();

                if (this.angel.getVector().y == -Player.JumpVelocity + Player.StandardVelocity) {
                        this.makeSound("src\\main\\resurces\\Angel_audio.wav");
                }
                if (this.devil.getVector().y == -Player.JumpVelocity + Player.StandardVelocity) {
                        this.makeSound("src\\main\\resurces\\Devil_audio.wav");
                }

        }

        private void makeSound(String fileName) {
                File file = new File(fileName);
                try {
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(file));
                        clip.start();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
}