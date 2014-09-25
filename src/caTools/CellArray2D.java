package caTools;

/**
 * A CellArray is a 2-d array of Cell objects which can retrieve the
 * neighborhood about individual cells
 * 
 * @author Zach Tidwell
 * 
 */
public class CellArray2D {
	public final int length;
	public final int width;
	private final Cell[][] cells;

	/**
	 * Instantiates a wXh CellArray2D containing only dead cells
	 * 
	 * @param w
	 * @param h
	 */
	public CellArray2D(int l, int w) {
		this(initializeCells(new int[l][w]));
	}

	/**
	 * Instantiates a CellArray2D from a given binary array
	 * 
	 * @param binArray
	 */
	public CellArray2D(int[][] binArray) {
		this(initializeCells(binArray));
	}

	/**
	 * Instantiates a CellArray2D from a given CellArray[]
	 * 
	 * @param c
	 */
	private CellArray2D(Cell[][] cells) {
		this.length = cells.length;
		this.width = cells[0].length;
		this.cells = cells;
	}

	/**
	 * Populates a CellArray with Cells whose states are determined from the
	 * binary Array.
	 * 
	 * @param binArray
	 * @return
	 */
	private static Cell[][] initializeCells(int[][] binArray) {
		Cell[][] cells = new Cell[binArray.length][binArray[0].length];
		for (int i = 0; i < binArray.length; i++) {
			for (int j = 0; j < binArray[0].length; j++)
				cells[i][j] = new Cell(binArray[i][j]);
		}
		return cells;
	}

	protected static boolean boundariesOK(int ii, int i, int min, int max) {
		return (!(ii < min) && !(ii >= max) && (ii != i));
	}

	/**
	 * Retrieves the number of living cells in the vertical neighborhood about the
	 * ij-th cell of radius r.
	 * @param i
	 * @param j
	 * @param r
	 * @return array of neighboring cells.
	 */
	public int getVertical(int i, int j, int r) {
		int imax = this.length;
		int sum = 0;
		for (int n = 0; n <= 2 * r; n++) {
			int ii = i - r + n;
			if (boundariesOK(ii, i, 0, imax)) {
				sum += this.getCell(ii, j).toBit();
			}
		}
		return sum;
	}

	/**
	 * Retrives the cells in the horizontal neighborhood orthogonal to the ij-th
	 * cell of radius r. Cells are organized in a left-right fashion.
	 * 
	 * @param i
	 * @param j
	 * @param r
	 * @return
	 */
	public int getHorizontal(int i, int j, int r) {
		int jmax = this.width;
		int sum = 0;
		for (int n = 0; n <= 2 * r; n++) {
			int jj = j - r + n;
			if (boundariesOK(jj, j, 0, jmax)) {
				sum += this.getCell(i,jj).toBit();
			}
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
	public int getRightDiag(int i, int j, int r) {
		int imax = this.length;
		int jmax = this.width;
		int sum = 0;
		for (int n = 0; n <= 2 * r; n++) {
			int ii = i + r - n;
			int jj = j - r + n;
			if (boundariesOK(ii, i, 0, imax) && boundariesOK(jj, j, 0, jmax)) {
				sum += this.getCell(ii, jj).toBit();
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
			int jj = j - r + n;
			if (boundariesOK(ii, i, 0, imax) && boundariesOK(jj, j, 0, jmax)) {
				sum += this.getCell(ii,jj).toBit();
			}
		}
		return sum;
	}
	
	/**
	 * Returns the number of living cells in the moore neighborhood about the ij-th cell. 
	 * @param i
	 * @param j
	 * @param r
	 * @return
	 */
	public int moore(int i, int j) {
		int sum = 0;
		sum += this.getVertical(i, j, 1);
		sum += this.getHorizontal(i, j, 1);
		sum += this.getRightDiag(i, j, 1);
		sum += this.getLeftDiag(i, j, 1);
		return sum;
	}
	
	/**
	 * Returns the number of living cells in the Von Neumann neighborhood of radius r about the ij-th cell.
	 * @param i
	 * @param j
	 * @param r
	 * @return
	 */
	public int vonNeumann(int i, int j, int r) {
		int sum = 0;
		sum += this.getVertical(i, j, r);
		sum += this.getHorizontal(i, j, r);
		sum += this.getRightDiag(i, j, r-1);
		sum += this.getLeftDiag(i, j, r-1);
		return sum;
	}

	/**
	 * Converts this CellArray to an Array of booleans representing the states
	 * of the cells contained therein.
	 * 
	 * @return boolean Array
	 */
	public int[][] toBinaryArray() {
		int[][] bin = new int[this.length][this.width];
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.width; j++) {
				bin[i][j] = this.getCell(i, j).toBit();
			}
		}
		return bin;
	}


	/**
	 * Counts the number of living Cells in this
	 * 
	 * @return
	 */
	public int livingCells() {
		int sum = 0;
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.width; j++) {
				sum += this.cells[i][j].toBit();
			}
		}
		return sum;
	}

	/**
	 * Retrieves the Cell in the ij-th position
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public Cell getCell(int i, int j) {
		return this.cells[i][j];
	}

	/**
	 * Kills the cell at the ij-th position in this
	 * 
	 * @param i
	 * @param j
	 */
	public void killCell(int i, int j) {
		this.cells[i][j].kill();
	}

	/**
	 * resurrects the cell at the ij-th position in this
	 * 
	 * @param i
	 * @param j
	 */
	public void resurrectCell(int i, int j) {
		this.cells[i][j].resurrect();
	}

	public String toString() {
		StringBuilder theString = new StringBuilder();
		theString.append("[");
		for (int i = 0; i < this.length; i++) {
			theString.append("[");
			for (int j = 0; j < this.width; j++) {
				theString.append(this.cells[i][j].toBit());
				if (j < this.width - 1)
					theString.append(", ");
			}
			theString.append("]");
			if (i < this.length)
				theString.append(", ");
		}
		theString.append("]");
		return theString.toString();
	}
}
