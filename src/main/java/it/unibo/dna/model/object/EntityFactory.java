package it.unibo.dna.model.object;


import java.util.Optional;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.api.Entity;

public interface EntityFactory {
    
    public Entity createEntity(Optional<MovablePlatform> movablePlatform,Entity.entityType type, Position2d ... position); 

}
