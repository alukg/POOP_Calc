package UserInterface;
import java.io.*;

import MainComponents.Calculator;
import MathComponents.Complex;
import MathComponents.Rational;
import MathComponents.Matrix;

import static UserInterface.Checks.checkVector;
import static UserInterface.Checks.checkMatrixSize;

public class InputFileUI implements UI {
    private String field;
    private BufferedReader reader;

    public InputFileUI(String txtFile){
        this.field = "0";
        String txtFilePath = Calculator.class.getProtectionDomain().getCodeSource().getLocation().getPath() + txtFile;
        try {
            this.reader =  new BufferedReader(new FileReader(txtFilePath));
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
        catch (IOException e) {
            System.out.println("File not found");
            System.exit(0);
        }
    }

    public void play() throws IOException {
        System.out.println("Please select the scalar field:");
        System.out.println("1) Rational Or 2) Complex");

        String line = reader.readLine().trim();
        if(!(line.equals("1") || line.equals("2"))){
            System.out.println("Wrong field input");
            System.exit(0);
        }
        this.field = line;

        System.out.println("Please select an option:");
        System.out.println("1) Addition");
        System.out.println("2) Multiplication");
        System.out.println("3) Solving linear equation systems");
        System.out.println("4) Exit");

        line = reader.readLine().trim();
        if (!(line.equals("1") || line.equals("2") || line.equals("3") || line.equals("4"))) {
            System.out.println("Wrong option input");
            System.exit(0);
        }
        else if (line.equals("4")) System.exit(0);
        else {
            System.out.println("You have selected option "+line);
            try{
                function(line);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void function(String option) throws Exception {
        System.out.println("Insert the matrix size: rows,columns");
        String matrixSize = reader.readLine().trim();
        if(!checkMatrixSize(matrixSize)){
            System.out.println("Wrong matrix size input");
            System.exit(0);
        }
        String[] splitSize = matrixSize.split(",",2);

        if(option.equals("3")) System.out.println("Insert the matrix:");
        else{ System.out.println("Insert the first matrix:"); }
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

    private Matrix getMatrixFromUser(int rows, int columns) throws Exception{
        Matrix mat = new Matrix(rows,columns);
        for(int i=0;i<mat.getRows();i++) {
            String[] tempVectorParm = reader.readLine().trim().split(" ", columns);
            if (tempVectorParm.length != columns) {
                System.out.println("Wrong vector input");
                System.exit(0);
            } else if (!checkVector(tempVectorParm, field)) {
                System.out.println("Wrong vector input");
                System.exit(0);
            } else {
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
            System.out.println(mat.add(matrix));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

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
            System.out.println(mat.mul(matrix));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
