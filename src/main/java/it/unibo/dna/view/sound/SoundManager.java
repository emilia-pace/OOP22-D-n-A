package it.unibo.dna.view.sound;

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

    /**
     * Retrieves an audio clip based on the specified file path.
     * 
     * @param namePath the name or path of the audio file
     * @return the audio clip associated with the specified file, or {@code null} if
     *         an error occurs
     */
    public Clip getClip(final String namePath) {
        try {
            final Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(ClassLoader.getSystemResource("sounds/" + namePath + ".wav")));
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
