package Testing;
import javax.security.sasl.SaslException;

import org.junit.*;

import MathComponents.*;

public class TestMatrix 
{
	private Matrix m1;
	private Matrix m2;
	private Matrix m3;
	private Matrix m4;
	private Matrix m5;
	private Matrix m6;
	private Matrix m7;

	
	@Before
	public void createComplex() 
	{

		MathVector mv1,mv3,mv4,mv5,mv6,mv7;
		Scalar[] vectorParm1,vectorParm3,vectorParm4,vectorParm5,vectorParm6, vectorParm7;
	try
	{
		vectorParm1 = new Scalar[5];
		vectorParm3 = new Scalar[4];
	    vectorParm4 = new Scalar[4];
		vectorParm5 = new Scalar[4];
		vectorParm6 = new Scalar[2];
		vectorParm7 = new Scalar[5];
		for(int i =0; i < vectorParm1.length; i++)//Only Rational
		{
			vectorParm1[i] = new Rational(i,1);
		}
		for(int i =0; i < vectorParm3.length; i++)//Only Complex
		{
			vectorParm3[i] = new Complex(new Rational(1,-1), new Rational(i,1));
		}
		for(int i =0; i < vectorParm4.length; i++)//Only Complex
		{
			vectorParm4[i] = new Complex(new Rational(2,1), new Rational(i,1));
		}
		for(int i =0; i < vectorParm5.length; i++)//Only Rational
		{
			vectorParm5[i] = new Rational(2,1);
		}
		for(int i =0; i < vectorParm6.length; i++)//Only Rational
		{
			vectorParm6[i] = new Rational(2,1);
		}
		for(int i =0; i < vectorParm7.length; i++)//Only Rational
		{
			vectorParm7[i] = new Rational(3,1);
		}
		mv1 = new MathVector(vectorParm1);
		mv3 = new MathVector(vectorParm3);
		mv4 = new MathVector(vectorParm4);
		mv5 = new MathVector(vectorParm5);
		mv6 = new MathVector(vectorParm6);
		mv7 = new MathVector(vectorParm7);
		MathVector[] arrmv1 = new MathVector[2];
		arrmv1[0] = new MathVector(mv1);
		arrmv1[1] = new MathVector(mv7);
		this.m1 = new Matrix(arrmv1);
		this.m3 = new Matrix(m1);
		MathVector[] arrmv4 = new MathVector[2];
		arrmv4[0] = new MathVector(mv3);
		arrmv4[1] = new MathVector(mv4);
		this.m4 = new Matrix(arrmv4);
		MathVector[] arrmv5 = new MathVector[2];
		arrmv5[0] = new MathVector(mv3);
		arrmv5[1] = new MathVector(mv3);
		this.m5 = new Matrix(arrmv5);
		MathVector[] arrmv7 = new MathVector[3];
		arrmv7[0] = new MathVector(mv3);
		arrmv7[1] = new MathVector(mv3);
		arrmv7[2] = new MathVector(mv4);
		this.m7 = new Matrix(arrmv7);
		arrmv7 = new MathVector[4];
		arrmv7[0] = new MathVector(mv3);
		arrmv7[1] = new MathVector(mv3);
		arrmv7[2] = new MathVector(4);
		arrmv7[3] = new MathVector(4);
		this.m7 = new Matrix(arrmv7);
		
	}
	catch(Exception e)
	{
		//System.out.println(e.getMessage());
	}
	try
	{
		MathVector[] arrmv2 = new MathVector[2];
		arrmv2[0] = new MathVector(3);
		arrmv2[1] = new MathVector(4);
		this.m5 = new Matrix(arrmv2);
		Assert.fail("Exception expected : the vectors of the matrix are not from the same size.");
		
	}
	catch(Exception e)
	{
		//System.out.println(e.getMessage());
	}
	try
	{
		vectorParm3 = new Scalar[1];
		vectorParm3[0] = new Rational();
	    vectorParm4 = new Scalar[1];
	    vectorParm4[0] = new Rational();
	    vectorParm5 = new Scalar[1];
	    vectorParm4[0] = new Complex();
		MathVector[] arrmv6 = new MathVector[3];
		arrmv6[0] = new MathVector(vectorParm3);
		arrmv6[1] = new MathVector(vectorParm4);
		arrmv6[2] = new MathVector(vectorParm5);
		this.m6 = new Matrix(arrmv6);
		Assert.fail("Exception expected : the vectors of the matrix are not from the same type.");
		
	}
	catch(Exception e)
	{	
		//System.out.println(e.getMessage());
	}
	}
	@Test
	public void testToString()
	{
		Assert.assertEquals("Should be 0/1  1/1  2/1  3/1  4/1\n3/1  3/1  3/1  3/1  3/1\n", "0/1  1/1  2/1  3/1  4/1\n3/1  3/1  3/1  3/1  3/1\n" , m1.toString());
		Assert.assertEquals("Should be 0/1  1/1  2/1  3/1  4/1\n3/1  3/1  3/1  3/1  3/1\n", "0/1  1/1  2/1  3/1  4/1\n3/1  3/1  3/1  3/1  3/1\n", m3.toString());
		Assert.assertEquals("Should be -1/1+0/1i  -1/1+1/1i  -1/1+2/1i  -1/1+3/1i\n2/1+0/1i  2/1+1/1i  2/1+2/1i  2/1+3/1i\n", "-1/1+0/1i  -1/1+1/1i  -1/1+2/1i  -1/1+3/1i\n2/1+0/1i  2/1+1/1i  2/1+2/1i  2/1+3/1i\n" , m4.toString());
	}@Test
	public void testAdd()
	{
		try
		{
		Assert.assertEquals("Should be 0/1  2/1  4/1  6/1  8/1\n6/1  6/1  6/1  6/1  6/1\n", "0/1  2/1  4/1  6/1  8/1\n6/1  6/1  6/1  6/1  6/1\n" , (m1.add(m1).toString()));
		Assert.assertEquals("Should be -2/1+0/1i  -2/1+2/1i  -2/1+4/1i  -2/1+6/1i\n4/1+0/1i  4/1+2/1i  4/1+4/1i  4/1+6/1i\n", "-2/1+0/1i  -2/1+2/1i  -2/1+4/1i  -2/1+6/1i\n4/1+0/1i  4/1+2/1i  4/1+4/1i  4/1+6/1i\n" , (m4.add(m4).toString()));
		}
		catch(Exception e)
		{
			//System.out.println(e.getMessage());
		}
		try
		{
			//System.out.println(m1.toString());
			//System.out.println(m7.toString());
			Matrix tmp = m1.add(m7);
			Assert.fail("Exception expected : the matrixes are not from the same size.");
		}
		catch(Exception e)
		{
			//System.out.println(e.getMessage());
		}
		try
		{
			Scalar[] s = new Scalar[5];
			for(int i = 0; i < s.length; i++)
			{
				s[i] = m5.getArrVector()[0].getVectorParm()[0];
			}
			MathVector[] mv = new MathVector[2];
			for(int i = 0; i < mv.length; i++)
			{
				mv[i] = new MathVector(s);
			}
			Matrix tmp = new Matrix(mv);
			System.out.println(m3.toString());
			System.out.println(tmp.toString());
			Matrix tmp2 = m3.add(tmp);
			Assert.fail("Exception expected : the matrixes are not from the same type.");
		}
		catch(Exception e)
		{
			//System.out.println(e.getMessage());
		}
	}
}
