package MathComponents;

public class MathVector {
    private int size;
    private Scalar[] vectorParm;

    public MathVector(Scalar[] vec) throws Exception {
        this.size = vec.length;
        this.vectorParm = new Scalar[vec.length];
        for (int i=0;i<vec.length;i++){
            if(!(((vec[i] instanceof Complex)&&(vec[0] instanceof Complex))||((vec[i] instanceof Rational)&&(vec[0] instanceof Rational)))){
                throw new Exception("The parameters is not from the same type");
            }
            else{
                if (vec[i] instanceof Complex) {
                    this.vectorParm[i] = new Complex((Complex) vec[i]);
                } else {
                    this.vectorParm[i] = new Rational((Rational) vec[i]);
                }
            }
        }
    }

    public MathVector(MathVector vec){
        this.size = vec.getSize();
        this.vectorParm = new Scalar[vec.getSize()];
        for (int i=0;i<vec.getSize();i++){
            if ((vec.getVectorParm())[i] instanceof Complex) {
                this.vectorParm[i] = new Complex((Complex)(vec.getVectorParm()[i]));
            }
            else {
                this.vectorParm[i] = new Rational((Rational)(vec.getVectorParm()[i]));
            }
        }
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
            MathVector newMathVector = new MathVector(this);
            newMathVector.add(addedVector);
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

    public Scalar mul(MathVector mulVector){
        Scalar ans = vectorParm[0].mul(mulVector.getVectorParm()[0]);
        for(int i=1;i<this.size;i++){
            ans.add(vectorParm[i].mul(mulVector.getVectorParm()[i]));
        }
        return ans;
    }

}
