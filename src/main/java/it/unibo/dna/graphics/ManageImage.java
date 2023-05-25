package it.unibo.dna.graphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import it.unibo.dna.common.Pair;
import it.unibo.dna.model.object.ActivableObject;
import it.unibo.dna.model.object.Door;
import it.unibo.dna.model.object.Puddle;
import it.unibo.dna.model.object.api.Player;

public class ManageImage {

    private int MAX_FRAME = 10;
    private int frame = 0;
    private int imageIndex = 0;
    private Map<Pair<Player.State, Player.State>, List<Image>> angelMap = new HashMap<>();
    private Map<Pair<Player.State, Player.State>, List<Image>> devilMap = new HashMap<>();

    public ManageImage() {
        // caricamento di tutte le immagini
        this.playerImage(angelMap, Player.Type.ANGEL);
        this.playerImage(devilMap, Player.Type.DEVIL);
    }

    private void playerImage(Map<Pair<Player.State, Player.State>, List<Image>> playerMap, Player.Type type) {
        String path;
        if (type.equals(Player.Type.ANGEL)) {
            path = "angelo";
        } else {
            path = "diavolo";
        }
        List.of(Player.State.STATE_JUMPING, Player.State.STATE_STANDING).forEach(state -> {
            playerMap.put(new Pair<>(state, Player.State.STATE_LEFT), new ArrayList<>());
            playerMap.put(new Pair<>(state, Player.State.STATE_RIGHT), new ArrayList<>());
            playerMap.put(new Pair<>(state, Player.State.STATE_STILL), new ArrayList<>());
            try {
                playerMap.get(new Pair<>(state, Player.State.STATE_LEFT))
                        .add(ImageIO.read(new File("src\\main\\resurces\\" + path + "_sinistra1.PNG")));
                playerMap.get(new Pair<>(state, Player.State.STATE_RIGHT))
                        .add(ImageIO.read(new File("src\\main\\resurces\\" + path + "_destra1.PNG")));
                playerMap.get(new Pair<>(state, Player.State.STATE_STILL))
                        .add(ImageIO.read(new File("src\\main\\resurces\\" + path + "_fronte.PNG")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        try {
            playerMap.get(new Pair<>(Player.State.STATE_STANDING, Player.State.STATE_LEFT))
                    .add(ImageIO.read(new File("src\\main\\resurces\\" + path + "_sinistra2.PNG")));
            playerMap.get(new Pair<>(Player.State.STATE_STANDING, Player.State.STATE_RIGHT))
                    .add(ImageIO.read(new File("src\\main\\resurces\\" + path + "_destra2.PNG")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        this.frame++;
        if (frame >= this.MAX_FRAME) {
            if (this.imageIndex == 0) {
                this.imageIndex = 1;
            } else {
                this.imageIndex = 0;
            }
            this.frame = 0;
        }
    }


    public Image playerChooseImage(Player p) {
        Map<Pair<Player.State, Player.State>, List<Image>> playerMap = p.getType().equals(Player.Type.ANGEL)
                ? this.angelMap
                : this.devilMap;

        if (p.getState().equals(new Pair<>(Player.State.STATE_STANDING, Player.State.STATE_LEFT))
                || p.getState().equals(new Pair<>(Player.State.STATE_STANDING, Player.State.STATE_RIGHT))) {
            return playerMap.get(p.getState()).get(this.imageIndex);
        }
        return playerMap.get(p.getState()).get(0);
    }

    public Image ActivableObjectChooseImage(ActivableObject actObj) {
        String actObjName;
        String dirName;
        Image image = null;
        if(actObj.getType().equals(ActivableObject.Activator.LEVER)){
            actObjName = "Leva_";
        } else {
            actObjName = "Bottone_";
        }
        if(actObj.isActivated()){
            dirName = "on.PNG";
        }else {
            dirName = "off.PNG";
        }
        try{
            image = ImageIO.read(new File("src\\main\\resurces\\" + actObjName + dirName));
        } catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public Image DoorChooseImage(Door door){
        String doorName = "porta_";
        String doorType;
        String doorState;
        Image image = null;
        if(door.getDoorState().equals(Door.doorState.OPEN_DOOR)){
            doorState = "aperta.PNG";
        }else {
            doorState = "chiusa.PNG";
        }
        if(door.getDoorType().equals(Door.doorType.ANGEL_DOOR)){
            doorType = "angelo_";
        } else {
            doorType = "diavolo_";
        }
        try{
            image = ImageIO.read(new File("src\\main\\resurces\\" + doorName + doorType + doorState)); 
        } catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public Image PuddleChooseImage(Puddle puddle) {
        String puddleName = "Pozza_";
        String puddleColor;
        Image image = null;
        if(puddle.getPuddleType().equals(Puddle.puddleType.RED)) {
            puddleColor = "rossa.jpg";
        }else if(puddle.getPuddleType().equals(Puddle.puddleType.BLUE)) {
            puddleColor = "azzurra.jpg";
        }else {
            puddleColor = "viola.jpg";
        }
        try {
            image = ImageIO.read(new File("src\\main\\resurces\\" + puddleName + puddleColor));
        } catch( IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public Image MovablePlatformImage(){
        Image image = null;
        try {
            image = ImageIO.read(new File("src\\main\\resurces\\MovablePlatform.PNG"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public Image PlatformImage() {
        Image image = null;
        try {
            image = ImageIO.read(new File("src\\main\\resurces\\Piattaforma_terra.jpg"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}
