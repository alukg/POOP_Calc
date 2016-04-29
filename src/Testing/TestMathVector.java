package Testing;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import MathComponents.*;

public class TestMathVector 
{
	private MathVector mv1;
	private MathVector mv2;
	private MathVector mv3;
	private MathVector mv4;
	private MathVector mv5;
	private MathVector mv6;
	private MathVector mv7;

	
	@Before
	public void createComplex() 
	{
	try
	{
		Scalar[] vectorParm1,vectorParm3,vectorParm4,vectorParm5,vectorParm6;
		vectorParm1 = new Scalar[5];
		vectorParm3 = new Scalar[4];
	    vectorParm4 = new Scalar[4];
		vectorParm5 = new Scalar[4];
		vectorParm6 = new Scalar[2];
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
		this.mv1 = new MathVector(vectorParm1);
		this.mv3 = new MathVector(vectorParm3);
		this.mv4 = new MathVector(vectorParm4);
		this.mv5 = new MathVector(vectorParm5);
		this.mv6 = new MathVector(vectorParm6);
		this.mv7 = new MathVector(this.mv6);
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
	try
	{
		Scalar[] vectorParm2 = new Scalar[4];
		vectorParm2[0] = new Rational(3,1);
		for(int i =1; i < vectorParm2.length; i++)//Rational and Complex
		{
			vectorParm2[i] = new Complex(new Rational(),new Rational(i,1));
		}
		this.mv2 = new MathVector(vectorParm2);
		Assert.fail("Exception expected : the vector include both Complex and Rational objects.");
	}
	catch(Exception e)
	{
	}
	
	}
	
	@Test
	public void testTostring()
	{
		Assert.assertEquals("Should be 0/1  1/1  2/1  3/1  4/1" , "0/1  1/1  2/1  3/1  4/1", this.mv1.toString());
		Assert.assertEquals("Should be -1/1+0/1i  -1/1+1/1i  -1/1+2/1i  -1/1+3/1i" , "-1/1+0/1i  -1/1+1/1i  -1/1+2/1i  -1/1+3/1i", this.mv3.toString());
		Assert.assertEquals("Should be 2/1  2/1" , "2/1  2/1", this.mv6.toString());
		Assert.assertEquals("Should be 2/1  2/1" , "2/1  2/1", this.mv7.toString());
	}
	@Test
	public void testAdd() throws Exception
	{
		Assert.assertEquals("Should be 0/1  2/1  4/1  6/1  8/1" , "0/1  2/1  4/1  6/1  8/1", (this.mv1.add(this.mv1).toString()));
		try
		{
		MathVector tmp =  mv1.add(mv6);
		Assert.fail("Exception expected : vector not suitable");
		}
		catch(Exception e)
		{
			
		}
		try
		{
		MathVector tmp =  mv5.add(mv4);
		Assert.fail("Exception expected : the vector are not of the same type");
		}
		catch(Exception e)
		{
			
		}
		Assert.assertEquals("Should be 4/1  4/1" , "4/1  4/1", this.mv6.add(mv7).toString());
		Assert.assertEquals("Should be 1/1+0/1i  1/1+2/1i  1/1+4/1i  1/1+6/1i" , "1/1+0/1i  1/1+2/1i  1/1+4/1i  1/1+6/1i", this.mv3.add(mv4).toString());
	}
	@Test
	public void testMulByScalar()
	{
		try
		{
			Assert.assertEquals("Shoud be -2/1+-1/1i  -3/1+1/1i  -4/1+3/1i  -5/1+5/1i", "-2/1+-1/1i  -3/1+1/1i  -4/1+3/1i  -5/1+5/1i", this.mv3.mulByScalar((mv4.getVectorParm()[1])).toString());
			Assert.assertEquals("Shoud be 0/1  0/1", "0/1  0/1", this.mv6.mulByScalar((mv1.getVectorParm()[0])).toString());
			Assert.assertEquals("Shoud be 4/1  4/1", "4/1  4/1", this.mv6.mulByScalar(new Rational(2,1)).toString());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		try
		{
			MathVector tmp = this.mv3.mulByScalar((mv1.getVectorParm()[0]));
			Assert.fail("Exception excpected : The multiplication is illegal.");
		}
		catch(Exception e)
		{
			
		}
		}
	@Test
	public void testMul()
	{
		try
		{
			Assert.assertEquals("Shoud be -22/1+6/1i", "-22/1+6/1i", this.mv3.mul(mv4).toString());
			Assert.assertEquals("Shoud be 8/1", "8/1", this.mv6.mul(mv7).toString());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		try
		{
			Scalar tmp = this.mv3.mul(mv6);
			Assert.fail("Exception excpected : The vectors are not of the same type.");
		}
		catch(Exception e)
		{
		}
		try
		{
			Scalar tmp = this.mv5.mul(mv6);
			Assert.fail("Exception excpected : The size of the vectors is not suitable.");
		}
		catch(Exception e)
		{
		}
	}
}
