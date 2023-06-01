package it.unibo.dna.model.object;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.dna.common.Pair;
import it.unibo.dna.model.object.State.StateEnum;
import it.unibo.dna.model.object.api.Player;
import javax.imageio.ImageIO;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyObserver implements PropertyChangeListener {

    private State state;
    private Map<Pair<State.StateEnum, State.StateEnum>, List<Image>> playerMap = new HashMap<>();
    private Player.PlayerType type;
    private Image playerImage;
    private int MAX_FRAME = 100;
    private int frame = 0;
    private int imageIndex = 0;

    public MyObserver(State state, Player.PlayerType type, int height, int width, double tileSize) {
        state.addChangeListener(this);
        this.state = state;
        this.type = type;
        this.playerImage(playerMap, height * (int) tileSize, width * (int) tileSize);
        playerImage = this.playerMap.get(state.getState()).get(0);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        this.state = ((State) event.getSource());
        if (event.getNewValue().equals(event.getOldValue())) {
            playerImage = this.playerMap.get(((State) event.getSource()).getState()).get(imageIndex);
            System.out.println("ciao");
        } else {
            playerImage = this.playerMap.get(((State) event.getSource()).getState()).get(0);
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
            if (this.state.getX().equals(StateEnum.STATE_STANDING)
                    && (this.state.getY().equals(StateEnum.STATE_LEFT)
                            || this.state.getY().equals(StateEnum.STATE_RIGHT))) {
                this.propertyChange(new PropertyChangeEvent(this.state, null, this.state.getY(), this.state.getY()));
            }
        }
    }

    private void playerImage(final Map<Pair<State.StateEnum, State.StateEnum>, List<Image>> playerMap,
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

    public Image getPlayerImage() {
        return this.playerImage;
    }

    public Player.PlayerType getType() {
        return this.type;
    }

}
