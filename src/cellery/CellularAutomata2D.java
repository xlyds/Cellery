package cellery;

import java.lang.reflect.*;


/**
 * CellularAutomata2D is a 2 dimensional extension of {@link CellularAutomata}
 * @author Zach Tidwell
 *
 */
public abstract class CellularAutomata2D extends CellularAutomata {
	
	protected CellArray2D cells; // represents the current generation
	protected CellArray2D buffer; // represents the next generation
	
	//Grid dimensions
	protected final int length;
	protected final int width;
	
	/**
	 * Constructs a {@link CellularAutomata1D} from a {@link CellArray2D}
	 * @param cells a CellArray1D of Cells.
	 */
	protected CellularAutomata2D(CellArray2D cells) {
		this.cells = cells;
		this.length = cells.length;
		this.width = cells.width;
		this.buffer = null;
		@SuppressWarnings("rawtypes")
		Constructor[] ctors = cells.getClass().getConstructors();
		@SuppressWarnings("rawtypes")
		Constructor ctor = ctors[0];
		try{
			ctor.setAccessible(true);
			this.buffer = (CellArray2D) ctor.newInstance(((Object) (new int[length][width])));
			
		} catch (InstantiationException x) {
		    x.printStackTrace();
	 	} catch (InvocationTargetException x) {
	 	    x.printStackTrace();
		} catch (IllegalAccessException x) {
		    x.printStackTrace();
		}
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
}
