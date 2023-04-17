package it.unibo.dna.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unibo.dna.model.object.Diamond;
import it.unibo.dna.model.object.GameObject;

public class Map {
    
    private List<GameObject> objs;
    private Character character;
    private Diamond diamond;
    private RectBoundingBox borderBox;
    

    public Map(final RectBoundingBox borderB){
        this.borderBox = borderB;
        objs = new ArrayList<GameObject>();
    }


    public List<GameObject> getObjs() {
        return this.objs;
    }

    public void setObjs(final List<GameObject> objs) {
        this.objs = objs;
    }

    public Character getCharacter() {
        return this.character;
    }

    public void setCharacter(final Character character) {
        this.character = character;
    }

    public Diamond getDiamond() {
        return this.diamond;
    }

    public void setDiamond(final Diamond d){
        this.diamond = d;
    }


    public RectBoundingBox getBorderBox() {
        return this.borderBox;
    }

    public void setBorderBox(final RectBoundingBox borderBox) {
        this.borderBox = borderBox;
    }


    public void addObj(final GameObject obj){
        objs.add(obj);
    }
    
    public void removeObj(final GameObject obj){
        objs.remove(obj);
    }

    /**
	 * @TODO to be implemented
	 */
    public Optional<GameObject> checkCollision(){
        return Optional.empty();
    }

}
