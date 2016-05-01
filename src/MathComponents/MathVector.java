package MathComponents;

public class MathVector {
    private int size;
    private Scalar[] vectorParm;

    public MathVector(Scalar[] vec) throws Exception 
    {
        this.size = vec.length;
        this.vectorParm = new Scalar[vec.length];
        if(checkIfAllScalarsAreSameType(vec))
         {
            for (int i=0;i<vec.length;i++)
            {
            	if (vec[i] instanceof Complex) {
            		this.vectorParm[i] = new Complex((Complex) vec[i]);
            		} 
            	else 
            	{
                    this.vectorParm[i] = new Rational((Rational) vec[i]);
            	}
            }
         }
        else
        {
        	throw new Exception("The parameters are not from the same type");
        }
    }
    public MathVector(int size)
    {
    	this.size = size;
    	this.vectorParm = new Scalar[this.size];
    	for(int i = 0; i < this.size; i++)
    	{
    		this.vectorParm[i] = null;
    	}
    }
    public MathVector(MathVector vec) throws Exception
    {
    	this(vec.getVectorParm());
    }
    private Boolean checkIfAllScalarsAreSameType(Scalar[] vec)
    {
    	for(int i = 0; i < vec.length; i++)
    	{
    		if(!(((vec[i] instanceof Complex)&&(vec[0] instanceof Complex))||((vec[i] instanceof Rational)&&(vec[0] instanceof Rational))))
    		{
    			
    			return false;
    		}
    	}
    	return true;
    }
    public int getSize(){
        return size;
    }

    public Scalar[] getVectorParm() {
        return vectorParm;
    }

    public MathVector add (MathVector addedVector) throws Exception {
        if(addedVector.getSize() != this.size){
            throw new Exception("The added vector is not suitable");
        }
        else{
            Scalar[] newVactorParm = this.vectorParm;
            Scalar[] toAdd = addedVector.getVectorParm();
            for(int i = 0; i < this.size; i++)
            {
            	Scalar tmp = newVactorParm[i].add(toAdd[i]);
            	if(tmp instanceof Rational)
            	{
            		newVactorParm[i] = new Rational((Rational)tmp);
            	}
            	else
            	{
            		newVactorParm[i] = new Complex((Complex)tmp);
            	}
            }
            MathVector newMathVector = new MathVector(newVactorParm);
            return newMathVector;
        }
    }

    public MathVector mulByScalar (Scalar scalar) throws Exception {
        Scalar[] parm = new Scalar[this.size];
        for(int i=0;i<parm.length;i++){
            parm[i] = vectorParm[i].mul(scalar);
        }
        return (new MathVector(parm));
    }

    public Scalar mul(MathVector mulVector) throws Exception{
        Scalar ans = vectorParm[0].mul(mulVector.getVectorParm()[0]);
        for(int i=1;i<this.size;i++){
            ans = ans.add(vectorParm[i].mul(mulVector.getVectorParm()[i]));
        }
        return ans;
    }
    public String toString()
    {
    	String ans = "";
    	for(int i = 0; i < this.size; i++)
    	{
            if(i==this.size-1){
                ans = ans + this.vectorParm[i].toString();
            }
            else{
                ans = ans + this.vectorParm[i].toString() + "  ";
            }
    	}
    	return ans;
    }
}
