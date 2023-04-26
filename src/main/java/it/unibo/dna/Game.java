package it.unibo.dna;

import java.util.ArrayList;
import java.util.List;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.EventFactory;
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
    
    private Display display;
    private List<Entity> entities;
    private BoundingBox borderBox;
    private EventFactory event;
    private Score score;

    public Game(int width, int height) {
        display = new Display(width, height);
    }

    public void update() {

    }

    public void render() {
        display.render(this);
    }
    

    public Game(final RectBoundingBox borderB){
        this.borderBox = borderB;
        this.entities = new ArrayList<Entity>();
        this.score = new Score();
    }


    public BoundingBox getBorderBox() {
        return this.borderBox;
    }

    public void setBorderBox(final RectBoundingBox borderBox) {
        this.borderBox = borderBox;
    }

    public void addEntity(final Entity e){
        this.entities.add(e);
    }

    public void removeEntity(final Entity e){
        this.entities.remove(e);
    }

    public List<Entity> getEntities(){
        return this.entities;
    }

    public void checkCollisions(final Player character){
        Position2d ChPos = character.getPosition();
        double ChHeight = character.getBoundingBox().getHeight();
        double ChLenght = character.getBoundingBox().getLenght();

        for (Entity e : this.getEntities()) {
            if(e.getBoundingBox().isCollidingWith(ChPos, ChHeight,ChLenght)){
                switch(e.getClass().getName()){
                    case "it.unibo.dna.model.object.Button" -> event.hitButtonEvent((Button)e);
                    case "it.unibo.dna.model.object.Door" -> event.hitDoorEvent((Door)e,character);
                    case "it.unibo.dna.model.object.Lever" -> event.hitLeverEvent((Lever)e);
                    case "it.unibo.dna.model.object.Diamod" -> event.hitDiamondEvent((Diamond)e, score);
                }
                
            }
        }
        
    }
    
    
    public boolean checkBorders(final Player character){
        Position2d ChPos = character.getPosition();
        double ChHeight = character.getBoundingBox().getHeight();
        double ChLenght = character.getBoundingBox().getLenght();

        return ChPos.x == this.borderBox.getPosition().x //bordo sx
            || ChPos.x + ChLenght == this.borderBox.getPosition().x + this.borderBox.getLenght() //bordo dx
            || ChPos.y == this.borderBox.getPosition().y //bordo nord
            || ChPos.y + ChHeight == this.borderBox.getPosition().y + this.borderBox.getHeight(); //bordo sud
    }


}
