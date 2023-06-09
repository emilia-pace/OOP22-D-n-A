package it.unibo.dna.view.menu.api;

/**
 * Represents a factory for creating game menus.
 */
public interface MenuFactory {

    /**
     * Creates and returns a start menu.
     *
     * @return The created start menu.
     */
    GameMenu startMenu();

    /**
     * Creates and returns a game over menu with the current level number.
     *
     * @param levelNumber The level number.
     * @return The created game over menu.
     */
    GameMenu gameOverMenu(int levelNumber);

    /**
     * Creates and returns a victory menu with the specified total score.
     *
     * @param lvl the number of the next level.
     * @return The created victory menu.
     */
    GameMenu victoryMenu(int lvl);

    /**
     * Creates and returns the victory menu of the last level.
     *
     * @return The last victory menu.
     */
    GameMenu lastVictoryMenu();

    /**
     * Creates and returns the pause menu.
     *
     * @return The created pause menu.
     */
    GameMenu pauseMenu();
}
