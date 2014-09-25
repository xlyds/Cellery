package caTools;

import java.lang.reflect.*;

/**
 * CellularAutomate2D is an object that can evolve Cells contained in a CellArray2D 
 * according to a set of rules.
 * @author Zach Tidwell
 *
 */
public abstract class CellularAutomata2D extends CellularAutomata {
	
	protected CellArray2D cells; // represents the current generation
	protected CellArray2D buffer; // represents the next generation
	
	//Grid dimensions
	protected final int length;
	protected final int width;

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

	protected void updateCells(CellArray2D buffer) {
		// Swap references between buffer and cells.
		this.buffer = cells;
		this.cells = buffer;
	}

	protected CellArray2D cells() {
		return this.cells;
	}

	protected CellArray2D buffer() {
		return this.buffer;
	}

	public Cell getCell(int i, int j) {
		return cells.getCell(i, j);
	}
}
