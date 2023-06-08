package it.unibo.dna.model.game.level;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.common.Vector2d;
import it.unibo.dna.model.object.player.api.Player;
import it.unibo.dna.model.object.player.impl.PlayerImpl;
import it.unibo.dna.model.object.entity.api.Entity;
import it.unibo.dna.model.object.entity.api.EntityFactory;
import it.unibo.dna.model.object.entity.api.Entity.EntityType;
import it.unibo.dna.model.object.entity.impl.EntityFactoryImpl;
import it.unibo.dna.model.object.movableentity.impl.MovablePlatform;

/**
 * Represents a level in the game, containing entities and characters.
 */
public class Level {

    private List<Entity> entities = new ArrayList<>();
    private List<Player> characters = new ArrayList<>();
    //private String nameFile;
    private InputStream nameFile;
    private String s;
    private EntityFactoryImpl entityFactoryImpl = new EntityFactoryImpl();
    private PlayerImpl angel;
    private PlayerImpl devil;

    /**
     * Constructs the Level object with the specified level number.
     * 
     * @param lvl The level number.
     */
    public Level(final int lvl) {
        getFile(lvl);
        try {
            this.entitiesList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the level file and creates the list of entities and characters.
     * 
     * @throws IOException if an I/O error occurs.
     */
    public void entitiesList() throws IOException {
        FileReader f = new FileReader(nameFile);
        BufferedReader b;

        b = new BufferedReader(f);
        s = b.readLine();

        while (s != null) {
            String[] splittedC = s.split(" ");
            switch (splittedC[0]) {
                case "angel":
                    angel = new PlayerImpl(
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2])),
                            new Vector2d(0, 0), EntityFactory.PLAYER_HEIGHT, EntityFactory.PLAYER_WIDTH,
                            PlayerImpl.PlayerType.ANGEL);
                    characters.add(angel);
                    break;

                case "devil":
                    devil = new PlayerImpl(
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2])),
                            new Vector2d(0, 0), EntityFactory.PLAYER_HEIGHT, EntityFactory.PLAYER_WIDTH,
                            PlayerImpl.PlayerType.DEVIL);
                    characters.add(devil);
                    break;

                case "dDevil":
                    entities.add(entityFactoryImpl.createEntity(Optional.empty(), EntityType.DEVIL_DOOR,
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2]))));
                    break;

                case "dAngel":
                    entities.add(entityFactoryImpl.createEntity(Optional.empty(), EntityType.ANGEL_DOOR,
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2]))));
                    break;

                case "diamond":
                    entities.add(entityFactoryImpl.createEntity(Optional.empty(), EntityType.DIAMOND,
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2]))));
                    break;

                case "button":
                    entities.add(entityFactoryImpl.createEntity(Optional.of((MovablePlatform) entities.stream()
                            .filter(e -> e.getType().equals(EntityType.MOVABLEPLATFORM))
                            .reduce((first, second) -> second).get()),
                            EntityType.BUTTON,
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2]))));
                    break;

                case "lever":
                    entities.add(entityFactoryImpl.createEntity(Optional.of((MovablePlatform) entities.stream()
                            .filter(e -> e.getType().equals(EntityType.MOVABLEPLATFORM))
                            .reduce((first, second) -> second).get()),
                            EntityType.LEVER,
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2]))));
                    break;

                case "platform":
                    entities.add(entityFactoryImpl.createEntity(Optional.empty(), EntityType.PLATFORM,
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2]))));
                    break;

                case "movablePlatform":
                    entities.add(entityFactoryImpl.createEntity(Optional.empty(), EntityType.MOVABLEPLATFORM,
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2])),
                            new Position2d(Double.parseDouble(splittedC[3]), Double.parseDouble(splittedC[4]))));
                    break;

                case "rPuddle":
                    entities.add(entityFactoryImpl.createEntity(Optional.empty(), EntityType.RED_PUDDLE,
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2]))));
                    break;

                case "bPuddle":
                    entities.add(entityFactoryImpl.createEntity(Optional.empty(), EntityType.BLUE_PUDDLE,
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2]))));
                    break;

                case "pPuddle":
                    entities.add(entityFactoryImpl.createEntity(Optional.empty(), EntityType.PURPLE_PUDDLE,
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2]))));
                    break;
                default:
                    break;
            }
            s = b.readLine();
        }

        b.close();
    }

    /**
     * Returns the list of entities in the level.
     * 
     * @return The list of entities.
     */
    public List<Entity> getEntities() {
        return this.entities;
    }

    private void getFile(final int lvl) {
        switch (lvl) {
            case 1:
                nameFile = ClassLoader.getSystemResource("levels/lvl1.txt").toString();
                break;
            case 2:
                nameFile = ClassLoader.getSystemResource("levels/lvl2.txt").toString();
                break;
            case 3:
                nameFile = ClassLoader.getSystemResource("levels/lvl3.txt").toString();
                break;
            default:
                break;
        }
    }

    /**
     * Returns the list of characters in the level.
     * 
     * @return The list of characters.
     */
    public List<Player> getCharacters() {
        return this.characters;
    }
}
