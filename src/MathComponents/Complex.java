package MathComponents;

public class Complex implements Scalar {
    private Rational real;
    private Rational imaginary;
    static final Complex zeroComplex = new Complex(new Rational(0,1),new Rational(0,1));

    public Complex(Rational real, Rational imaginary)
    {
        this.real = new Rational(real);
        this.imaginary = new Rational(imaginary);
    }
    public Complex(Complex complex)
    {
        this(complex.getReal(), complex.getImaginary());
    }
    public Complex()
    {
    	this.real = new Rational();
    	this.imaginary = new Rational();
    }

    public boolean equal(Complex s) {
        if (!s.getReal().equal(this.real) || !s.getImaginary().equal(this.imaginary)) return false;
        return true;
    }

    public Rational getReal()
    {
        return this.real;
    }
    public Rational getImaginary()
    {
        return this.imaginary;
    }
    @Override
    public Scalar add(Scalar s)
    {
        Rational newReal = (Rational)(this.real.add(((Complex)(s)).getReal()));
        Rational newImaginary = (Rational)(this.imaginary.add(((Complex)(s)).getImaginary()));
        return new Complex(newReal, newImaginary);
    }

    @Override
    public Scalar mul(Scalar s)
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

    @Override
    public Scalar neg()
    {
        Rational newReal = (Rational)(this.real.neg());
        Rational newImaginary = (Rational)(this.imaginary.neg());
        return new Complex(newReal, newImaginary);
    }

    @Override
    public Scalar inv()
    {
        //Rational newReal = (Rational)(this.real.inv());
        Rational newImaginary = (Rational)(this.imaginary.neg());
        Complex tmp = new Complex(this.real, newImaginary);
        Rational demonator = ((Complex)(tmp.mul(this))).getReal();
        demonator = (Rational)(demonator.inv());
        Complex ans = new Complex((Rational)(tmp.getReal().mul(demonator)), (Rational)(tmp.getImaginary().mul(demonator)));
        return ans;
    }

    public String toString()
    {
        return this.real.toString()+"+"+this.imaginary.toString()+"i";
    }
    public Boolean IsZero()
    {
    	return (this.real.IsZero() && this.imaginary.IsZero());
    }
    public double abs()
    {
    	this.real.mul(this.real);
    	this.imaginary.mul(this.imaginary);
        return 0;
    }
}