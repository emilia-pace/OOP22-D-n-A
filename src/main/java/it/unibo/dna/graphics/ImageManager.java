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
import it.unibo.dna.model.object.Door.DoorState;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.player.StateObserver;
import it.unibo.dna.model.object.player.api.Player;
import it.unibo.dna.model.object.EntityFactory;
/**
 * A class for loading, storing and returning images of the elements of the levels.
 */
public class ImageManager {

    private Map<Class<? extends AbstractEntity>, List<Image>> map = new HashMap<>();
    private List<StateObserver> obsPlayers = new ArrayList<>();

    public ImageManager(List<Player> playerList) {
        // caricamento di tutte le immagini
        for (int i = 0; i < playerList.size(); i++) {
            obsPlayers.add(new StateObserver(playerList.get(i).getState(), playerList.get(i).getPlayerType()));
        }
        loadImages();
    }

    public Image getPlayerImage(Player player) {
        return obsPlayers.stream().filter(e -> e.getPlayerType().equals(player.getPlayerType())).findFirst().get()
                .getImage();
    }

    /**
     * A method to get the image for any Entity
     * @param entity the Entity that needs its Image
     * @return the Image of the Entity
     */
    public Image getImage(final Entity entity) {
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

    /**
     * A method that resizes the image to fit the size of the entity.
     * @param image the image that needs to be resized
     * @param height the height that the image needs to have
     * @param width the width that the image needs to have
     * @return the resized image
     */
    private Image resizeImage(final Image image, final int height, final int width) {
        return image.getScaledInstance(width * Display.TILE_SIZE,
                height * Display.TILE_SIZE,
                Image.SCALE_DEFAULT);
    }

    /**
     * A method that loads all the images for the entities and stores them in a map of images.
     */
    private void loadImages() {
        String path = "src\\main\\resources\\";
        List<Image> doorImageList = new ArrayList<>();
        List<Image> activableObjectImageList = new ArrayList<>();
        List<Image> puddleImageList = new ArrayList<>();
        List<Image> platformImageList = new ArrayList<>();
        List<Image> movablePlatformImageList = new ArrayList<>();
        List<Image> diamondImage = new ArrayList<>();
        try {
            doorImageList.add(this.resizeImage(ImageIO.read(new File(path + "porta_angelo.PNG")),
                EntityFactory.DOOR_HEIGHT, EntityFactory.DOOR_WIDTH));
            doorImageList.add(this.resizeImage(ImageIO.read(new File(path + "porta_angelo_aperta.PNG")),
                EntityFactory.DOOR_HEIGHT, EntityFactory.DOOR_WIDTH));
            doorImageList.add(this.resizeImage(ImageIO.read(new File(path + "porta_diavolo.PNG")),
                EntityFactory.DOOR_HEIGHT, EntityFactory.DOOR_WIDTH));
            doorImageList.add(this.resizeImage(ImageIO.read(new File(path + "porta_diavolo_aperta.PNG")),
                EntityFactory.DOOR_HEIGHT, EntityFactory.DOOR_WIDTH));
            activableObjectImageList.add(this.resizeImage(ImageIO.read(new File(path + "Bottone_off.PNG")),
                EntityFactory.BUTTON_HEIGHT, EntityFactory.DEF_WIDTH));
            activableObjectImageList.add(this.resizeImage(ImageIO.read(new File(path + "Bottone_on.PNG")),
                EntityFactory.BUTTON_HEIGHT, EntityFactory.DEF_WIDTH));
            activableObjectImageList.add(this.resizeImage(ImageIO.read(new File(path + "Leva_off.PNG")),
                EntityFactory.LEVER_HEIGHT, EntityFactory.DEF_WIDTH));
            activableObjectImageList.add(this.resizeImage(ImageIO.read(new File(path + "Leva_on.PNG")),
                EntityFactory.LEVER_HEIGHT, EntityFactory.DEF_WIDTH));
            puddleImageList.add(this.resizeImage(ImageIO.read(new File(path + "Pozza_azzurra.jpg")),
                EntityFactory.DEF_HEIGHT, EntityFactory.PUDDLE_WIDTH));
            puddleImageList.add(this.resizeImage(ImageIO.read(new File(path + "Pozza_rossa.jpg")),
                EntityFactory.DEF_HEIGHT, EntityFactory.PUDDLE_WIDTH));
            puddleImageList.add(this.resizeImage(ImageIO.read(new File(path + "Pozza_viola.jpg")),
                EntityFactory.DEF_HEIGHT, EntityFactory.PUDDLE_WIDTH));
            platformImageList.add(this.resizeImage(ImageIO.read(new File(path + "Piattaforma_terra.jpg")),
                EntityFactory.DEF_HEIGHT, EntityFactory.PLATFORM_WIDTH));
            movablePlatformImageList.add(this.resizeImage(ImageIO.read(new File(path + "MovablePlatform.jpg")),
                EntityFactory.DEF_HEIGHT, EntityFactory.PLATFORM_WIDTH));
            diamondImage.add(this.resizeImage(ImageIO.read(new File(path + "diamond.png")),
                EntityFactory.DEF_HEIGHT, EntityFactory.DEF_HEIGHT));
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

    public List<StateObserver> getObservers() {
        return this.obsPlayers;
    }

    /**
     * A method that gets the image for a {@link ActivableObject}
     * @param activableObject the activableobject we need an image for
     * @return the image of the activableobject
     */
    public Image getActObjImage(final ActivableObjectImpl activableObject) {
        if (activableObject.getType().equals(Entity.EntityType.BUTTON)) {
            return (activableObject.isActivated()) ? this.map.get(ActivableObjectImpl.class).get(1)
                    : this.map.get(ActivableObjectImpl.class).get(0);
        }
        return (activableObject.isActivated()) ? this.map.get(ActivableObjectImpl.class).get(3)
                : this.map.get(ActivableObjectImpl.class).get(2);
    }

    /**
     * A method that gets the image for the puddles.
     * @param puddle the puddle we want the Image for
     * @return the Image of the puddle
     */
    public Image getPuddleImage(final Puddle puddle) {
        Entity.EntityType type = puddle.getType();
        List<Image> puddleImages = this.map.get(Puddle.class);
        if (type.equals(Entity.EntityType.BLUE_PUDDLE)) {
            return puddleImages.get(0);
        } else if (type.equals(Entity.EntityType.BLUE_PUDDLE)) {
            return puddleImages.get(1);
        }
        return puddleImages.get(2);
    }

    /**
     * A method that gets the image of the door.
     * @param door the door we want an Image for
     * @return the Image of the door
     */
    public Image getDoorImage(final Door door) {
        Entity.EntityType type = door.getType();
        DoorState state = door.getDoorState();
        List<Image> doorImages = this.map.get(Door.class);
        if (type.equals(Entity.EntityType.ANGEL_DOOR)) {
            return state.equals(Door.DoorState.CLOSED_DOOR) ? doorImages.get(0) : doorImages.get(1);
        }
        return state.equals(Door.DoorState.CLOSED_DOOR) ? doorImages.get(2) : doorImages.get(3);
    }

    /**
     * A method that gets the image for the {@link MovablePlatform}
     * @return the Image of the platform
     */
    public Image getPlatformImage() {
        return this.map.get(Platform.class).get(0);
    }

    /**
     * A method that gets the image for the {@link MovablePlatform}.
     * @return the image of the MovablePlatform
     */
    public Image getMovablePlatformImage() {
        return this.map.get(MovablePlatform.class).get(0);
    }

    /**
     * A method that gets the image for the {@link Diamond}.
     * @return the image of the diamond
     */
    public Image getDiamondImage() {
        return this.map.get(Diamond.class).get(0);
    }

}
