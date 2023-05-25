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
import it.unibo.dna.model.object.api.Player;

public class ManageImage {

    private int MAX_FRAME = 10;
    private int frame = 0;
    private int imageIndex = 0;
    private Map<Pair<Player.State, Player.State>, List<Image>> angelMap = new HashMap<>();
    private Map<Pair<Player.State, Player.State>, List<Image>> devilMap = new HashMap<>();
    private Image diamondImg;

    public ManageImage() {
        // caricamento di tutte le immagini
        this.playerImage(angelMap, Player.Type.ANGEL);
        this.playerImage(devilMap, Player.Type.DEVIL);
        this.diamondImage();
    }


    private void playerImage(Map<Pair<Player.State, Player.State>, List<Image>> playerMap, Player.Type type) {
        String path;
        if (type.equals(Player.Type.ANGEL)) {
            path = "angelo";
        } else {
            path = "diavolo";
        }
        List.of(Player.State.STATE_JUMPING, Player.State.STATE_STANDING).forEach(state -> {
            playerMap.put(new Pair<>(state, Player.State.STATE_LEFT), new ArrayList<>());
            playerMap.put(new Pair<>(state, Player.State.STATE_RIGHT), new ArrayList<>());
            playerMap.put(new Pair<>(state, Player.State.STATE_STILL), new ArrayList<>());
            try {
                playerMap.get(new Pair<>(state, Player.State.STATE_LEFT))
                        .add(ImageIO.read(new File("src\\main\\resurces\\" + path + "_sinistra1.PNG")));
                playerMap.get(new Pair<>(state, Player.State.STATE_RIGHT))
                        .add(ImageIO.read(new File("src\\main\\resurces\\" + path + "_destra1.PNG")));
                playerMap.get(new Pair<>(state, Player.State.STATE_STILL))
                        .add(ImageIO.read(new File("src\\main\\resurces\\" + path + "_fronte.PNG")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        try {
            playerMap.get(new Pair<>(Player.State.STATE_STANDING, Player.State.STATE_LEFT))
                    .add(ImageIO.read(new File("src\\main\\resurces\\" + path + "_sinistra2.PNG")));
            playerMap.get(new Pair<>(Player.State.STATE_STANDING, Player.State.STATE_RIGHT))
                    .add(ImageIO.read(new File("src\\main\\resurces\\" + path + "_destra2.PNG")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void diamondImage(){
        try{
            diamondImg = ImageIO.read(new File("src\\main\\resurces\\diamond.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image getDiamondImage(){
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

}
