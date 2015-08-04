package java.cellery;
import java.cellery.rules.Rule;
import java.cellery.topology.Topology2D;


/**
 * Automaton2D is a 2 dimensional extension of {@link Automaton}
 * @author Zach Tidwell
 *
 */
public abstract class Automaton2D extends Automaton {
	
	/**
	 * Represents the current generation of {@link Cell}s in this {@link Automaton}.
	 */
	private CellArray2D cells; 
	
	/**
	 * Represents the next generation of {@link Cell}s in this {@link Automaton}
	 */
	private CellArray2D buffer;
		
	/**
	 * The Length of the underlying {@link CellArray2D}
	 */
	protected final int length;
	
	/**
	 * The width of the underlying {@link CellArray2D}
	 */
	protected final int width;
	
	/**
	 * Constructs a {@link Automaton1D} from a {@link CellArray2D}
	 * @param cells a CellArray1D of Cells.
	 */
	protected Automaton2D(CellArray2D cells, Rule rule) {
		this.cells = cells;
		this.length = cells.length;
		this.width = cells.width;
		this.buffer = new CellArray2D( new int[this.length][this.width], this.cells.topo );
		this.setRule(rule);
		
		
	}

	/**
	 * Updates the {@link Cell}s in this from a Cell buffer.
	 * @param buffer containing the next generation's Cell configuration.
	 */
	protected void updateCells(CellArray2D buffer) {
		// Swap references between buffer and cells.
		this.buffer = cells;
		this.cells = buffer;
	}
	
	/**
	 * Returns a {@link CellArray1D} containing the Cells of this in it's current generation.
	 * @return a CellArray1D containing the Cells in this.
	 */
	protected CellArray2D cells() {
		return this.cells;
	}
	
	/**
	 * Returns the buffer of this
	 * @return the buffer of this.
	 */
	protected CellArray2D buffer() {
		return this.buffer;
	}
	
	/**
	 * Retrieve the {@link Cell} at the i-th position.
	 * @param i position of the Cell.
	 * @return the Cell.
	 */
	public Cell getCell(int i, int j) {
		return cells.getCell(i, j);
	}
	
	/**
	 * Reset the topology of the underlying space that this {@link Automaton2D} lives
	 * @param topo
	 */
	public void setTopology(Topology2D topo){
		this.cells.setTopology(topo);
		this.buffer.setTopology(topo);
	}

}
