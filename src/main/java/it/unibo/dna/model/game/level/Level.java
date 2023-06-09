package it.unibo.dna.model.game.level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unibo.dna.model.object.player.api.Player;
import it.unibo.dna.model.object.player.impl.PlayerImpl;
import it.unibo.dna.view.image.ImageManager;
import it.unibo.dna.model.common.Position2d;
import it.unibo.dna.model.common.Vector2d;
import it.unibo.dna.model.object.entity.api.Entity;
import it.unibo.dna.model.object.entity.api.EntityFactory;
import it.unibo.dna.model.object.entity.api.Entity.EntityType;
import it.unibo.dna.model.object.entity.impl.EntityFactoryImpl;
import it.unibo.dna.model.object.movableentity.impl.MovablePlatform;

/**
 * Represents a level in the game, containing entities and characters.
 */
public class Level {

    private final List<Entity> entities = new ArrayList<>();
    private final List<Player> characters = new ArrayList<>();
    private InputStream nameFile;
    private final EntityFactoryImpl entityFactoryImpl = new EntityFactoryImpl();
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageManager.class);

    /**
     * Constructs the Level object with the specified level number.
     * 
     * @param lvl The level number.
     */
    public Level(final int lvl) {
        nameFile(lvl);
        try {
            this.entitiesList();
        } catch (IOException e) {
            LOGGER.error("IOEexception occurred", e);
        }
    }

    /**
     * Reads the level file and creates the list of entities and characters.
     * 
     * @throws IOException if an I/O error occurs.
     */
    public final void entitiesList() throws IOException {
        BufferedReader b;

        final Reader reader = new InputStreamReader(nameFile);
        b = new BufferedReader(reader);
        String s = b.readLine();

        while (s != null) {
            final String[] splittedC = s.split(" ");
            switch (splittedC[0]) {
                case "angel":
                    final PlayerImpl angel = new PlayerImpl(
                            new Position2d(Double.parseDouble(splittedC[1]), Double.parseDouble(splittedC[2])),
                            new Vector2d(0, 0), EntityFactory.PLAYER_HEIGHT, EntityFactory.PLAYER_WIDTH,
                            PlayerImpl.PlayerType.ANGEL);
                    characters.add(angel);
                    break;

                case "devil":
                    final PlayerImpl devil = new PlayerImpl(
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
        return new ArrayList<>(entities);
    }

    /**
     * Returns the list of characters in the level.
     * 
     * @return The list of characters.
     */
    public List<Player> getCharacters() {
        return new ArrayList<>(characters);
    }

    private void nameFile(final int lvl) {
        switch (lvl) {
            case 1:
                nameFile = getClass().getClassLoader().getResourceAsStream("levels/lvl1.txt");
                break;
            case 2:
                nameFile = getClass().getClassLoader().getResourceAsStream("levels/lvl2.txt");
                break;
            case 3:
                nameFile = getClass().getClassLoader().getResourceAsStream("levels/lvl3.txt");
                break;
            default:
                break;
        }
    }

}
