package it.unibo.dna;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.graphics.Display;
import it.unibo.dna.model.EventFactory;
import it.unibo.dna.model.EventFactoryImpl;
import it.unibo.dna.model.RectBoundingBox;
import it.unibo.dna.model.Score;
import it.unibo.dna.model.object.Door;
import it.unibo.dna.model.object.MovablePlatform;
import it.unibo.dna.model.object.ActivableObject;
import it.unibo.dna.model.object.Diamond;
import it.unibo.dna.model.object.api.BoundingBox;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.api.MovableEntity;
import it.unibo.dna.model.object.api.Player;

public class Game {

    public static final double Gravity = 4;

    private Display display;
    private List<Entity> entities = new ArrayList<>();
    private BoundingBox boundingBox;
    private EventFactory event = new EventFactoryImpl();
    private Score score;

    public Game(int width, int height, int level) {
        this.boundingBox = new RectBoundingBox(new Position2d(0, 0), height, width);
        this.score = new Score();
        this.display = new Display(width, height);
        this.entities.add(display.button);
        this.entities.add(display.lever);
        this.entities.add(display.p1);
        this.entities.add(display.p2);
        this.entities.add(display.mp1);
        this.entities.add(display.mp2);
    }

    public void update() {
        this.gravity(display.angel);
        this.gravity(display.devil);

        for (Entity ent : entities) {
            if (ent instanceof MovableEntity) {
                ((MovablePlatform) ent).update();
            }
        }
        this.checkCollisions(display.angel);
        this.checkCollisions(display.devil);

        this.checkBorders(display.angel);
        this.checkBorders(display.devil);

        display.angel.update();
        display.devil.update();

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

        for (Entity e : this.getEntities()) {
            String cl = e.getClass().getName();
            if (e.getBoundingBox().isCollidingWith(ChPos, ChHeight, ChWidth)) {
                switch (cl) {
                    case "it.unibo.dna.model.object.Platform" -> event.hitPlatformEvent(e, character).manage(this);
                    case "it.unibo.dna.model.object.MovablePlatform" ->
                        event.hitMovablePlatformEvent((MovablePlatform) e, character).manage(this);
                    case "it.unibo.dna.model.object.ActivableObject" -> {
                        if (((ActivableObject) e).type.equals(ActivableObject.Activator.BUTTON)) {
                            event.hitButtonEvent((ActivableObject) e, character).manage(this);
                        } else {
                            event.hitLeverEvent((ActivableObject) e, character).manage(this);
                        }
                    }
                    case "it.unibo.dna.model.object.Door" -> event.hitDoorEvent((Door) e, character).manage(this);
                    case "it.unibo.dna.model.object.Diamond" -> event.hitDiamondEvent((Diamond) e, score).manage(this);
                }
            } else if (cl.equals("it.unibo.dna.model.object.ActivableObject")) {
                Optional<Player> objPlayer = ((ActivableObject) e).getPlayer();
                if (objPlayer.isPresent() && objPlayer.get().equals(character)) {
                    freeActivableObject((ActivableObject) e);
                }
            }
        }

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

        double sxBorder = this.boundingBox.getPosition().x;
        double dxBorder = this.boundingBox.getPosition().x + this.boundingBox.getWidth();
        double northBorder = this.boundingBox.getPosition().y;
        double southBorder = this.boundingBox.getPosition().y + this.boundingBox.getHeight();

        if (ChPos.x <= sxBorder || ChPos.x + ChLenght >= dxBorder) {
            event.hitBorderYEvent(character).manage(this);
        }
        if (ChPos.y <= northBorder || ChPos.y + ChHeight >= southBorder) {
            event.hitBorderXEvent(character).manage(this);
        }
    }

}
