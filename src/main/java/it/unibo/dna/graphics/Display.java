package it.unibo.dna.graphics;

import javax.swing.*;

import it.unibo.dna.GameState;
import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.input.KeyboardHandler;
import it.unibo.dna.model.object.EntityFactoryImpl;
import it.unibo.dna.model.object.MovablePlatform;
import it.unibo.dna.model.object.PlayerImpl;
import it.unibo.dna.model.object.api.Entity.entityType;
import it.unibo.dna.model.object.api.Entity;

import java.util.Optional;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {

        private Canvas canvas;
        public ManageImage mi = new ManageImage();
        public PlayerImpl angel;
        public PlayerImpl devil;
        public EntityFactoryImpl entityFactoryImpl = new EntityFactoryImpl();
        public Optional<MovablePlatform> emptyParameter = Optional.empty();

        public Entity platform1 = entityFactoryImpl.createEntity(emptyParameter, entityType.PLATFORM,new Position2d(400, 550));
        public Entity platform2 = entityFactoryImpl.createEntity(emptyParameter,entityType.PLATFORM,new Position2d(90, 450));
        public Entity movablePlatform1 = entityFactoryImpl.createEntity(emptyParameter,entityType.MOVABLEPLATFORM,new Position2d(200, 230),new Position2d(200,100));
        public Entity movablePlatform2 = entityFactoryImpl.createEntity(emptyParameter,entityType.MOVABLEPLATFORM,new Position2d(340, 350),new Position2d(390, 200));
        public Entity lever = entityFactoryImpl.createEntity(Optional.of((MovablePlatform)movablePlatform1),entityType.LEVER,new Position2d(120, 420));
        public Entity button = entityFactoryImpl.createEntity(Optional.of((MovablePlatform)movablePlatform2),entityType.BUTTON,new Position2d(500, 530));
        public Entity diamond = entityFactoryImpl.createEntity(emptyParameter,entityType.DIAMOND,new Position2d(200, 400));
        JLabel boh = new JLabel("\u2194");

        public Display(final int width, final int height, GameState game) {
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

                angel = new PlayerImpl(game, new Position2d(100, 500), new Vector2d(0, 0), 40, 30,
                                PlayerImpl.PlayerType.ANGEL);
                devil = new PlayerImpl(game, new Position2d(200, 500), new Vector2d(0, 0), 40, 30,
                                PlayerImpl.PlayerType.DEVIL);

                this.addKeyListener(new KeyboardHandler(KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, KeyEvent.VK_UP, angel));
                this.addKeyListener(new KeyboardHandler(KeyEvent.VK_D, KeyEvent.VK_A, KeyEvent.VK_W, devil));

        }

        public void render(GameState game) {
                BufferStrategy bufferStrategy = canvas.getBufferStrategy();
                Graphics graphics = bufferStrategy.getDrawGraphics();

                graphics.setColor(Color.BLACK);
                graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                if (game.getEntities().contains(diamond)) {
                        graphics.drawImage(mi.getDiamondImage().getScaledInstance(
                                        (int) diamond.getBoundingBox().getWidth(),
                                        (int) diamond.getBoundingBox().getHeight(), Image.SCALE_DEFAULT),
                                        (int) diamond.getPosition().getX(), (int) diamond.getPosition().getY(), this);

                }

                graphics.drawImage(
                                mi.playerChooseImage(this.angel),
                                (int) angel.getPosition().getX(), (int) angel.getPosition().getY(), this);
                graphics.drawImage(
                                mi.playerChooseImage(this.devil),
                                (int) devil.getPosition().getX(), (int) devil.getPosition().getY(), this);
                graphics.setColor(Color.WHITE);
                graphics.drawImage(mi.getImage(platform1).getScaledInstance(
                                (int) platform1.getBoundingBox().getWidth(),
                                (int) platform1.getBoundingBox().getHeight(), Image.SCALE_DEFAULT),
                                (int) platform1.getPosition().getX(), (int) platform1.getPosition().getY(), this);

                graphics.drawImage(mi.getImage(platform2).getScaledInstance(
                                (int) platform2.getBoundingBox().getWidth(),
                                (int) platform2.getBoundingBox().getHeight(), Image.SCALE_DEFAULT),
                                (int) platform2.getPosition().getX(), (int) platform2.getPosition().getY(), this);
                graphics.setColor(Color.GREEN);
                graphics.drawImage(mi.getImage(movablePlatform1).getScaledInstance(
                                (int) movablePlatform1.getBoundingBox().getWidth(),
                                (int) movablePlatform1.getBoundingBox().getHeight(), Image.SCALE_DEFAULT),
                                (int) movablePlatform1.getPosition().getX(), (int) movablePlatform1.getPosition().getY(), this);
                graphics.drawImage(mi.getImage(movablePlatform1).getScaledInstance(
                                (int) movablePlatform2.getBoundingBox().getWidth(),
                                (int) movablePlatform2.getBoundingBox().getHeight(), Image.SCALE_DEFAULT),
                                (int) movablePlatform2.getPosition().getX(), (int) movablePlatform2.getPosition().getY(), this);
                graphics.setColor(Color.GREEN);
                graphics.drawImage(
                                mi.getImage(this.lever).getScaledInstance(
                                                (int) lever.getBoundingBox().getWidth(),
                                                (int) lever.getBoundingBox().getHeight(), Image.SCALE_DEFAULT),
                                (int) lever.getPosition().getX(), (int) lever.getPosition().getY(), this);
                graphics.setColor(Color.MAGENTA);
                graphics.drawImage(mi.getImage(this.button).getScaledInstance(
                                                (int) button.getBoundingBox().getWidth(),
                                                (int) button.getBoundingBox().getHeight(), Image.SCALE_DEFAULT),
                                (int) button.getPosition().getX(), (int) button.getPosition().getY(), this);
                graphics.dispose();
                bufferStrategy.show();

        }
}