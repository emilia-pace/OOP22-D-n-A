package it.unibo.dna.model.object;

import javax.swing.BoundedRangeModel;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.RectBoundingBox;
import it.unibo.dna.model.object.api.BoundingBox;

public class Platform extends AbstractEntity{

    private Position2d pos;
    private BoundingBox box;

    public Platform(Position2d pos, double height, double width) {
        super(pos, height, width);
        this.box=new RectBoundingBox(pos, height, width);
    }

    public Position2d getPos(){
        return this.pos;
    }
    
    public void setPosition(final Position2d p){
        this.pos=p;
    }

    public BoundingBox getBoundingBox(){
        return this.box;
    }
    
}
