package UserInterface;
import java.util.Scanner;

import MathComponents.Complex;
import MathComponents.Rational;
import MathComponents.Matrix;
import static UserInterface.Checks.checkVector;
import static UserInterface.Checks.checkMatrixSize;

public class CommandLineUI implements UI {
    private String field;

    public CommandLineUI(){
        this.field = "0";
    }

    public void play() {
        Scanner console = new Scanner(System.in);

        System.out.println("Please select the scalar field:");
        System.out.println("1) Rational Or 2) Complex");
        while (this.field == "0") {
            String inField = console.next();
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
        String option = console.next();
        while (!(option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4"))) {
            System.out.println("Wring input, please select an option:");
            System.out.println("1) Addition");
            System.out.println("2) Multiplication");
            System.out.println("3) Solving linear equation systems");
            System.out.println("4) Exit");
            option = console.next();
        }
        if (option.equals("4")) System.exit(0);
        else if (option.equals("1") || option.equals("2") || option.equals("3")) {
            System.out.println("You have selected option "+option);
            try{
                function(option);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void function(String option) throws Exception {
        Scanner console = new Scanner(System.in);
        System.out.println("Insert the matrix size: rows,columns");
        String matrixSize = console.next();
        while(!checkMatrixSize(matrixSize)){
            System.out.println("Input is not good.");
            System.out.println("Insert again the matrix size: rows,columns");
            matrixSize = console.next();
        }
        String[] splitSize = matrixSize.split(",",2);

        if(option.equals("3")) System.out.println("Insert the matrix:");
        else{ System.out.println("Insert the first matrix:"); }
        Matrix matrix = getMatrixFromUser(Integer.parseInt(splitSize[0]),Integer.parseInt(splitSize[1]));
        if(option.equals("3")){
            try{
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

    private Matrix getMatrixFromUser(int rows, int columns){
        Matrix mat = new Matrix(rows,columns);
        boolean succeeded = true;
        Scanner console = new Scanner(System.in);
        do {
            for(int i=0;i<mat.getRows();i++){
                String[] tempVectorParm = console.nextLine().split(" ",columns);
                if(tempVectorParm.length!=columns) succeeded = false;
                else if (!checkVector(tempVectorParm,field)) {
                    succeeded = false;
                }
                else{
                    if (field.equals("2")){
                        for(int num=0;num<tempVectorParm.length;num++){
                            tempVectorParm[num] = tempVectorParm[num].substring(0,tempVectorParm[num].length()-1);
                            String[] split = tempVectorParm[num].split("\\+",2);
                            String[] firstRat = split[0].split("\\/",2);
                            String[] secondRat = split[1].split("\\/",2);
                            mat.getArrVector()[i].getVectorParm()[num] = new Complex(new Rational(Integer.parseInt(firstRat[0]),Integer.parseInt(firstRat[1])),new Rational(Integer.parseInt(secondRat[0]),Integer.parseInt(secondRat[1])));
                        }
                    }
                    if (field.equals("1")){
                        for(int num=0;num<tempVectorParm.length;num++){
                            String[] split = tempVectorParm[num].split("\\/",2);
                            mat.getArrVector()[i].getVectorParm()[num] = new Rational(Integer.parseInt(split[0]),Integer.parseInt(split[1]));
                        }
                    }
                    succeeded = true;
                }
            }
            if(!succeeded) System.out.println("Wrong input, insert the matrix again:");
        }
        while(!succeeded);
        return mat;
    }

    private void add(Matrix mat) {
        Scanner console = new Scanner(System.in);
        System.out.println("Insert the second matrix size: rows,columns");
        String matrixSize = console.next();
        while(!checkMatrixSize(matrixSize)){
            System.out.println("Input is not good.");
            System.out.println("Insert again the matrix size: rows,columns");
            matrixSize = console.next();
        }
        String[] splitSize = matrixSize.split(",",2);
        System.out.println("Insert the second matrix:");
        Matrix matrix = getMatrixFromUser(Integer.parseInt(splitSize[0]),Integer.parseInt(splitSize[1]));
        try{
            System.out.println(mat.add(matrix));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void mul(Matrix mat){
        Scanner console = new Scanner(System.in);
        System.out.println("Insert the second matrix size: rows,columns");
        String matrixSize = console.next();
        while(!checkMatrixSize(matrixSize)){
            System.out.println("Input is not good.");
            System.out.println("Insert again the matrix size: rows,columns");
            matrixSize = console.next();
        }
        String[] splitSize = matrixSize.split(",",2);
        System.out.println("Insert the second matrix:");
        Matrix matrix = getMatrixFromUser(Integer.parseInt(splitSize[0]),Integer.parseInt(splitSize[1]));
        try{
            System.out.println(mat.mul(matrix));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
