package Logic;

import UserInterface.CommandLineUI;
import UserInterface.UI;

/**
 * The main class that runs the program.
 * Determines which UI will work.
 */
public class Calculator {
    private static UI ui;

    public static void main(String[] args){
        ui = new CommandLineUI();
        ui.play();
    }
}