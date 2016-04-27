package Testing;


import MathComponents.*;


public class Testing_Matrix {

	public static void main(String[] args) throws Exception
	{
		Scalar[] vec = new Rational[5];
		for(int i = 0; i < vec.length; i++)
			vec[i] = new Rational(i,i+2);
		Scalar[] vec2 = new Rational[5];
		for(int i = 0; i < vec2.length; i++)
			vec2[i] = new Rational(i+10,i+5);
		MathVector[] arr = new MathVector[5];
		arr[0] = new MathVector(vec2);
		for(int i = 1; i < arr.length; i++)
		{
			arr[i] = new MathVector(vec);
		}
		Matrix m = new Matrix(arr,5,5);
		System.out.println(m.toString());
		//System.out.println(m.add(m).toString());
		//System.out.println(m.mul(m).toString());
		m.rowSweitching(0, 2);
		System.out.println(m.toString());
		}
	}

