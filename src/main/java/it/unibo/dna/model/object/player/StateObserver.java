package it.unibo.dna.model.object.player;

import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.dna.common.Pair;
import it.unibo.dna.graphics.Display;
import it.unibo.dna.model.object.api.EntityFactory;
import it.unibo.dna.model.object.player.State.StateEnum;
import it.unibo.dna.model.object.player.api.Player;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A observer class of a player's state.
 */
public class StateObserver implements PropertyChangeListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(StateObserver.class);

    private final Map<Pair<State.StateEnum, State.StateEnum>, List<Image>> playerMap = new HashMap<>();
    private final Player.PlayerType type;
    private Image playerImage;

    /**
     * Constructs a new StateObserver object.
     *
     * @param state The player's state to observe
     * @param type  The player's type to which the state belongs
     */
    public StateObserver(final State state, final Player.PlayerType type) {
        this.inizialize(state);
        this.type = type;
        this.loadPlayerImage(playerMap, EntityFactory.PLAYER_HEIGHT * Display.TILE_SIZE,
                EntityFactory.PLAYER_WIDTH * Display.TILE_SIZE);
        playerImage = this.playerMap.get(state.getPairState()).get(0);
    }

    /**
     * Initializes the object with the provided state and sets up change listeners.
     *
     * @param state The state object to be initialized.
     */
    private void inizialize(final State state) {
        state.addChangeListeners(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        final State state = (State) event.getSource();
        playerImage = state.getX().equals(StateEnum.STATE_STANDING)
                && (state.getY().equals(StateEnum.STATE_LEFT) || state.getY().equals(StateEnum.STATE_RIGHT))
                        ? this.playerMap.get(state.getPairState()).get(state.getImageIndex())
                        : this.playerMap.get(state.getPairState()).get(0);
    }

    /**
     * Loads the player images based on the player's type and states.
     *
     * @param playerMap The map with states as keys and images as values
     * @param width     The width of the players
     * @param height    The height of the player
     */
    private void loadPlayerImage(final Map<Pair<State.StateEnum, State.StateEnum>, List<Image>> playerMap,
            final int width, final int height) {
        final String path = this.type.equals(Player.PlayerType.ANGEL) ? "angel" : "devil";
        final String relativePath = "src\\main\\resources\\playerImage\\";
        List.of(State.StateEnum.STATE_JUMPING, State.StateEnum.STATE_STANDING).forEach(state -> {
            playerMap.put(new Pair<>(state, State.StateEnum.STATE_LEFT), new ArrayList<>());
            playerMap.put(new Pair<>(state, State.StateEnum.STATE_RIGHT), new ArrayList<>());
            playerMap.put(new Pair<>(state, State.StateEnum.STATE_STILL), new ArrayList<>());
            try {
                playerMap.get(new Pair<>(state, State.StateEnum.STATE_LEFT))
                        .add(ImageIO.read(new File(relativePath + path + "_left1.PNG"))
                                .getScaledInstance(height, width, Image.SCALE_DEFAULT));
                playerMap.get(new Pair<>(state, State.StateEnum.STATE_RIGHT))
                        .add(ImageIO.read(new File(relativePath + path + "_right1.PNG"))
                                .getScaledInstance(height, width, Image.SCALE_DEFAULT));
                playerMap.get(new Pair<>(state, State.StateEnum.STATE_STILL))
                        .add(ImageIO.read(new File(relativePath + path + "_front.PNG"))
                                .getScaledInstance(height, width, Image.SCALE_DEFAULT));
            } catch (IOException e) {
                LOGGER.error("IOexception occured", e);
            }
        });
        try {
            playerMap.get(new Pair<>(State.StateEnum.STATE_STANDING, State.StateEnum.STATE_LEFT))
                    .add(ImageIO.read(new File(relativePath + path + "_left2.PNG"))
                            .getScaledInstance(height, width, Image.SCALE_DEFAULT));
            playerMap.get(new Pair<>(State.StateEnum.STATE_STANDING, State.StateEnum.STATE_RIGHT))
                    .add(ImageIO.read(new File(relativePath + path + "_right2.PNG"))
                            .getScaledInstance(height, width, Image.SCALE_DEFAULT));
        } catch (IOException e) {
            LOGGER.error("IOexception occured", e);
        }
    }

    /**
     * Returns the player's image.
     * 
     * @return the player's image
     */
    public Image getImage() {
        return new ImageIcon(this.playerImage).getImage();
    }

    /**
     * Returns the typer of the player.
     * 
     * @return the type of the player
     */
    public Player.PlayerType getPlayerType() {
        return this.type;
    }

}
