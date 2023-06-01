package it.unibo.dna.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.awt.Dimension;
import java.awt.Toolkit;

import it.unibo.dna.common.Position2d;
import it.unibo.dna.model.object.EntityFactoryImpl;
import it.unibo.dna.model.object.MovablePlatform;
import it.unibo.dna.model.object.PlayerImpl;
import it.unibo.dna.model.object.api.Entity.entityType;
import it.unibo.dna.model.object.api.Entity;


public class Level {
    
    File lvlFile;
    ArrayList <Entity> entitiesList = new ArrayList<>();
    String nameFile;
    String s;
    int lvl;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double tileSize = screenSize.getHeight()/100;
    public EntityFactoryImpl entityFactoryImpl = new EntityFactoryImpl();
    

    ArrayList <Entity> entitiesList() throws IOException{
        ArrayList <Entity> entities = new ArrayList<>();
        FileReader f = new FileReader(nameFile);
        BufferedReader b;
        
        b=new BufferedReader(f);
        s = b.readLine();
        
    

        while(s!= null){
            String[] splittedC = s.split("//s");
            switch(splittedC[0]){
                case "angel" : PlayerImpl angel = new PlayerImpl(null, new 
                Position2d(Double.parseDouble(splittedC[1])*(tileSize), Double.parseDouble(splittedC[2])*(tileSize)), 
                null, Double.parseDouble(splittedC[3])*(tileSize), Double.parseDouble(splittedC[4])*(tileSize), 
                PlayerImpl.PlayerType.ANGEL); entities.add(angel);

                case "devil" : PlayerImpl devil = new PlayerImpl(null, new 
                Position2d(Double.parseDouble(splittedC[1])*(tileSize), Double.parseDouble(splittedC[2])*(tileSize)), 
                null, Double.parseDouble(splittedC[3])*(tileSize), Double.parseDouble(splittedC[4])*(tileSize), 
                PlayerImpl.PlayerType.DEVIL); entities.add(devil);
                
                case "dDevil" : entities.add(entityFactoryImpl.createEntity(null, entityType.DEVIL_DOOR,
                 new Position2d(Double.parseDouble(splittedC[1])*(tileSize), Double.parseDouble(splittedC[2])*(tileSize)))); 

                case "dAngel" : entities.add(entityFactoryImpl.createEntity(null, entityType.ANGEL_DOOR,
                new Position2d(Double.parseDouble(splittedC[1])*(tileSize), Double.parseDouble(splittedC[2])*(tileSize)))); 

                case "diamond" : entities.add(entityFactoryImpl.createEntity(null, entityType.DIAMOND,
                 new Position2d(Double.parseDouble(splittedC[1])*(tileSize), Double.parseDouble(splittedC[2])*(tileSize)))); 

                case "button" : entities.add(entityFactoryImpl.createEntity(Optional.of((MovablePlatform) entities.stream()
                .filter(e -> e.getType().equals(entityType.MOVABLEPLATFORM)).reduce((first, second)->second).get()), 
                entityType.BUTTON, new Position2d(Double.parseDouble(splittedC[1])*(tileSize), Double.parseDouble(splittedC[2])*(tileSize)))); 

                case "lever" : entities.add(entityFactoryImpl.createEntity(Optional.of((MovablePlatform) entities.stream()
                .filter(e -> e.getType().equals(entityType.MOVABLEPLATFORM)).reduce((first, second)->second).get()), 
                entityType.LEVER, new Position2d(Double.parseDouble(splittedC[1])*(tileSize), Double.parseDouble(splittedC[2])*(tileSize))));
                
                case "platform" : entities.add(entityFactoryImpl.createEntity(null, entityType.PLATFORM, 
                new Position2d(Double.parseDouble(splittedC[1])*(tileSize), Double.parseDouble(splittedC[2])*(tileSize))));

                case "movablePlatform" : entities.add(entityFactoryImpl.createEntity(null, entityType.MOVABLEPLATFORM, 
                new Position2d(Double.parseDouble(splittedC[1])*(tileSize), Double.parseDouble(splittedC[2])*(tileSize))));
    
            }
            b.readLine();
        }

        b.close();
        return entities;
    }

    void getFile(int lvl) {
        switch(lvl){
            case 1: nameFile = "lvl1.txt";
            case 2: nameFile = "lvl2.txt";
            case 3: nameFile = "lvl3.txt";
        }

    }

    
    

}
