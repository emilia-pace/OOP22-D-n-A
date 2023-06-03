package it.unibo.dna.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.awt.Dimension;
import java.awt.Toolkit;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.object.EntityFactory;
import it.unibo.dna.model.object.EntityFactoryImpl;
import it.unibo.dna.model.object.MovablePlatform;
import it.unibo.dna.model.object.api.Entity.entityType;
import it.unibo.dna.model.object.player.PlayerImpl;
import it.unibo.dna.model.object.player.api.Player;
import it.unibo.dna.model.object.api.Entity;

public class Level {

    File lvlFile;
    List<Entity> entities = new ArrayList<>();
    List<Player> characters = new ArrayList<>();
    String nameFile;
    String s;
    public EntityFactoryImpl entityFactoryImpl = new EntityFactoryImpl();
    PlayerImpl angel;
    PlayerImpl devil;

    public Level(int lvl) {
        getFile(lvl);
        try {
            this.entitiesList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void entitiesList() throws IOException {
        FileReader f = new FileReader(nameFile);
        BufferedReader b;

        b = new BufferedReader(f);
        s = b.readLine();

        while (s != null) {
            String[] splittedC = s.split(" ");
            switch (splittedC[0]) {
                case "angel":
                    angel = new PlayerImpl(null,
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2])),
                            new Vector2d(0, 0), EntityFactory.PLAYER_HEIGHT, EntityFactory.PLAYER_WIDTH,
                            PlayerImpl.PlayerType.ANGEL);
                    characters.add(angel);
                    System.out.println("a \n");
                    break;

                case "devil":
                    devil = new PlayerImpl(null,
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2])),
                            new Vector2d(0, 0), EntityFactory.PLAYER_HEIGHT, EntityFactory.PLAYER_WIDTH,
                            PlayerImpl.PlayerType.DEVIL);
                    characters.add(devil);
                    System.out.println("d \n");
                    break;

                case "dDevil":
                    entities.add(entityFactoryImpl.createEntity(Optional.empty(), entityType.DEVIL_DOOR,
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2]))));
                    System.out.println("c \n");
                    break;

                case "dAngel":
                    entities.add(entityFactoryImpl.createEntity(Optional.empty(), entityType.ANGEL_DOOR,
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2]))));
                    System.out.println("c \n");
                    break;

                case "diamond":
                    entities.add(entityFactoryImpl.createEntity(Optional.empty(), entityType.DIAMOND,
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2]))));
                    break;

                case "button":
                    entities.add(entityFactoryImpl.createEntity(Optional.of((MovablePlatform) entities.stream()
                            .filter(e -> e.getType().equals(entityType.MOVABLEPLATFORM))
                            .reduce((first, second) -> second).get()),
                            entityType.BUTTON,
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2]))));
                    break;

                case "lever":
                    entities.add(entityFactoryImpl.createEntity(Optional.of((MovablePlatform) entities.stream()
                            .filter(e -> e.getType().equals(entityType.MOVABLEPLATFORM))
                            .reduce((first, second) -> second).get()),
                            entityType.LEVER,
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2]))));
                    break;

                case "platform":
                    entities.add(entityFactoryImpl.createEntity(Optional.empty(), entityType.PLATFORM,
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2]))));
                    System.out.println("1 \n");
                    break;

                case "movablePlatform":
                    entities.add(entityFactoryImpl.createEntity(Optional.empty(), entityType.MOVABLEPLATFORM,
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2])),
                            new Position2d(Double.parseDouble(splittedC[3]), Double.parseDouble(splittedC[4]))));
                    break;

                case "rPuddle":
                    entities.add(entityFactoryImpl.createEntity(Optional.empty(), entityType.RED_PUDDLE,
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2]))));
                    break;

                case "bPuddle":
                    entities.add(entityFactoryImpl.createEntity(Optional.empty(), entityType.BLUE_PUDDLE,
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2]))));
                    break;

                case "pPuddle":
                    entities.add(entityFactoryImpl.createEntity(Optional.empty(), entityType.PURPLE_PUDDLE,
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2]))));
                    break;

            }
            System.out.println(entities.size());
            System.out.println(characters.size());
            s = b.readLine();
        }

        b.close();
    }

    public List<Entity> getEntities() {
        return this.entities;
    }

    private void getFile(int lvl) {
        switch (lvl) {
            case 1:
                nameFile = "src\\main\\resources\\levels\\lv1.txt";
                break;
            case 2:
                nameFile = "lvl2.txt";
                break;
            case 3:
                nameFile = "lvl3.txt";
                break;
        }

    }

    public List<Player> getCharacters() {
        return this.characters;
    }

}
