package caTools;

import java.util.ArrayList;

/**
 * CellArray is a 2d array of Cell objects which can kill or resurrect Cells and
 * retrieve neighborhoods about individual Cells.
 */
public class CellArray1D {

	public final int length;
	private final Cell[] cells;
	private final Cell[] copy;
	private final ArrayList<Cell> roster = new ArrayList<Cell>();

	/**
	 * Instantiates a CellArray of length containing only dead cells
	 * 
	 * @param length
	 */
	public CellArray1D(int length) {
		this(initializeCells(new int[length]));
	}

	/**
	 * Instantiates a CellArray from a given binaryArray
	 * 
	 * @param binArray
	 */
	public CellArray1D(int[] binArray) {
		this(initializeCells(binArray));

	}

	/**
	 * Instantiates a CellArray from a Cell[]
	 * 
	 * @param cells
	 */
	protected CellArray1D(Cell[] cells) {
		this.length = cells.length;
		this.cells = cells;
		this.copy = new Cell[length];
	}

	/**
	 * Initializes a CellArray with dead cells
	 * 
	 * @param binArray
	 * @return
	 */
	protected static Cell[] initializeCells(int[] binArray) {
		Cell[] newCells = new Cell[binArray.length];
		for (int i = 0; i < binArray.length; i++) {
			newCells[i] = new Cell(binArray[i]);
		}
		return newCells;
	}

	/**
	 * Retrieves the CellArray containing the Cells about the i-th cell in a
	 * neighborhood of radius r.
	 * 
	 * @param r
	 * @return
	 */
	public CellArray1D getNeighbors(int i, int r) {
		roster.clear();
		if (r > this.length / 2)
			r = this.length / 2;
		for (int x = i - r; x <= i + r; x++) {
			if (!(x < 0) && !(x >= this.length) && (x != i))
				roster.add(this.getCell(x));
		}
		return new CellArray1D(roster.toArray(new Cell[roster.size()]));
	}

	/**
	 * Gives an array of the bit values of the Cells in this.
	 * 
	 * @return
	 */
	public int[] toBinaryArray() {
		int[] bin = new int[this.length];
		for (int i = 0; i < this.length; i++) {
			bin[i] = this.cells[i].toBit();
		}
		return bin;
	}

	/**
	 * Computes the number of living cells in a given CellArray.
	 */
	public int livingCells() {
		int sum = 0;
		for (Cell cell : this.cells) {
			sum += cell.toBit();
		}
		return sum;
	}

	/**
	 * Retrieves the Cell at position i.
	 * 
	 * @param i
	 * @return
	 */
	public Cell getCell(int i) {
		return cells[i];
	}

	/**
	 * Kills the Cell at position i
	 * 
	 * @param i
	 */
	public void killCell(int i) {
		this.cells[i].kill();
	}

	/**
	 * Resurrect the Cell at position i.
	 * 
	 * @param i
	 */
	public void resurrectCell(int i) {
		this.cells[i].resurrect();
	}

	/**
	 * Makes a deep copy of this
	 */
	public CellArray1D copy() {
		System.arraycopy(this.cells, 0, this.copy, 0, this.length);
		return new CellArray1D(this.copy);
	}

	public String toString() {
		StringBuilder theString = new StringBuilder();
		theString.append("[");
		for (int i = 0; i < this.length; i++) {
			theString.append(this.getCell(i).toBit());
			if (i < this.length - 1)
				theString.append(", ");
		}
		theString.append("]");
		return theString.toString();
	}
}
