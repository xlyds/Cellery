package cellery;

/**
 * CellularAutomata is an object that can evolve {@link Cell}s contained within a
 * {@link CellArray2D} or {@link CellArray1D}.
 * 
 * @author Zach Tidwell
 * 
 */
public abstract class CellularAutomata {

	private int step;
	
	/**
	 * Calculates the next generation of this.
	 */
	public abstract void iterate();
		//put evolution rules here
	
	/**
	 * Returns the number of times this has been iterated.
	 * @return the number of times this has been iterated.
	 */
	public int step() {
		return this.step;
	}
	
	/**
	 * Increments the iteration count of this.
	 */
	public void next() {
		// should probably be somewhere in iterate()
		this.step++;
	}

}
