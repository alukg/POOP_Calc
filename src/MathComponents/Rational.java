package MathComponents;

public class Rational implements Scalar {

    private int numerator;
    private int demonator;

    public Rational(int numberator, int demonator)
    {
        if(demonator == 0)
        {
            //להחזיר הודעת שגיאה על חלוקה ב0
        }
        if(demonator < 0)//לוודא שמינוס יהיה רק במונה ,ביטול של מינוס במכנה ומונה יחדיו.
        {
            demonator = demonator * (-1);
            numberator = numberator * (-1);
        }
        this.demonator = demonator;
        this.numerator = numberator;
    }
    public Rational(Rational r)
    {
        this(r.getDemonator(),r.getNumerator());
    }

    public int getNumerator()
    {
        return this.numerator;
    }
    public int getDemonator()
    {
        return this.demonator;
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
    //פונקציה שמחזירה את המחלק המקסימלי המשותף
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

}
