package it.unibo.dna;

import it.unibo.dna.graphics.MenuFactory;
import it.unibo.dna.graphics.MenuFactoryImpl;
import it.unibo.dna.graphics.GameMenu;

public class Launcher {
    public static void main(String[] args) {

        GameMenu GameMenu;
        MenuFactory boh;

        boh = new MenuFactoryImpl();

        GameMenu = boh.startMenu();

        GameMenu.createMenuFrame();


      
    }
}
