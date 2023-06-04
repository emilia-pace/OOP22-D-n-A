package it.unibo.dna;

import it.unibo.dna.graphics.MenuFactory;
import it.unibo.dna.graphics.MenuFactoryImpl;
import it.unibo.dna.graphics.GameMenu;

public class Launcher {
    private static MenuFactory menuFactory;

    public static void main(String[] args) {
        menuFactory = new MenuFactoryImpl();
        GameMenu gameMenu = menuFactory.startMenu();
        gameMenu.createMenuFrame();
    }

    public static MenuFactory getMenuFactory() {
        return menuFactory;
    }
}
