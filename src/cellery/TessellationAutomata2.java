package cellery;


/**
 * {@link CellularAutomata2D} endowed with Tessellation rules of type 2.
 * @author Zach Tidwell
 */
public class TessellationAutomata2 extends CellularAutomata2D {

	// generation controls
	private final int M; // modulo
	private final int cellDeath; // 1

	public TessellationAutomata2(CellArray2D cells) {
		this(cells, 3, 0);
	}

	/**
	 * Constructs a {@link CellularAutomata2D} with Tessellation rules of type 2.
	 * Cell death is enabled when cellDeath is equal to 1 and off otherwise.
	 * the modulus M controls the frequency of rule changes.
	 * @param cells the first generation of this
	 * @param M the modulus of this
	 * @param cellDeath 
	 */
	public TessellationAutomata2(CellArray2D cells, int M, int cellDeath) {
		super(cells);
		this.M = M;
		this.cellDeath = cellDeath;
	}

	/**
	 * Determines the {@link Cell} at the ij-th position the the next
	 * generation when step is odd.
	 * @param i the horizontal position of the cell
	 * @param j the vertical position of the cell
	 * @return 0 or 1
	 */
	public int oddStep(int i, int j) {
		if (this.cellDeath != 1 && this.cells().getCell(i, j).isAlive())
			return 1;
		if (this.cells().moore(i, j) == 1)
			return 1;
		else
			return 0;
	}

	/**
	 * Determines the {@link Cell} at the ij-th position the the next
	 * generation when step is even.
	 * @param i the horizontal position of the cell
	 * @param j the vertical position of the cell
	 * @return 0 or 1
	 */
	public int evenStep(int i, int j) {
		if (this.cellDeath != 1 && this.cells().getCell(i, j).isAlive())
			return 1;
		if (this.cells().vonNeumann(i, j, 1) == 1)
			return 1;
		else
			return 0;
	}
	
	@Override
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
