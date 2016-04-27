package UserInterface;
import java.util.Scanner;

public class CommandLineUI implements UI {
    private int field;

    public CommandLineUI(){
        this.field = 0;
    }

    public void play() {
        Scanner console = new Scanner(System.in);

        System.out.println("Please select the scalar field:");
        System.out.println("1) Rational Or 2) Complex");
        while(this.field==0){
            int field = console.nextInt();
            if(field==1 || field ==2)
                this.field=field;
            else{
                System.out.println("Wring number, choose field again.");
                System.out.println("1) Rational Or 2) Complex");
            }
        }
        System.out.println("Please select an option:");
        System.out.println("1) Addition");
        System.out.println("2) Multiplication");
        System.out.println("3) Solving linear equation systems");
        System.out.println("4) Exit");




    }


}
