package MainComponents;

import UserInterface.CommandLineUI;
import UserInterface.InputFileUI;
import java.io.IOException;

/**
 * The main class that runs the program.
 * Determines which UI will work.
 */
public class Calculator {
    public static void main(String[] args){
        if(args.length>0){ //if txt file name inserted.
            InputFileUI IFUI = new InputFileUI(args[0]); //creates an instance of the InputFileUI.
            try {
                IFUI.play();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{ //if txt file name wasn't inserted.
            CommandLineUI CLUI = new CommandLineUI();
            CLUI.play();
        }
    }
}