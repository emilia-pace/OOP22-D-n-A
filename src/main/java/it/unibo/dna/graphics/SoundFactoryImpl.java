package it.unibo.dna.graphics;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.dna.graphics.api.SoundFactory;

/**
 * Class that implements the {@link SoundFactory} interface.
 */
public class SoundFactoryImpl implements SoundFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(SoundFactoryImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public Clip jumpAngelClip() {
        try {
            final Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("src\\main\\resources\\sounds\\Angel_audio.wav")));
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Clip jumpDevilClip() {
        try {
            final Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("src\\main\\resources\\sounds\\Devil_audio.wav")));
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Clip diamondClip() {
        try {
            final Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("src\\main\\resources\\sounds\\Diamond_sound.wav")));
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Clip gameOverClip() {
        try {
            final Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("src\\main\\resources\\sounds\\GameOver_sound.wav")));
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Clip victoryClip() {
        try {
            final Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("src\\main\\resources\\sounds\\Victory_sound.wav")));
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
