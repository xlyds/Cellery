package caTools;

/**
 * CellularAutomata is an object that can evolve Cells contained within a
 * Data structure
 * 
 * @author Zach Tidwell
 * 
 */
public abstract class CellularAutomata {

	private int step;

	public abstract void iterate();
		//put evolution rules here
	
	public int step() {
		return this.step;
	}

	public void next() {
		// should probably be somewhere in iterate()
		this.step++;
	}

}
