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
		
		*/

		Scalar[] vec1 = new Rational[4];
		vec1[0]=new Rational(1,1);
		vec1[1]=new Rational(1,1);
		vec1[2]=new Rational(1,1);
		vec1[3]=new Rational(2,1);
		Scalar[] vec2 = new Rational[4];
		vec2[0]=new Rational(1,1);
		vec2[1]=new Rational(-1,1);
		vec2[2]=new Rational(-1,1);
		vec2[3]=new Rational(4,1);
		Scalar[] vec3 = new Rational[4];
		vec3[0]=new Rational(2,1);
		vec3[1]=new Rational(1,1);
		vec3[2]=new Rational(3,1);
		vec3[3]=new Rational(6,1);
		MathVector mat1 = new MathVector(vec1);
		MathVector mat2 = new MathVector(vec2);
		MathVector mat3 = new MathVector(vec3);
		MathVector[] arr = new MathVector[3];
		arr[0]=mat1;
		arr[1]=mat2;
		arr[2]=mat3;
		//System.out.println(arr[0].mul(arr[1]));
		Matrix m = new Matrix(arr);
		System.out.println(m.toString());
		//System.out.println(m.transpose());
		//System.out.println(m.add(m).toString());
		//System.out.println(m.mul(m).toString());
		//m.rowSweitching(0, 1);
		//System.out.println(m.toString());
		System.out.println(m.solve().toString());
		}
	}

