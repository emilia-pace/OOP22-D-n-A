package it.unibo.dna.graphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import it.unibo.dna.model.object.AbstractEntity;
import it.unibo.dna.model.object.ActivableObjectImpl;
import it.unibo.dna.model.object.Diamond;
import it.unibo.dna.model.object.Door;
import it.unibo.dna.model.object.MovablePlatform;
import it.unibo.dna.model.object.MyObserver;
import it.unibo.dna.model.object.Platform;
import it.unibo.dna.model.object.Puddle;
import it.unibo.dna.model.object.Door.doorState;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.api.Player;

public class ImageManager {

    private int defaultButtonHeight = 20;
    private int defaultWidth = 30;
    private int defaultHeight = 4;
    private double defaultPuddleWidth = 40;
    private int defaultDoorHeight = 7;
    private int defaultDoorWidth = 5;
    private int defaultPlatformWidth = 30;
    private int defaultMovablePlatformWidth = 1;
    private double diamondDimension = 4;

    private int diamondValue = 1;
    private int tileSize;

    private Map<Class<? extends AbstractEntity>, List<Image>> map = new HashMap<>();
    private MyObserver obsPlayer1;
    private MyObserver obsPlayer2;

    public ImageManager(List<Player> playerList, int tileSize) {
        // caricamento di tutte le immagini
        this.tileSize = tileSize;
        obsPlayer1 = new MyObserver(playerList.get(0).getState(), playerList.get(0).getPlayerType(),
                (int) playerList.get(0).getBoundingBox().getHeight(),
                (int) playerList.get(0).getBoundingBox().getWidth(), this.tileSize);
        obsPlayer2 = new MyObserver(playerList.get(1).getState(), playerList.get(1).getPlayerType(),
                (int) playerList.get(1).getBoundingBox().getHeight(),
                (int) playerList.get(1).getBoundingBox().getWidth(), this.tileSize);
        loadImages();
    }

    public Image getPlayerImage(Player player) {
        return (obsPlayer1.getType().equals(player.getPlayerType())) ? obsPlayer1.getPlayerImage()
                : obsPlayer2.getPlayerImage();
    }

    public Image getImage(Entity entity) {
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
        } else if (entity.getClass().equals(MovablePlatform.class)) {
            image = getDiamondImage();
        }
        return image;
    }

    private Image resizeImage(Image image) {
        return image.getScaledInstance(image.getHeight(null) * tileSize,
                image.getWidth(null) * tileSize,
                Image.SCALE_DEFAULT);
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
            doorImageList.add(
                    (ImageIO.read(new File(path + "porta_angelo.PNG"))).getScaledInstance(defaultDoorHeight * tileSize,
                            defaultDoorWidth * tileSize,
                            Image.SCALE_DEFAULT));
            doorImageList.add((ImageIO.read(new File(path + "porta_angelo_aperta.PNG"))));
            doorImageList.add((ImageIO.read(new File(path + "porta_diavolo.PNG"))));
            doorImageList.add((ImageIO.read(new File(path + "porta_diavolo_aperta.PNG"))));
            activableObjectImageList.add(this.resizeImage(ImageIO.read(new File(path + "Bottone_off.PNG"))));
            activableObjectImageList.add(this.resizeImage(ImageIO.read(new File(path + "Bottone_on.PNG"))));
            activableObjectImageList.add(this.resizeImage(ImageIO.read(new File(path + "Leva_off.PNG"))));
            activableObjectImageList.add(this.resizeImage(ImageIO.read(new File(path + "Leva_on.PNG"))));
            puddleImageList.add(this.resizeImage(ImageIO.read(new File(path + "Pozza_azzurra.jpg"))));
            puddleImageList.add(this.resizeImage(ImageIO.read(new File(path + "Pozza_rossa.jpg"))));
            puddleImageList.add(this.resizeImage(ImageIO.read(new File(path + "Pozza_viola.jpg"))));
            platformImageList.add(this.resizeImage(ImageIO.read(new File(path + "Piattaforma_terra.jpg")))
                    .getScaledInstance(defaultPlatformWidth * tileSize,
                            defaultHeight * tileSize,
                            Image.SCALE_DEFAULT));
            movablePlatformImageList.add(this.resizeImage(ImageIO.read(new File(path + "MovablePlatform.jpg")))
                    .getScaledInstance(defaultPlatformWidth * tileSize,
                            defaultHeight * tileSize,
                            Image.SCALE_DEFAULT));
            ;
            diamondImage.add(this.resizeImage(ImageIO.read(new File(path + "diamond.png"))));
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

    public List<MyObserver> getObservers() {
        return List.of(obsPlayer1, obsPlayer2);
    }

    /**
     * 
     * @param actObj the {@link ActivableObjectImpl} we need an image for
     * @return the image of the {@link ActivableObkect}
     */
    public Image getActObjImage(ActivableObjectImpl activableObject) {
        if (activableObject.getType().equals(/* ActivableObjectImpl.Activator.BUTTON */Entity.entityType.BUTTON)) {
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
        // puddleType type = puddle.getPuddleType();
        Entity.entityType type = puddle.getType();
        List<Image> puddleImages = this.map.get(Puddle.class);
        if (type.equals(/* puddleType.BLUE) */Entity.entityType.BLUE_PUDDLE)) {
            return puddleImages.get(0);
        } else if (type.equals(/* puddleType.RED */Entity.entityType.BLUE_PUDDLE)) {
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
