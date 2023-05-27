package it.unibo.dna.graphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import java.awt.*;
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
    private Image diamondImg;
    private Pair<Image,Image> buttonImages = new Pair<Image,Image>(null,null);
    private Pair<Image,Image> leverImages = new Pair<Image,Image>(null,null);
    private Pair<Image,Image> portaAngelo = new Pair<Image,Image>(null,null);
    private Pair<Image,Image> portaDiavolo = new Pair<Image,Image>(null,null);

    private List<Image> puddleImage = new ArrayList<>(); //0 azzurra, 1 rossa, 2 viola
    private Image platfformImage;
    private Image MovablePlatformImage; 

    public ManageImage() {
        // caricamento di tutte le immagini
        this.playerImage(angelMap, Player.Type.ANGEL);
        this.playerImage(devilMap, Player.Type.DEVIL);
        this.diamondImage();
        this.actObjImage();
        this.doorImage();
        this.PuddleImage();
        this.platformImages();
    }

    private void playerImage(Map<Pair<Player.State, Player.State>, List<Image>> playerMap, Player.Type type) {
        String path;
        if (type.equals(Player.Type.ANGEL)) {
            path = "angel";
        } else {
            path = "devil";
        }
        List.of(Player.State.STATE_JUMPING, Player.State.STATE_STANDING).forEach(state -> {
            playerMap.put(new Pair<>(state, Player.State.STATE_LEFT), new ArrayList<>());
            playerMap.put(new Pair<>(state, Player.State.STATE_RIGHT), new ArrayList<>());
            playerMap.put(new Pair<>(state, Player.State.STATE_STILL), new ArrayList<>());
            try {
                playerMap.get(new Pair<>(state, Player.State.STATE_LEFT))
                        .add(ImageIO.read(new File("src\\main\\resources\\playerImage\\" + path + "_left1.PNG")));
                playerMap.get(new Pair<>(state, Player.State.STATE_RIGHT))
                        .add(ImageIO.read(new File("src\\main\\resources\\playerImage\\" + path + "_right1.PNG")));
                playerMap.get(new Pair<>(state, Player.State.STATE_STILL))
                        .add(ImageIO.read(new File("src\\main\\resources\\playerImage\\" + path + "_front.PNG")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        try {
            playerMap.get(new Pair<>(Player.State.STATE_STANDING, Player.State.STATE_LEFT))
                    .add(ImageIO.read(new File("src\\main\\resources\\playerImage\\" + path + "_left2.PNG")));
            playerMap.get(new Pair<>(Player.State.STATE_STANDING, Player.State.STATE_RIGHT))
                    .add(ImageIO.read(new File("src\\main\\resources\\playerImage\\" + path + "_right2.PNG")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void diamondImage() {
        try {
            diamondImg = ImageIO.read(new File("src\\main\\resources\\diamond.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image getDiamondImage() {
        return this.diamondImg;
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

    /**
     * Sets the image for the possible {@link ActivableObject} (button, lever) and their respective states
     */
    private void actObjImage(){
        try{
            buttonImages.setX(ImageIO.read(new File("src\\main\\resources\\Bottone_off.PNG")));
            buttonImages.setY(ImageIO.read(new File("src\\main\\resources\\Bottone_on.PNG")));
            leverImages.setX(ImageIO.read(new File("src\\main\\resources\\Leva_off.PNG")));
            leverImages.setY(ImageIO.read(new File("src\\main\\resources\\Leva_on.PNG")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param actObj the {@link ActivableObject} we need an image for 
     * @return the image of the {@link ActivableObkect}
     */
    public Image getActObjImage(ActivableObject actObj) {
        Pair<Image,Image> p = this.buttonImages;
        if(actObj.getType().equals(ActivableObject.Activator.LEVER)){
            p = this.leverImages;
        }
        if(actObj.isActivated()){
            return p.getY();
        }
        return p.getX();
    }

    /**
     * Sets the image for the 2 types of {@link Door} and their 2 respective states.
     */
    private void doorImage() {
        try {
            portaAngelo.setX(ImageIO.read(new File("src\\main\\resources\\porta_angelo.PNG")));
            portaAngelo.setY(ImageIO.read(new File("src\\main\\resources\\porta_angelo_aperta.PNG")));
            portaDiavolo.setX(ImageIO.read(new File("src\\main\\resources\\porta_diavolo.PNG")));
            portaDiavolo.setY(ImageIO.read(new File("src\\main\\resources\\porta_diavolo_aperta.PNG")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param d the door we need an image for
     * @return the image of the {@link Door}
     */
    public Image getDoor(Door d) {
        Pair<Image,Image> door;
        if(d.getDoorType().equals(Door.doorType.ANGEL_DOOR)){
            door = portaAngelo;
        }else {
            door = portaDiavolo;
        }
        if(d.getDoorState().equals(Door.doorState.OPEN_DOOR)){
            return door.getY();
        }
        return door.getX();
    }

    /**
     * Sets the image for the 3 types of {@link Puddle}
     */
    private void PuddleImage() {
        try {
            puddleImage.add(ImageIO.read(new File("src\\main\\resources\\Pozza_azzurra.jpg")));
            puddleImage.add(ImageIO.read(new File("src\\main\\resources\\Pozza_rossa.jpg")));
            puddleImage.add(ImageIO.read(new File("src\\main\\resources\\Pozza_viola.jpg")));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param p the puddle we need the image for.
     * @return the image for the {@link Puddle}
     */
    public Image getPuddleImage(Puddle p){
        if(p.getPuddleType().equals(Puddle.puddleType.BLUE)){
            return this.puddleImage.get(0);
        }else if(p.getPuddleType().equals(Puddle.puddleType.RED)){
            return this.puddleImage.get(1);
        }
        return this.puddleImage.get(2);
    }

    /**
     * Sets the images for the {@link MovablePlatform} and the {@link Platform}
     */
    private void platformImages(){
        try {
            this.MovablePlatformImage = ImageIO.read(new File("src\\main\\resources\\MovablePlatform.jpg"));
            this.platfformImage = ImageIO.read(new File("src\\main\\resources\\Piattaforma_terra.jpg"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @return the image of the standard {@link Platform}
     */
    public Image getPlatformImage() {
        return this.platfformImage;
    }

    /**
     * 
     * @return the image of the {@link MovablePlatform} 
     */
    public Image getMovablePlatformImage() {
        return this.MovablePlatformImage;
    }

}
