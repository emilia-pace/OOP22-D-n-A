package it.unibo.dna.view.image;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import java.awt.Image;
import java.io.IOException;

import it.unibo.dna.model.object.entity.api.Entity;
import it.unibo.dna.model.object.entity.api.EntityFactory;
import it.unibo.dna.model.object.entity.impl.AbstractEntity;
import it.unibo.dna.model.object.movableentity.impl.MovablePlatform;
import it.unibo.dna.model.object.player.api.Player;
import it.unibo.dna.model.object.stillentity.impl.ActivableObjectImpl;
import it.unibo.dna.model.object.stillentity.impl.Diamond;
import it.unibo.dna.model.object.stillentity.impl.Door;
import it.unibo.dna.model.object.stillentity.impl.Platform;
import it.unibo.dna.model.object.stillentity.impl.Puddle;
import it.unibo.dna.model.object.stillentity.impl.Door.DoorState;
import it.unibo.dna.view.Display;

/**
 * A class for loading, storing and returning images of the elements of the
 * levels.
 */
public class ImageManager {

    private final Map<Class<? extends AbstractEntity>, List<Image>> map = new HashMap<>();
    private final List<StateObserver> obsPlayers = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageManager.class);

    /**
     * Constructs an ImageManager object with the given player list.
     *
     * @param playerList the list of players in the game
     */
    public ImageManager(final List<Player> playerList) {
        playerList.forEach(p -> obsPlayers.add(new StateObserver(p.getState(), p.getPlayerType())));
        loadImages();
    }

    /**
     * Returns the image that matches the specified player.
     *
     * @param player the player to retrieve the image for
     * @return the image that matches the player
     */
    public Image getPlayerImage(final Player player) {
        return obsPlayers.stream()
                .filter(e -> e.getPlayerType().equals(player.getPlayerType()))
                .findFirst()
                .get()
                .getImage();
    }

    /**
     * A method to get the image for any Entity.
     * 
     * @param entity the Entity that needs its Image
     * @return the Image of the Entity
     */
    public Image getImage(final Entity entity) {
        Image image = getDiamondImage();
        if (entity instanceof Door) {
            image = getDoorImage((Door) entity);
        } else if (entity instanceof ActivableObjectImpl) {
            image = getActObjImage((ActivableObjectImpl) entity);
        } else if (entity instanceof Puddle) {
            image = getPuddleImage((Puddle) entity);
        } else if (entity instanceof Platform) {
            image = getPlatformImage();
        } else if (entity instanceof MovablePlatform) {
            image = getMovablePlatformImage();
        }
        return image;
    }

    /**
     * A method that resizes the image to fit the size of the entity.
     * 
     * @param image  the image that needs to be resized
     * @param height the height that the image needs to have
     * @param width  the width that the image needs to have
     * @return the resized image
     */
    private Image resizeImage(final Image image, final int height, final int width) {
        return image.getScaledInstance(width * Display.TILE_SIZE,
                height * Display.TILE_SIZE,
                Image.SCALE_DEFAULT);
    }

    /**
     * A method that loads all the images for the entities and stores them in a map
     * of images.
     */
    private void loadImages() {
        final List<Image> doorImageList = new ArrayList<>();
        final List<Image> activableObjectImageList = new ArrayList<>();
        final List<Image> puddleImageList = new ArrayList<>();
        final List<Image> platformImageList = new ArrayList<>();
        final List<Image> movablePlatformImageList = new ArrayList<>();
        final List<Image> diamondImage = new ArrayList<>();
        try {
            doorImageList.add(this.resizeImage(ImageIO.read(ClassLoader.getSystemResource("porta_angelo.PNG")),
                    EntityFactory.DOOR_HEIGHT, EntityFactory.DOOR_WIDTH));
            doorImageList.add(this.resizeImage(ImageIO.read(ClassLoader.getSystemResource("porta_angelo_aperta.PNG")),
                    EntityFactory.DOOR_HEIGHT, EntityFactory.DOOR_WIDTH));
            doorImageList.add(this.resizeImage(ImageIO.read(ClassLoader.getSystemResource("porta_diavolo.PNG")),
                    EntityFactory.DOOR_HEIGHT, EntityFactory.DOOR_WIDTH));
            doorImageList.add(this.resizeImage(ImageIO.read(ClassLoader.getSystemResource("porta_diavolo_aperta.PNG")),
                    EntityFactory.DOOR_HEIGHT, EntityFactory.DOOR_WIDTH));
            activableObjectImageList
                    .add(this.resizeImage(ImageIO.read(ClassLoader.getSystemResource("Bottone_off.PNG")),
                            EntityFactory.BUTTON_HEIGHT, EntityFactory.DEF_WIDTH));
            activableObjectImageList.add(this.resizeImage(ImageIO.read(ClassLoader.getSystemResource("Bottone_on.PNG")),
                    EntityFactory.BUTTON_HEIGHT, EntityFactory.DEF_WIDTH));
            activableObjectImageList.add(this.resizeImage(ImageIO.read(ClassLoader.getSystemResource("Leva_off.PNG")),
                    EntityFactory.LEVER_HEIGHT, EntityFactory.DEF_WIDTH));
            activableObjectImageList.add(this.resizeImage(ImageIO.read(ClassLoader.getSystemResource("Leva_on.PNG")),
                    EntityFactory.LEVER_HEIGHT, EntityFactory.DEF_WIDTH));
            puddleImageList.add(this.resizeImage(ImageIO.read(ClassLoader.getSystemResource("Pozza_azzurra.jpg")),
                    EntityFactory.PUDDLE_HEIGHT, EntityFactory.PUDDLE_WIDTH));
            puddleImageList.add(this.resizeImage(ImageIO.read(ClassLoader.getSystemResource("Pozza_rossa.jpg")),
                    EntityFactory.PUDDLE_HEIGHT, EntityFactory.PUDDLE_WIDTH));
            puddleImageList.add(this.resizeImage(ImageIO.read(ClassLoader.getSystemResource("Pozza_viola.jpg")),
                    EntityFactory.PUDDLE_HEIGHT, EntityFactory.PUDDLE_WIDTH));
            platformImageList.add(this.resizeImage(ImageIO.read(ClassLoader.getSystemResource("Piattaforma_terra.jpg")),
                    EntityFactory.DEF_HEIGHT, EntityFactory.PLATFORM_WIDTH));
            movablePlatformImageList
                    .add(this.resizeImage(ImageIO.read(ClassLoader.getSystemResource("MovablePlatform.jpg")),
                            EntityFactory.DEF_HEIGHT, EntityFactory.PLATFORM_WIDTH));
            diamondImage.add(this.resizeImage(ImageIO.read(ClassLoader.getSystemResource("diamond.png")),
                    EntityFactory.DIAMOND_HEIGHT, EntityFactory.DIAMOND_WIDTH));
        } catch (IOException e) {
            LOGGER.error("IOEexception occurred", e);
        }
        this.map.put(Door.class, doorImageList);
        this.map.put(ActivableObjectImpl.class, activableObjectImageList);
        this.map.put(Puddle.class, puddleImageList);
        this.map.put(Platform.class, platformImageList);
        this.map.put(MovablePlatform.class, movablePlatformImageList);
        this.map.put(Diamond.class, diamondImage);
    }

    /**
     * A method that gets the image for a {@link ActivableObject}.
     * 
     * @param activableObject the activableobject we need an image for
     * @return the image of the activableobject
     */
    public Image getActObjImage(final ActivableObjectImpl activableObject) {
        if (activableObject.getType().equals(Entity.EntityType.BUTTON)) {
            return activableObject.isActivated() ? this.map.get(ActivableObjectImpl.class).get(1)
                    : this.map.get(ActivableObjectImpl.class).get(0);
        }
        return activableObject.isActivated() ? this.map.get(ActivableObjectImpl.class).get(3)
                : this.map.get(ActivableObjectImpl.class).get(2);
    }

    /**
     * A method that gets the image for the puddles.
     * 
     * @param puddle the puddle we want the Image for
     * @return the Image of the puddle
     */
    public Image getPuddleImage(final Puddle puddle) {
        final Entity.EntityType type = puddle.getType();
        final List<Image> puddleImages = this.map.get(Puddle.class);
        if (type.equals(Entity.EntityType.BLUE_PUDDLE)) {
            return puddleImages.get(0);
        } else if (type.equals(Entity.EntityType.RED_PUDDLE)) {
            return puddleImages.get(1);
        }
        return puddleImages.get(2);
    }

    /**
     * A method that gets the image of the door.
     * 
     * @param door the door we want an Image for
     * @return the Image of the door
     */
    public Image getDoorImage(final Door door) {
        final Entity.EntityType type = door.getType();
        final DoorState state = door.getDoorState();
        final List<Image> doorImages = this.map.get(Door.class);
        if (type.equals(Entity.EntityType.ANGEL_DOOR)) {
            return state.equals(Door.DoorState.CLOSED_DOOR) ? doorImages.get(0) : doorImages.get(1);
        }
        return state.equals(Door.DoorState.CLOSED_DOOR) ? doorImages.get(2) : doorImages.get(3);
    }

    /**
     * A method that gets the image for the {@link MovablePlatform}.
     * 
     * @return the Image of the platform
     */
    public Image getPlatformImage() {
        return this.map.get(Platform.class).get(0);
    }

    /**
     * A method that gets the image for the {@link MovablePlatform}.
     * 
     * @return the image of the MovablePlatform
     */
    public Image getMovablePlatformImage() {
        return this.map.get(MovablePlatform.class).get(0);
    }

    /**
     * A method that gets the image for the {@link Diamond}.
     * 
     * @return the image of the diamond
     */
    public Image getDiamondImage() {
        return this.map.get(Diamond.class).get(0);
    }

}
