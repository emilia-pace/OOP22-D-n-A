package it.unibo.dna.graphics;

import it.unibo.dna.model.Score;

public interface MenuFactory  {
    
    GameMenu startMenu();

    GameMenu gameOverMenu();

    GameMenu victoryMenu(Score totalScore);
}
