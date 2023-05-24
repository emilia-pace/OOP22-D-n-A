package it.unibo.dna.graphics;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import it.unibo.dna.model.object.api.Player;

public class ManageSounds {

    private static Map<String, Clip> soundsMap = new HashMap<>();

    static {
        ManageSounds.getSounds();
    }

    private static void getSounds() {
        try {
            soundsMap.put("angel_jump", AudioSystem.getClip());
            soundsMap.get("angel_jump")
                    .open(AudioSystem.getAudioInputStream(new File("src\\main\\resurces\\Angel_audio.wav")));
            soundsMap.put("devil_jump", AudioSystem.getClip());
            soundsMap.get("devil_jump")
                    .open(AudioSystem.getAudioInputStream(new File("src\\main\\resurces\\Devil_audio.wav")));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sound(String s) {
        if (soundsMap.containsKey(s)) {
            soundsMap.get(s).start();
        }
    }

    public static void makeSoundPlayer(Player p) {
        if (p.getVector().y == (-Player.JumpVelocity + Player.StandardVelocity)) {
            if (p.getType().equals(Player.Type.ANGEL)) {
                soundsMap.get("angel_jump").start();
            } else {
                soundsMap.get("devil_jump").start();
            }
        }
    }
    
}
