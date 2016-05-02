package UserInterface;
import java.io.*;

import MainComponents.Calculator;
import MathComponents.Complex;
import MathComponents.Rational;
import MathComponents.Matrix;

import static UserInterface.Checks.checkVector;
import static UserInterface.Checks.checkMatrixSize;

/**
 * UI that handle with auto input from txt file.
 */
public class InputFileUI implements UI {
    private String field; //Determine which field to work with, Complex or Rational.
    private BufferedReader reader; //Gets the line from the input txt file.

    /**
     * Constructor of the UI.
     * @param txtFile The name of the txt file that the program will know to look for.
     */
    public InputFileUI(String txtFile){
        this.field = "0";
        String txtFilePath = Calculator.class.getProtectionDomain().getCodeSource().getLocation().getPath() + txtFile; //Gets the path of the running folder contains the jar file.
        //Tries to create a reader of the inserted txt file. if the file not found return FileNotFoundException.
        try {
            this.reader =  new BufferedReader(new FileReader(txtFilePath));
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
    }

    /**
     * The main function of the UI, runs the program.
     * @throws IOException Exception for the reader.
     */
    public void play() throws IOException {
        System.out.println("Please select the scalar field:");
        System.out.println("1) Rational Or 2) Complex");

        String line = reader.readLine().trim(); //Gets the next line of the file.
        if(!(line.equals("1") || line.equals("2"))){ //Checks the legality of the input.
            System.out.println("Wrong field input");
            System.exit(0);
        }
        this.field = line; //Sets the field the inserted.

        System.out.println("Please select an option:");
        System.out.println("1) Addition");
        System.out.println("2) Multiplication");
        System.out.println("3) Solving linear equation systems");
        System.out.println("4) Exit");

        line = reader.readLine().trim();
        if (!(line.equals("1") || line.equals("2") || line.equals("3") || line.equals("4"))) { //If the input isn't equal for non of the options.
            System.out.println("Wrong option input");
            System.exit(0);
        }
        else if (line.equals("4")) System.exit(0);
        else { //If one of the 1-3 options selcted.
            System.out.println("You have selected option "+line);
            try{
                function(line); //Runs a solution function for the code arrangement, sends the chosen option.
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Solution function for the code arrangement.
     * @param option The chosen option by the user from 1-3 to calculate.
     * @throws Exception
     */
    private void function(String option) throws Exception {
        System.out.println("Insert the matrix size: rows,columns");
        String matrixSize = reader.readLine().trim();
        if(!checkMatrixSize(matrixSize,option)){ //Checks if the matrix size that inserted is from the wanted pattern.
            System.out.println("Wrong matrix size input");
            System.exit(0);
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
    private Matrix getMatrixFromUser(int rows, int columns) throws Exception{
        Matrix mat = new Matrix(rows,columns); //Creates an empty matrix.
        for(int i=0;i<mat.getRows();i++) {
            String[] tempVectorParm = reader.readLine().trim().split(" ", columns);
            if (tempVectorParm.length != columns) { //Checks if the inserted vector has enough components.
                System.out.println("Wrong vector input");
                System.exit(0);
            } else if (!checkVector(tempVectorParm, field)) { //Checks if the inserted vector is from the wanted pattern in step to the inserted field.
                System.out.println("Wrong vector input");
                System.exit(0);
            } else { //Insert the vector to the matrix.
                if (field.equals("2")) {
                    for (int num = 0; num < tempVectorParm.length; num++) {
                        tempVectorParm[num] = tempVectorParm[num].substring(0, tempVectorParm[num].length() - 1);
                        String[] split = tempVectorParm[num].split("\\+", 2);
                        String[] firstRat = split[0].split("\\/", 2);
                        String[] secondRat = split[1].split("\\/", 2);
                        mat.getArrVector()[i].getVectorParm()[num] = new Complex(new Rational(Integer.parseInt(firstRat[0]), Integer.parseInt(firstRat[1])), new Rational(Integer.parseInt(secondRat[0]), Integer.parseInt(secondRat[1])));
                    }
                }
                if (field.equals("1")) {
                    for (int num = 0; num < tempVectorParm.length; num++) {
                        String[] split = tempVectorParm[num].split("\\/", 2);
                        mat.getArrVector()[i].getVectorParm()[num] = new Rational(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                    }
                }
            }
        }
        return mat;
    }

    /**
     * Calculate addition of two matrix.
     * @param mat The first matrix.
     * @throws Exception
     */
    private void add(Matrix mat) throws Exception {
        System.out.println("Insert the second matrix size: rows,columns");
        String matrixSize = reader.readLine().trim();
        if(!checkMatrixSize(matrixSize)){
            System.out.println("Wrong vector input");
            System.exit(0);
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
    private void mul(Matrix mat) throws Exception{
        System.out.println("Insert the second matrix size: rows,columns");
        String matrixSize = reader.readLine().trim();
        if(!checkMatrixSize(matrixSize)){
            System.out.println("Wrong vector input");
            System.exit(0);
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
