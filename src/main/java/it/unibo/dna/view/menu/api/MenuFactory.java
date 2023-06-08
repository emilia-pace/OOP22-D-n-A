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
     * Creates and returns a game over menu.
     * @param i
     *
     * @return The created game over menu.
     */
    GameMenu gameOverMenu(int i);

    /**
     * Creates and returns a victory menu with the specified total score.
     * @param i
     *
     * @return The created victory menu.
     */
    GameMenu victoryMenu(int i);

    GameMenu lastVictoryMenu();

    GameMenu pauseMenu();
}