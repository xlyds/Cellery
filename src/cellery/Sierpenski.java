package cellery;


/**
 * Generates a Sierpenski triangle using Cellular Automata rules
 * @author Zachary Tidwell
 *
 */
public class Sierpenski extends CellularAutomata1D {

	/**
	 * Constructs a {@link CellularAutomata1D} imbued with the Game of Life rules.
	 * @param binArray
	 */
	public Sierpenski(CellArray1D binArray) {
		super(binArray);
	}

	public void iterate() {
		
		for (int i = 0; i < length; i++) {
			CellArray1D n = cells().getNeighbors(i, 1);
			if (n.livingCells() == 1)
				buffer().reviveCell(i);
			else
				buffer().killCell(i);
		}
		this.updateCells(buffer());
		this.next();

	}
}
