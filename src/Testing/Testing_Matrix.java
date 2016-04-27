package Testing;


import MathComponents.*;


public class Testing_Matrix {

	public static void main(String[] args) throws Exception
	{
		/*Rational r = new Rational(1,2);
		Rational r2 = new Rational(3,4);
		Scalar[] s = new Scalar[2];
		s[0] = r;
		s[1] = r2;
		MathVector m = new MathVector(s);
		System.out.println(r.mul(r2));
		System.out.println(m.mulByScalar(r));
		System.out.println(m.mul(m));
		
		*/Scalar[] vec = new Rational[2];
		for(int i = 0; i < vec.length; i++)
			vec[i] = new Rational(i,1);
		Scalar[] vec2 = new Rational[2];
		for(int i = 0; i < vec2.length; i++)
			vec2[i] = new Rational(i,1);
		MathVector[] arr = new MathVector[2];
		arr[0] = new MathVector(vec2);
		for(int i = 1; i < arr.length; i++)
		{
			arr[i] = new MathVector(vec);
		}
		System.out.println(arr[0].mul(arr[1]));
		Matrix m = new Matrix(arr,2,2);
		System.out.println(m.toString());
		System.out.println(m.transpose());
		System.out.println(m.add(m).toString());
		System.out.println(m.mul(m).toString());
		m.rowSweitching(0, 1);
		System.out.println(m.toString());
		}
	}

