package MathComponents;

public interface Scalar {
    public Scalar add(Scalar s) throws Exception;
    public Scalar mul(Scalar s) throws Exception;
    public Scalar neg() throws Exception;
    public Scalar inv() throws Exception;
    public double abs() ;
}