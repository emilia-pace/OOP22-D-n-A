package it.unibo.dna;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.RectBoundingBox;
import it.unibo.dna.model.object.api.BoundingBox;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.api.Player;

public class Game {
    
    private Display display;
    private List<Entity> entities;
    private BoundingBox borderBox;

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
        entities = new ArrayList<Entity>();
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

    public Optional<Entity> checkCollisions(final Player character){
        Position2d ChPos = character.getPosition();
        double ChHeight = character.getBoundingBox().getHeight();
        double ChLenght = character.getBoundingBox().getLenght();

        for (Entity e : this.getEntities()) {
            if(e.getBoundingBox().isCollidingWith(ChPos, ChHeight,ChLenght)){
                return Optional.of(e);
            }
        }
        return Optional.empty();
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
