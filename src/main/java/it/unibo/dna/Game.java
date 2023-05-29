package it.unibo.dna;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.lang.IllegalArgumentException;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.graphics.Display;
import it.unibo.dna.model.EventFactory;
import it.unibo.dna.model.EventFactoryImpl;
import it.unibo.dna.model.EventQueue;
import it.unibo.dna.model.RectBoundingBox;
import it.unibo.dna.model.Score;
import it.unibo.dna.model.object.Door;
import it.unibo.dna.model.object.MovablePlatform;
import it.unibo.dna.model.object.ActivableObjectImpl;
import it.unibo.dna.model.object.Diamond;
import it.unibo.dna.model.object.api.BoundingBox;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.api.Player;

/**
 * Class that models the state of the game.
 */
public class Game {

    public static final double Gravity = 4;

    private Display display;
    private List<Entity> entities = new ArrayList<>();
    private BoundingBox boundingBox;
    private EventFactory event = new EventFactoryImpl();
    private Score score;
    private EventQueue eventQueue = new EventQueue();

    /**
     * {@link Game} constructor.
     * 
     * @param width  the width of the game
     * @param height the height of the game
     * @param level  the level of the game
     */
    public Game(final int width, final int height, final int level) {
        this.boundingBox = new RectBoundingBox(new Position2d(0, 0), height, width);
        this.score = new Score();
        this.display = new Display(width, height, this);
        this.entities.add(display.button);
        this.entities.add(display.lever);
        this.entities.add(display.p1);
        this.entities.add(display.p2);
        this.entities.add(display.mp1);
        this.entities.add(display.mp2);
        this.entities.add(display.diamond);
    }

    /**
     * Updates the state of the game.
     */
    public void update() {

        this.gravity(display.angel);
        this.gravity(display.devil);

        this.checkCollisions(display.angel);
        this.checkCollisions(display.devil);

        this.eventQueue.manageEvents(this);

        this.checkBorders(display.angel);
        this.checkBorders(display.devil);

        display.mi.update();

        display.angel.update();
        display.devil.update();

        for (Entity ent : entities) {
            if (ent instanceof MovablePlatform) {
                ((MovablePlatform) ent).setLastPosition();
                ((MovablePlatform) ent).update();
                ((MovablePlatform) ent).findLimit();
            }
        }
    }

    private void gravity(Player player) {
        if (player.getVector().getY() < Gravity) {
            player.getVector().sumY(Player.STANDARDVELOCITY);
        }
    }

    public void render() {
        display.render(this);
    }

    /**
     * 
     * @return the {@link BoundingBox}
     */
    public BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    /**
     * 
     * @param boundingBox the {@link BoundingBox}
     */
    public void setBoundingBox(final RectBoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    /**
     * Adds a new {@link Entity} in the game.
     * 
     * @param e the {@link Entity} to add
     */
    public void addEntity(final Entity e) {
        this.entities.add(e);
    }

    /**
     * Removes an {@link Entity} from the game.
     * 
     * @param e the {@link Entity} to remove
     */
    public void removeEntity(final Entity e) {
        this.entities.remove(e);
    }

    /**
     * 
     * @return the list of {@link Entity} of the game
     */
    public List<Entity> getEntities() {
        return this.entities;
    }

    /**
     * 
     * @return the list of {@link Event}
     */
    public EventQueue getEventQueue() {
        return this.eventQueue;
    }

    /**
     * Manages when a character leaves an {@link ActivableObjectImpl}.
     * 
     * @param character the {@link Player} to check
     */
    private void freeActivableObject(final Player character) {
        var box = character.getBoundingBox();
        this.getEntities().stream().filter((e) -> 
        !e.getBoundingBox().isCollidingWith(box.getPosition(), box.getHeight(), box.getWidth())).filter((e) -> 
        e.getClass().getName().equals("it.unibo.dna.model.object.ActivableObjectImpl")).forEach((e) -> {
            Optional<Player> objPlayer = ((ActivableObjectImpl) e).getPlayer();
            if (objPlayer.isPresent() && objPlayer.get().equals(character)) {
                if (((ActivableObjectImpl) e).type.equals(ActivableObjectImpl.Activator.BUTTON)) {
                    ((ActivableObjectImpl) e).deactivate();
                }
                ((ActivableObjectImpl) e).resetPlayer();
            }
        });
    }

    /**
     * Checks the collision of a character with the entities in the game.
     * 
     * @param character the moving {@link Player}
     */
    private void checkCollisions(final Player character) {
        Position2d chPos = character.getPosition().sum(character.getVector());
        double chHeight = character.getBoundingBox().getHeight();
        double chWidth = character.getBoundingBox().getWidth();

        this.getEntities().stream().filter((e) -> e.getBoundingBox().isCollidingWith(chPos, chHeight, chWidth)).forEach((e) -> {
            String cl = e.getClass().getName();
            switch (cl) {
                case "it.unibo.dna.model.object.Platform" ->
                    this.eventQueue.addEvent(event.hitPlatformEvent(e, character));
                case "it.unibo.dna.model.object.MovablePlatform" -> {
                    this.eventQueue.addEvent(event.hitPlatformEvent(e, character));
                    this.eventQueue.addEvent(event.hitMovablePlatformEvent((MovablePlatform) e, character));
                }
                case "it.unibo.dna.model.object.ActivableObjectImpl" -> {
                    if (((ActivableObjectImpl) e).type.equals(ActivableObjectImpl.Activator.BUTTON)) {
                        this.eventQueue.addEvent(event.hitButtonEvent((ActivableObjectImpl) e, character));
                    } else {
                        this.eventQueue.addEvent(event.hitLeverEvent((ActivableObjectImpl) e, character));
                    }
                }
                case "it.unibo.dna.model.object.Door" ->
                    this.eventQueue.addEvent(event.hitDoorEvent((Door) e, character));
                case "it.unibo.dna.model.object.Diamond" -> {
                    this.eventQueue.addEvent(event.soundEvent("Diamond_sound"));
                    this.eventQueue.addEvent(event.hitDiamondEvent((Diamond) e, score));
                }
                default -> throw new IllegalArgumentException();
            }
        });

        freeActivableObject(character);
    }

    /**
     * Checks the collision of a character with the vertical borders.
     * 
     * @param pos    the x coordinate of the character's position
     * @param lenght the lenght of the character
     * @return true if the character is colliding with a vertical border
     */
    public boolean checkVerticalBorders(final double pos, final double lenght) {
        double sxBorder = this.boundingBox.getPosition().getX();
        double dxBorder = this.boundingBox.getPosition().getX() + this.boundingBox.getWidth();

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
        double northBorder = this.boundingBox.getPosition().getY();
        double southBorder = this.boundingBox.getPosition().getY() + this.boundingBox.getHeight();

        return pos <= northBorder || pos + height >= southBorder;
    }

    /**
     * Checks the collision of a character with the borders.
     * 
     * @param character the moving {@link Player}
     */
    public void checkBorders(final Player character) {
        Position2d chPos = character.getPosition().sum(character.getVector());
        double chHeight = character.getBoundingBox().getHeight();
        double chLenght = character.getBoundingBox().getWidth();

        if (this.checkVerticalBorders(chPos.getX(), chLenght)) {
            event.hitBorderYEvent(character).manage(this);
        }
        if (this.checkHorizontalBorders(chPos.getY(), chHeight)) {
            event.hitBorderXEvent(character).manage(this);
        }
    }
}
