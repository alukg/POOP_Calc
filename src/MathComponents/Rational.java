package MathComponents;

public class Rational implements Scalar {

    private int numerator;
    private int demonator;

    public Rational(int numberator, int demonator)
    {
        if(demonator == 0)
        {
            //
        }
        if(demonator < 0)//
        {
            demonator = demonator * (-1);
            numberator = numberator * (-1);
        }
        this.numerator = numberator;
        this.demonator = demonator;
    }
    public Rational()
    {
    	this.demonator = 1;
    	this.numerator = 0;
    }
    public Rational(Rational r)
    {
        this(r.getNumerator(), r.getDemonator());
    }

    public int getNumerator()
    {
        return this.numerator;
    }
    public int getDemonator()
    {
        return this.demonator;
    }
    public void setDemonator(int demonator)
    {
    	if(demonator == 0)
    	{
    		//Error message
    	}
    	this.demonator = demonator;
    }
    public void setNumberator(int numerator)
    {
    	this.numerator = numerator;
    }
    @Override
    public Scalar add(Scalar s)
    {
        int sDemonator = ((Rational)(s)).getDemonator();
        int sNumberator = ((Rational)(s)).getNumerator();
        int newNumerator = this.demonator *sNumberator + this.numerator* sDemonator;
        int newDenomator = this.demonator * sDemonator;
        int gcd = gcd(newNumerator, newDenomator);
        return new Rational((newNumerator / gcd), (newDenomator / gcd));
    }
    //מחזיר מחלק משותף מקסימלי
    private int gcd(int a, int b)
    {
        if (b==0) return a;
        return gcd(b,a%b);
    }

    @Override
    public Scalar mul(Scalar s)
    {
        int newNumerator = this.numerator *((Rational)(s)).getNumerator();
        int newDenomator = this.demonator * ((Rational)(s)).getDemonator();
        int gcd = gcd(newNumerator, newDenomator);
        return new Rational((newNumerator / gcd), (newDenomator / gcd));
    }

    @Override
    public Scalar neg()
    {
        int newNumerator = this.numerator * (-1);
        return new Rational(newNumerator, this.demonator);
    }

    @Override
    public Scalar inv() {
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
    public Scalar Abs()
    {
    	int newNumerator = this.numerator;
    	if(this.numerator < 0)
    		newNumerator = newNumerator * -1;
    	return new Rational(newNumerator, this.demonator);
    		
    }

}
