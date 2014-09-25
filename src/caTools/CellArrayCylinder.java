package caTools;

public class CellArrayCylinder extends CellArray2D{
	
	public CellArrayCylinder( int[][] cells ) {
		super( cells );
	}
	
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
	 * about the ij-th cell. Neighboring cells are listed in a counter-clockwise
	 * fashion.
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	@Override
	public int getRightDiag(int i, int j, int r) {
		int imax = this.length;
		int jmax = this.width;
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
	 * about the ij-th cell. Neighboring Cells are list in a counter-clockwise
	 * fashion.
	 * 
	 * @param i
	 * @param j
	 * @param r
	 * @return
	 */
	public int getLeftDiag(int i, int j, int r) {
		int imax = this.length;
		int jmax = this.width;
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
	
	public static int mod(int x, int N){
		if ( x < 0 ){
			return mod(N+x,N);
		}
		else{
		    return x%N;
		}
	}

}
