package it.unibo.dna;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.graphics.Display;
import it.unibo.dna.model.EventFactory;
import it.unibo.dna.model.EventFactoryImpl;
import it.unibo.dna.model.EventQueue;
import it.unibo.dna.model.RectBoundingBox;
import it.unibo.dna.model.Score;
import it.unibo.dna.model.object.Door;
import it.unibo.dna.model.object.MovablePlatform;
import it.unibo.dna.model.object.ActivableObject;
import it.unibo.dna.model.object.Diamond;
import it.unibo.dna.model.object.api.BoundingBox;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.api.Player;

public class Game {

    public static final double Gravity = 4;

    private Display display;
    private List<Entity> entities = new ArrayList<>();
    private BoundingBox boundingBox;
    private EventFactory event = new EventFactoryImpl();
    private Score score;
    private EventQueue eventQueue = new EventQueue();

    public Game(int width, int height, int level) {
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
                ((MovablePlatform) ent).findLimit();
                ((MovablePlatform) ent).update();
            }
        }

    }

    private void gravity(Player player) {
        if (player.getVector().y < Gravity) {
            player.getVector().sumY(Player.StandardVelocity);
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

    public EventQueue getEvents() {
        return this.eventQueue;
    }

    private void freeActivableObject(ActivableObject e) {
        if (e.type.equals(ActivableObject.Activator.BUTTON)) {
            e.deactivate();

        }
        e.resetPlayer();
    }

    /**
     * Checks the collision of a character with the entities in the game.
     * 
     * @param character the moving {@link Player}
     */
    private void checkCollisions(final Player character) {
        Position2d ChPos = character.getPosition().sum(character.getVector());
        double ChHeight = character.getBoundingBox().getHeight();
        double ChWidth = character.getBoundingBox().getWidth();

        this.getEntities().stream().filter((e) -> e.getBoundingBox().isCollidingWith(ChPos, ChHeight, ChWidth))
                .forEach((e) -> {
                    String cl = e.getClass().getName();
                    switch (cl) {
                        case "it.unibo.dna.model.object.Platform" ->
                            this.eventQueue.addEvent(event.hitPlatformEvent(e, character));
                        case "it.unibo.dna.model.object.MovablePlatform" -> {
                            this.eventQueue.addEvent(event.hitPlatformEvent(e, character));
                            this.eventQueue.addEvent(event.hitMovablePlatformEvent((MovablePlatform) e, character));
                        }
                        case "it.unibo.dna.model.object.ActivableObject" -> {
                            if (((ActivableObject) e).type.equals(ActivableObject.Activator.BUTTON)) {
                                this.eventQueue.addEvent(event.hitButtonEvent((ActivableObject) e, character));
                            } else {
                                this.eventQueue.addEvent(event.hitLeverEvent((ActivableObject) e, character));
                            }
                        }
                        case "it.unibo.dna.model.object.Door" ->
                            this.eventQueue.addEvent(event.hitDoorEvent((Door) e, character));
                        case "it.unibo.dna.model.object.Diamond" -> {
                            this.eventQueue.addEvent(event.soundEvent("Diamond_sound"));
                            this.eventQueue.addEvent(event.hitDiamondEvent((Diamond) e, score));
                        }
                    }
                });

        this.getEntities().stream().filter((e) -> !e.getBoundingBox().isCollidingWith(ChPos, ChHeight, ChWidth))
                .filter((e) -> e.getClass().getName().equals("it.unibo.dna.model.object.ActivableObject"))
                .forEach((e) -> {
                    Optional<Player> objPlayer = ((ActivableObject) e).getPlayer();
                    if (objPlayer.isPresent() && objPlayer.get().equals(character)) {
                        freeActivableObject((ActivableObject) e);
                    }
                });
    }

    /**
     * Checks the collision of a character with the vertical borders.
     * 
     * @param pos    the x coordinate of the character's position
     * @param lenght the lenght of the character
     * @return true if the character is colliding with a vertical border
     */
    public boolean checkVerticalBorders(double pos, double lenght) {
        double sxBorder = this.boundingBox.getPosition().x;
        double dxBorder = this.boundingBox.getPosition().x + this.boundingBox.getWidth();

        return pos <= sxBorder || pos + lenght >= dxBorder;
    }

    /**
     * Checks the collision of a character with the horizontal borders.
     * 
     * @param pos    the y coordinate of the character's position
     * @param height the height of the character
     * @return true if the character is colliding with an horizontal border
     */
    public boolean checkHorizontalBorders(double pos, double height) {
        double northBorder = this.boundingBox.getPosition().y;
        double southBorder = this.boundingBox.getPosition().y + this.boundingBox.getHeight();

        return pos <= northBorder || pos + height >= southBorder;
    }

    /**
     * Checks the collision of a character with the borders.
     * 
     * @param character the moving {@link Player}
     * @return true if the character is colliding with the borders
     */
    public void checkBorders(final Player character) {
        Position2d ChPos = character.getPosition().sum(character.getVector());
        double ChHeight = character.getBoundingBox().getHeight();
        double ChLenght = character.getBoundingBox().getWidth();

        if (this.checkVerticalBorders(ChPos.x, ChLenght)) {
            event.hitBorderYEvent(character).manage(this);
        }
        if (this.checkHorizontalBorders(ChPos.y, ChHeight)) {
            event.hitBorderXEvent(character).manage(this);
        }
    }

    public EventQueue getEventQueue() {
        return this.eventQueue;
    }

}
