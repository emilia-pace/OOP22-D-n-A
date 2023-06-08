package it.unibo.dna.graphics;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that implements the {@link SoundFactory} interface.
 */
public class SoundManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(SoundManager.class);

    public Clip getClip(String namePath) {
        try {
            final Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("src\\main\\resources\\sounds\\" + namePath + ".wav")));
            return clip;
        } catch (IOException e) {
            LOGGER.error("IOException occurred", e);
        } catch (LineUnavailableException e) {
            LOGGER.error("LineUnavailableException occurred", e);
        } catch (UnsupportedAudioFileException e) {
            LOGGER.error("UnsupportedAudioFileException occurred", e);
        }
        return null;
    }
    
}
