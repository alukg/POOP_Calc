package Logic;

/**
 * Class that unites all the the needed checks for the UIs.
 * It was created for the order of the project.
 */
public class Checks {
    /**
     * Checks the legality of the inserted matrix size.
     * @param matrixSize input to check.
     * @return if the pattern of the input is okay.
     */
    public static boolean checkMatrixSize(String matrixSize,String option){
        if (matrixSize.indexOf(',')<0) return false; //if the comma sign isn't exist.
        String[] splitSize = new String[2];
        //checks the two numbers near the comma sign
        splitSize = matrixSize.split(",",2);
        try{
            if(splitSize[0]==null || splitSize[1]==null || Integer.parseInt(splitSize[0])==0 || Integer.parseInt(splitSize[1])==0) return false; //if there is chars near the comma sign.
        }
        catch (Exception e){
            return false;
        }
        if(!isNumeric(splitSize[0]) || !isNumeric(splitSize[1])) return false; //if those chars are numbers.
        try{
            if(option.equals("3"))
                if((Integer.parseInt(splitSize[0])+1)!=Integer.parseInt(splitSize[1])) return false;
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean checkMatrixSize(String matrixSize){
        if (matrixSize.indexOf(',')<0) return false; //if the comma sign isn't exist.
        String[] splitSize = new String[2];
        //checks the two numbers near the comma sign
        splitSize = matrixSize.split(",",2);
        try{
            if(splitSize[0]==null || splitSize[1]==null || Integer.parseInt(splitSize[0])==0 || Integer.parseInt(splitSize[1])==0) return false; //if there is chars near the comma sign.
        }
        catch (Exception e){
            return false;
        }
        if(!isNumeric(splitSize[0]) || !isNumeric(splitSize[1])) return false; //if those chars are numbers.
        return true;
    }

    /**
     * Checks if the inserted string is a number.
     * @param str requested string to check.
     * @return if the string is number or not.
     */
    private static boolean isNumeric(String str)
    {
        try
        {
            int number = Integer.parseInt(str); //try to convert the string to number.
        }
        catch(NumberFormatException nfe) //the result will be an exception if not all chars are numbers.
        {
            return false;
        }
        return true;
    }

    /**
     * Checks the legality of the inserted vector.
     * @param vectorParm vector parameters to check.
     * @param field which field we work with.
     * @return  if the vector pattern is okay.
     */
    public static boolean checkVector(String[] vectorParm, String field){
        if(field.equals("1")){ //Rational
            for(int i=0; i<vectorParm.length ;i++){
                if(!isRational(vectorParm[i])) return false; //checks if each number in the vector is from the right pattern.
            }
        }
        if(field.equals("2")){ //Complex
            for(int i=0; i<vectorParm.length ;i++){
                if(!isComplex(vectorParm[i])) return false;
            }
        }
        return true;
    }

    /**
     * checks if the string is from appropriate complex number pattern.
     * @param s string to check.
     * @return if the pattern is okay.
     */
    private static boolean isComplex (String s){
        if(s.charAt(s.length()-1)!='i') return false;
        s = s.substring(0,s.length()-1); //check the string without the 'i' char.
        String[] split = new String[2];
        //checks if the two strings near the plus sign are rational numbers.
        split = s.split("\\+",2);
        if (split[0]==null || split[1]==null) return false;
        if(!isRational(split[0]) || !isRational(split[1])) return false;
        return true;
    }

    /**
     * checks if the string is from appropriate rational number pattern.
     * @param s string to check.
     * @return if the pattern is okay.
     */
    private static boolean isRational (String s){
        String[] split = new String[2];
        split = s.split("\\/",2); //checks if there is the slash char.
        try{
            if (split[0]==null || split[1]==null || Integer.parseInt(split[1])==0) return false; //checks if the chars near the slash are not empty, or that the denominator equal zero.
        }
        catch (Exception e){
            return false;
        }
        if(!isNumeric(split[0]) || !isNumeric(split[1])) return false; //checks if the chars are numbers.
        return true;
    }
}
