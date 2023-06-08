package it.unibo.dna.graphics.api;

import javax.sound.sampled.Clip;

/**
 * Interface for a sound factory that provides different sound clips.
 */
public interface SoundFactory {

    /**
     * Returns the sound clip for an angel jump.
     *
     * @return the sound clip for an angel jump
     */
    Clip jumpAngelClip();

    /**
     * Returns the sound clip for a devil jump.
     *
     * @return the sound clip for a devil jump
     */
    Clip jumpDevilClip();

    /**
     * Returns the sound clip for a diamond.
     *
     * @return the sound clip for a diamond
     */
    Clip diamondClip();

    /**
     * Returns the sound clip for game over of a level.
     *
     * @return the sound clip for game over
     */
    Clip gameOverClip();

    /**
     * Returns the sound clip for victory of a level.
     *
     * @return the sound clip for victory
     */
    Clip victoryClip();

}
