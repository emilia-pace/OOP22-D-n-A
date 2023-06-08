package it.unibo.dna.graphics.api;

import javax.sound.sampled.Clip;

/**
 * Interface for a sound factory that provides different sound clips.
 */
public interface SoundFactory {

    /**
     * Returns the sound clip for the angel's jump.
     *
     * @return the sound clip for the angel's jump
     */
    Clip jumpAngelClip();

    /**
     * Returns the sound clip for the devil's jump.
     *
     * @return the sound clip for the devil's jump
     */
    Clip jumpDevilClip();

    /**
     * Returns the sound clip for reaching a diamond 
     *
     * @return the sound clip for reaching a diamond
     */
    Clip diamondClip();

    /**
     * Returns the sound clip for the game over of a level.
     *
     * @return the sound clip for the game over
     */
    Clip gameOverClip();

    /**
     * Returns the sound clip for the victory of a level.
     *
     * @return the sound clip for the victory
     */
    Clip victoryClip();

}
