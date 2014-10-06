package cellery;


/**
 * CellularAutomata1D is a 1 dimensional extension {@link CellularAutomata}
 * @author Zach Tidwell
 *
 */
public abstract class CellularAutomata1D extends CellularAutomata {
	
	private CellArray1D cells; //represents the current generation
	private CellArray1D buffer; //represents the next generation.
	
	//CA dimensions
	protected final int length;
	
	/**
	 * Constructs a {@link CellularAutomata1D} from a {@link CellArray1D},
	 * @param cells a CellArray1D of Cells.
	 */
	protected CellularAutomata1D(CellArray1D cells) {
		this.cells = cells;
		this.length = cells.length;
		//We only need a blank CellArray1D at first
		this.buffer = new CellArray1D(length);
	}
	
	/**
	 * Updates the {@link Cell}s in this from a Cell buffer.
	 * @param buffer containing the next generation's Cell configuration.
	 */
	protected void updateCells(CellArray1D buffer) {
		// Swap references between buffer and cells
		this.buffer = cells;
		this.cells = buffer;
	}

	/**
	 * Returns a {@link CellArray1D} containing the Cells of this in it's current generation.
	 * @return a CellArray1D containing the Cells in this.
	 */
	protected CellArray1D cells() {
		return this.cells;
	}
	
	/**
	 * Returns the buffer of this
	 * @return the buffer of this.
	 */
	protected CellArray1D buffer() {
		return this.buffer;
	}
	
	/**
	 * Retrieve the {@link Cell} at the i-th position.
	 * @param i position of the Cell.
	 * @return the Cell.
	 */
	public Cell getCell(int i) {
		return cells.getCell(i);
	}

}
