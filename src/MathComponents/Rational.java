package MathComponents;

public class Rational implements Scalar {

    private int numerator;
    private int demonator;
    static final Rational zeroRational = new Rational();
    
    public Rational(int numberator, int demonator) throws Exception
    {
        if(demonator == 0)
        {
            throw new Exception("Demonator 0 is illegal.");
        }
        if(demonator < 0)//
        {
            demonator = demonator * (-1);
            numberator = numberator * (-1);
        }
        int gcd = gcd(Math.abs(numberator),Math.abs(demonator));
        this.numerator = numberator / gcd;
        this.demonator = demonator / gcd;
    }
    public Rational()
    {
    	this.demonator = 1;
    	this.numerator = 0;
    }
    public Rational(Rational r) throws Exception
    {
        this(r.getNumerator(), r.getDemonator());
    }

    public boolean equal(Rational s) {
        if (s.getNumerator()!=this.numerator || s.getDemonator()!=this.demonator) return false;
        return true;
    }

    public int getNumerator()
    {
        return this.numerator;
    }
    public int getDemonator()
    {
        return this.demonator;
    }
    public void setDemonator(int demonator) throws Exception
    {
    	if(demonator == 0)
    	{
            throw new Exception("Demonator 0 is illegal.");
    	}
    	else
    	{
    		this.demonator = demonator;
    	}
    }
    public void setNumberator(int numerator)
    {
    	this.numerator = numerator;
    }
    @Override
    public Scalar add(Scalar s) throws Exception
    {
    	if(s instanceof Rational)
    	{
        int sDemonator = ((Rational)(s)).getDemonator();
        int sNumberator = ((Rational)(s)).getNumerator();
        int newNumerator = this.demonator *sNumberator + this.numerator* sDemonator;
        int newDenomator = this.demonator * sDemonator;
        int gcd = gcd(newNumerator, newDenomator);
        return new Rational((newNumerator / gcd), (newDenomator / gcd));
    	}
    	else
    	{
    		throw new Exception("The added scalar is not from the same type");
    	}
    }
    private int gcd(int a, int b)
    {
        if (b==0) return a;
        return gcd(b,a%b);
    }

    @Override
    public Scalar mul(Scalar s)throws Exception
    {
        int newNumerator = this.numerator *((Rational)(s)).getNumerator();
        int newDenomator = this.demonator * ((Rational)(s)).getDemonator();
        int gcd = gcd(newNumerator, newDenomator);
        return new Rational((newNumerator / gcd), (newDenomator / gcd));
    }

    public Scalar neg() throws Exception
    {
        int newNumerator = this.numerator * (-1);
        return new Rational(newNumerator, this.demonator);
    }

    @Override
    public Scalar inv() throws Exception{
        return new Rational(this.demonator, this.numerator);
    }

    public String toString()
    {
        return this.numerator +"/"+ this.demonator;
    }
    public Boolean IsZero()
    {
    	return this.numerator == 0;
    }
    public double abs()
    {
    	int newNumerator = this.numerator;
    	if(this.numerator < 0)
    		newNumerator = newNumerator * -1;
    	return newNumerator/this.demonator;
    }

}
