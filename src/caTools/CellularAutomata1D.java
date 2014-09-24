package caTools;

/**
 * CellularAutomata1D is an object that can evolve cells contained within 
 * a CellArray1D.
 * @author Zachary Tidwell
 *
 */
public abstract class CellularAutomata1D extends CellularAutomata {
	
	private CellArray1D cells; //represents the current generation
	private CellArray1D buffer; //represents the next generation.
	
	//CA dimensions
	protected final int length;

	protected CellularAutomata1D(CellArray1D cells) {
		this.cells = cells;
		this.length = cells.length;
		//We only need a blank CellArray1D at first
		this.buffer = new CellArray1D(length);
	}

	protected void updateCells(CellArray1D buffer) {
		// Swap references between buffer and cells
		this.buffer = cells;
		this.cells = buffer;
	}

	protected CellArray1D cells() {
		return this.cells;
	}

	protected CellArray1D buffer() {
		return this.buffer;
	}

	public Cell getCell(int i) {
		return cells.getCell(i);
	}

}
