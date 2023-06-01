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
import it.unibo.dna.model.object.AbstractEntity;
import it.unibo.dna.model.object.ActivableObjectImpl;
import it.unibo.dna.model.object.Diamond;
import it.unibo.dna.model.object.Door;
import it.unibo.dna.model.object.MovablePlatform;
import it.unibo.dna.model.object.Platform;
import it.unibo.dna.model.object.Puddle;
import it.unibo.dna.model.object.Door.doorState;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.api.Player;

public class ManageImage {

    public static double LEVERHEIGHT = 30.0;
    public static double ACTIVABLEOBJECTWIDTH = 30.0;
    public static double BUTTONHEIGHT = 20.0;

    private int MAX_FRAME = 10;
    private int frame = 0;
    private int imageIndex = 0;
    private Map<Pair<Player.State, Player.State>, List<Image>> angelMap = new HashMap<>();
    private Map<Pair<Player.State, Player.State>, List<Image>> devilMap = new HashMap<>();
    private Map<Class<? extends AbstractEntity>, List<Image>> map = new HashMap<>();

    public ManageImage() {
        // caricamento di tutte le immagini
        this.playerImage(angelMap, Player.PlayerType.ANGEL, 40, 30);
        this.playerImage(devilMap, Player.PlayerType.DEVIL, 40, 30);
        loadImages();
    }

    Image getImage(Entity entity) {
        Image image = getDiamondImage();
        if (entity.getClass().equals(Door.class)) {
            image = getDoorImage(((Door) entity));
        } else if (entity.getClass().equals(ActivableObjectImpl.class)) {
            image = getActObjImage(((ActivableObjectImpl) entity));
        } else if (entity.getClass().equals(Puddle.class)) {
            image = getPuddleImage(((Puddle) entity));
        } else if (entity.getClass().equals(Platform.class)) {
            image = getPlatformImage();
        } else if (entity.getClass().equals(MovablePlatform.class)) {
            image = getMovablePlatformImage();
        } else if(entity.getClass().equals(MovablePlatform.class)){
            image = getDiamondImage();
        }
        return image;
    }

    private void loadImages() {
        String path = "src\\main\\resources\\";
        List<Image> doorImageList = new ArrayList<>();
        List<Image> activableObjectImageList = new ArrayList<>();
        List<Image> puddleImageList = new ArrayList<>();
        List<Image> platformImageList = new ArrayList<>();
        List<Image> movablePlatformImageList = new ArrayList<>();
        List<Image> diamondImage = new ArrayList<>();
        try {
            doorImageList.add(ImageIO.read(new File(path + "porta_angelo.PNG")));
            doorImageList.add(ImageIO.read(new File(path + "porta_angelo_aperta.PNG")));
            doorImageList.add(ImageIO.read(new File(path + "porta_diavolo.PNG")));
            doorImageList.add(ImageIO.read(new File(path + "porta_diavolo_aperta.PNG")));
            activableObjectImageList.add(ImageIO.read(new File(path + "Bottone_off.PNG")));
            activableObjectImageList.add(ImageIO.read(new File(path + "Bottone_on.PNG")));
            activableObjectImageList.add(ImageIO.read(new File(path + "Leva_off.PNG")));
            activableObjectImageList.add(ImageIO.read(new File(path + "Leva_on.PNG")));
            puddleImageList.add(ImageIO.read(new File(path + "Pozza_azzurra.jpg")));
            puddleImageList.add(ImageIO.read(new File(path + "Pozza_rossa.jpg")));
            puddleImageList.add(ImageIO.read(new File(path + "Pozza_viola.jpg")));
            platformImageList.add(ImageIO.read(new File(path + "Piattaforma_terra.jpg")));
            movablePlatformImageList.add(ImageIO.read(new File(path + "MovablePlatform.jpg")));
            diamondImage.add(ImageIO.read(new File(path + "diamond.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.map.put(Door.class, doorImageList);
        this.map.put(ActivableObjectImpl.class, activableObjectImageList);
        this.map.put(Puddle.class, puddleImageList);
        this.map.put(Platform.class, platformImageList);
        this.map.put(MovablePlatform.class, movablePlatformImageList);
        this.map.put(Diamond.class, diamondImage);
    }

    /**
     * @param playerMap
     * @param type
     */
    private void playerImage(final Map<Pair<Player.State, Player.State>, List<Image>> playerMap,
            final Player.PlayerType type, final int width, final int height) {
        String path = (type.equals(Player.PlayerType.ANGEL)) ? "angel" : "devil";
        List.of(Player.State.STATE_JUMPING, Player.State.STATE_STANDING).forEach(state -> {
            playerMap.put(new Pair<>(state, Player.State.STATE_LEFT), new ArrayList<>());
            playerMap.put(new Pair<>(state, Player.State.STATE_RIGHT), new ArrayList<>());
            playerMap.put(new Pair<>(state, Player.State.STATE_STILL), new ArrayList<>());
            try {
                playerMap.get(new Pair<>(state, Player.State.STATE_LEFT))
                        .add(ImageIO.read(new File("src\\main\\resources\\playerImage\\" + path + "_left1.PNG"))
                                .getScaledInstance(height, width, Image.SCALE_DEFAULT));
                playerMap.get(new Pair<>(state, Player.State.STATE_RIGHT))
                        .add(ImageIO.read(new File("src\\main\\resources\\playerImage\\" + path + "_right1.PNG"))
                                .getScaledInstance(height, width, Image.SCALE_DEFAULT));
                playerMap.get(new Pair<>(state, Player.State.STATE_STILL))
                        .add(ImageIO.read(new File("src\\main\\resources\\playerImage\\" + path + "_front.PNG"))
                                .getScaledInstance(height, width, Image.SCALE_DEFAULT));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        try {
            playerMap.get(new Pair<>(Player.State.STATE_STANDING, Player.State.STATE_LEFT))
                    .add(ImageIO.read(new File("src\\main\\resources\\playerImage\\" + path + "_left2.PNG"))
                            .getScaledInstance(height, width, Image.SCALE_DEFAULT));
            playerMap.get(new Pair<>(Player.State.STATE_STANDING, Player.State.STATE_RIGHT))
                    .add(ImageIO.read(new File("src\\main\\resources\\playerImage\\" + path + "_right2.PNG"))
                            .getScaledInstance(height, width, Image.SCALE_DEFAULT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    public void update() {
        this.frame++;
        if (frame >= this.MAX_FRAME) {
            this.imageIndex = (this.imageIndex == 0) ? 1 : 0;
            this.frame = 0;
        }
    }

    /**
     * @param p
     * @return
     */
    public Image playerChooseImage(final Player p) {
        Map<Pair<Player.State, Player.State>, List<Image>> playerMap = p.getPlayerType().equals(/*Player.Type.ANGEL*/Player.PlayerType.ANGEL)
                ? this.angelMap
                : this.devilMap;

        if (p.getState().equals(new Pair<>(Player.State.STATE_STANDING, Player.State.STATE_LEFT))
                || p.getState().equals(new Pair<>(Player.State.STATE_STANDING, Player.State.STATE_RIGHT))) {
            return playerMap.get(p.getState()).get(this.imageIndex);
        }
        return playerMap.get(p.getState()).get(0);
    }

    /**
     * 
     * @param actObj the {@link ActivableObjectImpl} we need an image for
     * @return the image of the {@link ActivableObkect}
     */
    public Image getActObjImage(ActivableObjectImpl activableObject) {
        if (activableObject.getType().equals(/*ActivableObjectImpl.Activator.BUTTON*/Entity.entityType.BUTTON)) {
            return (activableObject.isActivated()) ? this.map.get(ActivableObjectImpl.class).get(1)
                    : this.map.get(ActivableObjectImpl.class).get(0);
        }
        return (activableObject.isActivated()) ? this.map.get(ActivableObjectImpl.class).get(3)
                : this.map.get(ActivableObjectImpl.class).get(2);
    }

    /**
     * 
     * @param puddle the puddle we want the Image for
     * @return the Image
     */
    public Image getPuddleImage(Puddle puddle) {
        //puddleType type = puddle.getPuddleType();
        Entity.entityType type = puddle.getType();
        List<Image> puddleImages = this.map.get(Puddle.class);
        if (type.equals(/*puddleType.BLUE)*/Entity.entityType.BLUE_PUDDLE)) {
            return puddleImages.get(0);
        } else if (type.equals(/*puddleType.RED*/Entity.entityType.BLUE_PUDDLE)) {
            return puddleImages.get(1);
        }
        return puddleImages.get(2);
    }

    /**
     * 
     * @param door the door we want an Image for
     * @return the Image of the door
     */
    public Image getDoorImage(Door door) {
        Entity.entityType type = door.getType();
        doorState state = door.getDoorState();
        List<Image> doorImages = this.map.get(Door.class);
        if (type.equals(Entity.entityType.ANGEL_DOOR)) {
            return state.equals(Door.doorState.CLOSED_DOOR) ? doorImages.get(0) : doorImages.get(1);
        }
        return state.equals(Door.doorState.CLOSED_DOOR) ? doorImages.get(2) : doorImages.get(3);
    }

    /**
     * 
     * @return the Image of the platform
     */
    public Image getPlatformImage() {
        return this.map.get(Platform.class).get(0);
    }

    /**
     * 
     * @return the image of the MovablePlatform
     */
    public Image getMovablePlatformImage() {
        return this.map.get(MovablePlatform.class).get(0);
    }

    /**
     * 
     * @return the image of the {@link Diamond}
     */
    public Image getDiamondImage() {
        return this.map.get(Diamond.class).get(0);
    }

}
