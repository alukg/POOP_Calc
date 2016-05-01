package Testing;
import MathComponents.*;
import org.junit.*;

public class TestRational
{
	private Rational r1;
	private Rational r2;
	private Rational r3;
	private Rational r4;
	
	@Before
	public void createRational() 
	{
	try
	{
	this.r1 = new Rational();
	this.r2 = new Rational(2, -8);
	this.r3 = new Rational(this.r2);
	this.r4 = new Rational(3,28);
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
	}
	
	@Test
	public void testTostring() {
		Assert.assertEquals("Should be 0/1 ","0/1", r1.toString());
		Assert.assertEquals("Should be -1/4 ","-1/4", r2.toString());
		Assert.assertEquals("Should be -1/4 ","-1/4", r3.toString());
		Assert.assertEquals("Should be 3/28 ","3/28", r4.toString());
	}
	@Test
	public void testAdd() throws Exception
	{
		Assert.assertEquals("Should be 0/1 ","0/1", (r1.add(r1)).toString());
		Assert.assertEquals("Should be -1/7 ","-1/7", (r2.add(r4)).toString());
		Assert.assertEquals("Should be -1/2 ","-1/2", (r2.add(r3)).toString());
		Assert.assertEquals("Should be 3/28 ","3/28", (r4.add(r1)).toString());
	}

	@Test
	public void testMul() throws Exception
	{
		Assert.assertEquals("Should be 0/1 ","0/1", (r1.mul(r1)).toString());
		Assert.assertEquals("Should be -3/112 ","-3/112", (r2.mul(r4)).toString());
		Assert.assertEquals("Should be 1/16 ","1/16", (r2.mul(r3)).toString());
		Assert.assertEquals("Should be 0/1 ","0/1", (r4.mul(r1)).toString());
	}

	@Test
	public void testInv() throws Exception
	{
		try
		{
			Rational tmp = (Rational)r1.inv();
			Assert.fail("Exception expected");
		}
		catch(Exception e) 
		{
		}
			Assert.assertEquals("Should be -4/1 ","-4/1",(r2.inv()).toString());
			Assert.assertEquals("Should be -4/1 ","-4/1",(r3.inv()).toString());
			Assert.assertEquals("Should be 28/3 ","28/3",(r4.inv()).toString());
		}

	@Test
	public void testNeg() throws Exception
	{
		Assert.assertEquals("Should be 0/1 ","0/1", (r1.neg()).toString());
		Assert.assertEquals("Should be 1/4 ","1/4", (r2.neg()).toString());
		Assert.assertEquals("Should be 1/4 ","1/4", (r3.neg()).toString());
		Assert.assertEquals("Should be -3/28 ","-3/28", (r4.neg()).toString());
	}
}
