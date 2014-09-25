package caTools;

/**
 * CellArray2D embedded in a Cylindrical Space
 * @author Zach Tidwell
 *
 */
public class CellArrayCylinder extends CellArray2D{
	
	public CellArrayCylinder( int[][] cells ) {
		super( cells );
	}
	
	/**
	 * Retrieve the number of living cells in the horizontal neighborhood of radius r about
	 * the ij-th cell in the cylinder.
	 */
	@Override
	public int getHorizontal(int i, int j, int r) {
		int sum = 0;
		for (int n = 0; n <= 2*r; n++ ){
			int jj = mod(j - r + n, this.width);
			if (jj!=j)
				sum+=getCell(i,jj).toBit();
		}
		return sum;
	}
	
	/**
	 * Retrieves the cells in the right-hand diagonal neighborhood of radius r
	 * about the ij-th cell in the cylinder.
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	@Override
	public int getRightDiag(int i, int j, int r) {
		int imax = this.length;
		int sum = 0;
		for (int n = 0; n <= 2 * r; n++) {
			int ii = i + r - n;
			int jj = mod(j - r + n, this.width);
			if (boundariesOK(ii, i, 0, imax) && jj!=j) {
				sum += getCell(ii, jj).toBit();
			}
		}
		return sum;
	}
	
	/**
	 * Retrieves the Cells in the left-hand diagonal neighborhood of radius r
	 * about the ij-th cell in the cylinder.
	 * 
	 * @param i
	 * @param j
	 * @param r
	 * @return
	 */
	public int getLeftDiag(int i, int j, int r) {
		int imax = this.length;
		int sum = 0;
		for (int n = 0; n <= 2 * r; n++) {
			int ii = i - r + n;
			int jj = mod(j - r + n, this.width);
			if (boundariesOK(ii, i, 0, imax) && jj!=j) {
				sum += getCell(ii,jj).toBit();
			}
		}
		return sum;
	}
	
	/**
	 * Quotient function that identifies each opposite end of the array with the other
	 * @param x
	 * @param N
	 * @return
	 */
	public static int mod(int x, int N){
		if ( x < 0 ){
			return mod(N+x,N);
		}
		else{
		    return x%N;
		}
	}

}
