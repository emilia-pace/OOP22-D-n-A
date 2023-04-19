package it.unibo.dna.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unibo.dna.model.object.api.BoundingBox;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.api.Player;
import it.unibo.dna.common.Position2d;


public class Map {
    
    
    private List<Entity> entities;

    private BoundingBox borderBox;
    

    public Map(final RectBoundingBox borderB){
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

        if(this.getBorderBox().isCollidingWith(ChPos, ChHeight, ChLenght)){
            return true;
        }

        return false;
    }

}
