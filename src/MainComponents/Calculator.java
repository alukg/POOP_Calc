package MainComponents;

import UserInterface.CommandLineUI;
import UserInterface.InputFileUI;

import java.io.IOException;

public class Calculator {
    public static void main(String[] args){
        if(args.length>0){
            InputFileUI IFUI = new InputFileUI(args[0]);
            try {
                IFUI.play();
            } catch (IOException e) {
                System.out.println("need to check the reader ioexception");
            }
        }
        else{
            CommandLineUI CLUI = new CommandLineUI();
            CLUI.play();
        }
    }
}