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
import it.unibo.dna.model.object.Platform;
import it.unibo.dna.model.object.Puddle;
import it.unibo.dna.model.object.Door.doorState;
import it.unibo.dna.model.object.api.Entity;

public class ManageImage {

    public static double LEVERHEIGHT = 30.0;
    public static double ACTIVABLEOBJECTWIDTH = 30.0;
    public static double BUTTONHEIGHT = 20.0;
    public static int tileSize = 10;

    private Map<Class<? extends AbstractEntity>, List<Image>> map = new HashMap<>();

    public ManageImage() {
        // caricamento di tutte le immagini
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

    private Image resizeImage(Image image) {
        return image.getScaledInstance(image.getHeight(null)*tileSize, image.getWidth(null)*tileSize,Image.SCALE_DEFAULT);
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
            doorImageList.add(this.resizeImage(ImageIO.read(new File(path + "porta_angelo.PNG"))));
            doorImageList.add(this.resizeImage(ImageIO.read(new File(path + "porta_angelo_aperta.PNG"))));
            doorImageList.add(this.resizeImage(ImageIO.read(new File(path + "porta_diavolo.PNG"))));
            doorImageList.add(this.resizeImage(ImageIO.read(new File(path + "porta_diavolo_aperta.PNG"))));
            activableObjectImageList.add(this.resizeImage(ImageIO.read(new File(path + "Bottone_off.PNG"))));
            activableObjectImageList.add(this.resizeImage(ImageIO.read(new File(path + "Bottone_on.PNG"))));
            activableObjectImageList.add(this.resizeImage(ImageIO.read(new File(path + "Leva_off.PNG"))));
            activableObjectImageList.add(this.resizeImage(ImageIO.read(new File(path + "Leva_on.PNG"))));
            puddleImageList.add(this.resizeImage(ImageIO.read(new File(path + "Pozza_azzurra.jpg"))));
            puddleImageList.add(this.resizeImage(ImageIO.read(new File(path + "Pozza_rossa.jpg"))));
            puddleImageList.add(this.resizeImage(ImageIO.read(new File(path + "Pozza_viola.jpg"))));
            platformImageList.add(this.resizeImage(ImageIO.read(new File(path + "Piattaforma_terra.jpg"))));
            movablePlatformImageList.add(this.resizeImage(ImageIO.read(new File(path + "MovablePlatform.jpg"))));
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
