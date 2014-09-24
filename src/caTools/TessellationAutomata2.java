package caTools;

/**
 * Tessalation Automata object of mod M. Provides methods the control the time
 * evolution of the automaton
 * 
 * @author Zach Tidwell
 * 
 */
public class TessellationAutomata2 extends CellularAutomata2D {

	// generation controls
	private final int M; // modulo
	private final int cellDeath; // 1

	public TessellationAutomata2(CellArray2D cells) {
		this(cells, 3, 0);
	}

	/**
	 * Instantiates a Tessellation Automata of type 2A mod M. When cellDeath is
	 * equal to 1, then cell deaths are enabled in the Tessellation Automata
	 * evolution.
	 * 
	 * @param cells
	 * @param M
	 * @param cellDeath
	 */
	public TessellationAutomata2(CellArray2D cells, int M, int cellDeath) {
		super(cells);
		this.M = M;
		this.cellDeath = cellDeath;
	}

	/**
	 * Gets all of the elements of cells that are orthogonal and diagonal to the
	 * cell cells[i][j]
	 * 
	 * @param i
	 *            horizontal position of cell
	 * @param j
	 *            vertical position of cell
	 * @return the array of orthogonal and diagonal elements
	 */
	public int getDiagOrth(int i, int j) {
		CellArray1D h = this.cells().getHorizontal(i, j, 1);
		CellArray1D v = this.cells().getVertical(i, j, 1);
		CellArray1D d1 = this.cells().getLeftDiag(i, j, 1);
		CellArray1D d2 = this.cells().getRightDiag(i, j, 1);
		return (h.livingCells() + v.livingCells() + d1.livingCells() + d2
				.livingCells());
	}

	/**
	 * Gets all of the elements of cells that are orthogonal to the cell
	 * cells[i][j]
	 * 
	 * @param i
	 *            horizontal position of cell
	 * @param j
	 *            vertical position of cell
	 * @return the array of orthogonal elements
	 */
	public int getOrth(int i, int j) {
		CellArray1D h = this.cells().getHorizontal(i, j, 1);
		CellArray1D v = this.cells().getVertical(i, j, 1);
		return (h.livingCells() + v.livingCells());

	}

	/**
	 * Determines if the cell at the i,jth position of cells is the the next
	 * generation of automata when step is odd. 1 indicates that the i,jth will
	 * be in the next generation and no otherwise.
	 * 
	 * @param i
	 *            the horizontal position of the cell
	 * @param j
	 *            the vertical position of the cell
	 * @return 0 or 1
	 */
	public int oddStep(int i, int j) {
		if (this.cellDeath != 1 && this.cells().getCell(i, j).isAlive())
			return 1;
		if (this.getDiagOrth(i, j) == 1)
			return 1;
		else
			return 0;
	}

	/**
	 * Determines if the cell at the i,jth position of cells is the the next
	 * generation of automata when step is even. 1 indicates that the i,jth will
	 * be in the next generation and no otherwise
	 * 
	 * @param i
	 *            the horizontal position of the cell
	 * @param j
	 *            the vertical position of the cell
	 * @return 0 or 1
	 */
	public int evenStep(int i, int j) {
		if (this.cellDeath != 1 && this.cells().getCell(i, j).isAlive())
			return 1;
		if (this.getOrth(i, j) == 1)
			return 1;
		else
			return 0;
	}

	/**
	 * Computes the positions of the next automata generation
	 * 
	 * @modifies cells
	 */
	public void iterate() {
		CellArray2D buffer = this.buffer();
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.width; j++) {
				switch (this.step() % this.M) {

				case 0:
					if (evenStep(i, j) == 1)
						buffer.resurrectCell(i, j);
					else
						buffer.killCell(i, j);
					break;

				default:
					if (oddStep(i, j) == 1)
						buffer.resurrectCell(i, j);
					else
						buffer.killCell(i, j);
					break;
				}
			}
		}
		this.updateCells(buffer);
		this.next();

	}

}
