package MainComponents;

import UserInterface.CommandLineUI;
import UserInterface.InputFileUI;
import UserInterface.UI;

/**
 * The main class that runs the program.
 * Determines which UI will work.
 */
public class Calculator {
    private static UI ui;

    public static void main(String[] args){
        if(args.length>0){ //if text file name inserted.
            ui = new InputFileUI(args[0]); //creates an instance of the InputFileUI.
        }
        else{ //if text file name wasn't inserted.
            ui = new CommandLineUI();
        }
        try {
            ui.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}