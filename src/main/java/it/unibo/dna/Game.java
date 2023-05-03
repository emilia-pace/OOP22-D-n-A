package it.unibo.dna;

import java.util.ArrayList;
import java.util.List;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.EventFactory;
import it.unibo.dna.model.EventFactoryImpl;
import it.unibo.dna.model.RectBoundingBox;
import it.unibo.dna.model.Score;
import it.unibo.dna.model.object.Button;
import it.unibo.dna.model.object.Door;
import it.unibo.dna.model.object.Lever;
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

    public Game(int width, int height) {
        display = new Display(width, height);
        this.addEntity(display.door);
        this.addEntity(display.door2);
        System.out.println("Bounding box porta = " + display.door.getBoundingBox().getWidth() + "x"
                + display.door.getBoundingBox().getHeight());
        System.out.println("Bounding box angel = " + display.angel.getBoundingBox().getWidth() + "x"
                + display.angel.getBoundingBox().getHeight());
        System.out.println("Bounding box devil = " + display.devil.getBoundingBox().getWidth() + "x"
                + display.devil.getBoundingBox().getHeight());
    }

    public void update() {
        if (display.angel.getVector().y < Gravity) {
            display.angel.getVector().sumY(Player.StandardVelocity);
        }
        if (display.devil.getVector().y < Gravity) {
            display.devil.getVector().sumY(Player.StandardVelocity);
        }
        display.angel.update();
        display.devil.update();
        this.checkCollisions(display.angel);
        this.checkCollisions(display.devil);
    }

    public void render() {
        display.render(this);
    }

    /**
     * 
     * @param boundingB the {@link BoundingBox}
     */
    public Game(final RectBoundingBox boundingB) {
        this.boundingBox = boundingB;
        this.entities = new ArrayList<Entity>();
        this.score = new Score();
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
     * Checks the collision of a character with the entities in the game.
     * 
     * @param character the moving {@link Player}
     */
    public void checkCollisions(final Player character) {
        Position2d ChPos = character.getPosition();
        double ChHeight = character.getBoundingBox().getHeight();
        double ChWidth = character.getBoundingBox().getWidth();

        for (Entity e : this.getEntities()) {
            if (e.getBoundingBox().isCollidingWith(ChPos, ChHeight, ChWidth)) {
                switch (e.getClass().getName()) {
                    case "it.unibo.dna.model.object.Button" -> event.hitButtonEvent((Button) e);
                    case "it.unibo.dna.model.object.Door" -> event.hitDoorEvent((Door) e, character).manage(this);
                    case "it.unibo.dna.model.object.Lever" -> event.hitLeverEvent((Lever) e);
                    case "it.unibo.dna.model.object.Diamod" -> event.hitDiamondEvent((Diamond) e, score);
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
    public boolean checkBorders(final Player character) {
        Position2d ChPos = character.getPosition();
        double ChHeight = character.getBoundingBox().getHeight();
        double ChLenght = character.getBoundingBox().getWidth();

        double sxBorder = this.boundingBox.getPosition().x;
        double dxBorder = this.boundingBox.getPosition().x + this.boundingBox.getWidth();
        double northBorder = this.boundingBox.getPosition().y;
        double southBorder = this.boundingBox.getPosition().y + this.boundingBox.getHeight();

        return ChPos.x == sxBorder
                || ChPos.x + ChLenght == dxBorder
                || ChPos.y == northBorder
                || ChPos.y + ChHeight == southBorder;
    }

}
