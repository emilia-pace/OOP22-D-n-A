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
import it.unibo.dna.model.object.EntityFactory;
import it.unibo.dna.model.object.player.State.StateEnum;
import it.unibo.dna.model.object.player.api.Player;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

/**
 * A observer class of a player's state.
 */
public class StateObserver implements PropertyChangeListener {

    private final int MAXFRAME = 100;

    private int frame = 0;
    private int imageIndex = 0;

    private State state;
    private Map<Pair<State.StateEnum, State.StateEnum>, List<Image>> playerMap = new HashMap<>();
    private Player.PlayerType type;
    private Image playerImage;

    /**
     * @param state The player's state to observe
     * @param type  The player's type to which the state belongs
     */
    public StateObserver(final State state, final Player.PlayerType type) {
        state.addChangeListener(this);
        this.state = state;
        this.type = type;
        this.loadPlayerImage(playerMap, EntityFactory.PLAYER_HEIGHT * Display.TILE_SIZE,
                EntityFactory.PLAYER_WIDTH * Display.TILE_SIZE);
        playerImage = this.playerMap.get(state.getPairState()).get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        this.state = ((State) event.getSource());
        if (event.getNewValue().equals(event.getOldValue())) {
            playerImage = this.playerMap.get(((State) event.getSource()).getPairState()).get(imageIndex);
            System.out.println("ciao");
        } else {
            playerImage = this.playerMap.get(((State) event.getSource()).getPairState()).get(0);
        }
    }

    /**
     * A method that control the change of images when the player is walking to the
     * right or to the left.
     */
    public void update() {
        this.frame++;
        if (frame >= this.MAXFRAME) {
            this.imageIndex = (this.imageIndex == 0) ? 1 : 0;
            this.frame = 0;
            if (this.state.getX().equals(StateEnum.STATE_STANDING)
                    && (this.state.getY().equals(StateEnum.STATE_LEFT)
                            || this.state.getY().equals(StateEnum.STATE_RIGHT))) {
                this.propertyChange(new PropertyChangeEvent(this.state, null, this.state.getY(), this.state.getY()));
            }
        }
    }

    /**
     * @param playerMap The map with states as keys and images as values
     * @param width     The width of the players
     * @param height    The height of the player
     */
    private void loadPlayerImage(final Map<Pair<State.StateEnum, State.StateEnum>, List<Image>> playerMap,
            final int width, final int height) {
        String path = (this.type.equals(Player.PlayerType.ANGEL)) ? "angel" : "devil";
        List.of(State.StateEnum.STATE_JUMPING, State.StateEnum.STATE_STANDING).forEach(state -> {
            playerMap.put(new Pair<>(state, State.StateEnum.STATE_LEFT), new ArrayList<>());
            playerMap.put(new Pair<>(state, State.StateEnum.STATE_RIGHT), new ArrayList<>());
            playerMap.put(new Pair<>(state, State.StateEnum.STATE_STILL), new ArrayList<>());
            try {
                playerMap.get(new Pair<>(state, State.StateEnum.STATE_LEFT))
                        .add(ImageIO.read(new File("src\\main\\resources\\playerImage\\" + path + "_left1.PNG"))
                                .getScaledInstance(height, width, Image.SCALE_DEFAULT));
                playerMap.get(new Pair<>(state, State.StateEnum.STATE_RIGHT))
                        .add(ImageIO.read(new File("src\\main\\resources\\playerImage\\" + path + "_right1.PNG"))
                                .getScaledInstance(height, width, Image.SCALE_DEFAULT));
                playerMap.get(new Pair<>(state, State.StateEnum.STATE_STILL))
                        .add(ImageIO.read(new File("src\\main\\resources\\playerImage\\" + path + "_front.PNG"))
                                .getScaledInstance(height, width, Image.SCALE_DEFAULT));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        try {
            playerMap.get(new Pair<>(State.StateEnum.STATE_STANDING, State.StateEnum.STATE_LEFT))
                    .add(ImageIO.read(new File("src\\main\\resources\\playerImage\\" + path + "_left2.PNG"))
                            .getScaledInstance(height, width, Image.SCALE_DEFAULT));
            playerMap.get(new Pair<>(State.StateEnum.STATE_STANDING, State.StateEnum.STATE_RIGHT))
                    .add(ImageIO.read(new File("src\\main\\resources\\playerImage\\" + path + "_right2.PNG"))
                            .getScaledInstance(height, width, Image.SCALE_DEFAULT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the player's image
     */
    public Image getImage() {
        return this.playerImage;
    }

    /**
     * @return the type of the player
     */
    public Player.PlayerType getPlayerType() {
        return this.type;
    }

}
