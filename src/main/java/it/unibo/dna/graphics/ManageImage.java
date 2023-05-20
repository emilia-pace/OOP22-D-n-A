package it.unibo.dna.graphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;

import it.unibo.dna.common.Pair;
import it.unibo.dna.model.object.api.Player;

public class ManageImage {

    private int MAX_FRAME = 10;
    private int frame = 0;
    private int imageIndex = 0;
    private Map<Pair<Player.State, Player.State>, List<ImageIO>> angelMap = new HashMap<>();
    private Map<Pair<Player.State, Player.State>, List<ImageIO>> devilMap = new HashMap<>();

    public ManageImage() {
        // caricamento di tutte le immagini
        this.playerImage(angelMap);
        this.playerImage(devilMap);
    }

    /*
     * da lavorare sul nome da mettere nelle immagini in modo da lavorare con i nomi
     * e poter rendere il tutto meno copiato per ogni personaggio
     */
    private void playerImage(Map<Pair<Player.State, Player.State>, List<ImageIO>> playerMap) {
        List.of(Player.State.STATE_JUMPING, Player.State.STATE_STANDING).forEach(state -> {
            playerMap.put(new Pair<>(state, Player.State.STATE_LEFT), new ArrayList<>());
            playerMap.put(new Pair<>(state, Player.State.STATE_RIGHT), new ArrayList<>());
            playerMap.put(new Pair<>(state, Player.State.STATE_STILL), new ArrayList<>());
        });
        playerMap.get(new Pair<>(Player.State.STATE_STANDING, Player.State.STATE_LEFT)).add(null);
        playerMap.get(new Pair<>(Player.State.STATE_STANDING, Player.State.STATE_RIGHT)).add(null);
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

    public ImageIO playerChooseImage(Player p) {
        Map<Pair<Player.State, Player.State>, List<ImageIO>> playerMap;
        if (p.getType().equals(Player.Type.ANGEL)) {
            playerMap = this.angelMap;
        } else {
            playerMap = this.devilMap;
        }

        if (p.getState().equals(new Pair<>(Player.State.STATE_STANDING, Player.State.STATE_LEFT))
                || p.getState().equals(new Pair<>(Player.State.STATE_STANDING, Player.State.STATE_RIGHT))) {
            return playerMap.get(p.getState()).get(this.imageIndex);
        }
        return playerMap.get(p.getState()).get(0);
    }

}
