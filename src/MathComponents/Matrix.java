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
		this.arrVector = new MathVector[rows];
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
	public Matrix(MathVector[] arrVector)
	{
		this.arrVector = arrVector;
		this.rows = arrVector.length;
		this.columns = arrVector[0].getSize();
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

	public Matrix solve() throws Exception
	{
		Matrix ans = new Matrix(this);
		int i = 0;
		int j = 0;
		while (i < ans.getRows() && j < ans.getColumns()-1){
			int max = i;
			for (int k=i; k < rows; k++) {
				if (arrVector[k].getVectorParm()[j].abs() > arrVector[max].getVectorParm()[j].abs()) {
					max = k;
				}
			}
			Scalar check = ans.getArrVector()[max].getVectorParm()[j];
			if ((check instanceof Rational && !((Rational) check).equal(Rational.zeroRational)) || (check instanceof Complex && !((Complex) check).equal(Complex.zeroComplex))){
				ans.rowSweitching(i,max);
				ans.getArrVector()[i] = ans.getArrVector()[i].mulByScalar(ans.getArrVector()[i].getVectorParm()[j].inv());
				for (int u=i+1 ; u < ans.getRows() ; u++){
					MathVector tmp = ans.getArrVector()[i].mulByScalar(ans.getArrVector()[u].getVectorParm()[j].neg());
					ans.getArrVector()[u] = ans.getArrVector()[u].add(tmp);
				}
				i=i+1;
			}
			j=j+1;
		}

		for (int x=1;x<ans.getRows();x++){
			boolean found = false;
			int y = 0;
			while( y < ans.getArrVector()[x].getSize() && !found){
				if((ans.getArrVector()[x].getVectorParm()[y] instanceof Rational && ((Rational) ans.getArrVector()[x].getVectorParm()[y]).equal(Rational.zeroRational)) || (ans.getArrVector()[x].getVectorParm()[y] instanceof Complex && ((Complex) ans.getArrVector()[x].getVectorParm()[y]).equal(Complex.zeroComplex))){
					found=false;
				}
				else{
					found=true;
				}
				y++;
			}
			if (y == ans.getArrVector()[x].getSize()){
				throw new Exception("There is no unique solution for this matrix");
			}
			for (int z=0;z<x;z++) {
				MathVector tmp = new MathVector(ans.getArrVector()[x]);
				tmp = tmp.mulByScalar(ans.getArrVector()[z].getVectorParm()[y].neg());
				ans.getArrVector()[z] = ans.getArrVector()[z].add(tmp);
			}
		}

		return ans;
	}

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
