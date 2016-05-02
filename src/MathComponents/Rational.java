package MathComponents;

/**
 * The class that represents a rational number. 
 *
 */
public class Rational implements Scalar {
	//Variables
    private int numerator;
    private int denominator;
    static final Rational zeroRational = new Rational();
    //Constructors
    public Rational(int numberator, int denominator) throws Exception
    {
        if(denominator == 0)
        {
            throw new Exception("denominator 0 is illegal.");
        }
        if(denominator < 0)//Change the numerator to negative number if it is necessary.
        {
            denominator = denominator * (-1);
            numberator = numberator * (-1);
        }
        int gcd = gcd(Math.abs(numberator),Math.abs(denominator));
        this.numerator = numberator / gcd;
        this.denominator = denominator / gcd;
    }
    public Rational()
    {
    	this.denominator = 1;
    	this.numerator = 0;
    }
    public Rational(Rational r) throws Exception
    {
        this(r.getNumerator(), r.getdenominator());
    }
    //Getters
    public int getNumerator()
    {
        return this.numerator;
    }
    public int getdenominator()
    {
        return this.denominator;
    }
    //Setters
    public void setdenominator(int denominator) throws Exception
    {
    	if(denominator == 0)
    	{
            throw new Exception("denominator 0 is illegal.");
    	}
    	else
    	{
    		this.denominator = denominator;
    	}
    }
    public void setNumberator(int numerator)
    {
    	this.numerator = numerator;
    }
    /** 
	  * The function returns whether the current rational number is equal to the variable rat.
	  * @param  rat saves the rational number to compare with.
	  * @return true if the current rational number and rat are equals else false.
	 ***/
    public boolean equal(Rational rat) {
        if (rat.getNumerator()!=this.numerator || rat.getdenominator()!=this.denominator) return false;
        return true;
    }/** 
	  * The function returns the sum of the current rational number with the variable s.
	  * @throws An exception that the added scalar is not of rational type.
	  * @param  s saves the rational number to add.
	  * @return The sum of the current rational number and the variable s.
	 ***/
    public Scalar add(Scalar s) throws Exception
    {
    	if(s instanceof Rational)
    	{
        int sdenominator = ((Rational)(s)).getdenominator();
        int sNumberator = ((Rational)(s)).getNumerator();
        int newNumerator = this.denominator *sNumberator + this.numerator* sdenominator;
        int newDenomator = this.denominator * sdenominator;
        int gcd = gcd(newNumerator, newDenomator);
        return new Rational((newNumerator / gcd), (newDenomator / gcd));
    	}
    	else
    	{
    		throw new Exception("The added scalar is not from the same type");
    	}
    }

    /** 
	  * The function returns the multiplication of the current rational number with the variable s.
	  * @throws An exception that the multiplied scalar is not of rational type.
	  * @param  s saves the rational number to multiply.
	  * @return The multiplication of the current rational number with the variable s.
	 ***/
    public Scalar mul(Scalar s)throws Exception
    {
    	if(s instanceof Rational)
    	{
        int newNumerator = this.numerator *((Rational)(s)).getNumerator();
        int newDenomator = this.denominator * ((Rational)(s)).getdenominator();
        int gcd = gcd(newNumerator, newDenomator);
        return new Rational((newNumerator / gcd), (newDenomator / gcd));
    	}
    	else
    		throw new Exception("The multiplied scalar is not from the same type");
    }
    /** 
	  * The function returns the negative of the current rational number.
	  * @throws An exception if denominator is 0 in a rational number.
	  * @return The negative of the current rational number.
	 ***/
    public Scalar neg() throws Exception
    {
        int newNumerator = this.numerator * (-1);
        return new Rational(newNumerator, this.denominator);
    }
    /** 
	  * The function returns the inverse of the current rational number.
	  * @throws An exception if denominator is 0 in a rational number.
	  * @return The inverse of the current rational number.
	 ***/
    public Scalar inv() throws Exception{
        return new Rational(this.denominator, this.numerator);
    }
    /** 
	  * The function returns whether the current rational number is zero.
	  * @return true if the current rational number is zero else false.
	 ***/
    public Boolean IsZero()
    {
    	return this.numerator == 0;
    }
    /** 
	  * The function returns the absolute value of the current rational number.
	  * @return the absolute value.
	 ***/
    public double abs()
    {
    	int newNumerator = this.numerator;
    	if(this.numerator < 0)
    		newNumerator = newNumerator * -1;
    	return newNumerator/this.denominator;
    }
	 /** 
	  * The function returns the greatest common divisor of tow numbers.
	  * @param a an integer number.
	  * @param b an integer number.
	  * @return The greatest common divisor of a and b.
	 ***/
   private int gcd(int a, int b)
   {
       if (b==0) return a;
       return gcd(b,a%b);
   }/** 
	  * The function returns a string that show the rational number.
	  * @return a string representation of the current rational.
	 ***/
   public String toString()
   {
       return this.numerator +"/"+ this.denominator;
   }


}
