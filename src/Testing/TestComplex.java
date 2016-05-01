package Testing;

import org.junit.*;
import MathComponents.*;

public class TestComplex {

		private Complex c1;
		private Complex c2;
		private Complex c3;
		private Complex c4;
		
		@Before
		public void createComplex() 
		{
		try
		{
		this.c1 = new Complex();
		this.c2 = new Complex(new Rational(2,3), new Rational(7,1));
		this.c3 = new Complex(this.c2);
		this.c4 = new Complex(new Rational(10,-3), new Rational(12,-7));
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		}
		
		@Test
		public void testTostring()
		{
			try
			{
			Assert.assertEquals("Should be 0/1+0/1i ","0/1+0/1i", c1.toString());
			Assert.assertEquals("Should be 2/3+7/1i ","2/3+7/1i", c2.toString());
			Assert.assertEquals("Should be 2/3+7/1i ","2/3+7/1i", c3.toString());
			Assert.assertEquals("Should be -10/3+-12/7i ","-10/3+-12/7i", c4.toString());
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		@Test
		public void testAdd() throws Exception
		{
			try
			{
			Assert.assertEquals("Should be 0/1+0/1i ","0/1+0/1i", (c1.add(c1)).toString());
			Assert.assertEquals("Should be -8/3+37/7i ","-8/3+37/7i", (c2.add(c4)).toString());
			Assert.assertEquals("Should be 4/3+14/1i ","4/3+14/1i", (c2.add(c3)).toString());
			Assert.assertEquals("Should be -10/3+-12/7i ","-10/3+-12/7i", (c4.add(c1)).toString());
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			try
			{
				c1.add(new Rational()).toString();
				Assert.fail("Exceptiom expected : The added scalar is not from the same type");
			}
			catch(Exception e)
			{
			}
		}
		@Test
		public void testMul() throws Exception
		{
			try
			{
			Assert.assertEquals("Should be 0/1+0/1i","0/1+0/1i", (c1.mul(c1)).toString());
			Assert.assertEquals("Should be 88/9+-514/21i ","88/9+-514/21i", (c2.mul(c4)).toString());
			Assert.assertEquals("Should be -437/9+28/3i ","-437/9+28/3i", (c2.mul(c3)).toString());
			Assert.assertEquals("Should be 0/1+0/1i ","0/1+0/1i", (c4.mul(c1)).toString());
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}

			try
			{
				c1.mul(new Rational()).toString();
				Assert.fail("Exceptiom expected : The added scalar is not from the same type");
			}
			catch(Exception e)
			{
			}
		}
		@Test
		public void testInv() throws Exception
		{
			try
			{
				Assert.assertEquals("Should be 6/445-63/445i ","6/445+-63/445i",(c2.inv()).toString());
				Assert.assertEquals("Should be -735/3098+189/1549i", "-735/3098+189/1549i",(c4.inv()).toString());
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			try
			{
				Complex tmp = (Complex)c1.inv();
				Assert.fail("Exception expected : denominator 0 is illegal.");
			}
			catch(Exception e)
			{
			}
		}
		@Test
		public void testNeg()  throws Exception
		{
			try
			{
				Assert.assertEquals("Should be 0/1+0/1i ","0/1+0/1i", (c1.neg()).toString());
				Assert.assertEquals("Should be -2/3+-7/1i ","-2/3+-7/1i", (c2.neg()).toString());
				Assert.assertEquals("Should be -2/3+-7/1i ","-2/3+-7/1i", (c3.neg()).toString());
				Assert.assertEquals("Should be 10/3+12/7i ","10/3+12/7i", (c4.neg()).toString());
			}
			catch(Exception e)
			{
			}
		}
}
