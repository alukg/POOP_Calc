package MathComponents;

public class Matrix
{
	private MathVector[] arrVector ;
	private int rows;
	private int columns;
	
	public Matrix(Matrix mat) throws Exception
	{
		this.rows= mat.getRows();
		this.columns = mat.getColumns();
		for(int i = 0; i < this.rows; i++)
		{
			if(!(((mat.getArrVector()[0].getVectorParm()[0] instanceof Complex)&&(mat.getArrVector()[i].getVectorParm()[0] instanceof Complex))
					||((mat.getArrVector()[0].getVectorParm()[0] instanceof Rational)&&(mat.getArrVector()[i].getVectorParm()[0] instanceof Rational))))
					{
				throw new Exception("The parameters are not from the same type");
					}
		}
        for(int i = 0; i < this.rows; i++)
		{
			if (mat.arrVector[i].getSize() != this.columns)
			{
				 throw new Exception("The number of scalars in the vector "+mat.arrVector[i].toString()+" is not corresponding to the matrix.");
			}
			else
			{
				this.arrVector[i] = new MathVector(mat.arrVector[i]);
			}
		}
	}
	public Matrix(MathVector[] arrVector,int rows,int columns)
	{
		this.arrVector = arrVector;
		this.rows = rows;
		this.columns = columns;
	}
	public Matrix(int rows, int columns)
	{
		this.rows = rows;
		this.columns = columns;
		this.arrVector = new MathVector[this.rows];
		for(int i = 0; i < rows; i++)
		{
			this.arrVector[i] = new MathVector(this.columns);
		}
	}
	public Matrix add(Matrix mat) throws Exception
	{
		Matrix ans;
		if(mat.getColumns() != this.columns || mat.getRows() != this.rows)
			 {
			throw new Exception("The added matrix is not suitable.");
			 }
		else
		{
			ans = this;
			for(int i = 0; i < this.rows; i++)
				ans.arrVector[i] = ans.arrVector[i].add(mat.getArrVector()[i]);
		}
		return ans;
	}
	public Matrix mul(Matrix mat) throws Exception
	{
		if(this.columns != mat.getRows())
		{
			throw new Exception("The multiplication matrix is not suitable");
		}
		Matrix ans = new Matrix(this.rows,mat.getColumns());
		mat = mat.transpose();
		for(int i = 0; i < ans.getRows(); i++)
		{
			for(int j= 0; j < ans.getColumns(); j++)
			{
				ans.getArrVector()[i].getVectorParm()[j] = this.arrVector[i].mul(mat.getArrVector()[j]);
			}
		}
		return ans;
	}
	public Matrix transpose()
	{
		Matrix ans = new Matrix(this.columns, this.rows);
		for(int i = 0; i < ans.getRows(); i++)
		{
			MathVector tmp = ans.getArrVector()[i];
			for(int j = 0; j < ans.getColumns(); j++)
			{
				tmp.getVectorParm()[j] = this.arrVector[j].getVectorParm()[i];
			}
		}
		return ans;
	}
	public void rowSweitching(int i, int j) throws Exception
	{
		if(i > this.rows || j > this.rows || i < 0 || j <0)
		{
			throw new Exception("Rows to switch are illegal.");
		}
		if(i != j)
		{
			MathVector tmp = new MathVector(this.arrVector[i]);
			this.arrVector[i] = new MathVector(this.arrVector[j]);
			this.arrVector[j] = new MathVector(tmp);
		}
	}
	/*public Matrix solve() throws Exception
	{
		Matrix ans = new Matrix(this);
		int i = 0;
		int j = 0;
				while (i < this.rows and j < this.columns)
				{
				  //Find pivot in column j, starting in row i:
				  int maxi = i;
				  for (int k = i+1;i  m do
				    if abs(A[k,j]) > abs(A[maxi,j]) then
				      maxi := k
				    end if
				  end for
				  if A[maxi,j]  0 then
				    swap rows i and maxi, but do not change the value of i
				    Now A[i,j] will contain the old value of A[maxi,j].
				    divide each entry in row i by A[i,j]
				    Now A[i,j] will have the value 1.
				    for u := i+1 to m do
				      subtract A[u,j] * row i from row u
				      Now A[u,j] will be 0, 
				       since A[u,j] - A[i,j] * A[u,j] = A[u,j] - 1 * A[u,j] = 0.
				    end for
				    i := i + 1
				  end if
				  j := j + 1
				end while
		return ans;
	}*/
	/*private bool CheckMatrixAreTheSameType(Matrix mat)
	{
		
	}
	private Scalar[] CreateRationalArrScalar(int columns)
	{
		Scalar[] arr = new Scalar[columns];
		for(int i = 0; i < arr.length; i++)
		{
			arr[i] = new Rational(0,1);
		}
		return arr;
	}
	private Scalar[] CreateComplexArrScalar(int columns)
	{
		Scalar[] arr = new Scalar[columns];
		Rational tmp = new Rational(0,1);
		for(int i = 0; i < arr.length; i++)
		{
			arr[i] = new Complex(tmp,tmp);
		}
		return arr;
	}*/
	public int getRows()
	{
		return this.rows;
	}
	public int getColumns()
	{
		return this.columns;
	}
	public MathVector[] getArrVector()
	{
		return this.arrVector;
	}
	public String toString()
	{
		String ans = "";
		for(int i =0; i < this.rows; i++)
		{
			ans = ans +"\n" + this.arrVector[i].toString();
		}
		return ans;
	}
}
