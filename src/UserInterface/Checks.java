package UserInterface;

public class Checks {
    public static boolean checkMatrixSize(String matrixSize){
        if (matrixSize.indexOf(',')<0) return false;
        String[] splitSize = new String[2];
        splitSize = matrixSize.split(",",2);
        if(splitSize[0]==null || splitSize[1]==null) return false;
        if(!isNumeric(splitSize[0]) || !isNumeric(splitSize[1])) return false;
        return true;
    }

    private static boolean isNumeric(String str)
    {
        try
        {
            int number = Integer.parseInt(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    public static boolean checkVector(String[] vectorParm, String field){
        if(field.equals("1")){ //Rational
            for(int i=0; i<vectorParm.length ;i++){
                if(!isRational(vectorParm[i])) return false;
            }
        }
        if(field.equals("2")){ //Complex
            for(int i=0; i<vectorParm.length ;i++){
                if(!isComplex(vectorParm[i])) return false;
            }
        }
        return true;
    }

    private static boolean isComplex (String s){
        if(s.charAt(s.length()-1)!='i') return false;
        s = s.substring(0,s.length()-1);
        String[] split = new String[2];
        split = s.split("\\+",2);
        if (split[0]==null || split[1]==null) return false;
        if(!isRational(split[0]) || !isRational(split[1])) return false;
        return true;
    }

    private static boolean isRational (String s){
        String[] split = new String[2];
        split = s.split("\\/",2);
        if (split[0]==null || split[1]==null || Integer.parseInt(split[1])==0) return false;
        if(!isNumeric(split[0]) || !isNumeric(split[1])) return false;
        return true;
    }
}
