package it.unibo.dna.graphics.menu.api;

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
     *
     * @return The created game over menu.
     */
    GameMenu gameOverMenu();

    /**
     * Creates and returns a victory menu with the specified total score.
     *
     * @return The created victory menu.
     */
    GameMenu victoryMenu();

    GameMenu pauseMenu();
}
