package cellery;


/**
 * {@link CellArray2D} embedded in a Cylindrical Space
 * @author Zach Tidwell
 *
 */
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
	
	@Override
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
	 * returns x mod N
	 * @param x an integer.
	 * @param N a positive integer.
	 * @return x mod N
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
