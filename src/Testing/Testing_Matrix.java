package Testing;

import MathComponents.*;
import org.junit.*;

public class Testing_Matrix {

	private Rational r;
	public static void main(String[] args) throws Exception
	{
		try
		{
		Rational r = new Rational();
		System.out.println(r.add(r));
		//Rational r2 = new Rational(0,0);
		Rational r3 = new Rational(10,2);
		Rational r4 = new Rational(0,7);
		Rational r5 = new Rational(3,9);
		Rational r6 = (Rational)r5.neg();
		Rational r7 = (Rational)r3.inv();
		System.out.println("Rational r is "+r.toString()); // Solution should be 0/1
		System.out.println("Rational r3 is "+r3.toString()); // Solution should be 5/1
		System.out.println("Rational r4 is "+r4.toString()); //Solution Should be 0/1
		System.out.println("Rational r5 is "+r5.toString()); // Solution should be 1/3
		System.out.println("Rational r*r3 is "+(r.mul(r3)).toString()); // Solution should be 0/1
		System.out.println("Rational r3*r5 is "+(r3.mul(r5)).toString()); // Solution should be 5/3
		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	}

