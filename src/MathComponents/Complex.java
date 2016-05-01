package MathComponents;

/**
 * The class that represents a complex number. 
 *
 */
public class Complex implements Scalar {
	//Variables
    private Rational real;
    private Rational imaginary;
    static final Complex zeroComplex = new Complex();
    //Constructors
    public Complex(Rational real, Rational imaginary) throws Exception
    {
        this.real = new Rational(real);
        this.imaginary = new Rational(imaginary);
    }
    public Complex(Complex complex) throws Exception
    {
        this(complex.getReal(), complex.getImaginary());
    }
    public Complex()
    {
    	this.real = new Rational();
    	this.imaginary = new Rational();
    }
    //Getters
    public Rational getReal()
    {
        return this.real;
    }
    public Rational getImaginary()
    {
        return this.imaginary;
    }
    /** 
	  * The function returns whether the current complex number is equal to the variable comp.
	  * @param  comp saves the complex number to compare with.
	  * @return true if the current complex number and comp are equals else false.
	 ***/
    public boolean equal(Complex comp) {
        if (!comp.getReal().equal(this.real) || !comp.getImaginary().equal(this.imaginary)) return false;
        return true;
    }
    /** 
	  * The function returns the sum of the current complex number with the variable s.
	  * @throws An exception that the added scalar is not of complex type.
	  * @param  s saves the complex number to add.
	  * @return The sum of the current complex number and the variable s.
	 ***/
    public Scalar add(Scalar s)throws Exception
    {
    	if(s instanceof Complex)
    	{
        Rational newReal = (Rational)(this.real.add(((Complex)(s)).getReal()));
        Rational newImaginary = (Rational)(this.imaginary.add(((Complex)(s)).getImaginary()));
        return new Complex(newReal, newImaginary);
    	}
    	else
    	{
    		throw new Exception("The added scalar is not from the same type");
    	}
    }
    /** 
	  * The function returns the multiplication of the current complex number with the variable s.
	  * @throws An exception that the multiplied scalar is not of complex type.
	  * @param  s saves the complex number to multiply.
	  * @return The multiplication of the current complex number with the variable s.
	 ***/
    public Scalar mul(Scalar s)throws Exception
    {
    	if(s instanceof Complex)
    	{
        Rational newReal = (Rational)(this.real.mul(((Complex)(s)).getReal()));
        Rational newReal2 = (Rational)(this.imaginary.mul(((Complex)(s)).getImaginary()));
        newReal2 = (Rational)(newReal2.neg());
        Rational newImaginary = (Rational)(this.imaginary.mul(((Complex)(s)).getReal()));
        Rational newImaginary2 = (Rational)(this.real.mul(((Complex)(s)).getImaginary()));
        newImaginary = (Rational)(newImaginary.add(newImaginary2));
        newReal = (Rational)(newReal.add(newReal2));
        Complex ans = new Complex(newReal, newImaginary);
        return ans;
    	}
    	else
    		throw new Exception("The multiplication scalar is not from the same type");
    }
    /** 
	  * The function returns the negative of the current complex number.
	  * @throws An exception if denominator is 0 in a rational number.
	  * @return The negative of the current complex number.
	 ***/
    public Scalar neg() throws Exception
    {
        Rational newReal = (Rational)(this.real.neg());
        Rational newImaginary = (Rational)(this.imaginary.neg());
        return new Complex(newReal, newImaginary);
    }
    /** 
	  * The function returns the inverse of the current complex number.
	  * @throws An exception if denominator is 0 in a rational number.
	  * @return The inverse of the current complex number.
	 ***/
    public Scalar inv( )throws Exception
    {
        Rational newImaginary = (Rational)(this.imaginary.neg());
        Complex tmp = new Complex(this.real, newImaginary);
        Rational denominator = ((Complex)(tmp.mul(this))).getReal();
        denominator = (Rational)(denominator.inv());
        Complex ans = new Complex((Rational)(tmp.getReal().mul(denominator)), (Rational)(tmp.getImaginary().mul(denominator)));
        return ans;
    }
    /** 
	  * The function returns whether the current complex number is zero.
	  * @return true if the current complex number is zero else false.
	 ***/
    public Boolean IsZero()
    {
    	return (this.real.IsZero() && this.imaginary.IsZero());
    }
    /** 
	  * The function returns the absolute value of the current complex number.
	  * @return the absolute value.
	 ***/
    public double abs()
    {
    	try
    	{
    	Scalar RealSquared = this.real.mul(this.real);
        Scalar ImgSquared = this.imaginary.mul(this.imaginary);
        Rational RealPlusImg = (Rational)RealSquared.add(ImgSquared);
        double ans = (double)RealPlusImg.getNumerator()/RealPlusImg.getdenominator();
        return Math.sqrt(ans);
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return 0.0;
    }
    /** 
	  * The function returns a string that show the complex number.
	  * @return a string representation of the current complex.
	 ***/
    public String toString()
    {
        return this.real.toString()+"+"+this.imaginary.toString()+"i";
    }
}