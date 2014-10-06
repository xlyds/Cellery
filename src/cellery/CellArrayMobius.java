package cellery;


/**
 * CellArray2D on a Moebius Strip
 * 
 * @author zach
 * 
 */
public class CellArrayMobius extends CellArray2D {

	public CellArrayMobius(int[][] cells) {
		super(cells);
	}
	
	@Override
	public int getHorizontal(int i, int j, int r){
		return this.getHorizontal(i, j);
	}
	
	/**
	 * Retrieves the {@link Cell}s in the left-hand diagonal neighborhood of radius 1
	 * about the ij-th cell in the cylinder.
	 * @param i the row position
	 * @param j the column position
	 * @return the number of living Cells in the neighborhood.
	 */
	public int getHorizontal(int i, int j) {
		int sum = 0;
		int ii;
		int jj;
		
		// check left boundary
		if (j == 0) {
			//get left component
			ii = this.length - i - 1;
			jj = mod(j - 1, this.width);
			if ( !(ii < 0) && !(jj < 0) )
				sum += getCell(ii, jj).toBit();
			
			//get right component
			ii = i;
			jj = mod(j + 1, this.width);
			if ( !(jj >= this.width) ){
				sum += getCell(ii, jj).toBit();
			}
			return sum;
		}
			
		// check right boundary
		if (j == this.width - 1) {
			//get left component
			ii = i;
			jj = mod(j - 1, this.width);
			if ( !(jj < 0) ){
				sum += getCell(ii, jj).toBit();
			}
			
			//get right component
			ii = this.length - i - 1;
			jj = mod(j+1, this.width);
			if ( !(ii >= this.length) && !(jj >= this.width) ){
				sum += this.getCell(ii, jj).toBit();
			}
			return sum;
			
		} 
		
		// Not on the boundary	
		else {
			 return super.getHorizontal(i, j, 1);
		}
	}
	
	@Override
	public int getLeftDiag(int i, int j, int r){
		return this.getLeftDiag(i, j);
	}
	
	/**
	 * Retrieves the cells in the left-hand diagonal neighborhood of radius 1
	 * about the ij-th cell in the cylinder.
	 * @param i the row position
	 * @param j the column position
	 * @return the number of living Cells in the neighborhood.
	 */
	public int getLeftDiag(int i, int j) {
		int sum = 0;
		int ii;
		int jj;
		
		//Center is on the left-boundary
		if (j == 0) {
			//get left component
			ii = this.length - i;
			jj = mod(j - 1, this.width );
			if ( !(ii < 0) && !(ii >= this.length) )
				sum += getCell(ii,jj).toBit();
			
			//get right component
			ii = i + 1;
			jj = j + 1;
			if ( !(ii < 0) && !(ii >= this.length) )
				sum += getCell(ii,jj).toBit();
			return sum;
		}
		
		//Center is on the right boundary
		else if ( j == this.width - 1 ) {
			//get left component
			ii = i - 1;
			jj = j - 1;
			if ( !(ii < 0) && !(ii >= this.length) )
				sum += getCell(ii,jj).toBit();
			
			//get right component
			ii = this.length - i - 2;
			jj = mod(j + 1, this.width);
			if ( !(ii < 0) && !(ii >= this.length) )
				sum += getCell(ii,jj).toBit();
			
			return sum;
		}
		
		//center is in the interior
		else {
			
			return super.getLeftDiag(i, j, 1);
		}
		
	}
	
	@Override
	public int getRightDiag(int i, int j, int r){
		return this.getRightDiag(i, j);
	}
	
	/**
	 * Retrieves the cells in the right-hand diagonal neighborhood of radius 1
	 * about the ij-th cell in the cylinder.
	 * @param i the row position
	 * @param j the column position
	 * @return the number of living Cells in the neighborhood.
	 */
	public int getRightDiag(int i, int j) {
		int sum = 0;
		int ii;
		int jj;
		
		//center is on the left boundary
		if ( j==0 ){
			//get left component
			ii = this.length - i - 2;
			jj = mod(j - 1, this.width );
			if ( !(ii < 0) && !(ii>= this.length) ){
				sum += this.getCell(ii, jj).toBit();
			}
			
			//get right component
			ii = i - 1;
			jj = j + 1;
			if ( !(ii < 0) && !(ii>= this.length) ){
				sum += this.getCell(ii, jj).toBit();
			}
			
			return sum;
			
		} 
		
		//center is on the right boundary
		else if ( j == this.width -1 ) {
			//get left component
			ii = i + 1;
			jj = j - 1;
			if ( !(ii < 0) && !(ii>= this.length) )
				sum += this.getCell(ii, jj).toBit();
			
			//get right component
			ii = this.length - i;
			jj = mod( j + 1, this.width );
			if ( !(ii < 0) && !(ii >= this.length) ){
				sum += this.getCell(ii, jj).toBit();
			}
			
			return sum;
		}
		
		//center is in the interior
		else {
			
			return super.getRightDiag(i, j, 1);
		}
	}
	
	public static int mod(int x, int N) {

		if (x >= 0) {
			return x % N;
		} else {
			return mod( N + x, N);
		}
	}

}