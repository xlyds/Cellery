package cellery;

import cellery.rules.Rule;

/**
 * cellery.Automaton1D is a 1 dimensional extension of {@link Automaton}
 * @author Zach Tidwell
 *
 */
public abstract class Automaton1D extends Automaton {
	
	/**
	 * Represents the current generation of {@link Cell}s in this {@link Automaton}.
	 */
	private CellArray1D cells; //represents the current generation
	
	/**
	 * Represents the next generation of {@link Cell}s in this {@link Automaton}
	 */
	private CellArray1D buffer; //represents the next generation.
	
	/**
	 * The Length of the underlying {@link CellArray1D}
	 */
	protected final int length;
	
	/**
	 * Constructs a {@link Automaton1D} from a {@link CellArray1D},
	 * @param cells a cellery.CellArray1D of Cells.
	 */
	protected Automaton1D(CellArray1D cells, Rule rule) {
		this.cells = cells;
		this.length = cells.length;
		this.buffer = new CellArray1D(length, cells.getTopology());
		this.setRule(rule);
		
	}
	
	/**
	 * Updates the {@link Cell}s in this from a cellery.Cell buffer.
	 * @param buffer containing the next generation's cellery.Cell configuration.
	 */
	protected void updateCells(CellArray1D buffer) {
		// Swap references between buffer and cells
		this.buffer = cells;
		this.cells = buffer;
	}

	/**
	 * Returns a {@link CellArray1D} containing the Cells of this in it's current generation.
	 * @return a cellery.CellArray1D containing the Cells in this.
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
	 * @param i position of the cellery.Cell.
	 * @return the cellery.Cell.
	 */
	public Cell getCell(int i) {
		return cells.getCell(i);
	}

	
}
