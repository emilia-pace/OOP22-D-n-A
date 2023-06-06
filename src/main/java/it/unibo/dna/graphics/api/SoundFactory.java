package it.unibo.dna.graphics.api;

import javax.sound.sampled.Clip;

public interface SoundFactory {

    Clip jumpAngelClip();

    Clip jumpDevilClip();

    Clip diamondClip();

    Clip GameOverClip();

    Clip victoryClip();

}