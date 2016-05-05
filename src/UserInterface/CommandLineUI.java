package UserInterface;
import java.util.Scanner;

import MathComponents.Complex;
import MathComponents.Rational;
import MathComponents.Matrix;
import static Logic.Checks.checkVector;
import static Logic.Checks.checkMatrixSize;

/**
 * UI that handle with user input from the command line.
 */
public class CommandLineUI implements UI {
    private String field; //Determine which field to work with, Complex or Rational.
    private Scanner console;

    /**
     * Constructor of the UI.
     */
    public CommandLineUI(){
        console = new Scanner(System.in);
    }

    /**
     * The main function of the UI, runs the program.
     */
    public void play() {
        while (true){
            this.field = "0";
            System.out.println();
            System.out.println("Please select the scalar field:");
            System.out.println("1) Rational Or 2) Complex");

            while (this.field == "0") { //While not inserted a legal field value.
                String inField = console.nextLine(); //Gets the wanted field from the user.
                if (inField.equals("1") || inField.equals("2"))
                    this.field = inField;
                else {
                    System.out.println("Wrong input, please select the scalar field:");
                    System.out.println("1) Rational Or 2) Complex");
                }
            }
            System.out.println("Please select an option:");
            System.out.println("1) Addition");
            System.out.println("2) Multiplication");
            System.out.println("3) Solving linear equation systems");
            System.out.println("4) Exit");
            String option = console.nextLine(); //Gets the wanted option from the user.
            while (!(option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4"))) { //While not inserted a legal option value.
                System.out.println("Wring input, please select an option:");
                System.out.println("1) Addition");
                System.out.println("2) Multiplication");
                System.out.println("3) Solving linear equation systems");
                System.out.println("4) Exit");
                option = console.nextLine(); //Ask for value again.
            }
            if (option.equals("4")){
            	System.out.println("Thank you for using our calculator, see you next time");
            	System.exit(0);
            }
            else if (option.equals("1") || option.equals("2") || option.equals("3")) {
                System.out.println("You have selected option "+option);
                function(option); //Runs a solution function for the code arrangement, sends the chosen option.
            }
        }
    }


    /**
     * Solution function for the code arrangement.
     * @param option The chosen option by the user from 1-3 to calculate.
     * @throws Exception
     */
    private void function(String option) {
        System.out.println("Insert the matrix size: rows,columns");
        String matrixSize = console.nextLine();
        while(!checkMatrixSize(matrixSize,option)){ //While not inserted a legal matrix size.
            System.out.println("Input is not good.");
            System.out.println("Insert again the matrix size: rows,columns");
            matrixSize = console.nextLine(); //Ask again for matrix size.
        }
        String[] splitSize = matrixSize.split(",",2); //Splits the String for row and column size.

        if(option.equals("3")) System.out.println("Insert the matrix:");
        else{ System.out.println("Insert the first matrix:"); } //For options 1,2 will be another matrix to insert.
        Matrix matrix = getMatrixFromUser(Integer.parseInt(splitSize[0]),Integer.parseInt(splitSize[1]));
        if(option.equals("3")){
            try{
                System.out.println("The solution is:");
                System.out.println(matrix.solve());
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        else if (option.equals("1")){
            add(matrix);
        }
        else {
            mul(matrix);
        }
    }

    /**
     * Creates a new matrix.
     * @param rows Rows of the wanted matrix.
     * @param columns Columns of the wanted matrix.
     * @return Matrix that created by the given data.
     * @throws Exception
     */
    private Matrix getMatrixFromUser(int rows, int columns){
        Matrix mat = new Matrix(rows,columns); //Creates an empty matrix.
        boolean succeeded; //Boolean value that determine at the end of the process if the inserted matrix is okay.
        do {
            succeeded = true;
            for(int i=0;i<mat.getRows();i++){
                String[] tempVectorParm = console.nextLine().split(" ",columns);
                if(tempVectorParm.length!=columns) succeeded = false; //Checks if the inserted vector has enough components.
                else if (!checkVector(tempVectorParm,field)) { //Checks if the inserted vector is from the wanted pattern in step to the inserted field.
                    succeeded = false;
                }
                else{
                    if (field.equals("2")){
                        for(int num=0;num<tempVectorParm.length;num++){
                            tempVectorParm[num] = tempVectorParm[num].substring(0,tempVectorParm[num].length()-1);
                            String[] split = tempVectorParm[num].split("\\+",2);
                            String[] firstRat = split[0].split("\\/",2);
                            String[] secondRat = split[1].split("\\/",2);
                            try {
                                mat.getArrVector()[i].getVectorParm()[num] = new Complex(new Rational(Integer.parseInt(firstRat[0]),Integer.parseInt(firstRat[1])),new Rational(Integer.parseInt(secondRat[0]),Integer.parseInt(secondRat[1])));
                            } catch (Exception e) {
                                e.printStackTrace();
                                succeeded = false;
                            }
                        }
                    }
                    if (field.equals("1")){
                        for(int num=0;num<tempVectorParm.length;num++){
                            String[] split = tempVectorParm[num].split("\\/",2);
                            try {
                                mat.getArrVector()[i].getVectorParm()[num] = new Rational(Integer.parseInt(split[0]),Integer.parseInt(split[1]));
                            } catch (Exception e) {
                                e.printStackTrace();
                                succeeded = false;
                            }
                        }
                    }
                }
            }
            if(!succeeded) System.out.println("Wrong input, insert the matrix again:");
        }
        while(!succeeded); //While the process didn't succeed, start the all process again.
        return mat;
    }

    /**
     * Calculate addition of two matrix.
     * @param mat The first matrix.
     * @throws Exception
     */
    private void add(Matrix mat){
        System.out.println("Insert the second matrix size: rows,columns");
        String matrixSize = console.nextLine();
        while(!checkMatrixSize(matrixSize)){
            System.out.println("Input is not good.");
            System.out.println("Insert again the matrix size: rows,columns");
            matrixSize = console.nextLine();
        }
        String[] splitSize = matrixSize.split(",",2);
        System.out.println("Insert the second matrix:");
        Matrix matrix = getMatrixFromUser(Integer.parseInt(splitSize[0]),Integer.parseInt(splitSize[1]));
        try{
            System.out.println("The solution is:");
            System.out.println(mat.add(matrix)); //Print the first matrix added with the second.
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Calculate multiplication of two matrix.
     * @param mat The first matrix.
     * @throws Exception
     */
    private void mul(Matrix mat){
        System.out.println("Insert the second matrix size: rows,columns");
        String matrixSize = console.nextLine();
        while(!checkMatrixSize(matrixSize)){
            System.out.println("Input is not good.");
            System.out.println("Insert again the matrix size: rows,columns");
            matrixSize = console.nextLine();
        }
        String[] splitSize = matrixSize.split(",",2);
        System.out.println("Insert the second matrix:");
        Matrix matrix = getMatrixFromUser(Integer.parseInt(splitSize[0]),Integer.parseInt(splitSize[1]));
        try{
            System.out.println("The solution is:");
            System.out.println(mat.mul(matrix)); //Print the first matrix multiplied with the second.
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
