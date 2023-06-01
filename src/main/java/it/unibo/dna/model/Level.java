package it.unibo.dna.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.awt.Dimension;
import java.awt.Toolkit;
 
import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.EntityFactoryImpl;
import it.unibo.dna.model.object.MovablePlatform;
import it.unibo.dna.model.object.PlayerImpl;
import it.unibo.dna.model.object.api.Entity.entityType;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.api.Player;


public class Level {
    
    File lvlFile;
    List<Entity> entitiesList = new ArrayList<>();
    List<Player> characters = new ArrayList<>();
    String nameFile;
    String s;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double tileSize = screenSize.getHeight()/100;
    public EntityFactoryImpl entityFactoryImpl = new EntityFactoryImpl();
    PlayerImpl angel;
    PlayerImpl devil;

    public Level(int lvl){
        getFile(lvl);
    }
    

    public List<Entity> entitiesList() throws IOException{
        FileReader f = new FileReader(nameFile);
        BufferedReader b;
        
        b=new BufferedReader(f);
        s= b.readLine();
        System.out.println(" 1 \n");
        
        while(s!= null){
            String[] splittedC = s.split("//s");
            switch(splittedC[0]){
                case "angel" : angel = new PlayerImpl(null, new 
                Position2d(Double.parseDouble(splittedC[1])*(tileSize), Double.parseDouble(splittedC[2])*(tileSize)), 
                null, Double.parseDouble(splittedC[3])*(tileSize), Double.parseDouble(splittedC[4])*(tileSize), 
                PlayerImpl.PlayerType.ANGEL); 

                case "devil" : devil = new PlayerImpl(null, new 
                Position2d(Double.parseDouble(splittedC[1])*(tileSize), Double.parseDouble(splittedC[2])*(tileSize)), 
                null, Double.parseDouble(splittedC[3])*(tileSize), Double.parseDouble(splittedC[4])*(tileSize), 
                PlayerImpl.PlayerType.DEVIL); characters.add(devil);
                
                case "dDevil" : entitiesList.add(entityFactoryImpl.createEntity(null, entityType.DEVIL_DOOR,
                 new Position2d(Double.parseDouble(splittedC[1])*(tileSize), Double.parseDouble(splittedC[2])*(tileSize)))); 

                case "dAngel" : entitiesList.add(entityFactoryImpl.createEntity(null, entityType.ANGEL_DOOR,
                new Position2d(Double.parseDouble(splittedC[1])*(tileSize), Double.parseDouble(splittedC[2])*(tileSize)))); 

                case "diamond" : entitiesList.add(entityFactoryImpl.createEntity(null, entityType.DIAMOND,
                 new Position2d(Double.parseDouble(splittedC[1])*(tileSize), Double.parseDouble(splittedC[2])*(tileSize)))); 

                case "button" : entitiesList.add(entityFactoryImpl.createEntity(Optional.of((MovablePlatform) entitiesList.stream()
                .filter(e -> e.getType().equals(entityType.MOVABLEPLATFORM)).reduce((first, second)->second).get()), 
                entityType.BUTTON, new Position2d(Double.parseDouble(splittedC[1])*(tileSize), Double.parseDouble(splittedC[2])*(tileSize)))); 

                case "lever" : entitiesList.add(entityFactoryImpl.createEntity(Optional.of((MovablePlatform) entitiesList.stream()
                .filter(e -> e.getType().equals(entityType.MOVABLEPLATFORM)).reduce((first, second)->second).get()), 
                entityType.LEVER, new Position2d(Double.parseDouble(splittedC[1])*(tileSize), Double.parseDouble(splittedC[2])*(tileSize))));
                
                case "platform" : entitiesList.add(entityFactoryImpl.createEntity(null, entityType.PLATFORM, 
                new Position2d(Double.parseDouble(splittedC[1])*(tileSize), Double.parseDouble(splittedC[2])*(tileSize))));

                case "movablePlatform" : entitiesList.add(entityFactoryImpl.createEntity(null, entityType.MOVABLEPLATFORM, 
                new Position2d(Double.parseDouble(splittedC[1])*(tileSize), Double.parseDouble(splittedC[2])*(tileSize))));

                case "rPuddle" : entitiesList.add(entityFactoryImpl.createEntity(null, entityType.RED_PUDDLE, 
                new Position2d(Double.parseDouble(splittedC[1])*(tileSize), Double.parseDouble(splittedC[2])*(tileSize))));

                case "bPuddle" : entitiesList.add(entityFactoryImpl.createEntity(null, entityType.BLUE_PUDDLE, 
                new Position2d(Double.parseDouble(splittedC[1])*(tileSize), Double.parseDouble(splittedC[2])*(tileSize))));

                case "pPuddle" : entitiesList.add(entityFactoryImpl.createEntity(null, entityType.PURPLE_PUDDLE, 
                new Position2d(Double.parseDouble(splittedC[1])*(tileSize), Double.parseDouble(splittedC[2])*(tileSize))));


            }
            s = b.readLine();
        }

        b.close();
        return entitiesList;
    }


    private void getFile(int lvl) {
        switch(lvl){
            case 1: nameFile = "lvl1.txt"; break;
            case 2: nameFile = "lvl2.txt"; break;
            case 3: nameFile = "lvl3.txt"; break;
        }

    }

    public List<Player> getCharacters(){
        return this.characters;
    }

    
    

}
