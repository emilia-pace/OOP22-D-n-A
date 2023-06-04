package it.unibo.dna;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.graphics.MenuFactory;
import it.unibo.dna.graphics.MenuFactoryImpl;
import it.unibo.dna.model.EventFactory;
import it.unibo.dna.model.EventFactoryImpl;
import it.unibo.dna.model.EventQueue;
import it.unibo.dna.model.RectBoundingBox;
import it.unibo.dna.model.Score;
import it.unibo.dna.model.object.Door;
import it.unibo.dna.model.object.MovablePlatform;
import it.unibo.dna.model.object.Puddle;
import it.unibo.dna.model.object.ActivableObjectImpl;
import it.unibo.dna.model.object.Diamond;
import it.unibo.dna.model.object.api.BoundingBox;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.player.api.Player;

/**
 * Class that implements {@link GameState}.
 */
public class GameStateImpl implements GameState {

    public static final double Gravity = 0.8;

    private final List<Entity> entities;
    private final List<Player> characters;
    private BoundingBox boundingBox;
    private final EventFactory event = new EventFactoryImpl();
    private final MenuFactory menuFactory = new MenuFactoryImpl();
    private Score score;
    private final EventQueue eventQueue = new EventQueue();

    /**
     * {@link Game} constructor.
     * 
     * @param width  the width of the game
     * @param height the height of the game
     * @param level  the level of the game
     * @throws IOException
     */
    public GameStateImpl(final int width, final int height, final List<Entity> level, List<Player> players)
            throws IOException {
        this.boundingBox = new RectBoundingBox(new Position2d(0, 0), height, width);
        this.score = new Score(0.0);
        this.entities = level;
        this.characters = players;
    }

    private void checkForEndGame() {
        long numberOfOpenedDoors = entities.stream()
                                    .filter(entity -> entity instanceof Door)
                                    .map(entity -> (Door)entity)
                                    .filter(entity -> entity.getDoorState().equals(Door.DoorState.OPEN_DOOR))
                                    .count();
        if (numberOfOpenedDoors == 2) {
            menuFactory.victoryMenu(score).createMenuFrame();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {

        characters.stream().forEach((c) -> {
            this.gravity(c);
            this.checkCollisions(c);
            this.checkBorders(c);
        });

        this.eventQueue.manageEvents(this);

        characters.stream().forEach((c) -> c.update());

        entities.stream()
            .filter(entity -> entity instanceof MovablePlatform)
            .map(entity -> (MovablePlatform) entity)
            .forEach(entity -> entity.movablePlatformUpdate());
    }

    /**
     * Manages the gravity of the player
     * 
     * @param player the {@link Player} to manage
     */
    private void gravity(Player player) {
        if (player.getVector().getY() < Gravity) {
            player.getVector().sumY(Player.STANDARDVELOCITY);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBoundingBox(final BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEntity(final Entity e) {
        this.entities.add(e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEntity(final Entity e) {
        this.entities.remove(e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Entity> getEntities() {
        return this.entities;
    }

    /**
     * {@inheritDoc}
     */
    public List<Player> getCharacters() {
        return this.characters;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EventQueue getEventQueue() {
        return this.eventQueue;
    }

    /**
     * {@inheritDoc}
     */
    public Score getScore(){
        return this.score;
    }

    /**
     * {@inheritDoc}
     */
    public void updateScore(Score score){
        this.score = score;
    }

    /**
     * Manages when a character leaves an {@link ActivableObjectImpl}.
     * 
     * @param character the {@link Player} to check
     */
    private void freeActivableObject(final Player character) {
        final var box = character.getBoundingBox();
        this.getEntities().stream()
                .filter((e) -> !e.getBoundingBox().isCollidingWith(box.getPosition(), box.getHeight(), box.getWidth()))
                .filter((e) -> (e.getType().equals(Entity.EntityType.BUTTON))
                        || e.getType().equals(Entity.EntityType.LEVER))
                .forEach((e) -> {
                    final Optional<Player> objPlayer = ((ActivableObjectImpl) e).getPlayer();
                    if (objPlayer.isPresent() && objPlayer.get().equals(character)) {
                        if (e.getType().equals(Entity.EntityType.BUTTON))
                            ((ActivableObjectImpl) e).deactivate();
                        ((ActivableObjectImpl) e).resetPlayer();
                    }
                });
    }

    private void freeDoor(final Player character) {
        final var box = character.getBoundingBox();
        this.getEntities().stream()
                .filter((e) -> !e.getBoundingBox().isCollidingWith(box.getPosition(), box.getHeight(), box.getWidth()))
                .filter((e) -> (e.getType().equals(Entity.EntityType.DEVIL_DOOR))
                        || e.getType().equals(Entity.EntityType.ANGEL_DOOR))
                .map((e) -> (Door) e)
                .forEach((e) -> {
                    final Optional<Player> doorPlayer = e.getPlayer();
                    if(doorPlayer.isPresent() && doorPlayer.get().equals(character)) {
                        e.closeDoor();
                        e.resetPlayer();
                    }
                });
    }

    /**
     * Checks the collision of a character with the entities in the game.
     * 
     * @param character the moving {@link Player}
     */
    private void checkCollisions(final Player character) {
        final Position2d chPos = character.getPosition().sum(character.getVector());
        final double chHeight = character.getBoundingBox().getHeight();
        final double chWidth = character.getBoundingBox().getWidth();

        this.getEntities().stream().filter((e) -> e.getBoundingBox().isCollidingWith(chPos, chHeight, chWidth))
                .forEach((e) -> {
                    switch (e.getType()) {
                        case PLATFORM -> this.eventQueue.addEvent(event.hitPlatformEvent(e, character));
                        case MOVABLEPLATFORM -> {
                            this.eventQueue.addEvent(event.hitPlatformEvent(e, character));
                            this.eventQueue.addEvent(event.hitMovablePlatformEvent((MovablePlatform) e, character));
                        }
                        case BUTTON ->
                            this.eventQueue.addEvent(event.hitButtonEvent((ActivableObjectImpl) e, character));
                        case LEVER -> this.eventQueue.addEvent(event.hitLeverEvent((ActivableObjectImpl) e, character));
                        case ANGEL_DOOR, DEVIL_DOOR -> {
                            this.eventQueue.addEvent(event.hitDoorEvent((Door) e, character, score));
                            this.checkForEndGame();
                        }
                        case DIAMOND -> {
                            this.eventQueue.addEvent(event.soundEvent("Diamond_sound"));
                            this.eventQueue.addEvent(event.hitDiamondEvent((Diamond) e, score));
                        }
                        case RED_PUDDLE, BLUE_PUDDLE, PURPLE_PUDDLE -> {
                            this.eventQueue.addEvent(event.hitPlatformEvent(e, character));
                            this.eventQueue.addEvent(event.hitPuddleEvent((Puddle) e, character));
                        }
                        default -> throw new IllegalArgumentException();
                    }
                });

        freeActivableObject(character);
        freeDoor(character);
    }

    /**
     * Checks the collision of a character with the vertical borders.
     * 
     * @param pos    the x coordinate of the character's position
     * @param lenght the lenght of the character
     * @return true if the character is colliding with a vertical border
     */
    public boolean checkVerticalBorders(final double pos, final double lenght) {
        final double sxBorder = this.boundingBox.getPosition().getX();
        final double dxBorder = this.boundingBox.getPosition().getX() + this.boundingBox.getWidth();

        return pos <= sxBorder || pos + lenght >= dxBorder;
    }

    /**
     * Checks the collision of a character with the horizontal borders.
     * 
     * @param pos    the y coordinate of the character's position
     * @param height the height of the character
     * @return true if the character is colliding with an horizontal border
     */
    public boolean checkHorizontalBorders(final double pos, final double height) {
        final double northBorder = this.boundingBox.getPosition().getY();
        final double southBorder = this.boundingBox.getPosition().getY() + this.boundingBox.getHeight();

        return pos <= northBorder || pos + height >= southBorder;
    }

    /**
     * Checks the collision of a character with the borders.
     * 
     * @param character the moving {@link Player}
     */
    private void checkBorders(final Player character) {
        final Position2d chPos = character.getPosition().sum(character.getVector());
        final double chHeight = character.getBoundingBox().getHeight();
        final double chLenght = character.getBoundingBox().getWidth();

        if (this.checkVerticalBorders(chPos.getX(), chLenght)) {
            event.hitBorderYEvent(character).manage(this);
        }
        if (this.checkHorizontalBorders(chPos.getY(), chHeight)) {
            event.hitBorderXEvent(character).manage(this);
        }
    }
}
